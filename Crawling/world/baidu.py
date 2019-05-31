from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import pymysql

#f = codecs.open("C:/Users/gooni/Documents/캡스톤디자인/link.txt", encoding="utf-8",mode="w")
driver = webdriver.Chrome("C:/Users/LJH/Documents/GitHub/CapstoneCoin/Crawling/world/chromedriver.exe")

def Baidu(url):
    conn = pymysql.connect(host='localhost', user='root',
                           db='capstone', charset='utf8')
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("div",class_="list-row")
    data = []
    for s4 in s3[1:]:
        s5 = s4.find_all("div")
        s6 = s5[0].find("a")
        link = "https://talent.baidu.com/external/baidu/index.html" + s6['href']

        for i in range(len(s5)):
            data.append((s5[i].text).strip() )
        print(data)
        #f.write(link + "\r\n")
        curs = conn.cursor()
        sql = "INSERT INTO capstone.baidu_table values('" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "','" + data[4] + "','" + link + "');"
        curs.execute(sql)
    conn.commit()

url = "https://talent.baidu.com/external/baidu/index.html#/social/2"
Baidu(url)

#f.close()