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

qaccount = QuotationAccount()
qaccount.accountId = 1242
qaccount.apiRetCode = 5000
qaccount.accountState = QuotationAccountState.ACCOUNT_DISABLED
qaccount.invalidErrorCode = 500
qaccount.invalidReason = "invalid reason"
stub.updateQuotationAccount(routeKey= 1000, timeout=2000,account= qaccount)