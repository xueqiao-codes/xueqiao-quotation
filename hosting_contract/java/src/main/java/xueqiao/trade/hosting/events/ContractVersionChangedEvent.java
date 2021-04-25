/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.trade.hosting.events;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 合约版本变更事件
 */
public class ContractVersionChangedEvent implements org.apache.thrift.TBase<ContractVersionChangedEvent, ContractVersionChangedEvent._Fields>, java.io.Serializable, Cloneable, Comparable<ContractVersionChangedEvent> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ContractVersionChangedEvent");

  private static final org.apache.thrift.protocol.TField EVENT_TIMESTAMP_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("eventTimestampMs", org.apache.thrift.protocol.TType.I64, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ContractVersionChangedEventStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ContractVersionChangedEventTupleSchemeFactory());
  }

  public long eventTimestampMs; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EVENT_TIMESTAMP_MS((short)1, "eventTimestampMs");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // EVENT_TIMESTAMP_MS
          return EVENT_TIMESTAMP_MS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __EVENTTIMESTAMPMS_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.EVENT_TIMESTAMP_MS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EVENT_TIMESTAMP_MS, new org.apache.thrift.meta_data.FieldMetaData("eventTimestampMs", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ContractVersionChangedEvent.class, metaDataMap);
  }

  public ContractVersionChangedEvent() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ContractVersionChangedEvent(ContractVersionChangedEvent other) {
    __isset_bitfield = other.__isset_bitfield;
    this.eventTimestampMs = other.eventTimestampMs;
  }

  public ContractVersionChangedEvent deepCopy() {
    return new ContractVersionChangedEvent(this);
  }

  @Override
  public void clear() {
    setEventTimestampMsIsSet(false);
    this.eventTimestampMs = 0;
  }

  public long getEventTimestampMs() {
    return this.eventTimestampMs;
  }

  public ContractVersionChangedEvent setEventTimestampMs(long eventTimestampMs) {
    this.eventTimestampMs = eventTimestampMs;
    setEventTimestampMsIsSet(true);
    return this;
  }

  public void unsetEventTimestampMs() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EVENTTIMESTAMPMS_ISSET_ID);
  }

  /** Returns true if field eventTimestampMs is set (has been assigned a value) and false otherwise */
  public boolean isSetEventTimestampMs() {
    return EncodingUtils.testBit(__isset_bitfield, __EVENTTIMESTAMPMS_ISSET_ID);
  }

  public void setEventTimestampMsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EVENTTIMESTAMPMS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EVENT_TIMESTAMP_MS:
      if (value == null) {
        unsetEventTimestampMs();
      } else {
        setEventTimestampMs((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EVENT_TIMESTAMP_MS:
      return Long.valueOf(getEventTimestampMs());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EVENT_TIMESTAMP_MS:
      return isSetEventTimestampMs();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ContractVersionChangedEvent)
      return this.equals((ContractVersionChangedEvent)that);
    return false;
  }

  public boolean equals(ContractVersionChangedEvent that) {
    if (that == null)
      return false;

    boolean this_present_eventTimestampMs = true && this.isSetEventTimestampMs();
    boolean that_present_eventTimestampMs = true && that.isSetEventTimestampMs();
    if (this_present_eventTimestampMs || that_present_eventTimestampMs) {
      if (!(this_present_eventTimestampMs && that_present_eventTimestampMs))
        return false;
      if (this.eventTimestampMs != that.eventTimestampMs)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ContractVersionChangedEvent other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetEventTimestampMs()).compareTo(other.isSetEventTimestampMs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEventTimestampMs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventTimestampMs, other.eventTimestampMs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ContractVersionChangedEvent(");
    boolean first = true;

    if (isSetEventTimestampMs()) {
      sb.append("eventTimestampMs:");
      sb.append(this.eventTimestampMs);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ContractVersionChangedEventStandardSchemeFactory implements SchemeFactory {
    public ContractVersionChangedEventStandardScheme getScheme() {
      return new ContractVersionChangedEventStandardScheme();
    }
  }

  private static class ContractVersionChangedEventStandardScheme extends StandardScheme<ContractVersionChangedEvent> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ContractVersionChangedEvent struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EVENT_TIMESTAMP_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.eventTimestampMs = iprot.readI64();
              struct.setEventTimestampMsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ContractVersionChangedEvent struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetEventTimestampMs()) {
        oprot.writeFieldBegin(EVENT_TIMESTAMP_MS_FIELD_DESC);
        oprot.writeI64(struct.eventTimestampMs);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ContractVersionChangedEventTupleSchemeFactory implements SchemeFactory {
    public ContractVersionChangedEventTupleScheme getScheme() {
      return new ContractVersionChangedEventTupleScheme();
    }
  }

  private static class ContractVersionChangedEventTupleScheme extends TupleScheme<ContractVersionChangedEvent> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ContractVersionChangedEvent struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetEventTimestampMs()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetEventTimestampMs()) {
        oprot.writeI64(struct.eventTimestampMs);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ContractVersionChangedEvent struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.eventTimestampMs = iprot.readI64();
        struct.setEventTimestampMsIsSet(true);
      }
    }
  }

}
