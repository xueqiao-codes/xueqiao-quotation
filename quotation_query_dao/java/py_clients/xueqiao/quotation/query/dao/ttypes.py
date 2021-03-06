#
# -*- coding: utf-8 -*-
# Autogenerated by Thrift Compiler (0.9.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TException, TApplicationException
import comm.ttypes
import xueqiao.quotation.ttypes


from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol, TProtocol
try:
  from thrift.protocol import fastbinary
except:
  fastbinary = None


class QuotationQueryDaoErrorCode:
  CONTRACT_NOT_FOUND = 3001
  QUERY_LIMITED = 3002

  _VALUES_TO_NAMES = {
    3001: "CONTRACT_NOT_FOUND",
    3002: "QUERY_LIMITED",
  }

  _NAMES_TO_VALUES = {
    "CONTRACT_NOT_FOUND": 3001,
    "QUERY_LIMITED": 3002,
  }


class ContractBasicInfo:
  """
  Attributes:
   - platform
   - contractSymbols
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'platform', None, None, ), # 1
    (2, TType.STRING, 'contractSymbols', None, None, ), # 2
  )

  def __init__(self, platform=None, contractSymbols=None,):
    self.platform = platform
    self.contractSymbols = contractSymbols

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
          self.contractSymbols = iprot.readString();
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
    oprot.writeStructBegin('ContractBasicInfo')
    if self.platform is not None:
      oprot.writeFieldBegin('platform', TType.STRING, 1)
      oprot.writeString(self.platform)
      oprot.writeFieldEnd()
    if self.contractSymbols is not None:
      oprot.writeFieldBegin('contractSymbols', TType.STRING, 2)
      oprot.writeString(self.contractSymbols)
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

class QueryTickOption:
  """
  Attributes:
   - contractBasic
   - startTimestampS
   - endTimestampS
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'contractBasic', (ContractBasicInfo, ContractBasicInfo.thrift_spec), None, ), # 1
    (2, TType.I64, 'startTimestampS', None, None, ), # 2
    (3, TType.I64, 'endTimestampS', None, None, ), # 3
  )

  def __init__(self, contractBasic=None, startTimestampS=None, endTimestampS=None,):
    self.contractBasic = contractBasic
    self.startTimestampS = startTimestampS
    self.endTimestampS = endTimestampS

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
        if ftype == TType.STRUCT:
          self.contractBasic = ContractBasicInfo()
          self.contractBasic.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.I64:
          self.startTimestampS = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.I64:
          self.endTimestampS = iprot.readI64();
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
    oprot.writeStructBegin('QueryTickOption')
    if self.contractBasic is not None:
      oprot.writeFieldBegin('contractBasic', TType.STRUCT, 1)
      self.contractBasic.write(oprot)
      oprot.writeFieldEnd()
    if self.startTimestampS is not None:
      oprot.writeFieldBegin('startTimestampS', TType.I64, 2)
      oprot.writeI64(self.startTimestampS)
      oprot.writeFieldEnd()
    if self.endTimestampS is not None:
      oprot.writeFieldBegin('endTimestampS', TType.I64, 3)
      oprot.writeI64(self.endTimestampS)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    if self.contractBasic is None:
      raise TProtocol.TProtocolException(message='Required field contractBasic is unset!')
    return


  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class QueryKLineMinuteOption:
  """
  Attributes:
   - contractBasic
   - startMinuteTimestampS
   - endMinuteTimestampS
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'contractBasic', (ContractBasicInfo, ContractBasicInfo.thrift_spec), None, ), # 1
    (2, TType.I64, 'startMinuteTimestampS', None, None, ), # 2
    (3, TType.I64, 'endMinuteTimestampS', None, None, ), # 3
  )

  def __init__(self, contractBasic=None, startMinuteTimestampS=None, endMinuteTimestampS=None,):
    self.contractBasic = contractBasic
    self.startMinuteTimestampS = startMinuteTimestampS
    self.endMinuteTimestampS = endMinuteTimestampS

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
        if ftype == TType.STRUCT:
          self.contractBasic = ContractBasicInfo()
          self.contractBasic.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.I64:
          self.startMinuteTimestampS = iprot.readI64();
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.I64:
          self.endMinuteTimestampS = iprot.readI64();
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
    oprot.writeStructBegin('QueryKLineMinuteOption')
    if self.contractBasic is not None:
      oprot.writeFieldBegin('contractBasic', TType.STRUCT, 1)
      self.contractBasic.write(oprot)
      oprot.writeFieldEnd()
    if self.startMinuteTimestampS is not None:
      oprot.writeFieldBegin('startMinuteTimestampS', TType.I64, 2)
      oprot.writeI64(self.startMinuteTimestampS)
      oprot.writeFieldEnd()
    if self.endMinuteTimestampS is not None:
      oprot.writeFieldBegin('endMinuteTimestampS', TType.I64, 3)
      oprot.writeI64(self.endMinuteTimestampS)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    if self.contractBasic is None:
      raise TProtocol.TProtocolException(message='Required field contractBasic is unset!')
    return


  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)
