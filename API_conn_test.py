# -*- coding: utf-8 -*-
import requests
import json

login_url_tlpt = 'http://62.234.57.125:3000/login/cellphone?phone={}&password={}'

def PrintJson(data):
    print(str(json.dumps(data,sort_keys=True,indent=4,separators=(', ', ': '),ensure_ascii=False)))

phoneNum ='13940200592'
password = 'wdj1234567'
login_url = login_url_tlpt.format(phoneNum,password)
# login_url = login_url_tlpt.format('18561762719','19971003Zrplove')
r = requests.post(login_url)
res_data = json.loads(r.content)
PrintJson(res_data)
