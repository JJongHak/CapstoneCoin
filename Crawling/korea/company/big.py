from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import pymysql

def SAMSUNG():
    conn = pymysql.connect(host='localhost', user='root', password='t13579',
                          db='dbclass', charset='utf8')
    driver.get("http://www.samsungcareers.com/main.html")
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("tr", class_="table_list")
    for s4 in s3:
        s5 = s4.find_all("td")
        a = str(s5[1].text)
        if '경력' in a:
            a1 = '2'
        else:
            a1 = '1'
        b = str(s5[2].text)
        c = str(s5[3].text)
        d = str(s5[4].text)
        kara = d[0:10]
        made = d[11:21]
        curs = conn.cursor()
        sql = "insert into dbclass.company values ('"+a1+"', '"+b+"', '"+c+"', '"+kara+"', '"+made+"');"
        curs.execute(sql)
        conn.commit()


f = codecs.open("C:/Users/SAMSUNG/Desktop/capstone/crawler/link.txt", encoding="utf-8", mode="w")
driver = webdriver.Chrome("C:/Users/SAMSUNG/Desktop/capstone/crawler/chromedriver.exe")
SAMSUNG()
