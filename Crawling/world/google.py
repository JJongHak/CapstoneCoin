from bs4 import BeautifulSoup
import bs4
from selenium import webdriver
import codecs
import pymysql


driver = webdriver.Chrome("C:/Users/gooni/Desktop/CapstoneCoin/Crawling/world/chromedriver.exe")


def crawler(url):
    driver.get(url)
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    return s2

def Google(url):
    conn = pymysql.connect(host='localhost', user='root', password='Capstone123.',
                           db='capstone', charset='utf8')
    ss = crawler(url)
    s1 = ss.findAll("a", class_="gc-card")
    for s2 in s1:
        s3 = s2.find("h2")
        s4 = s2.find("ul",class_="gc-job-tags gc-h-flex")
        if isinstance(s4, bs4.element.Tag):
            s5 = s4.findAll("li")
            link = s2['href']
            a = (s3.text).strip()
            b = (s5[0].text).strip()

            if len(s5)==3:
                c = (s5[1].text).strip() + ", " + (s5[2].text).strip()
            else:
                c = (s5[1].text).strip()
            d = "https://careers.google.com/" + link

            curs = conn.cursor()
            sql = "INSERT INTO capstone.google_table values('" + a + "','" + b + "','" + c + "','" + d + "');"
            curs.execute(sql)
    conn.commit()

#12345
urls = []
url0 = "https://careers.google.com/jobs/results/?category=DATA_CENTER_OPERATIONS&category=DEVELOPER_RELATIONS&category=HARDWARE\
_ENGINEERING&category=INFORMATION_TECHNOLOGY&category=MANUFACTURING_SUPPLY_CHAIN&category=NETWORK_ENGINEERING&category=PRODUCT\
_MANAGEMENT&category=PROGRAM_MANAGEMENT&category=SOFTWARE_ENGINEERING&category=TECHNICAL_INFRASTRUCTURE_ENGINEERING&category=\
TECHNICAL_SOLUTIONS&category=TECHNICAL_WRITING&company=Google&company=YouTube&p1age=&"

for i in range(1, 6):
    url = url0 + "page=%d&q="%i
    Google(url)