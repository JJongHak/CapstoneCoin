from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import bs4
import pymysql

driver = webdriver.Chrome("C:/Users/LJH/Documents/GitHub/CapstoneCoin/Crawling/world/chromedriver.exe")


def facebook(url):
    conn = pymysql.connect(host='localhost', user='root',
                            db='capstone', charset='utf8')
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("a",class_="_69jm")
    for s4 in s3:
        s5 = s4.find("div", class_="_69jo")
        a = s5.text
        s6 = s4.find_all("div", class_="_75tr")
        alist = []
        for s7 in s6:
            alist.append(s7.text)
        b = ', '.join(alist)
        s8 = s4.find("div", class_="_1n-z _6hy- _21-h")
        c = s8.text
        link = 'https://www.facebook.com' + s4['href']

        curs = conn.cursor()
        sql = "INSERT INTO capstone.facebook_table(title, classify, address, link) values('"+a+"','"+b+"','"+c+"','"+link+"');"
        curs.execute(sql)
    conn.commit()

url = "https://www.facebook.com/careers/jobs?divisions[0]=Facebook&divisions[1]=Instagram&divisions[2]=WhatsApp&divisions[3]=Oculus&teams[0]=Software%20Engineering&locations[0]=North%20America"
facebook(url)
