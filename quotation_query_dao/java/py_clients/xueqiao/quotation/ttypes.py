#
# -*- coding: utf-8 -*-
# Autogenerated by Thrift Compiler (0.9.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TException, TApplicationException

from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol, TProtocol
try:
  from thrift.protocol import fastbinary
except:
  fastbinary = None



class QuotationItem:
  """
  Attributes:
   - platform
   - contractSymbol
   - openPrice
   - highPrice
   - lowPrice
   - preClosePrice
   - preSettlementPrice
   - preOpenInterest
   - openInterest
   - volumn
   - turnover
   - updateTimestampMs
   - lastPrice
   - lastQty
   - upperLimitPrice
   - lowerLimitPrice
   - averagePrice
   - bidPrice
   - bidQty
   - askPrice
   - askQty
   - receivedTimestampMs
   - receivedHostName
   - receivedProcessId
   - raceTimestampMs
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'platform', None, None, ), # 1
    (2, TType.STRING, 'contractSymbol', None, None, ), # 2
    (3, TType.DOUBLE, 'openPrice', None, None, ), # 3
    (4, TType.DOUBLE, 'highPrice', None, None, ), # 4
    (5, TType.DOUBLE, 'lowPrice', None, None, ), # 5
    (6, TType.DOUBLE, 'preClosePrice', None, None, ), # 6
    (7, TType.DOUBLE, 'preSettlementPrice', None, None, ), # 7
    (8, TType.I64, 'preOpenInterest', None, None, ), # 8
    (9, TType.I64, 'openInterest', None, None, ), # 9
    (10, TType.I64, 'volumn', None, None, ), # 10
    (11, TType.DOUBLE, 'turnover', None, None, ), # 11
    (12, TType.I64, 'updateTimestampMs', None, None, ), # 12
    (13, TType.DOUBLE, 'lastPrice', None, None, ), # 13
    (14, TType.I64, 'lastQty', None, None, ), # 14
    (15, TType.DOUBLE, 'upperLimitPrice', None, None, ), # 15
    (16, TType.DOUBLE, 'lowerLimitPrice', None, None, ), # 16
    (17, TType.DOUBLE, 'averagePrice', None, None, ), # 17
    None, # 18
    None, # 19
    None, # 20
    (21, TType.LIST, 'bidPrice', (TType.DOUBLE,None), None, ), # 21
    (22, TType.LIST, 'bidQty', (TType.I64,None), None, ), # 22
    (23, TType.LIST, 'askPrice', (TType.DOUBLE,None), None, ), # 23
    (24, TType.LIST, 'askQty', (TType.I64,None), None, ), # 24
    None, # 25
    None, # 26
    None, # 27
    None, # 28
    None, # 29
    None, # 30
    None, # 31
    None, # 32
    None, # 33
    None, # 34
    None, # 35
    None, # 36
    None, # 37
    None, # 38
    None, # 39
    None, # 40
    None, # 41
    None, # 42
    None, # 43
    None, # 44
    None, # 45
    None, # 46
    None, # 47
    None, # 48
    None, # 49
    None, # 50
    None, # 51
    None, # 52
    None, # 53
    None, # 54
    None, # 55
    None, # 56
    None, # 57
    None, # 58
    None, # 59
    None, # 60
    None, # 61
    None, # 62
    None, # 63
    None, # 64
    None, # 65
    None, # 66
    None, # 67
    None, # 68
    None, # 69
    None, # 70
    None, # 71
    None, # 72
    None, # 73
    None, # 74
    None, # 75
    None, # 76
    None, # 77
    None, # 78
    None, # 79
    None, # 80
    None, # 81
    None, # 82
    None, # 83
    None, # 84
    None, # 85
    None, # 86
    None, # 87
    None, # 88
    None, # 89
    None, # 90
    None, # 91
    None, # 92
    None, # 93
    None, # 94
    None, # 95
    None, # 96
    None, # 97
    None, # 98
    None, # 99
    (100, TType.I64, 'receivedTimestampMs', None, None, ), # 100
    (101, TType.STRING, 'receivedHostName', None, None, ), # 101
    (102, TType.I16, 'receivedProcessId', None, None, ), # 102
    None, # 103
    None, # 104
    None, # 105
    None, # 106
    None, # 107
    None, # 108
    None, # 109
    None, # 110
    None, # 111
    None, # 112
    None, # 113
    None, # 114
    None, # 115
    None, # 116
    None, # 117
    None, # 118
    None, # 119
    (120, TType.I64, 'raceTimestampMs', None, None, ), # 120
  )

  def __init__(self, platform=None, contractSymbol=None, openPrice=None, highPrice=None, lowPrice=None, preClosePrice=None, preSettlementPrice=None, preOpenInterest=None, openInterest=None, volumn=None, turnover=None, updateTimestampMs=None, lastPrice=None, lastQty=None, upperLimitPrice=None, lowerLimitPrice=None, averagePrice=None, bidPrice=None, bidQty=None, askPrice=None, askQty=None, receivedTimestampMs=None, receivedHostName=None, receivedProcessId=None, raceTimestampMs=None,):
    self.platform = platform
    self.contractSymbol = contractSymbol
    self.openPrice = openPrice
    self.highPrice = highPrice
    self.lowPrice = lowPrice
    self.preClosePrice = preClosePrice
    self.preSettlementPrice = preSettlementPrice
    self.preOpenInterest = preOpenInterest
    self.openInterest = openInterest
    self.volumn = volumn
    self.turnover = turnover
    self.updateTimestampMs = updateTimestampMs
    self.lastPrice = lastPrice
    self.lastQty = lastQty
    self.upperLimitPrice = upperLimitPrice
    self.lowerLimitPrice = lowerLimitPrice
    self.averagePrice = averagePrice
    self.bidPrice = bidPrice
    self.bidQty = bidQty
    self.askPrice = askPrice
    self.askQty = askQty
    self.receivedTimestampMs = receivedTimestampMs
    self.receivedHostName = receivedHostName
    self.receivedProcessId = receivedProcessId
    self.raceTimestampMs = raceTimestampMs

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.STRING:
          self.platform = iprot.readString();
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.contractSymbol = iprot.readString();
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.DOUBLE:
          self.openPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.DOUBLE:
          self.highPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 5:
        if ftype == TType.DOUBLE:
          self.lowPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 6:
        if ftype == TType.DOUBLE:
          self.preClosePrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 7:
        if ftype == TType.DOUBLE:
          self.preSettlementPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 8:
        if ftype == TType.I64:
          self.preOpenInterest = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 9:
        if ftype == TType.I64:
          self.openInterest = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 10:
        if ftype == TType.I64:
          self.volumn = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 11:
        if ftype == TType.DOUBLE:
          self.turnover = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 12:
        if ftype == TType.I64:
          self.updateTimestampMs = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 13:
        if ftype == TType.DOUBLE:
          self.lastPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 14:
        if ftype == TType.I64:
          self.lastQty = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 15:
        if ftype == TType.DOUBLE:
          self.upperLimitPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 16:
        if ftype == TType.DOUBLE:
          self.lowerLimitPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 17:
        if ftype == TType.DOUBLE:
          self.averagePrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 21:
        if ftype == TType.LIST:
          self.bidPrice = []
          (_etype3, _size0) = iprot.readListBegin()
          for _i4 in xrange(_size0):
            _elem5 = iprot.readDouble();
            self.bidPrice.append(_elem5)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 22:
        if ftype == TType.LIST:
          self.bidQty = []
          (_etype9, _size6) = iprot.readListBegin()
          for _i10 in xrange(_size6):
            _elem11 = iprot.readI64();
            self.bidQty.append(_elem11)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 23:
        if ftype == TType.LIST:
          self.askPrice = []
          (_etype15, _size12) = iprot.readListBegin()
          for _i16 in xrange(_size12):
            _elem17 = iprot.readDouble();
            self.askPrice.append(_elem17)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 24:
        if ftype == TType.LIST:
          self.askQty = []
          (_etype21, _size18) = iprot.readListBegin()
          for _i22 in xrange(_size18):
            _elem23 = iprot.readI64();
            self.askQty.append(_elem23)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 100:
        if ftype == TType.I64:
          self.receivedTimestampMs = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 101:
        if ftype == TType.STRING:
          self.receivedHostName = iprot.readString();
        else:
          iprot.skip(ftype)
      elif fid == 102:
        if ftype == TType.I16:
          self.receivedProcessId = iprot.readI16();
        else:
          iprot.skip(ftype)
      elif fid == 120:
        if ftype == TType.I64:
          self.raceTimestampMs = iprot.readI64();
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('QuotationItem')
    if self.platform is not None:
      oprot.writeFieldBegin('platform', TType.STRING, 1)
      oprot.writeString(self.platform)
      oprot.writeFieldEnd()
    if self.contractSymbol is not None:
      oprot.writeFieldBegin('contractSymbol', TType.STRING, 2)
      oprot.writeString(self.contractSymbol)
      oprot.writeFieldEnd()
    if self.openPrice is not None:
      oprot.writeFieldBegin('openPrice', TType.DOUBLE, 3)
      oprot.writeDouble(self.openPrice)
      oprot.writeFieldEnd()
    if self.highPrice is not None:
      oprot.writeFieldBegin('highPrice', TType.DOUBLE, 4)
      oprot.writeDouble(self.highPrice)
      oprot.writeFieldEnd()
    if self.lowPrice is not None:
      oprot.writeFieldBegin('lowPrice', TType.DOUBLE, 5)
      oprot.writeDouble(self.lowPrice)
      oprot.writeFieldEnd()
    if self.preClosePrice is not None:
      oprot.writeFieldBegin('preClosePrice', TType.DOUBLE, 6)
      oprot.writeDouble(self.preClosePrice)
      oprot.writeFieldEnd()
    if self.preSettlementPrice is not None:
      oprot.writeFieldBegin('preSettlementPrice', TType.DOUBLE, 7)
      oprot.writeDouble(self.preSettlementPrice)
      oprot.writeFieldEnd()
    if self.preOpenInterest is not None:
      oprot.writeFieldBegin('preOpenInterest', TType.I64, 8)
      oprot.writeI64(self.preOpenInterest)
      oprot.writeFieldEnd()
    if self.openInterest is not None:
      oprot.writeFieldBegin('openInterest', TType.I64, 9)
      oprot.writeI64(self.openInterest)
      oprot.writeFieldEnd()
    if self.volumn is not None:
      oprot.writeFieldBegin('volumn', TType.I64, 10)
      oprot.writeI64(self.volumn)
      oprot.writeFieldEnd()
    if self.turnover is not None:
      oprot.writeFieldBegin('turnover', TType.DOUBLE, 11)
      oprot.writeDouble(self.turnover)
      oprot.writeFieldEnd()
    if self.updateTimestampMs is not None:
      oprot.writeFieldBegin('updateTimestampMs', TType.I64, 12)
      oprot.writeI64(self.updateTimestampMs)
      oprot.writeFieldEnd()
    if self.lastPrice is not None:
      oprot.writeFieldBegin('lastPrice', TType.DOUBLE, 13)
      oprot.writeDouble(self.lastPrice)
      oprot.writeFieldEnd()
    if self.lastQty is not None:
      oprot.writeFieldBegin('lastQty', TType.I64, 14)
      oprot.writeI64(self.lastQty)
      oprot.writeFieldEnd()
    if self.upperLimitPrice is not None:
      oprot.writeFieldBegin('upperLimitPrice', TType.DOUBLE, 15)
      oprot.writeDouble(self.upperLimitPrice)
      oprot.writeFieldEnd()
    if self.lowerLimitPrice is not None:
      oprot.writeFieldBegin('lowerLimitPrice', TType.DOUBLE, 16)
      oprot.writeDouble(self.lowerLimitPrice)
      oprot.writeFieldEnd()
    if self.averagePrice is not None:
      oprot.writeFieldBegin('averagePrice', TType.DOUBLE, 17)
      oprot.writeDouble(self.averagePrice)
      oprot.writeFieldEnd()
    if self.bidPrice is not None:
      oprot.writeFieldBegin('bidPrice', TType.LIST, 21)
      oprot.writeListBegin(TType.DOUBLE, len(self.bidPrice))
      for iter24 in self.bidPrice:
        oprot.writeDouble(iter24)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.bidQty is not None:
      oprot.writeFieldBegin('bidQty', TType.LIST, 22)
      oprot.writeListBegin(TType.I64, len(self.bidQty))
      for iter25 in self.bidQty:
        oprot.writeI64(iter25)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.askPrice is not None:
      oprot.writeFieldBegin('askPrice', TType.LIST, 23)
      oprot.writeListBegin(TType.DOUBLE, len(self.askPrice))
      for iter26 in self.askPrice:
        oprot.writeDouble(iter26)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.askQty is not None:
      oprot.writeFieldBegin('askQty', TType.LIST, 24)
      oprot.writeListBegin(TType.I64, len(self.askQty))
      for iter27 in self.askQty:
        oprot.writeI64(iter27)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.receivedTimestampMs is not None:
      oprot.writeFieldBegin('receivedTimestampMs', TType.I64, 100)
      oprot.writeI64(self.receivedTimestampMs)
      oprot.writeFieldEnd()
    if self.receivedHostName is not None:
      oprot.writeFieldBegin('receivedHostName', TType.STRING, 101)
      oprot.writeString(self.receivedHostName)
      oprot.writeFieldEnd()
    if self.receivedProcessId is not None:
      oprot.writeFieldBegin('receivedProcessId', TType.I16, 102)
      oprot.writeI16(self.receivedProcessId)
      oprot.writeFieldEnd()
    if self.raceTimestampMs is not None:
      oprot.writeFieldBegin('raceTimestampMs', TType.I64, 120)
      oprot.writeI64(self.raceTimestampMs)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class KLineQuotationDetail:
  """
  Attributes:
   - kLineOpenPrice
   - kLineHighPrice
   - kLineLowPrice
   - kLineClosePrice
   - kLineQty
   - kLineSettlementPrice
   - kLineOpenInterest
  """

  thrift_spec = (
    None, # 0
    (1, TType.DOUBLE, 'kLineOpenPrice', None, None, ), # 1
    (2, TType.DOUBLE, 'kLineHighPrice', None, None, ), # 2
    (3, TType.DOUBLE, 'kLineLowPrice', None, None, ), # 3
    (4, TType.DOUBLE, 'kLineClosePrice', None, None, ), # 4
    (5, TType.I64, 'kLineQty', None, None, ), # 5
    (6, TType.DOUBLE, 'kLineSettlementPrice', None, None, ), # 6
    (7, TType.I64, 'kLineOpenInterest', None, None, ), # 7
  )

  def __init__(self, kLineOpenPrice=None, kLineHighPrice=None, kLineLowPrice=None, kLineClosePrice=None, kLineQty=None, kLineSettlementPrice=None, kLineOpenInterest=None,):
    self.kLineOpenPrice = kLineOpenPrice
    self.kLineHighPrice = kLineHighPrice
    self.kLineLowPrice = kLineLowPrice
    self.kLineClosePrice = kLineClosePrice
    self.kLineQty = kLineQty
    self.kLineSettlementPrice = kLineSettlementPrice
    self.kLineOpenInterest = kLineOpenInterest

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.DOUBLE:
          self.kLineOpenPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.DOUBLE:
          self.kLineHighPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.DOUBLE:
          self.kLineLowPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.DOUBLE:
          self.kLineClosePrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 5:
        if ftype == TType.I64:
          self.kLineQty = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 6:
        if ftype == TType.DOUBLE:
          self.kLineSettlementPrice = iprot.readDouble();
        else:
          iprot.skip(ftype)
      elif fid == 7:
        if ftype == TType.I64:
          self.kLineOpenInterest = iprot.readI64();
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('KLineQuotationDetail')
    if self.kLineOpenPrice is not None:
      oprot.writeFieldBegin('kLineOpenPrice', TType.DOUBLE, 1)
      oprot.writeDouble(self.kLineOpenPrice)
      oprot.writeFieldEnd()
    if self.kLineHighPrice is not None:
      oprot.writeFieldBegin('kLineHighPrice', TType.DOUBLE, 2)
      oprot.writeDouble(self.kLineHighPrice)
      oprot.writeFieldEnd()
    if self.kLineLowPrice is not None:
      oprot.writeFieldBegin('kLineLowPrice', TType.DOUBLE, 3)
      oprot.writeDouble(self.kLineLowPrice)
      oprot.writeFieldEnd()
    if self.kLineClosePrice is not None:
      oprot.writeFieldBegin('kLineClosePrice', TType.DOUBLE, 4)
      oprot.writeDouble(self.kLineClosePrice)
      oprot.writeFieldEnd()
    if self.kLineQty is not None:
      oprot.writeFieldBegin('kLineQty', TType.I64, 5)
      oprot.writeI64(self.kLineQty)
      oprot.writeFieldEnd()
    if self.kLineSettlementPrice is not None:
      oprot.writeFieldBegin('kLineSettlementPrice', TType.DOUBLE, 6)
      oprot.writeDouble(self.kLineSettlementPrice)
      oprot.writeFieldEnd()
    if self.kLineOpenInterest is not None:
      oprot.writeFieldBegin('kLineOpenInterest', TType.I64, 7)
      oprot.writeI64(self.kLineOpenInterest)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class KLineQuotationMinuteItem:
  """
  Attributes:
   - platform
   - contractSymbol
   - kMinuteStartTimestampS
   - kMinutePeriod
   - detail
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'platform', None, None, ), # 1
    (2, TType.STRING, 'contractSymbol', None, None, ), # 2
    (3, TType.I64, 'kMinuteStartTimestampS', None, None, ), # 3
    (4, TType.I16, 'kMinutePeriod', None, None, ), # 4
    (5, TType.STRUCT, 'detail', (KLineQuotationDetail, KLineQuotationDetail.thrift_spec), None, ), # 5
  )

  def __init__(self, platform=None, contractSymbol=None, kMinuteStartTimestampS=None, kMinutePeriod=None, detail=None,):
    self.platform = platform
    self.contractSymbol = contractSymbol
    self.kMinuteStartTimestampS = kMinuteStartTimestampS
    self.kMinutePeriod = kMinutePeriod
    self.detail = detail

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.STRING:
          self.platform = iprot.readString();
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.contractSymbol = iprot.readString();
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.I64:
          self.kMinuteStartTimestampS = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.I16:
          self.kMinutePeriod = iprot.readI16();
        else:
          iprot.skip(ftype)
      elif fid == 5:
        if ftype == TType.STRUCT:
          self.detail = KLineQuotationDetail()
          self.detail.read(iprot)
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('KLineQuotationMinuteItem')
    if self.platform is not None:
      oprot.writeFieldBegin('platform', TType.STRING, 1)
      oprot.writeString(self.platform)
      oprot.writeFieldEnd()
    if self.contractSymbol is not None:
      oprot.writeFieldBegin('contractSymbol', TType.STRING, 2)
      oprot.writeString(self.contractSymbol)
      oprot.writeFieldEnd()
    if self.kMinuteStartTimestampS is not None:
      oprot.writeFieldBegin('kMinuteStartTimestampS', TType.I64, 3)
      oprot.writeI64(self.kMinuteStartTimestampS)
      oprot.writeFieldEnd()
    if self.kMinutePeriod is not None:
      oprot.writeFieldBegin('kMinutePeriod', TType.I16, 4)
      oprot.writeI16(self.kMinutePeriod)
      oprot.writeFieldEnd()
    if self.detail is not None:
      oprot.writeFieldBegin('detail', TType.STRUCT, 5)
      self.detail.write(oprot)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)
