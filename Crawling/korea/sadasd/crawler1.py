from selenium import webdriver
from bs4 import BeautifulSoup


driver = webdriver.Chrome("C:/Users/SAMSUNG/Desktop/crawler/chromedriver.exe")

driver.get("https://www.hallym.ac.kr")

driver.find_element_by_xpath('//*[@id="wrapper"]/div[1]/div/div[3]/div/div[2]/div[2]/ul/li[8]/div[1]/a').click()

driver.find_element_by_xpath('//*[@id="container"]/div/div[1]/ul/li[5]/a/p/span').click()

driver.find_element_by_xpath('//*[@id="inp-tblsearch"]').click()
username = driver.find_element_by_xpath('//*[@id="inp-tblsearch"]')
username.clear()
username.send_keys("abc")
driver.find_element_by_xpath('//*[@id="container"]/div/div[2]/div[1]/form/fieldset/div[2]/button').click()