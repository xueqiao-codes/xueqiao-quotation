#!/usr/bin/env python
# test for service QuotationQueryDao
import random
import time
from xueqiao.quotation.query.dao.ttypes import *
from xueqiao.quotation.query.dao.constants import *
from xueqiao.quotation.query.dao.client.QuotationQueryDaoStub import *

stub=QuotationQueryDaoStub()
#using like stub.xxxfunc(routeKey=random.randint(0, 100000), timeout=3000, args...)
#testing...

start = time.time()

tickOption = QueryTickOption()
tickOption.contractBasic = ContractBasicInfo()
tickOption.contractBasic.platform = "SCTP"
tickOption.contractBasic.contractSymbols = "T1803"
tickOption.startTimestampS = 1507860000
tickOption.endTimestampS = 1507860900

ticks = stub.getTicks(0, 3000, tickOption)

print "time_query:",(time.time()-start)

#  
for tick in ticks:
    print "tick:", time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(tick.raceTimestampMs/1000)) \
            , tick.volumn, tick.lastPrice, tick.lastQty \
            , tick.bidPrice[0], tick.bidQty[0], tick.askPrice[0], tick.askQty[0]

minuteOption = QueryKLineMinuteOption()
minuteOption.contractBasic = ContractBasicInfo()
minuteOption.contractBasic.platform= "SCTP"
minuteOption.contractBasic.contractSymbols = "T1803"
minuteOption.startMinuteTimestampS = 1507860000
minuteOption.endMinuteTimestampS = 1507946400


start = time.time()
minutes = stub.getKLineMinutes(0, 3000, minuteOption)
print "time_query:",(time.time()-start)

for minute in minutes:
    print "minute:", time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(minute.kMinuteStartTimestampS)), minute.detail.kLineOpenPrice \
        , minute.detail.kLineHighPrice, minute.detail.kLineLowPrice \
        , minute.detail.kLineClosePrice, minute.detail.kLineQty \
        , minute.detail.kLineSettlementPrice, minute.detail.kLineOpenInterest

