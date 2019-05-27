from typing import List
from bs4 import BeautifulSoup
from selenium import webdriver
import codecs
import pymysql


def SS():
    conn = pymysql.connect(host='localhost', user='root',
                           db='capstone', charset='utf8')
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
                    if a%4 == 1:
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

                            date = b1[6].split("~")
                            #print(date[1], b21[0:2])
                            #print(date1[0], b2[0])
                            da.append(date[1])
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
    curs.execute("select title from capstone.venture where c_name LIKE '%원더풀%'")
    compare = []

    dele = []
    while (True):
        row = curs.fetchone()
        if row == None:
            break
        compare.append(row[0])

    for i in compare:
        if i not in con:
            dele.append(i)
    cnt = 0
    leng1 = con
    for i in leng1:
        if i in compare :
            del con[cnt]
            del car[cnt]
            del da[cnt]
            del link[cnt]
            del com[cnt]
            continue
        cnt += 1

    for i in dele:
        curs.execute("delete from capstone.venture where content = '" + i + "'")
        conn.commit()

    for j in range(len(car)):
        link[j] = "http://www.jobkorea.co.kr/"+link[j]
        sql = "insert into capstone.venture values ('" + car[j] + "', '" + com[j] + "', '" +con[j]+ "', '" +da[j]+ "', '" + link[j] + "');"
        curs.execute(sql)
        conn.commit()




def SamSung():
    conn = pymysql.connect(host='localhost', user='root',
                           db='capstone', charset='utf8')
    driver.get("http://www.samsungcareers.com/main.html")
    cont = []
    care = []
    comp = []
    edate = []
    sdate = []
    cnt = 0

    s1 = driver.page_source
    s2 = BeautifulSoup(s1, "html.parser")
    s3 = s2.find_all("tr", class_="table_list")
    for s4 in s3:
        s5 = s4.find_all("td")
        a = str(s5[1].text)
        if '경력' in a:
            care.append('2')
        else:
            care.append('1')
        b = str(s5[2].text)
        c = str(s5[3].text)
        d = str(s5[4].text)
        cont.append(c)
        comp.append(b)
        sdate.append(d[0:10])
        edate.append(d[11:21])
        cnt+=1
    curs = conn.cursor()
    curs.execute("select title from capstone.samsung")
    compare = []
    dele = []
    while(True):
        row = curs.fetchone()
        if row == None:
            break
        compare.append(row[0])

    for i in compare:
        if i not in cont:
            dele.append(i)
    cnt = 0
    leng1 = cont
    for j in leng1:
        if j in compare:
            del cont[cnt]
            del care[cnt]
            del edate[cnt]
            del sdate[cnt]
            del comp[cnt]
            continue
        cnt += 1


    for i in dele:
        curs.execute("delete from capstone.company where title = '"+i+"'")
        conn.commit()


    #sql = "SELECT * FROM dbclass.company where companyname LIKE '%"+cont[]+"%';"
    for i in range(len(cont)):
        sql = "insert into capstone.samsung values ('" + care[i] + "', '" + comp[i] + "', '" +cont[i]+ "', '" +sdate[i]+ "', '" + edate[i]+ "');"
        curs.execute(sql)
        conn.commit()


#f = codecs.open("C:/Users/SAMSUNG/Desktop/capstone/crawler/link.txt", encoding="utf-8", mode="w")
driver = webdriver.Chrome("C:/Users/LJH/Documents/GitHub/CapstoneCoin/Crawling/korea/chromedriver.exe")
#SS()
SamSung()
