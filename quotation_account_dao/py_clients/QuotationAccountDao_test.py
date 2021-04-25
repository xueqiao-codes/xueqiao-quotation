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

req_option = ReqQuotationAccountSupportOption()
page_option= IndexedPageOption()

page= stub.reqQuotationAccountSupport(routeKey=100, timeout= 2000, option=req_option, pageOption= page_option)
print(page)