
from bs4 import BeautifulSoup
from selenium import webdriver
import time, codecs


f = codecs.open("C:/Users/SAMSUNG/Desktop/crawler/link.txt", encoding="utf-8",
                mode="w")
driver = webdriver.Chrome("C:/Users/SAMSUNG/Desktop/crawler/chromedriver.exe")

class Crawler:
    def __init__(self, _path, _tag1, _tag2,_tag3):
        self._path = _path
        self._tag1 = _tag1
        self._tag2 = _tag2
        self._tag3 = _tag3

    def run(self):
        driver.get(self._path)
        # driver.find_element_by_xpath('//*[@id="re_mallmenu"]/ul/li[3]/div/a/img').click()
        s1 = driver.page_source
        s2 = BeautifulSoup(s1, "html.parser")
        s3 = s2.find_all(self._tag1, self._tag2)

        for s4 in s3:
            s5 = s4.find_all(self._tag3)
            for _info in s5:
                f.write(str(_info.text) + "\r\n")
            f.write("------------------\r\n")

#//*[@id="new_job_list_14130"]/td[4]/a
#//*[@id="new_job_list_14153"]/td[4]/a
#//*[@id="new_job_list_14151"]/td[4]/a

Samsung = Crawler("http://www.samsungcareers.com/main.html","tr","table_list","td")
Samsung.run()