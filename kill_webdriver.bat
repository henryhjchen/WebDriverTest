@echo off
rem   just kills stray local webdriver instances.
rem   useful if you are trying to clean your project, and your ide is complaining.

taskkill /im chromedriver.exe /f
taskkill /im MicrosoftWebDriver.exe /f
taskkill /im geckodriver.exe /f