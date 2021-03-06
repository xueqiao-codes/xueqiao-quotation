/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.quotation.plan.bo;

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
 * 订阅账号类
 */
public class SubscribeAccountClass implements org.apache.thrift.TBase<SubscribeAccountClass, SubscribeAccountClass._Fields>, java.io.Serializable, Cloneable, Comparable<SubscribeAccountClass> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SubscribeAccountClass");

  private static final org.apache.thrift.protocol.TField SUBSCRIBE_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("subscribeNum", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField QUOTATION_ACCOUNT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("quotationAccountId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField QUOTATION_DEPLOY_SET_FIELD_DESC = new org.apache.thrift.protocol.TField("quotationDeploySet", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SubscribeAccountClassStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SubscribeAccountClassTupleSchemeFactory());
  }

  public int subscribeNum; // optional
  public long quotationAccountId; // optional
  /**
   * 
   * @see xueqiao.quotation.account.thriftapi.DeploySet
   */
  public xueqiao.quotation.account.thriftapi.DeploySet quotationDeploySet; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SUBSCRIBE_NUM((short)1, "subscribeNum"),
    QUOTATION_ACCOUNT_ID((short)2, "quotationAccountId"),
    /**
     * 
     * @see xueqiao.quotation.account.thriftapi.DeploySet
     */
    QUOTATION_DEPLOY_SET((short)3, "quotationDeploySet");

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
        case 1: // SUBSCRIBE_NUM
          return SUBSCRIBE_NUM;
        case 2: // QUOTATION_ACCOUNT_ID
          return QUOTATION_ACCOUNT_ID;
        case 3: // QUOTATION_DEPLOY_SET
          return QUOTATION_DEPLOY_SET;
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
  private static final int __SUBSCRIBENUM_ISSET_ID = 0;
  private static final int __QUOTATIONACCOUNTID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.SUBSCRIBE_NUM,_Fields.QUOTATION_ACCOUNT_ID,_Fields.QUOTATION_DEPLOY_SET};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SUBSCRIBE_NUM, new org.apache.thrift.meta_data.FieldMetaData("subscribeNum", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.QUOTATION_ACCOUNT_ID, new org.apache.thrift.meta_data.FieldMetaData("quotationAccountId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.QUOTATION_DEPLOY_SET, new org.apache.thrift.meta_data.FieldMetaData("quotationDeploySet", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, xueqiao.quotation.account.thriftapi.DeploySet.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SubscribeAccountClass.class, metaDataMap);
  }

  public SubscribeAccountClass() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SubscribeAccountClass(SubscribeAccountClass other) {
    __isset_bitfield = other.__isset_bitfield;
    this.subscribeNum = other.subscribeNum;
    this.quotationAccountId = other.quotationAccountId;
    if (other.isSetQuotationDeploySet()) {
      this.quotationDeploySet = other.quotationDeploySet;
    }
  }

  public SubscribeAccountClass deepCopy() {
    return new SubscribeAccountClass(this);
  }

  @Override
  public void clear() {
    setSubscribeNumIsSet(false);
    this.subscribeNum = 0;
    setQuotationAccountIdIsSet(false);
    this.quotationAccountId = 0;
    this.quotationDeploySet = null;
  }

  public int getSubscribeNum() {
    return this.subscribeNum;
  }

  public SubscribeAccountClass setSubscribeNum(int subscribeNum) {
    this.subscribeNum = subscribeNum;
    setSubscribeNumIsSet(true);
    return this;
  }

  public void unsetSubscribeNum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SUBSCRIBENUM_ISSET_ID);
  }

  /** Returns true if field subscribeNum is set (has been assigned a value) and false otherwise */
  public boolean isSetSubscribeNum() {
    return EncodingUtils.testBit(__isset_bitfield, __SUBSCRIBENUM_ISSET_ID);
  }

  public void setSubscribeNumIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SUBSCRIBENUM_ISSET_ID, value);
  }

  public long getQuotationAccountId() {
    return this.quotationAccountId;
  }

  public SubscribeAccountClass setQuotationAccountId(long quotationAccountId) {
    this.quotationAccountId = quotationAccountId;
    setQuotationAccountIdIsSet(true);
    return this;
  }

  public void unsetQuotationAccountId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __QUOTATIONACCOUNTID_ISSET_ID);
  }

  /** Returns true if field quotationAccountId is set (has been assigned a value) and false otherwise */
  public boolean isSetQuotationAccountId() {
    return EncodingUtils.testBit(__isset_bitfield, __QUOTATIONACCOUNTID_ISSET_ID);
  }

  public void setQuotationAccountIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __QUOTATIONACCOUNTID_ISSET_ID, value);
  }

  /**
   * 
   * @see xueqiao.quotation.account.thriftapi.DeploySet
   */
  public xueqiao.quotation.account.thriftapi.DeploySet getQuotationDeploySet() {
    return this.quotationDeploySet;
  }

  /**
   * 
   * @see xueqiao.quotation.account.thriftapi.DeploySet
   */
  public SubscribeAccountClass setQuotationDeploySet(xueqiao.quotation.account.thriftapi.DeploySet quotationDeploySet) {
    this.quotationDeploySet = quotationDeploySet;
    return this;
  }

  public void unsetQuotationDeploySet() {
    this.quotationDeploySet = null;
  }

  /** Returns true if field quotationDeploySet is set (has been assigned a value) and false otherwise */
  public boolean isSetQuotationDeploySet() {
    return this.quotationDeploySet != null;
  }

  public void setQuotationDeploySetIsSet(boolean value) {
    if (!value) {
      this.quotationDeploySet = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SUBSCRIBE_NUM:
      if (value == null) {
        unsetSubscribeNum();
      } else {
        setSubscribeNum((Integer)value);
      }
      break;

    case QUOTATION_ACCOUNT_ID:
      if (value == null) {
        unsetQuotationAccountId();
      } else {
        setQuotationAccountId((Long)value);
      }
      break;

    case QUOTATION_DEPLOY_SET:
      if (value == null) {
        unsetQuotationDeploySet();
      } else {
        setQuotationDeploySet((xueqiao.quotation.account.thriftapi.DeploySet)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SUBSCRIBE_NUM:
      return Integer.valueOf(getSubscribeNum());

    case QUOTATION_ACCOUNT_ID:
      return Long.valueOf(getQuotationAccountId());

    case QUOTATION_DEPLOY_SET:
      return getQuotationDeploySet();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SUBSCRIBE_NUM:
      return isSetSubscribeNum();
    case QUOTATION_ACCOUNT_ID:
      return isSetQuotationAccountId();
    case QUOTATION_DEPLOY_SET:
      return isSetQuotationDeploySet();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SubscribeAccountClass)
      return this.equals((SubscribeAccountClass)that);
    return false;
  }

  public boolean equals(SubscribeAccountClass that) {
    if (that == null)
      return false;

    boolean this_present_subscribeNum = true && this.isSetSubscribeNum();
    boolean that_present_subscribeNum = true && that.isSetSubscribeNum();
    if (this_present_subscribeNum || that_present_subscribeNum) {
      if (!(this_present_subscribeNum && that_present_subscribeNum))
        return false;
      if (this.subscribeNum != that.subscribeNum)
        return false;
    }

    boolean this_present_quotationAccountId = true && this.isSetQuotationAccountId();
    boolean that_present_quotationAccountId = true && that.isSetQuotationAccountId();
    if (this_present_quotationAccountId || that_present_quotationAccountId) {
      if (!(this_present_quotationAccountId && that_present_quotationAccountId))
        return false;
      if (this.quotationAccountId != that.quotationAccountId)
        return false;
    }

    boolean this_present_quotationDeploySet = true && this.isSetQuotationDeploySet();
    boolean that_present_quotationDeploySet = true && that.isSetQuotationDeploySet();
    if (this_present_quotationDeploySet || that_present_quotationDeploySet) {
      if (!(this_present_quotationDeploySet && that_present_quotationDeploySet))
        return false;
      if (!this.quotationDeploySet.equals(that.quotationDeploySet))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(SubscribeAccountClass other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSubscribeNum()).compareTo(other.isSetSubscribeNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubscribeNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subscribeNum, other.subscribeNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetQuotationAccountId()).compareTo(other.isSetQuotationAccountId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuotationAccountId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.quotationAccountId, other.quotationAccountId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetQuotationDeploySet()).compareTo(other.isSetQuotationDeploySet());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuotationDeploySet()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.quotationDeploySet, other.quotationDeploySet);
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
    StringBuilder sb = new StringBuilder("SubscribeAccountClass(");
    boolean first = true;

    if (isSetSubscribeNum()) {
      sb.append("subscribeNum:");
      sb.append(this.subscribeNum);
      first = false;
    }
    if (isSetQuotationAccountId()) {
      if (!first) sb.append(", ");
      sb.append("quotationAccountId:");
      sb.append(this.quotationAccountId);
      first = false;
    }
    if (isSetQuotationDeploySet()) {
      if (!first) sb.append(", ");
      sb.append("quotationDeploySet:");
      if (this.quotationDeploySet == null) {
        sb.append("null");
      } else {
        sb.append(this.quotationDeploySet);
      }
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

  private static class SubscribeAccountClassStandardSchemeFactory implements SchemeFactory {
    public SubscribeAccountClassStandardScheme getScheme() {
      return new SubscribeAccountClassStandardScheme();
    }
  }

  private static class SubscribeAccountClassStandardScheme extends StandardScheme<SubscribeAccountClass> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SubscribeAccountClass struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SUBSCRIBE_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.subscribeNum = iprot.readI32();
              struct.setSubscribeNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // QUOTATION_ACCOUNT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.quotationAccountId = iprot.readI64();
              struct.setQuotationAccountIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // QUOTATION_DEPLOY_SET
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.quotationDeploySet = xueqiao.quotation.account.thriftapi.DeploySet.findByValue(iprot.readI32());
              struct.setQuotationDeploySetIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SubscribeAccountClass struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetSubscribeNum()) {
        oprot.writeFieldBegin(SUBSCRIBE_NUM_FIELD_DESC);
        oprot.writeI32(struct.subscribeNum);
        oprot.writeFieldEnd();
      }
      if (struct.isSetQuotationAccountId()) {
        oprot.writeFieldBegin(QUOTATION_ACCOUNT_ID_FIELD_DESC);
        oprot.writeI64(struct.quotationAccountId);
        oprot.writeFieldEnd();
      }
      if (struct.quotationDeploySet != null) {
        if (struct.isSetQuotationDeploySet()) {
          oprot.writeFieldBegin(QUOTATION_DEPLOY_SET_FIELD_DESC);
          oprot.writeI32(struct.quotationDeploySet.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SubscribeAccountClassTupleSchemeFactory implements SchemeFactory {
    public SubscribeAccountClassTupleScheme getScheme() {
      return new SubscribeAccountClassTupleScheme();
    }
  }

  private static class SubscribeAccountClassTupleScheme extends TupleScheme<SubscribeAccountClass> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SubscribeAccountClass struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSubscribeNum()) {
        optionals.set(0);
      }
      if (struct.isSetQuotationAccountId()) {
        optionals.set(1);
      }
      if (struct.isSetQuotationDeploySet()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetSubscribeNum()) {
        oprot.writeI32(struct.subscribeNum);
      }
      if (struct.isSetQuotationAccountId()) {
        oprot.writeI64(struct.quotationAccountId);
      }
      if (struct.isSetQuotationDeploySet()) {
        oprot.writeI32(struct.quotationDeploySet.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SubscribeAccountClass struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.subscribeNum = iprot.readI32();
        struct.setSubscribeNumIsSet(true);
      }
      if (incoming.get(1)) {
        struct.quotationAccountId = iprot.readI64();
        struct.setQuotationAccountIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.quotationDeploySet = xueqiao.quotation.account.thriftapi.DeploySet.findByValue(iprot.readI32());
        struct.setQuotationDeploySetIsSet(true);
      }
    }
  }

}

