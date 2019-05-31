from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import bs4
import pymysql

driver = webdriver.Chrome("C:/Users/LJH/Documents/GitHub/CapstoneCoin/Crawling/world/chromedriver.exe")


def cisco(url):
    conn = pymysql.connect(host='localhost', user='root',
                           db='capstone', charset='utf8')
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find("table",class_="table_basic-1 table_striped")
    s4 = s3.find_all("tr")
    for s5 in s4:
        tdlist = []
        s6 = s5.find_all("td")
        s7 = s5.find("a")
        if isinstance(s7, bs4.element.Tag):
            link = s7['href']
            for s8 in s6:
                tdlist.append((s8.text).strip())
            a = tdlist[0]
            b = tdlist[1]
            c = tdlist[2]
            d = tdlist[3]

            curs = conn.cursor()
            sql = "INSERT INTO capstone.cisco_table(title, classify, type, address, link) values('"+a+"','"+b+"','"+c+"','"+d+"','"+link+"');"
            curs.execute(sql)
    conn.commit()

url = "https://jobs.cisco.com/jobs/SearchJobs/?3_109_3=%5B%22169482%22%5D&3_12_3=%5B%22186%22%5D"
cisco(url)