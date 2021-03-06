#
# -*- coding: utf-8 -*-
# Autogenerated by Thrift Compiler (0.9.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TException, TApplicationException
from ttypes import *
from thrift.Thrift import TProcessor
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol, TProtocol
try:
  from thrift.protocol import fastbinary
except:
  fastbinary = None


QuotationPlanBo_SERVICE_KEY=888
class Iface:
  def startGenPreviewSCClasses(self, platformArgs):
    """
    启动生成预览类任务

    Parameters:
     - platformArgs
    """
    pass

  def getGenPreviewState(self, platformArgs):
    """
    获取当前生成预览类任务状态

    Parameters:
     - platformArgs
    """
    pass

  def commitPreviewSCClasses(self, platformArgs):
    """
    提交预览商品订阅类

    Parameters:
     - platformArgs
    """
    pass

  def querySubscribeContractItemPage(self, platformArgs, queryOption, pageOption):
    """
    查询当前订阅合约列表项

    Parameters:
     - platformArgs
     - queryOption
     - pageOption
    """
    pass

  def getCurrentSCClasses(self, platformArgs):
    """
    获取当前正在使用的规划类

    Parameters:
     - platformArgs
    """
    pass

  def getPreviewSCClasses(self, platformArgs):
    """
    获取生成的预览规划类

    Parameters:
     - platformArgs
    """
    pass


class Client(Iface):
  def __init__(self, iprot, oprot=None):
    self._iprot = self._oprot = iprot
    if oprot is not None:
      self._oprot = oprot
    self._seqid = 0

  def startGenPreviewSCClasses(self, platformArgs):
    """
    启动生成预览类任务

    Parameters:
     - platformArgs
    """
    self.send_startGenPreviewSCClasses(platformArgs)
    return self.recv_startGenPreviewSCClasses()

  def send_startGenPreviewSCClasses(self, platformArgs):
    self._oprot.writeMessageBegin('startGenPreviewSCClasses', TMessageType.CALL, self._seqid)
    args = startGenPreviewSCClasses_args()
    args.platformArgs = platformArgs
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_startGenPreviewSCClasses(self):
    (fname, mtype, rseqid) = self._iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(self._iprot)
      self._iprot.readMessageEnd()
      raise x
    result = startGenPreviewSCClasses_result()
    result.read(self._iprot)
    self._iprot.readMessageEnd()
    if result.success is not None:
      return result.success
    if result.err is not None:
      raise result.err
    raise TApplicationException(TApplicationException.MISSING_RESULT, "startGenPreviewSCClasses failed: unknown result");

  def getGenPreviewState(self, platformArgs):
    """
    获取当前生成预览类任务状态

    Parameters:
     - platformArgs
    """
    self.send_getGenPreviewState(platformArgs)
    return self.recv_getGenPreviewState()

  def send_getGenPreviewState(self, platformArgs):
    self._oprot.writeMessageBegin('getGenPreviewState', TMessageType.CALL, self._seqid)
    args = getGenPreviewState_args()
    args.platformArgs = platformArgs
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_getGenPreviewState(self):
    (fname, mtype, rseqid) = self._iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(self._iprot)
      self._iprot.readMessageEnd()
      raise x
    result = getGenPreviewState_result()
    result.read(self._iprot)
    self._iprot.readMessageEnd()
    if result.success is not None:
      return result.success
    if result.err is not None:
      raise result.err
    raise TApplicationException(TApplicationException.MISSING_RESULT, "getGenPreviewState failed: unknown result");

  def commitPreviewSCClasses(self, platformArgs):
    """
    提交预览商品订阅类

    Parameters:
     - platformArgs
    """
    self.send_commitPreviewSCClasses(platformArgs)
    self.recv_commitPreviewSCClasses()

  def send_commitPreviewSCClasses(self, platformArgs):
    self._oprot.writeMessageBegin('commitPreviewSCClasses', TMessageType.CALL, self._seqid)
    args = commitPreviewSCClasses_args()
    args.platformArgs = platformArgs
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_commitPreviewSCClasses(self):
    (fname, mtype, rseqid) = self._iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(self._iprot)
      self._iprot.readMessageEnd()
      raise x
    result = commitPreviewSCClasses_result()
    result.read(self._iprot)
    self._iprot.readMessageEnd()
    if result.err is not None:
      raise result.err
    return

  def querySubscribeContractItemPage(self, platformArgs, queryOption, pageOption):
    """
    查询当前订阅合约列表项

    Parameters:
     - platformArgs
     - queryOption
     - pageOption
    """
    self.send_querySubscribeContractItemPage(platformArgs, queryOption, pageOption)
    return self.recv_querySubscribeContractItemPage()

  def send_querySubscribeContractItemPage(self, platformArgs, queryOption, pageOption):
    self._oprot.writeMessageBegin('querySubscribeContractItemPage', TMessageType.CALL, self._seqid)
    args = querySubscribeContractItemPage_args()
    args.platformArgs = platformArgs
    args.queryOption = queryOption
    args.pageOption = pageOption
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_querySubscribeContractItemPage(self):
    (fname, mtype, rseqid) = self._iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(self._iprot)
      self._iprot.readMessageEnd()
      raise x
    result = querySubscribeContractItemPage_result()
    result.read(self._iprot)
    self._iprot.readMessageEnd()
    if result.success is not None:
      return result.success
    if result.err is not None:
      raise result.err
    raise TApplicationException(TApplicationException.MISSING_RESULT, "querySubscribeContractItemPage failed: unknown result");

  def getCurrentSCClasses(self, platformArgs):
    """
    获取当前正在使用的规划类

    Parameters:
     - platformArgs
    """
    self.send_getCurrentSCClasses(platformArgs)
    return self.recv_getCurrentSCClasses()

  def send_getCurrentSCClasses(self, platformArgs):
    self._oprot.writeMessageBegin('getCurrentSCClasses', TMessageType.CALL, self._seqid)
    args = getCurrentSCClasses_args()
    args.platformArgs = platformArgs
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_getCurrentSCClasses(self):
    (fname, mtype, rseqid) = self._iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(self._iprot)
      self._iprot.readMessageEnd()
      raise x
    result = getCurrentSCClasses_result()
    result.read(self._iprot)
    self._iprot.readMessageEnd()
    if result.success is not None:
      return result.success
    if result.err is not None:
      raise result.err
    raise TApplicationException(TApplicationException.MISSING_RESULT, "getCurrentSCClasses failed: unknown result");

  def getPreviewSCClasses(self, platformArgs):
    """
    获取生成的预览规划类

    Parameters:
     - platformArgs
    """
    self.send_getPreviewSCClasses(platformArgs)
    return self.recv_getPreviewSCClasses()

  def send_getPreviewSCClasses(self, platformArgs):
    self._oprot.writeMessageBegin('getPreviewSCClasses', TMessageType.CALL, self._seqid)
    args = getPreviewSCClasses_args()
    args.platformArgs = platformArgs
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_getPreviewSCClasses(self):
    (fname, mtype, rseqid) = self._iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(self._iprot)
      self._iprot.readMessageEnd()
      raise x
    result = getPreviewSCClasses_result()
    result.read(self._iprot)
    self._iprot.readMessageEnd()
    if result.success is not None:
      return result.success
    if result.err is not None:
      raise result.err
    raise TApplicationException(TApplicationException.MISSING_RESULT, "getPreviewSCClasses failed: unknown result");


class Processor(Iface, TProcessor):
  def __init__(self, handler):
    self._handler = handler
    self._processMap = {}
    self._processMap["startGenPreviewSCClasses"] = Processor.process_startGenPreviewSCClasses
    self._processMap["getGenPreviewState"] = Processor.process_getGenPreviewState
    self._processMap["commitPreviewSCClasses"] = Processor.process_commitPreviewSCClasses
    self._processMap["querySubscribeContractItemPage"] = Processor.process_querySubscribeContractItemPage
    self._processMap["getCurrentSCClasses"] = Processor.process_getCurrentSCClasses
    self._processMap["getPreviewSCClasses"] = Processor.process_getPreviewSCClasses

  def process(self, iprot, oprot):
    (name, type, seqid) = iprot.readMessageBegin()
    if name not in self._processMap:
      iprot.skip(TType.STRUCT)
      iprot.readMessageEnd()
      x = TApplicationException(TApplicationException.UNKNOWN_METHOD, 'Unknown function %s' % (name))
      oprot.writeMessageBegin(name, TMessageType.EXCEPTION, seqid)
      x.write(oprot)
      oprot.writeMessageEnd()
      oprot.trans.flush()
      return
    else:
      self._processMap[name](self, seqid, iprot, oprot)
    return True

  def process_startGenPreviewSCClasses(self, seqid, iprot, oprot):
    args = startGenPreviewSCClasses_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = startGenPreviewSCClasses_result()
    try:
      result.success = self._handler.startGenPreviewSCClasses(args.platformArgs)
    except comm.ttypes.ErrorInfo, err:
      result.err = err
    oprot.writeMessageBegin("startGenPreviewSCClasses", TMessageType.REPLY, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()

  def process_getGenPreviewState(self, seqid, iprot, oprot):
    args = getGenPreviewState_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = getGenPreviewState_result()
    try:
      result.success = self._handler.getGenPreviewState(args.platformArgs)
    except comm.ttypes.ErrorInfo, err:
      result.err = err
    oprot.writeMessageBegin("getGenPreviewState", TMessageType.REPLY, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()

  def process_commitPreviewSCClasses(self, seqid, iprot, oprot):
    args = commitPreviewSCClasses_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = commitPreviewSCClasses_result()
    try:
      self._handler.commitPreviewSCClasses(args.platformArgs)
    except comm.ttypes.ErrorInfo, err:
      result.err = err
    oprot.writeMessageBegin("commitPreviewSCClasses", TMessageType.REPLY, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()

  def process_querySubscribeContractItemPage(self, seqid, iprot, oprot):
    args = querySubscribeContractItemPage_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = querySubscribeContractItemPage_result()
    try:
      result.success = self._handler.querySubscribeContractItemPage(args.platformArgs, args.queryOption, args.pageOption)
    except comm.ttypes.ErrorInfo, err:
      result.err = err
    oprot.writeMessageBegin("querySubscribeContractItemPage", TMessageType.REPLY, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()

  def process_getCurrentSCClasses(self, seqid, iprot, oprot):
    args = getCurrentSCClasses_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = getCurrentSCClasses_result()
    try:
      result.success = self._handler.getCurrentSCClasses(args.platformArgs)
    except comm.ttypes.ErrorInfo, err:
      result.err = err
    oprot.writeMessageBegin("getCurrentSCClasses", TMessageType.REPLY, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()

  def process_getPreviewSCClasses(self, seqid, iprot, oprot):
    args = getPreviewSCClasses_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = getPreviewSCClasses_result()
    try:
      result.success = self._handler.getPreviewSCClasses(args.platformArgs)
    except comm.ttypes.ErrorInfo, err:
      result.err = err
    oprot.writeMessageBegin("getPreviewSCClasses", TMessageType.REPLY, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()


# HELPER FUNCTIONS AND STRUCTURES

class startGenPreviewSCClasses_args:
  """
  Attributes:
   - platformArgs
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'platformArgs', (comm.ttypes.PlatformArgs, comm.ttypes.PlatformArgs.thrift_spec), None, ), # 1
  )

  def __init__(self, platformArgs=None,):
    self.platformArgs = platformArgs

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
          self.platformArgs = comm.ttypes.PlatformArgs()
          self.platformArgs.read(iprot)
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
    oprot.writeStructBegin('startGenPreviewSCClasses_args')
    if self.platformArgs is not None:
      oprot.writeFieldBegin('platformArgs', TType.STRUCT, 1)
      self.platformArgs.write(oprot)
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

class startGenPreviewSCClasses_result:
  """
  Attributes:
   - success
   - err
  """

  thrift_spec = (
    (0, TType.STRUCT, 'success', (GenPreviewState, GenPreviewState.thrift_spec), None, ), # 0
    (1, TType.STRUCT, 'err', (comm.ttypes.ErrorInfo, comm.ttypes.ErrorInfo.thrift_spec), None, ), # 1
  )

  def __init__(self, success=None, err=None,):
    self.success = success
    self.err = err

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 0:
        if ftype == TType.STRUCT:
          self.success = GenPreviewState()
          self.success.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 1:
        if ftype == TType.STRUCT:
          self.err = comm.ttypes.ErrorInfo()
          self.err.read(iprot)
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
    oprot.writeStructBegin('startGenPreviewSCClasses_result')
    if self.success is not None:
      oprot.writeFieldBegin('success', TType.STRUCT, 0)
      self.success.write(oprot)
      oprot.writeFieldEnd()
    if self.err is not None:
      oprot.writeFieldBegin('err', TType.STRUCT, 1)
      self.err.write(oprot)
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

class getGenPreviewState_args:
  """
  Attributes:
   - platformArgs
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'platformArgs', (comm.ttypes.PlatformArgs, comm.ttypes.PlatformArgs.thrift_spec), None, ), # 1
  )

  def __init__(self, platformArgs=None,):
    self.platformArgs = platformArgs

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
          self.platformArgs = comm.ttypes.PlatformArgs()
          self.platformArgs.read(iprot)
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
    oprot.writeStructBegin('getGenPreviewState_args')
    if self.platformArgs is not None:
      oprot.writeFieldBegin('platformArgs', TType.STRUCT, 1)
      self.platformArgs.write(oprot)
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

class getGenPreviewState_result:
  """
  Attributes:
   - success
   - err
  """

  thrift_spec = (
    (0, TType.STRUCT, 'success', (GenPreviewState, GenPreviewState.thrift_spec), None, ), # 0
    (1, TType.STRUCT, 'err', (comm.ttypes.ErrorInfo, comm.ttypes.ErrorInfo.thrift_spec), None, ), # 1
  )

  def __init__(self, success=None, err=None,):
    self.success = success
    self.err = err

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 0:
        if ftype == TType.STRUCT:
          self.success = GenPreviewState()
          self.success.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 1:
        if ftype == TType.STRUCT:
          self.err = comm.ttypes.ErrorInfo()
          self.err.read(iprot)
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
    oprot.writeStructBegin('getGenPreviewState_result')
    if self.success is not None:
      oprot.writeFieldBegin('success', TType.STRUCT, 0)
      self.success.write(oprot)
      oprot.writeFieldEnd()
    if self.err is not None:
      oprot.writeFieldBegin('err', TType.STRUCT, 1)
      self.err.write(oprot)
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

class commitPreviewSCClasses_args:
  """
  Attributes:
   - platformArgs
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'platformArgs', (comm.ttypes.PlatformArgs, comm.ttypes.PlatformArgs.thrift_spec), None, ), # 1
  )

  def __init__(self, platformArgs=None,):
    self.platformArgs = platformArgs

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
          self.platformArgs = comm.ttypes.PlatformArgs()
          self.platformArgs.read(iprot)
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
    oprot.writeStructBegin('commitPreviewSCClasses_args')
    if self.platformArgs is not None:
      oprot.writeFieldBegin('platformArgs', TType.STRUCT, 1)
      self.platformArgs.write(oprot)
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

class commitPreviewSCClasses_result:
  """
  Attributes:
   - err
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'err', (comm.ttypes.ErrorInfo, comm.ttypes.ErrorInfo.thrift_spec), None, ), # 1
  )

  def __init__(self, err=None,):
    self.err = err

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
          self.err = comm.ttypes.ErrorInfo()
          self.err.read(iprot)
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
    oprot.writeStructBegin('commitPreviewSCClasses_result')
    if self.err is not None:
      oprot.writeFieldBegin('err', TType.STRUCT, 1)
      self.err.write(oprot)
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

class querySubscribeContractItemPage_args:
  """
  Attributes:
   - platformArgs
   - queryOption
   - pageOption
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'platformArgs', (comm.ttypes.PlatformArgs, comm.ttypes.PlatformArgs.thrift_spec), None, ), # 1
    (2, TType.STRUCT, 'queryOption', (QuerySubscribeContractItemOption, QuerySubscribeContractItemOption.thrift_spec), None, ), # 2
    (3, TType.STRUCT, 'pageOption', (page.ttypes.IndexedPageOption, page.ttypes.IndexedPageOption.thrift_spec), None, ), # 3
  )

  def __init__(self, platformArgs=None, queryOption=None, pageOption=None,):
    self.platformArgs = platformArgs
    self.queryOption = queryOption
    self.pageOption = pageOption

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
          self.platformArgs = comm.ttypes.PlatformArgs()
          self.platformArgs.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRUCT:
          self.queryOption = QuerySubscribeContractItemOption()
          self.queryOption.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.STRUCT:
          self.pageOption = page.ttypes.IndexedPageOption()
          self.pageOption.read(iprot)
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
    oprot.writeStructBegin('querySubscribeContractItemPage_args')
    if self.platformArgs is not None:
      oprot.writeFieldBegin('platformArgs', TType.STRUCT, 1)
      self.platformArgs.write(oprot)
      oprot.writeFieldEnd()
    if self.queryOption is not None:
      oprot.writeFieldBegin('queryOption', TType.STRUCT, 2)
      self.queryOption.write(oprot)
      oprot.writeFieldEnd()
    if self.pageOption is not None:
      oprot.writeFieldBegin('pageOption', TType.STRUCT, 3)
      self.pageOption.write(oprot)
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

class querySubscribeContractItemPage_result:
  """
  Attributes:
   - success
   - err
  """

  thrift_spec = (
    (0, TType.STRUCT, 'success', (SubscribeContractItemPage, SubscribeContractItemPage.thrift_spec), None, ), # 0
    (1, TType.STRUCT, 'err', (comm.ttypes.ErrorInfo, comm.ttypes.ErrorInfo.thrift_spec), None, ), # 1
  )

  def __init__(self, success=None, err=None,):
    self.success = success
    self.err = err

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 0:
        if ftype == TType.STRUCT:
          self.success = SubscribeContractItemPage()
          self.success.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 1:
        if ftype == TType.STRUCT:
          self.err = comm.ttypes.ErrorInfo()
          self.err.read(iprot)
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
    oprot.writeStructBegin('querySubscribeContractItemPage_result')
    if self.success is not None:
      oprot.writeFieldBegin('success', TType.STRUCT, 0)
      self.success.write(oprot)
      oprot.writeFieldEnd()
    if self.err is not None:
      oprot.writeFieldBegin('err', TType.STRUCT, 1)
      self.err.write(oprot)
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

class getCurrentSCClasses_args:
  """
  Attributes:
   - platformArgs
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'platformArgs', (comm.ttypes.PlatformArgs, comm.ttypes.PlatformArgs.thrift_spec), None, ), # 1
  )

  def __init__(self, platformArgs=None,):
    self.platformArgs = platformArgs

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
          self.platformArgs = comm.ttypes.PlatformArgs()
          self.platformArgs.read(iprot)
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
    oprot.writeStructBegin('getCurrentSCClasses_args')
    if self.platformArgs is not None:
      oprot.writeFieldBegin('platformArgs', TType.STRUCT, 1)
      self.platformArgs.write(oprot)
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

class getCurrentSCClasses_result:
  """
  Attributes:
   - success
   - err
  """

  thrift_spec = (
    (0, TType.LIST, 'success', (TType.STRUCT,(SubscribeCommodityClass, SubscribeCommodityClass.thrift_spec)), None, ), # 0
    (1, TType.STRUCT, 'err', (comm.ttypes.ErrorInfo, comm.ttypes.ErrorInfo.thrift_spec), None, ), # 1
  )

  def __init__(self, success=None, err=None,):
    self.success = success
    self.err = err

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 0:
        if ftype == TType.LIST:
          self.success = []
          (_etype38, _size35) = iprot.readListBegin()
          for _i39 in xrange(_size35):
            _elem40 = SubscribeCommodityClass()
            _elem40.read(iprot)
            self.success.append(_elem40)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 1:
        if ftype == TType.STRUCT:
          self.err = comm.ttypes.ErrorInfo()
          self.err.read(iprot)
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
    oprot.writeStructBegin('getCurrentSCClasses_result')
    if self.success is not None:
      oprot.writeFieldBegin('success', TType.LIST, 0)
      oprot.writeListBegin(TType.STRUCT, len(self.success))
      for iter41 in self.success:
        iter41.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.err is not None:
      oprot.writeFieldBegin('err', TType.STRUCT, 1)
      self.err.write(oprot)
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

class getPreviewSCClasses_args:
  """
  Attributes:
   - platformArgs
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRUCT, 'platformArgs', (comm.ttypes.PlatformArgs, comm.ttypes.PlatformArgs.thrift_spec), None, ), # 1
  )

  def __init__(self, platformArgs=None,):
    self.platformArgs = platformArgs

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
          self.platformArgs = comm.ttypes.PlatformArgs()
          self.platformArgs.read(iprot)
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
    oprot.writeStructBegin('getPreviewSCClasses_args')
    if self.platformArgs is not None:
      oprot.writeFieldBegin('platformArgs', TType.STRUCT, 1)
      self.platformArgs.write(oprot)
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

class getPreviewSCClasses_result:
  """
  Attributes:
   - success
   - err
  """

  thrift_spec = (
    (0, TType.LIST, 'success', (TType.STRUCT,(SubscribeCommodityClass, SubscribeCommodityClass.thrift_spec)), None, ), # 0
    (1, TType.STRUCT, 'err', (comm.ttypes.ErrorInfo, comm.ttypes.ErrorInfo.thrift_spec), None, ), # 1
  )

  def __init__(self, success=None, err=None,):
    self.success = success
    self.err = err

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 0:
        if ftype == TType.LIST:
          self.success = []
          (_etype45, _size42) = iprot.readListBegin()
          for _i46 in xrange(_size42):
            _elem47 = SubscribeCommodityClass()
            _elem47.read(iprot)
            self.success.append(_elem47)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 1:
        if ftype == TType.STRUCT:
          self.err = comm.ttypes.ErrorInfo()
          self.err.read(iprot)
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
    oprot.writeStructBegin('getPreviewSCClasses_result')
    if self.success is not None:
      oprot.writeFieldBegin('success', TType.LIST, 0)
      oprot.writeListBegin(TType.STRUCT, len(self.success))
      for iter48 in self.success:
        iter48.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.err is not None:
      oprot.writeFieldBegin('err', TType.STRUCT, 1)
      self.err.write(oprot)
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
