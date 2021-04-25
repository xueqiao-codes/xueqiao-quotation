#!/usr/bin/env python
# test for service QuotationPlanBo
import random
from xueqiao.quotation.plan.bo.ttypes import *
from xueqiao.quotation.plan.bo.constants import *
from xueqiao.quotation.plan.bo.client.QuotationPlanBoStub import *

stub=QuotationPlanBoStub()
#using like stub.xxxfunc(routeKey=random.randint(0, 100000), timeout=3000, args...)
#testing...


#print stub.startGenPreviewSCClasses(0, 3000)
#print stub.getGenPreviewState(0, 3000)
#print stub.commitPreviewSCClasses(0, 3000)
#print "preview:", stub.getPreviewSCClasses(0, 3000)
#print "current:", stub.getCurrentSCClasses(0, 3000)

qryOption = QuerySubscribeContractItemOption()
#qryOption.quotationAccountIds = set([1245])
qryOption.sledCommodityIds = set([10438])
qryOption.platformEnv = QuotationPlatformEnv.REAL
pageOption = IndexedPageOption()
pageOption.pageIndex = 0
pageOption.pageSize = 10
pageOption.needTotalCount = True

print stub.querySubscribeContractItemPage(0, 3000, qryOption, pageOption)
