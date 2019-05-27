from typing import List

from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import pymysql

#12345
def SS():
    conn = pymysql.connect(host='localhost', user='root', password='t13579',
                           db='dbclass', charset='utf8')
    driver.get("http://www.jobkorea.co.kr/Search/?stext=%EC%9B%90%EB%8D%94%ED%92%80%ED%94%8C%EB%9E%AB%ED%8F%BC")
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("section", id="cnt")
    a = 0
    con = []
    car = []
    com = []
    da = []
    link = []
    for s4 in s3:
        s5 = s4.find_all("article", id="smGiList")
        for s6 in s5:
            s7 = s6.find_all("div", class_="list")
            for s8 in s7:
                s9 = s8.find_all("a")

                for i in s9:
                    if a%4 == 0:
                        com.append(str(i.text))
                        print(str(i.text))
                    if a%4 == 1:
                        print(str(i.text))
                        con.append(str(i.text))
                    if a % 4 == 2:
                        a += 1
                        continue
                    if a % 4 == 3:
                        b = str(i.text)
                        link.append(i['href'])
                        if ',' in b:
                            b1 = b.split(",")
                            b2 = b1[0].split("\n")
                            date = b1[1].split("~")
                            date1 = date[1].split("\n")
                            print(date1[0], b2[2])
                            da.append(date1[0])
                            if '경력' in b2[2]:
                                car.append('2')
                            elif '신입' in b2[2]:
                                car.append('1')
                            elif '계약' in b2[2]:
                                car.append('3')
                            elif '정규' in b2[2]:
                                car.append('4')
                        else:
                            b1 = b.split("\n")
                            b2 = b1[2].split("|")
                            date = b1[11].split("~")
                            date1 = date[1].split("\n")
                            print(date1[0], b2[0])
                            da.append(date1[0])
                            if '경력' in b2[0]:
                                car.append('2')
                            elif '신입' in b2[0]:
                                car.append('1')
                            elif '계약' in b2[0]:
                                car.append('3')
                            elif '정규' in b2[0]:
                                car.append('4')

                        #date1[0], b2[2]
                        #da[cnt] = date1[0]

                    a += 1

    curs = conn.cursor()
    for j in range(3):
        link[j] = "http://www.jobkorea.co.kr/"+link[j]
        sql = "insert into dbclass.company values ('" + car[j] + "', '" + com[j] + "', '" +con[j]+ "', '', '" +da[j]+ "', '" + link[j] + "');"
        curs.execute(sql)
        conn.commit()

def wonder():
    driver.get("http://www.jobkorea.co.kr/Recruit/GI_Read/28320931?Oem_Code=C1&view_type=01")
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("div", class_ = "jkGEN_content")
    for s4 in s3:
        s5 = s4.find_all("table", class_ = "__se_tbljkGEN_table")
        for s6 in s5:
            s7 = s6.find_all("td")
            print(s7)



def studio8():
    driver.get("http://www.saramin.co.kr/zf_user/company-info/view-inner-recruit?csn=1048162461")
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("ul", class_="list_employ")
    for s4 in s3:
        s5 = s4.find_all("li")
        for s6 in s5:
            s7 = s6.find_all("a")
            s8 = s6.find_all("span")
            for i in s7:
                f.write(str(i.text) + "\r\n")
            f.write("------------------------------\r\n")
            for i in s8:
                f.write(str(i.text) + "\r\n")
            f.write("++++++++++++++++++++++++++++++\r\n")
        f.write("------------------------------\r\n")


def test():
    driver.get("http://www.jobkorea.co.kr/Search/?stext=%EC%9B%90%EB%8D%94%ED%92%80%ED%94%8C%EB%9E%AB%ED%8F%BC")
    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("section", id="cnt")
    for s4 in s3:
        s5 = s4.find_all("article", id="smGiList")

        for s6 in s5:
            s7 = s6.find_all("div", class_="list")
            for s8 in s7:
                s9 = s8.find_all("a")
                for i in s9:
                    f.write(str(i['href'].text) + "\r\n")
                f.write("------------------------------\r\n")


def SamSung():
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
        print(a1, b, c, kara, made)
        curs = conn.cursor()
        sql = "insert into dbclass.company values ('" + a1 + "', '" + b + "', '" + c + "', '', '" + kara + "', '" + made + "');"
        curs.execute(sql)
        conn.commit()


f = codecs.open("C:/Users/SAMSUNG/Desktop/capstone/crawler/link.txt", encoding="utf-8", mode="w")
driver = webdriver.Chrome("C:/Users/SAMSUNG/Desktop/capstone/crawler/chromedriver.exe")
SamSung()

