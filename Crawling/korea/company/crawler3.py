from selenium import webdriver
from bs4 import BeautifulSoup
import codecs

f = codecs.open("C:/Users/SAMSUNG/Desktop/crawler/link.txt", encoding="utf-8",
                mode="w")
driver = webdriver.Chrome("C:/Users/SAMSUNG/Desktop/crawler/chromedriver.exe")

driver.get("http://www.kyobobook.co.kr/bestseller/bestSellerMain.laf?orderClick=d79")

s1 = driver.page_source

s2 = BeautifulSoup(s1, "html.parser")
s3 = s2.find_all("div", class_="title")
print(len(s3))
count = 0
for s4 in s3:
    count = count + 1
    if count != 1 and count != 2 and count != 3 and count != 4:
        f.write(str(s4).split('href="')[1].split('">')[0].replace("amp;", "")+"\r\n")
    
'''
crawler3.py
'''
