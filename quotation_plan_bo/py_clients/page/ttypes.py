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



class IndexedPageOption:
  """
  索引从0开始，利用pageIndex和pageSize不断流转的分页方式

  Attributes:
   - needTotalCount
   - pageIndex
   - pageSize
  """

  thrift_spec = (
    None, # 0
    (1, TType.BOOL, 'needTotalCount', None, None, ), # 1
    (2, TType.I32, 'pageIndex', None, None, ), # 2
    (3, TType.I32, 'pageSize', None, None, ), # 3
  )

  def __init__(self, needTotalCount=None, pageIndex=None, pageSize=None,):
    self.needTotalCount = needTotalCount
    self.pageIndex = pageIndex
    self.pageSize = pageSize

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
        if ftype == TType.BOOL:
          self.needTotalCount = iprot.readBool();
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.I32:
          self.pageIndex = iprot.readI32();
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.I32:
          self.pageSize = iprot.readI32();
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
    oprot.writeStructBegin('IndexedPageOption')
    if self.needTotalCount is not None:
      oprot.writeFieldBegin('needTotalCount', TType.BOOL, 1)
      oprot.writeBool(self.needTotalCount)
      oprot.writeFieldEnd()
    if self.pageIndex is not None:
      oprot.writeFieldBegin('pageIndex', TType.I32, 2)
      oprot.writeI32(self.pageIndex)
      oprot.writeFieldEnd()
    if self.pageSize is not None:
      oprot.writeFieldBegin('pageSize', TType.I32, 3)
      oprot.writeI32(self.pageSize)
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