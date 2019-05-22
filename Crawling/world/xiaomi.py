from bs4 import BeautifulSoup
import bs4
from selenium import webdriver
import time
import codecs
import pymysql

f = codecs.open("C:/Users/gooni/Documents/캡스톤디자인/link.txt", encoding="utf-8",mode="w")
driver = webdriver.Chrome("C:/Users/gooni/Desktop/CapstoneCoin/Crawling/world/chromedriver.exe")

def crawler(url):
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    return s2

def Xiaomi(url):
    conn = pymysql.connect(host='localhost', user='root',  password='Capstone123.',
                           db='capstone', charset='utf8')
    s = crawler(url)
    ss = s.findAll("a", class_="link-11ZhH")
    for s1 in ss:
        s2 = s1.find("div", class_="title-1X3Vf")
        s3 = s1.find("div", class_="status-3wqaa")
        s4 = s3.findAll("span")
        s5 = s1.find("span", class_="opened-at-3hbqT")
        link = s1['href']
        a = s2.text
        b = s4[0].text
        c = s4[1].text
        d = (s5.text)[5:]
        e = "http://job.hr.xiaomi.com/" + link
        curs = conn.cursor()
        #sql = "INSERT INTO capstone.xiaomi_table values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"');"
        sql = "INSERT INTO capstone.xiaomi_table (title, classify, type, data, link)\
                        SELECT ('" + a + "','" + b + "','" + c + "','" + d + "','" + e + "')\
                        FROM DUAL \
                        WHERE NOT EXISTS (SELECT title, data FROM capstone.xiaomi_table\
                        WHERE capstone.xiaomi_table.title = " + a + " AND \
                        capstone.xiaomi_table.date = " + c + " );"
        curs.execute(sql)
    conn.commit()
    print(1)

#urls = []
#url = "http://job.hr.xiaomi.com/#/jobs?page=1&zhineng=5286&_k=b8ig98"
#urls.append(url)
#s = crawler(url)
#s1 = s.find("ul", class_="rc-pagination theme-pagination")
#if isinstance(s1, bs4.element.Tag):
#    s2 = s1.findAll("li")
#maxpage = int(s2[len(s2)-2].text)


for i in range(1,2):
    Xiaomi("http://job.hr.xiaomi.com/#/jobs?page=%d&zhineng=5286&_k=b8ig98"%4)
    #urls.append("http://job.hr.xiaomi.com/#/jobs?page=%d&zhineng=5286&_k=b8ig98"%(i+1))
