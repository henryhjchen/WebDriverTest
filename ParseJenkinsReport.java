package com.ibm.dni.util.jenkins;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ParseJenkinsReport 
{
	private List<String> classExecutionSequence = null;
	private Map<String,Map<String,String>> executionResultMap = null;
	private StringBuffer finalResult = null;
	
	public ParseJenkinsReport(String hostnameURL,
			String category,
			String jobName,
			String executionNumber,
			String suiteFilePath,String ignoreClassListFilePath,
			String realSequenceResultFilePath)
	{
		try
		{
			String realURL = hostnameURL + 
					"/view/" + category + "/job/" + 
					jobName+ "/" + executionNumber + "/testReport/api/xml";
			
			init(); 
			//System.out.println(scrape(realURL,"AVDDE4858@nomail.relay.ibm.com","wb1forfn"));
			parseSuiteFile(suiteFilePath,ignoreClassListFilePath);
			if(parseXMLContent(realURL))
			{
				generateRealSequenceResult();
				saveFile(realSequenceResultFilePath);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void init()
	{
		try
		{
			classExecutionSequence = new ArrayList<String>();
			executionResultMap = new HashMap<String,Map<String,String>>();
			finalResult = new StringBuffer();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private InputStream scrape(String urlString, String username, String password) 
	{
		try
		{
			
			URI uri = URI.create(urlString);
			
			HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
			// Create AuthCache instance
			AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local auth cache
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(host, basicAuth);
			
			TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
		    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		    		sslContext, NoopHostnameVerifier.INSTANCE);
			
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).setSSLSocketFactory(sslsf).build();
			HttpGet httpGet = new HttpGet(uri);
			// Add AuthCache to the execution context
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setAuthCache(authCache);
	
			HttpResponse response = httpClient.execute(host, httpGet, localContext);
	
			return response.getEntity().getContent();
			//return EntityUtils.toString(response.getEntity());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean parseXMLContent(String reportURL) 
	{
		boolean result = false;
		
		try
		{
			//int index1 = 0;
			
			System.out.println("Parse Fail class and method name...");
			
			System.out.println("   Connect to: " + reportURL);
			
			InputStream reportStream = scrape(
					reportURL,
					"AVDDE4858@nomail.relay.ibm.com",
					"wb1forfn");
			
			if(reportStream == null)
			{
				System.out.println("   URL: " + reportURL + " is not valid.");
				return result;
			}
			else
			{
				result = true;
				Document dom = new SAXReader().read(reportStream);

	            for( Element suite : (List<Element>)dom.getRootElement().elements("suite")) 
	            {
	            	for ( Iterator<Element> iterator = suite.elementIterator("case");iterator.hasNext();)
	            	{
	            		Element caseElement = iterator.next();
	
	            		String status = caseElement.elementText("status");
	            		if(status != null && (status.equals("FAILED") || status.equals("REGRESSION")))     
	            		{
	            			String methodName = caseElement.elementText("name");
	            			String className = caseElement.elementText("className");
	            			String errorDetails = caseElement.elementText("errorDetails");
	            		
			                if(executionResultMap.containsKey(className) && 
			                		executionResultMap.get(className) != null)
			                {
			                	Map<String,String> sameClassResultMap = executionResultMap.get(className);
			                	
			                	sameClassResultMap.put(methodName, errorDetails);
			                }
			                else
			                {
			                	Map<String,String> sameClassResultMap = new TreeMap<String,String>();
			                	
			                	sameClassResultMap.put(methodName, errorDetails);
			                	
			                	executionResultMap.put(className, sameClassResultMap);
			                }
			                
			                //index1++;
	            		}
	            	}
	            }
			}
	            
//            System.out.println("==1===>" + index1);
//            
//            index1 = 0;
//            
//            for(Iterator<String> it = executionResultMap.keySet().iterator();it.hasNext();)
//            {
//            	String className = it.next();
//            	
//            	Map<String,String> sameClassResultMap = executionResultMap.get(className);
//            	
//            	for(Iterator<String> it2 = sameClassResultMap.keySet().iterator();it2.hasNext();)
//            	{
//            		String methodName = it2.next();
//        			
//        			String errorDetails = sameClassResultMap.get(methodName);
//        			
//                    System.out.println(
//                    		String.format("    MethodName:%s\t ClassName:%s\n      %s\n",
//                    				methodName,
//                    				className,
//                    				errorDetails));
//                    index1++;
//            	}
//            	
//            }
//            
//            System.out.println("==2===>" + index1);
            System.out.println("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void parseSuiteFile(String suiteFilePath,String ignoreClassListFilePath)
	{
		try
		{
			System.out.println("Generate suite class execution sequence...");
			
			File suiteFile = new File(suiteFilePath);
			File ignoreFile = new File(ignoreClassListFilePath);
			
			Set<String> ignoreSet = new HashSet<String>(FileUtils.readLines(ignoreFile));
			
			for(Iterator<String> it = FileUtils.readLines(suiteFile).iterator();it.hasNext();)
			{
				String line = it.next();
				if(line.indexOf("<Testcase>") >= 0)
				{
					line = line.replaceAll("<Testcase>", "").trim();
					line = line.replaceAll("</Testcase>", "").trim();
					
					if(!ignoreSet.contains(line))
					{
						classExecutionSequence.add(line);
						System.out.println("   Add class:[" + line + "] to sequence list");
					}
					else
						System.out.println("     *** Ignore class:[" + line + "]");
				}
			}
			
			System.out.println("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void generateRealSequenceResult()
	{
		try
		{
			System.out.println("Generate real execution sequence result...");
			
			for(int index = 0;index < classExecutionSequence.size();index++)
			{
				String className = classExecutionSequence.get(index);
				
				if(executionResultMap.containsKey(className) && 
						executionResultMap.get(className) != null)
				{
					Map<String,String> sameClassResultMap = executionResultMap.get(className);
	            	
	            	for(Iterator<String> it = sameClassResultMap.keySet().iterator();it.hasNext();)
	            	{
	            		String methodName = it.next();
	        			
	        			String errorDetails = sameClassResultMap.get(methodName);
	        			
	        			finalResult.append(
	        					String.format("    MethodName:%s\t ClassName:%s\n      %s\n",
	                    				methodName,
	                    				className,
	                    				errorDetails) + "\n");
	        			
	                    System.out.println(
	                    		String.format("    MethodName:%s\t ClassName:%s\n      %s\n",
	                    				methodName,
	                    				className,
	                    				errorDetails));
	            	}
	            	executionResultMap.remove(className);
	            	
	            	if(executionResultMap.size() == 0)
	            		break;
				}
			}
			
			if(executionResultMap.size() != 0)
			{
				System.out.println("  Following class is not in execution sequence list");
				System.out.println("");
				System.out.println(executionResultMap);
			}
			
			System.out.println("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void saveFile(String realSequenceResultFilePath)
	{
		try
		{
			System.out.println(
					"Save real execution sequence to file[" + realSequenceResultFilePath + "]");
			
			File file = new File(realSequenceResultFilePath);
			
			FileUtils.writeStringToFile(file, finalResult.toString(), false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
//		new ParseJenkinsReport(
//				"http://172.16.164.86:8080",
//				"WBIFN-DNF",
//				"FN-MSIF-V311PTFs",
//				"164",
//				"C:\\work\\TestCases_311PTF1.xml",
//				"C:\\work\\TestCases_311PTF1_IGNORE_LIST.txt",
//				"C:\\work\\FN-MSIF-V311PTFs_164_result.txt");

		new ParseJenkinsReport(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);
		
	}

}
