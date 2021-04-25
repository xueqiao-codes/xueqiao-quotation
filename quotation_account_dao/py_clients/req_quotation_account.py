#!/usr/bin/env python
# test for service QuotationAccountDao
import random
from xueqiao.quotation.account.thriftapi.dao.ttypes import *
from xueqiao.quotation.account.thriftapi.dao.constants import *
from xueqiao.quotation.account.thriftapi.dao.client.QuotationAccountDaoStub import *
from xueqiao.quotation.account.thriftapi.ttypes import *

stub=QuotationAccountDaoStub()
#using like stub.xxxfunc(routeKey=random.randint(0, 100000), timeout=3000, args...)
#testing...

req_option = ReqQuotationAccountOption()
page_option= IndexedPageOption()

req_option.platformEnv = QuotationPlatformEnv.SIM
page= stub.reqQuotationAccount(routeKey=100, timeout= 2000, option=req_option, pageOption= page_option)
print("1: all")
print(page)

req_option.orderBy = QuotationAccountOrderBy.CREATE_TIMESTAMP

page= stub.reqQuotationAccount(routeKey=100, timeout= 2000, option=req_option, pageOption= page_option)
print("2: order by create timestamp")
print(page)

page_option.needTotalCount = True
page_option.pageIndex = 0
page_option.pageSize = 10

page= stub.reqQuotationAccount(routeKey=100, timeout= 2000, option=req_option, pageOption= page_option)
print("3: page option")
print(page)