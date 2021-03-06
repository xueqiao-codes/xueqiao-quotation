/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.quotation.account.thriftapi;

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

public class ReqQuotationAccountSupportOption implements org.apache.thrift.TBase<ReqQuotationAccountSupportOption, ReqQuotationAccountSupportOption._Fields>, java.io.Serializable, Cloneable, Comparable<ReqQuotationAccountSupportOption> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReqQuotationAccountSupportOption");

  private static final org.apache.thrift.protocol.TField ACCOUNT_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("accountIds", org.apache.thrift.protocol.TType.SET, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ReqQuotationAccountSupportOptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ReqQuotationAccountSupportOptionTupleSchemeFactory());
  }

  public Set<Long> accountIds; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ACCOUNT_IDS((short)1, "accountIds");

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
        case 1: // ACCOUNT_IDS
          return ACCOUNT_IDS;
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
  private _Fields optionals[] = {_Fields.ACCOUNT_IDS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ACCOUNT_IDS, new org.apache.thrift.meta_data.FieldMetaData("accountIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReqQuotationAccountSupportOption.class, metaDataMap);
  }

  public ReqQuotationAccountSupportOption() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReqQuotationAccountSupportOption(ReqQuotationAccountSupportOption other) {
    if (other.isSetAccountIds()) {
      Set<Long> __this__accountIds = new HashSet<Long>(other.accountIds);
      this.accountIds = __this__accountIds;
    }
  }

  public ReqQuotationAccountSupportOption deepCopy() {
    return new ReqQuotationAccountSupportOption(this);
  }

  @Override
  public void clear() {
    this.accountIds = null;
  }

  public int getAccountIdsSize() {
    return (this.accountIds == null) ? 0 : this.accountIds.size();
  }

  public java.util.Iterator<Long> getAccountIdsIterator() {
    return (this.accountIds == null) ? null : this.accountIds.iterator();
  }

  public void addToAccountIds(long elem) {
    if (this.accountIds == null) {
      this.accountIds = new HashSet<Long>();
    }
    this.accountIds.add(elem);
  }

  public Set<Long> getAccountIds() {
    return this.accountIds;
  }

  public ReqQuotationAccountSupportOption setAccountIds(Set<Long> accountIds) {
    this.accountIds = accountIds;
    return this;
  }

  public void unsetAccountIds() {
    this.accountIds = null;
  }

  /** Returns true if field accountIds is set (has been assigned a value) and false otherwise */
  public boolean isSetAccountIds() {
    return this.accountIds != null;
  }

  public void setAccountIdsIsSet(boolean value) {
    if (!value) {
      this.accountIds = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ACCOUNT_IDS:
      if (value == null) {
        unsetAccountIds();
      } else {
        setAccountIds((Set<Long>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ACCOUNT_IDS:
      return getAccountIds();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ACCOUNT_IDS:
      return isSetAccountIds();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ReqQuotationAccountSupportOption)
      return this.equals((ReqQuotationAccountSupportOption)that);
    return false;
  }

  public boolean equals(ReqQuotationAccountSupportOption that) {
    if (that == null)
      return false;

    boolean this_present_accountIds = true && this.isSetAccountIds();
    boolean that_present_accountIds = true && that.isSetAccountIds();
    if (this_present_accountIds || that_present_accountIds) {
      if (!(this_present_accountIds && that_present_accountIds))
        return false;
      if (!this.accountIds.equals(that.accountIds))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ReqQuotationAccountSupportOption other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAccountIds()).compareTo(other.isSetAccountIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAccountIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.accountIds, other.accountIds);
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
    StringBuilder sb = new StringBuilder("ReqQuotationAccountSupportOption(");
    boolean first = true;

    if (isSetAccountIds()) {
      sb.append("accountIds:");
      if (this.accountIds == null) {
        sb.append("null");
      } else {
        sb.append(this.accountIds);
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ReqQuotationAccountSupportOptionStandardSchemeFactory implements SchemeFactory {
    public ReqQuotationAccountSupportOptionStandardScheme getScheme() {
      return new ReqQuotationAccountSupportOptionStandardScheme();
    }
  }

  private static class ReqQuotationAccountSupportOptionStandardScheme extends StandardScheme<ReqQuotationAccountSupportOption> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReqQuotationAccountSupportOption struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ACCOUNT_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set84 = iprot.readSetBegin();
                struct.accountIds = new HashSet<Long>(2*_set84.size);
                for (int _i85 = 0; _i85 < _set84.size; ++_i85)
                {
                  long _elem86;
                  _elem86 = iprot.readI64();
                  struct.accountIds.add(_elem86);
                }
                iprot.readSetEnd();
              }
              struct.setAccountIdsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReqQuotationAccountSupportOption struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.accountIds != null) {
        if (struct.isSetAccountIds()) {
          oprot.writeFieldBegin(ACCOUNT_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I64, struct.accountIds.size()));
            for (long _iter87 : struct.accountIds)
            {
              oprot.writeI64(_iter87);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ReqQuotationAccountSupportOptionTupleSchemeFactory implements SchemeFactory {
    public ReqQuotationAccountSupportOptionTupleScheme getScheme() {
      return new ReqQuotationAccountSupportOptionTupleScheme();
    }
  }

  private static class ReqQuotationAccountSupportOptionTupleScheme extends TupleScheme<ReqQuotationAccountSupportOption> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReqQuotationAccountSupportOption struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAccountIds()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetAccountIds()) {
        {
          oprot.writeI32(struct.accountIds.size());
          for (long _iter88 : struct.accountIds)
          {
            oprot.writeI64(_iter88);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReqQuotationAccountSupportOption struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TSet _set89 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.accountIds = new HashSet<Long>(2*_set89.size);
          for (int _i90 = 0; _i90 < _set89.size; ++_i90)
          {
            long _elem91;
            _elem91 = iprot.readI64();
            struct.accountIds.add(_elem91);
          }
        }
        struct.setAccountIdsIsSet(true);
      }
    }
  }

}

