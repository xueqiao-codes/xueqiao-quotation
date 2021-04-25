#
# -*- coding: utf-8 -*-
# Autogenerated by Thrift Compiler (0.9.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

import sys
import socket
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from thrift.transport.TTransport import TTransportException
from comm.ttypes import *
from page.ttypes import *
from xueqiao.quotation.account.thriftapi.ttypes import *
from xueqiao.quotation.plan.bo.ttypes import *
from xueqiao.quotation.plan.bo import QuotationPlanBo

PYTHON_SUPPORT_DIR='/usr/local/soldier/route_agent'
if not (PYTHON_SUPPORT_DIR in sys.path):
  sys.path.append(PYTHON_SUPPORT_DIR)
from route_finder_python import *

class QuotationPlanBoStub:
  def __init__(self):
    self.__peerAddr = None

  def setPeerAddr(self, peerAddr):
    self.__peerAddr = peerAddr

  def __getServiceAddr(self, methodName='', routeKey=0):
    if self.__peerAddr != None and self.__peerAddr !='':
      return self.__peerAddr
    return route_finder.GetRouteIp(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, methodName, routeKey)

  def startGenPreviewSCClasses(self, routeKey, timeout, ):
    platformArgs=PlatformArgs()
    frame = sys._getframe(1)
    code = frame.f_code
    platformArgs.sourceDesc = code.co_filename + '[' + code.co_name + ":" + str(code.co_firstlineno) + ']'
    platformArgs.sourceIp = socket.gethostbyname(socket.getfqdn())
    platformArgs.remoteAddress = self.__getServiceAddr('startGenPreviewSCClasses', routeKey)
    if platformArgs.remoteAddress == None or platformArgs.remoteAddress == '':
      raise Exception('No RouteIp is Found')
    platformArgs.remotePort = 10000 + QuotationPlanBo.QuotationPlanBo_SERVICE_KEY

    transport_socket = TSocket.TSocket(platformArgs.remoteAddress, platformArgs.remotePort)

    transport_socket.setTimeout(timeout)
    transport = TTransport.TFramedTransport(transport_socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client=QuotationPlanBo.Client(protocol)
    try:
      transport.open()
      result = client.startGenPreviewSCClasses(platformArgs,)
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'startGenPreviewSCClasses', platformArgs.remoteAddress, 0)
      return result
    except TTransportException,t:
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'startGenPreviewSCClasses', platformArgs.remoteAddress, 1)
      raise t
    finally:
      transport.close()

  def getGenPreviewState(self, routeKey, timeout, ):
    platformArgs=PlatformArgs()
    frame = sys._getframe(1)
    code = frame.f_code
    platformArgs.sourceDesc = code.co_filename + '[' + code.co_name + ":" + str(code.co_firstlineno) + ']'
    platformArgs.sourceIp = socket.gethostbyname(socket.getfqdn())
    platformArgs.remoteAddress = self.__getServiceAddr('getGenPreviewState', routeKey)
    if platformArgs.remoteAddress == None or platformArgs.remoteAddress == '':
      raise Exception('No RouteIp is Found')
    platformArgs.remotePort = 10000 + QuotationPlanBo.QuotationPlanBo_SERVICE_KEY

    transport_socket = TSocket.TSocket(platformArgs.remoteAddress, platformArgs.remotePort)

    transport_socket.setTimeout(timeout)
    transport = TTransport.TFramedTransport(transport_socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client=QuotationPlanBo.Client(protocol)
    try:
      transport.open()
      result = client.getGenPreviewState(platformArgs,)
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'getGenPreviewState', platformArgs.remoteAddress, 0)
      return result
    except TTransportException,t:
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'getGenPreviewState', platformArgs.remoteAddress, 1)
      raise t
    finally:
      transport.close()

  def commitPreviewSCClasses(self, routeKey, timeout, ):
    platformArgs=PlatformArgs()
    frame = sys._getframe(1)
    code = frame.f_code
    platformArgs.sourceDesc = code.co_filename + '[' + code.co_name + ":" + str(code.co_firstlineno) + ']'
    platformArgs.sourceIp = socket.gethostbyname(socket.getfqdn())
    platformArgs.remoteAddress = self.__getServiceAddr('commitPreviewSCClasses', routeKey)
    if platformArgs.remoteAddress == None or platformArgs.remoteAddress == '':
      raise Exception('No RouteIp is Found')
    platformArgs.remotePort = 10000 + QuotationPlanBo.QuotationPlanBo_SERVICE_KEY

    transport_socket = TSocket.TSocket(platformArgs.remoteAddress, platformArgs.remotePort)

    transport_socket.setTimeout(timeout)
    transport = TTransport.TFramedTransport(transport_socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client=QuotationPlanBo.Client(protocol)
    try:
      transport.open()
      result = client.commitPreviewSCClasses(platformArgs,)
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'commitPreviewSCClasses', platformArgs.remoteAddress, 0)
      return result
    except TTransportException,t:
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'commitPreviewSCClasses', platformArgs.remoteAddress, 1)
      raise t
    finally:
      transport.close()

  def querySubscribeContractItemPage(self, routeKey, timeout, queryOption,pageOption,):
    platformArgs=PlatformArgs()
    frame = sys._getframe(1)
    code = frame.f_code
    platformArgs.sourceDesc = code.co_filename + '[' + code.co_name + ":" + str(code.co_firstlineno) + ']'
    platformArgs.sourceIp = socket.gethostbyname(socket.getfqdn())
    platformArgs.remoteAddress = self.__getServiceAddr('querySubscribeContractItemPage', routeKey)
    if platformArgs.remoteAddress == None or platformArgs.remoteAddress == '':
      raise Exception('No RouteIp is Found')
    platformArgs.remotePort = 10000 + QuotationPlanBo.QuotationPlanBo_SERVICE_KEY

    transport_socket = TSocket.TSocket(platformArgs.remoteAddress, platformArgs.remotePort)

    transport_socket.setTimeout(timeout)
    transport = TTransport.TFramedTransport(transport_socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client=QuotationPlanBo.Client(protocol)
    try:
      transport.open()
      result = client.querySubscribeContractItemPage(platformArgs,queryOption,pageOption,)
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'querySubscribeContractItemPage', platformArgs.remoteAddress, 0)
      return result
    except TTransportException,t:
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'querySubscribeContractItemPage', platformArgs.remoteAddress, 1)
      raise t
    finally:
      transport.close()

  def getCurrentSCClasses(self, routeKey, timeout, ):
    platformArgs=PlatformArgs()
    frame = sys._getframe(1)
    code = frame.f_code
    platformArgs.sourceDesc = code.co_filename + '[' + code.co_name + ":" + str(code.co_firstlineno) + ']'
    platformArgs.sourceIp = socket.gethostbyname(socket.getfqdn())
    platformArgs.remoteAddress = self.__getServiceAddr('getCurrentSCClasses', routeKey)
    if platformArgs.remoteAddress == None or platformArgs.remoteAddress == '':
      raise Exception('No RouteIp is Found')
    platformArgs.remotePort = 10000 + QuotationPlanBo.QuotationPlanBo_SERVICE_KEY

    transport_socket = TSocket.TSocket(platformArgs.remoteAddress, platformArgs.remotePort)

    transport_socket.setTimeout(timeout)
    transport = TTransport.TFramedTransport(transport_socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client=QuotationPlanBo.Client(protocol)
    try:
      transport.open()
      result = client.getCurrentSCClasses(platformArgs,)
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'getCurrentSCClasses', platformArgs.remoteAddress, 0)
      return result
    except TTransportException,t:
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'getCurrentSCClasses', platformArgs.remoteAddress, 1)
      raise t
    finally:
      transport.close()

  def getPreviewSCClasses(self, routeKey, timeout, ):
    platformArgs=PlatformArgs()
    frame = sys._getframe(1)
    code = frame.f_code
    platformArgs.sourceDesc = code.co_filename + '[' + code.co_name + ":" + str(code.co_firstlineno) + ']'
    platformArgs.sourceIp = socket.gethostbyname(socket.getfqdn())
    platformArgs.remoteAddress = self.__getServiceAddr('getPreviewSCClasses', routeKey)
    if platformArgs.remoteAddress == None or platformArgs.remoteAddress == '':
      raise Exception('No RouteIp is Found')
    platformArgs.remotePort = 10000 + QuotationPlanBo.QuotationPlanBo_SERVICE_KEY

    transport_socket = TSocket.TSocket(platformArgs.remoteAddress, platformArgs.remotePort)

    transport_socket.setTimeout(timeout)
    transport = TTransport.TFramedTransport(transport_socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client=QuotationPlanBo.Client(protocol)
    try:
      transport.open()
      result = client.getPreviewSCClasses(platformArgs,)
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'getPreviewSCClasses', platformArgs.remoteAddress, 0)
      return result
    except TTransportException,t:
      route_finder.UpdateCallInfo(QuotationPlanBo.QuotationPlanBo_SERVICE_KEY, 'getPreviewSCClasses', platformArgs.remoteAddress, 1)
      raise t
    finally:
      transport.close()
