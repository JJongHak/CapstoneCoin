from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import bs4
import pymysql

#f = codecs.open("link.txt", encoding="utf-8", mode="w")
driver = webdriver.Chrome("C:/Users/LJH/Documents/GitHub/CapstoneCoin/Crawling/world/chromedriver.exe")


def Vivo(url):
    conn = pymysql.connect(host='localhost', user='root',
                           db='capstone', charset='utf8')
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find("tbody")
    s4 = s3.find_all("tr")
    for s5 in s4:
        s6 = s5.find_all("td")
        alist = []
        for s7 in s6:
            alist.append(s7)
        s8 = alist[0].find("a")
        a = (s8.text).strip()
        b = (alist[1].text).strip()
        c = (alist[2].text).strip()
        d = (alist[3].text).strip()
        link = s8['href']

        curs = conn.cursor()
        sql = "INSERT INTO capstone.vivo_table(title, classify, num, address, link) values('"+a+"','"+b+"','"+c+"','"+d+"','"+link+"');"
        curs.execute(sql)
    conn.commit()

url = "https://hr.vivo.com/wt/vivo/web/templet1000/index/corpwebPosition1000vivo!getPostListByCondition?operational=\
12dca3a95fa954cd06781ff212f8a006e8fb91ad53405c8ee115644529b1ec89118e6df0d71c532abfc501494bc2a61ef655595d1f03f95ecf2bd\
06f91a5c04e5a6dd52a9a7e97bc0e085d21da4a89aa5c2fa386db326f5ac80e44c7d2dab06420a342aa5bbfeb30&positionType=0/1227/37850\
532&comPart=&brandCode=1&releaseTime=0&trademark=0&useForm=0&recruitType=2&lanType=&positionName=&workPlace=&keyWord=&showComp=true"
Vivo(url)