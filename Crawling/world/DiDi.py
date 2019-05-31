from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import pymysql

#f = codecs.open("C:/Users/gooni/Documents/캡스톤디자인/link.txt", encoding="utf-8", mode="w")
driver = webdriver.Chrome("C:/Users/LJH/Documents/GitHub/CapstoneCoin/Crawling/world/chromedriver.exe")


def DiDi(url):
    conn = pymysql.connect(host='localhost', user='root',
                           db='capstone', charset='utf8')
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.findAll("a", class_="clearfix")
    for s4 in s3:
        link = "https://http://talent.didiglobal.com/" + s4['href']
        s5 = s4.find("div", class_="position-card-title")
        s6 = s4.find("div", class_="position-card-detail")
        ss = (s6.text).split('/')
        s7 = []
        for i in ss:
            s7.append(i.strip())
        s8 = s4.find("div", class_="position-card-time")
        print(s5.text)
        a = s5.text
        b = s7[0]
        c = s7[1]
        d = s7[2]
        e = s8.text
        f = link
        print(a,b,c,d,e,f)
        print('--------------')
        curs = conn.cursor()
        sql = "INSERT INTO capstone.didi_table values('" + a + "','" + b + "','" + c + "'," \
                                                        "'" + d + "','" + e + "','" + f + "');"
        curs.execute(sql)
    conn.commit()

url = "http://talent.didiglobal.com/social/list/1?jobType=1"
DiDi(url)
