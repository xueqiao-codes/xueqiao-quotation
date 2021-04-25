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

public class ReqAccountCommodityRegisterAbilityOption implements org.apache.thrift.TBase<ReqAccountCommodityRegisterAbilityOption, ReqAccountCommodityRegisterAbilityOption._Fields>, java.io.Serializable, Cloneable, Comparable<ReqAccountCommodityRegisterAbilityOption> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReqAccountCommodityRegisterAbilityOption");

  private static final org.apache.thrift.protocol.TField REGISTER_ABILITY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("registerAbilityId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField ACCOUNT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("accountId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField SLED_COMMODITY_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("sledCommodityIds", org.apache.thrift.protocol.TType.SET, (short)3);
  private static final org.apache.thrift.protocol.TField SLED_EXCHANGE_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("sledExchangeIds", org.apache.thrift.protocol.TType.SET, (short)4);
  private static final org.apache.thrift.protocol.TField SUPPORT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("supportType", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField EXCHANGE_MICS_FIELD_DESC = new org.apache.thrift.protocol.TField("exchangeMics", org.apache.thrift.protocol.TType.SET, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ReqAccountCommodityRegisterAbilityOptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ReqAccountCommodityRegisterAbilityOptionTupleSchemeFactory());
  }

  public long registerAbilityId; // optional
  public long accountId; // optional
  public Set<Integer> sledCommodityIds; // optional
  public Set<Integer> sledExchangeIds; // optional
  /**
   * 
   * @see SupportType
   */
  public SupportType supportType; // optional
  public Set<String> exchangeMics; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    REGISTER_ABILITY_ID((short)1, "registerAbilityId"),
    ACCOUNT_ID((short)2, "accountId"),
    SLED_COMMODITY_IDS((short)3, "sledCommodityIds"),
    SLED_EXCHANGE_IDS((short)4, "sledExchangeIds"),
    /**
     * 
     * @see SupportType
     */
    SUPPORT_TYPE((short)5, "supportType"),
    EXCHANGE_MICS((short)6, "exchangeMics");

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
        case 1: // REGISTER_ABILITY_ID
          return REGISTER_ABILITY_ID;
        case 2: // ACCOUNT_ID
          return ACCOUNT_ID;
        case 3: // SLED_COMMODITY_IDS
          return SLED_COMMODITY_IDS;
        case 4: // SLED_EXCHANGE_IDS
          return SLED_EXCHANGE_IDS;
        case 5: // SUPPORT_TYPE
          return SUPPORT_TYPE;
        case 6: // EXCHANGE_MICS
          return EXCHANGE_MICS;
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
  private static final int __REGISTERABILITYID_ISSET_ID = 0;
  private static final int __ACCOUNTID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.REGISTER_ABILITY_ID,_Fields.ACCOUNT_ID,_Fields.SLED_COMMODITY_IDS,_Fields.SLED_EXCHANGE_IDS,_Fields.SUPPORT_TYPE,_Fields.EXCHANGE_MICS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REGISTER_ABILITY_ID, new org.apache.thrift.meta_data.FieldMetaData("registerAbilityId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ACCOUNT_ID, new org.apache.thrift.meta_data.FieldMetaData("accountId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SLED_COMMODITY_IDS, new org.apache.thrift.meta_data.FieldMetaData("sledCommodityIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.SLED_EXCHANGE_IDS, new org.apache.thrift.meta_data.FieldMetaData("sledExchangeIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.SUPPORT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("supportType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, SupportType.class)));
    tmpMap.put(_Fields.EXCHANGE_MICS, new org.apache.thrift.meta_data.FieldMetaData("exchangeMics", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReqAccountCommodityRegisterAbilityOption.class, metaDataMap);
  }

  public ReqAccountCommodityRegisterAbilityOption() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReqAccountCommodityRegisterAbilityOption(ReqAccountCommodityRegisterAbilityOption other) {
    __isset_bitfield = other.__isset_bitfield;
    this.registerAbilityId = other.registerAbilityId;
    this.accountId = other.accountId;
    if (other.isSetSledCommodityIds()) {
      Set<Integer> __this__sledCommodityIds = new HashSet<Integer>(other.sledCommodityIds);
      this.sledCommodityIds = __this__sledCommodityIds;
    }
    if (other.isSetSledExchangeIds()) {
      Set<Integer> __this__sledExchangeIds = new HashSet<Integer>(other.sledExchangeIds);
      this.sledExchangeIds = __this__sledExchangeIds;
    }
    if (other.isSetSupportType()) {
      this.supportType = other.supportType;
    }
    if (other.isSetExchangeMics()) {
      Set<String> __this__exchangeMics = new HashSet<String>(other.exchangeMics);
      this.exchangeMics = __this__exchangeMics;
    }
  }

  public ReqAccountCommodityRegisterAbilityOption deepCopy() {
    return new ReqAccountCommodityRegisterAbilityOption(this);
  }

  @Override
  public void clear() {
    setRegisterAbilityIdIsSet(false);
    this.registerAbilityId = 0;
    setAccountIdIsSet(false);
    this.accountId = 0;
    this.sledCommodityIds = null;
    this.sledExchangeIds = null;
    this.supportType = null;
    this.exchangeMics = null;
  }

  public long getRegisterAbilityId() {
    return this.registerAbilityId;
  }

  public ReqAccountCommodityRegisterAbilityOption setRegisterAbilityId(long registerAbilityId) {
    this.registerAbilityId = registerAbilityId;
    setRegisterAbilityIdIsSet(true);
    return this;
  }

  public void unsetRegisterAbilityId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __REGISTERABILITYID_ISSET_ID);
  }

  /** Returns true if field registerAbilityId is set (has been assigned a value) and false otherwise */
  public boolean isSetRegisterAbilityId() {
    return EncodingUtils.testBit(__isset_bitfield, __REGISTERABILITYID_ISSET_ID);
  }

  public void setRegisterAbilityIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __REGISTERABILITYID_ISSET_ID, value);
  }

  public long getAccountId() {
    return this.accountId;
  }

  public ReqAccountCommodityRegisterAbilityOption setAccountId(long accountId) {
    this.accountId = accountId;
    setAccountIdIsSet(true);
    return this;
  }

  public void unsetAccountId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ACCOUNTID_ISSET_ID);
  }

  /** Returns true if field accountId is set (has been assigned a value) and false otherwise */
  public boolean isSetAccountId() {
    return EncodingUtils.testBit(__isset_bitfield, __ACCOUNTID_ISSET_ID);
  }

  public void setAccountIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ACCOUNTID_ISSET_ID, value);
  }

  public int getSledCommodityIdsSize() {
    return (this.sledCommodityIds == null) ? 0 : this.sledCommodityIds.size();
  }

  public java.util.Iterator<Integer> getSledCommodityIdsIterator() {
    return (this.sledCommodityIds == null) ? null : this.sledCommodityIds.iterator();
  }

  public void addToSledCommodityIds(int elem) {
    if (this.sledCommodityIds == null) {
      this.sledCommodityIds = new HashSet<Integer>();
    }
    this.sledCommodityIds.add(elem);
  }

  public Set<Integer> getSledCommodityIds() {
    return this.sledCommodityIds;
  }

  public ReqAccountCommodityRegisterAbilityOption setSledCommodityIds(Set<Integer> sledCommodityIds) {
    this.sledCommodityIds = sledCommodityIds;
    return this;
  }

  public void unsetSledCommodityIds() {
    this.sledCommodityIds = null;
  }

  /** Returns true if field sledCommodityIds is set (has been assigned a value) and false otherwise */
  public boolean isSetSledCommodityIds() {
    return this.sledCommodityIds != null;
  }

  public void setSledCommodityIdsIsSet(boolean value) {
    if (!value) {
      this.sledCommodityIds = null;
    }
  }

  public int getSledExchangeIdsSize() {
    return (this.sledExchangeIds == null) ? 0 : this.sledExchangeIds.size();
  }

  public java.util.Iterator<Integer> getSledExchangeIdsIterator() {
    return (this.sledExchangeIds == null) ? null : this.sledExchangeIds.iterator();
  }

  public void addToSledExchangeIds(int elem) {
    if (this.sledExchangeIds == null) {
      this.sledExchangeIds = new HashSet<Integer>();
    }
    this.sledExchangeIds.add(elem);
  }

  public Set<Integer> getSledExchangeIds() {
    return this.sledExchangeIds;
  }

  public ReqAccountCommodityRegisterAbilityOption setSledExchangeIds(Set<Integer> sledExchangeIds) {
    this.sledExchangeIds = sledExchangeIds;
    return this;
  }

  public void unsetSledExchangeIds() {
    this.sledExchangeIds = null;
  }

  /** Returns true if field sledExchangeIds is set (has been assigned a value) and false otherwise */
  public boolean isSetSledExchangeIds() {
    return this.sledExchangeIds != null;
  }

  public void setSledExchangeIdsIsSet(boolean value) {
    if (!value) {
      this.sledExchangeIds = null;
    }
  }

  /**
   * 
   * @see SupportType
   */
  public SupportType getSupportType() {
    return this.supportType;
  }

  /**
   * 
   * @see SupportType
   */
  public ReqAccountCommodityRegisterAbilityOption setSupportType(SupportType supportType) {
    this.supportType = supportType;
    return this;
  }

  public void unsetSupportType() {
    this.supportType = null;
  }

  /** Returns true if field supportType is set (has been assigned a value) and false otherwise */
  public boolean isSetSupportType() {
    return this.supportType != null;
  }

  public void setSupportTypeIsSet(boolean value) {
    if (!value) {
      this.supportType = null;
    }
  }

  public int getExchangeMicsSize() {
    return (this.exchangeMics == null) ? 0 : this.exchangeMics.size();
  }

  public java.util.Iterator<String> getExchangeMicsIterator() {
    return (this.exchangeMics == null) ? null : this.exchangeMics.iterator();
  }

  public void addToExchangeMics(String elem) {
    if (this.exchangeMics == null) {
      this.exchangeMics = new HashSet<String>();
    }
    this.exchangeMics.add(elem);
  }

  public Set<String> getExchangeMics() {
    return this.exchangeMics;
  }

  public ReqAccountCommodityRegisterAbilityOption setExchangeMics(Set<String> exchangeMics) {
    this.exchangeMics = exchangeMics;
    return this;
  }

  public void unsetExchangeMics() {
    this.exchangeMics = null;
  }

  /** Returns true if field exchangeMics is set (has been assigned a value) and false otherwise */
  public boolean isSetExchangeMics() {
    return this.exchangeMics != null;
  }

  public void setExchangeMicsIsSet(boolean value) {
    if (!value) {
      this.exchangeMics = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case REGISTER_ABILITY_ID:
      if (value == null) {
        unsetRegisterAbilityId();
      } else {
        setRegisterAbilityId((Long)value);
      }
      break;

    case ACCOUNT_ID:
      if (value == null) {
        unsetAccountId();
      } else {
        setAccountId((Long)value);
      }
      break;

    case SLED_COMMODITY_IDS:
      if (value == null) {
        unsetSledCommodityIds();
      } else {
        setSledCommodityIds((Set<Integer>)value);
      }
      break;

    case SLED_EXCHANGE_IDS:
      if (value == null) {
        unsetSledExchangeIds();
      } else {
        setSledExchangeIds((Set<Integer>)value);
      }
      break;

    case SUPPORT_TYPE:
      if (value == null) {
        unsetSupportType();
      } else {
        setSupportType((SupportType)value);
      }
      break;

    case EXCHANGE_MICS:
      if (value == null) {
        unsetExchangeMics();
      } else {
        setExchangeMics((Set<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case REGISTER_ABILITY_ID:
      return Long.valueOf(getRegisterAbilityId());

    case ACCOUNT_ID:
      return Long.valueOf(getAccountId());

    case SLED_COMMODITY_IDS:
      return getSledCommodityIds();

    case SLED_EXCHANGE_IDS:
      return getSledExchangeIds();

    case SUPPORT_TYPE:
      return getSupportType();

    case EXCHANGE_MICS:
      return getExchangeMics();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case REGISTER_ABILITY_ID:
      return isSetRegisterAbilityId();
    case ACCOUNT_ID:
      return isSetAccountId();
    case SLED_COMMODITY_IDS:
      return isSetSledCommodityIds();
    case SLED_EXCHANGE_IDS:
      return isSetSledExchangeIds();
    case SUPPORT_TYPE:
      return isSetSupportType();
    case EXCHANGE_MICS:
      return isSetExchangeMics();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ReqAccountCommodityRegisterAbilityOption)
      return this.equals((ReqAccountCommodityRegisterAbilityOption)that);
    return false;
  }

  public boolean equals(ReqAccountCommodityRegisterAbilityOption that) {
    if (that == null)
      return false;

    boolean this_present_registerAbilityId = true && this.isSetRegisterAbilityId();
    boolean that_present_registerAbilityId = true && that.isSetRegisterAbilityId();
    if (this_present_registerAbilityId || that_present_registerAbilityId) {
      if (!(this_present_registerAbilityId && that_present_registerAbilityId))
        return false;
      if (this.registerAbilityId != that.registerAbilityId)
        return false;
    }

    boolean this_present_accountId = true && this.isSetAccountId();
    boolean that_present_accountId = true && that.isSetAccountId();
    if (this_present_accountId || that_present_accountId) {
      if (!(this_present_accountId && that_present_accountId))
        return false;
      if (this.accountId != that.accountId)
        return false;
    }

    boolean this_present_sledCommodityIds = true && this.isSetSledCommodityIds();
    boolean that_present_sledCommodityIds = true && that.isSetSledCommodityIds();
    if (this_present_sledCommodityIds || that_present_sledCommodityIds) {
      if (!(this_present_sledCommodityIds && that_present_sledCommodityIds))
        return false;
      if (!this.sledCommodityIds.equals(that.sledCommodityIds))
        return false;
    }

    boolean this_present_sledExchangeIds = true && this.isSetSledExchangeIds();
    boolean that_present_sledExchangeIds = true && that.isSetSledExchangeIds();
    if (this_present_sledExchangeIds || that_present_sledExchangeIds) {
      if (!(this_present_sledExchangeIds && that_present_sledExchangeIds))
        return false;
      if (!this.sledExchangeIds.equals(that.sledExchangeIds))
        return false;
    }

    boolean this_present_supportType = true && this.isSetSupportType();
    boolean that_present_supportType = true && that.isSetSupportType();
    if (this_present_supportType || that_present_supportType) {
      if (!(this_present_supportType && that_present_supportType))
        return false;
      if (!this.supportType.equals(that.supportType))
        return false;
    }

    boolean this_present_exchangeMics = true && this.isSetExchangeMics();
    boolean that_present_exchangeMics = true && that.isSetExchangeMics();
    if (this_present_exchangeMics || that_present_exchangeMics) {
      if (!(this_present_exchangeMics && that_present_exchangeMics))
        return false;
      if (!this.exchangeMics.equals(that.exchangeMics))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ReqAccountCommodityRegisterAbilityOption other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRegisterAbilityId()).compareTo(other.isSetRegisterAbilityId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRegisterAbilityId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.registerAbilityId, other.registerAbilityId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAccountId()).compareTo(other.isSetAccountId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAccountId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.accountId, other.accountId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSledCommodityIds()).compareTo(other.isSetSledCommodityIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSledCommodityIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sledCommodityIds, other.sledCommodityIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSledExchangeIds()).compareTo(other.isSetSledExchangeIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSledExchangeIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sledExchangeIds, other.sledExchangeIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSupportType()).compareTo(other.isSetSupportType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSupportType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.supportType, other.supportType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExchangeMics()).compareTo(other.isSetExchangeMics());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExchangeMics()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.exchangeMics, other.exchangeMics);
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
    StringBuilder sb = new StringBuilder("ReqAccountCommodityRegisterAbilityOption(");
    boolean first = true;

    if (isSetRegisterAbilityId()) {
      sb.append("registerAbilityId:");
      sb.append(this.registerAbilityId);
      first = false;
    }
    if (isSetAccountId()) {
      if (!first) sb.append(", ");
      sb.append("accountId:");
      sb.append(this.accountId);
      first = false;
    }
    if (isSetSledCommodityIds()) {
      if (!first) sb.append(", ");
      sb.append("sledCommodityIds:");
      if (this.sledCommodityIds == null) {
        sb.append("null");
      } else {
        sb.append(this.sledCommodityIds);
      }
      first = false;
    }
    if (isSetSledExchangeIds()) {
      if (!first) sb.append(", ");
      sb.append("sledExchangeIds:");
      if (this.sledExchangeIds == null) {
        sb.append("null");
      } else {
        sb.append(this.sledExchangeIds);
      }
      first = false;
    }
    if (isSetSupportType()) {
      if (!first) sb.append(", ");
      sb.append("supportType:");
      if (this.supportType == null) {
        sb.append("null");
      } else {
        sb.append(this.supportType);
      }
      first = false;
    }
    if (isSetExchangeMics()) {
      if (!first) sb.append(", ");
      sb.append("exchangeMics:");
      if (this.exchangeMics == null) {
        sb.append("null");
      } else {
        sb.append(this.exchangeMics);
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

  private static class ReqAccountCommodityRegisterAbilityOptionStandardSchemeFactory implements SchemeFactory {
    public ReqAccountCommodityRegisterAbilityOptionStandardScheme getScheme() {
      return new ReqAccountCommodityRegisterAbilityOptionStandardScheme();
    }
  }

  private static class ReqAccountCommodityRegisterAbilityOptionStandardScheme extends StandardScheme<ReqAccountCommodityRegisterAbilityOption> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReqAccountCommodityRegisterAbilityOption struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REGISTER_ABILITY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.registerAbilityId = iprot.readI64();
              struct.setRegisterAbilityIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ACCOUNT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.accountId = iprot.readI64();
              struct.setAccountIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SLED_COMMODITY_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set148 = iprot.readSetBegin();
                struct.sledCommodityIds = new HashSet<Integer>(2*_set148.size);
                for (int _i149 = 0; _i149 < _set148.size; ++_i149)
                {
                  int _elem150;
                  _elem150 = iprot.readI32();
                  struct.sledCommodityIds.add(_elem150);
                }
                iprot.readSetEnd();
              }
              struct.setSledCommodityIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SLED_EXCHANGE_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set151 = iprot.readSetBegin();
                struct.sledExchangeIds = new HashSet<Integer>(2*_set151.size);
                for (int _i152 = 0; _i152 < _set151.size; ++_i152)
                {
                  int _elem153;
                  _elem153 = iprot.readI32();
                  struct.sledExchangeIds.add(_elem153);
                }
                iprot.readSetEnd();
              }
              struct.setSledExchangeIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SUPPORT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.supportType = SupportType.findByValue(iprot.readI32());
              struct.setSupportTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // EXCHANGE_MICS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set154 = iprot.readSetBegin();
                struct.exchangeMics = new HashSet<String>(2*_set154.size);
                for (int _i155 = 0; _i155 < _set154.size; ++_i155)
                {
                  String _elem156;
                  _elem156 = iprot.readString();
                  struct.exchangeMics.add(_elem156);
                }
                iprot.readSetEnd();
              }
              struct.setExchangeMicsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReqAccountCommodityRegisterAbilityOption struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetRegisterAbilityId()) {
        oprot.writeFieldBegin(REGISTER_ABILITY_ID_FIELD_DESC);
        oprot.writeI64(struct.registerAbilityId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetAccountId()) {
        oprot.writeFieldBegin(ACCOUNT_ID_FIELD_DESC);
        oprot.writeI64(struct.accountId);
        oprot.writeFieldEnd();
      }
      if (struct.sledCommodityIds != null) {
        if (struct.isSetSledCommodityIds()) {
          oprot.writeFieldBegin(SLED_COMMODITY_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.sledCommodityIds.size()));
            for (int _iter157 : struct.sledCommodityIds)
            {
              oprot.writeI32(_iter157);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.sledExchangeIds != null) {
        if (struct.isSetSledExchangeIds()) {
          oprot.writeFieldBegin(SLED_EXCHANGE_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.sledExchangeIds.size()));
            for (int _iter158 : struct.sledExchangeIds)
            {
              oprot.writeI32(_iter158);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.supportType != null) {
        if (struct.isSetSupportType()) {
          oprot.writeFieldBegin(SUPPORT_TYPE_FIELD_DESC);
          oprot.writeI32(struct.supportType.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.exchangeMics != null) {
        if (struct.isSetExchangeMics()) {
          oprot.writeFieldBegin(EXCHANGE_MICS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, struct.exchangeMics.size()));
            for (String _iter159 : struct.exchangeMics)
            {
              oprot.writeString(_iter159);
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

  private static class ReqAccountCommodityRegisterAbilityOptionTupleSchemeFactory implements SchemeFactory {
    public ReqAccountCommodityRegisterAbilityOptionTupleScheme getScheme() {
      return new ReqAccountCommodityRegisterAbilityOptionTupleScheme();
    }
  }

  private static class ReqAccountCommodityRegisterAbilityOptionTupleScheme extends TupleScheme<ReqAccountCommodityRegisterAbilityOption> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReqAccountCommodityRegisterAbilityOption struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRegisterAbilityId()) {
        optionals.set(0);
      }
      if (struct.isSetAccountId()) {
        optionals.set(1);
      }
      if (struct.isSetSledCommodityIds()) {
        optionals.set(2);
      }
      if (struct.isSetSledExchangeIds()) {
        optionals.set(3);
      }
      if (struct.isSetSupportType()) {
        optionals.set(4);
      }
      if (struct.isSetExchangeMics()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetRegisterAbilityId()) {
        oprot.writeI64(struct.registerAbilityId);
      }
      if (struct.isSetAccountId()) {
        oprot.writeI64(struct.accountId);
      }
      if (struct.isSetSledCommodityIds()) {
        {
          oprot.writeI32(struct.sledCommodityIds.size());
          for (int _iter160 : struct.sledCommodityIds)
          {
            oprot.writeI32(_iter160);
          }
        }
      }
      if (struct.isSetSledExchangeIds()) {
        {
          oprot.writeI32(struct.sledExchangeIds.size());
          for (int _iter161 : struct.sledExchangeIds)
          {
            oprot.writeI32(_iter161);
          }
        }
      }
      if (struct.isSetSupportType()) {
        oprot.writeI32(struct.supportType.getValue());
      }
      if (struct.isSetExchangeMics()) {
        {
          oprot.writeI32(struct.exchangeMics.size());
          for (String _iter162 : struct.exchangeMics)
          {
            oprot.writeString(_iter162);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReqAccountCommodityRegisterAbilityOption struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.registerAbilityId = iprot.readI64();
        struct.setRegisterAbilityIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.accountId = iprot.readI64();
        struct.setAccountIdIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TSet _set163 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.sledCommodityIds = new HashSet<Integer>(2*_set163.size);
          for (int _i164 = 0; _i164 < _set163.size; ++_i164)
          {
            int _elem165;
            _elem165 = iprot.readI32();
            struct.sledCommodityIds.add(_elem165);
          }
        }
        struct.setSledCommodityIdsIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TSet _set166 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.sledExchangeIds = new HashSet<Integer>(2*_set166.size);
          for (int _i167 = 0; _i167 < _set166.size; ++_i167)
          {
            int _elem168;
            _elem168 = iprot.readI32();
            struct.sledExchangeIds.add(_elem168);
          }
        }
        struct.setSledExchangeIdsIsSet(true);
      }
      if (incoming.get(4)) {
        struct.supportType = SupportType.findByValue(iprot.readI32());
        struct.setSupportTypeIsSet(true);
      }
      if (incoming.get(5)) {
        {
          org.apache.thrift.protocol.TSet _set169 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.exchangeMics = new HashSet<String>(2*_set169.size);
          for (int _i170 = 0; _i170 < _set169.size; ++_i170)
          {
            String _elem171;
            _elem171 = iprot.readString();
            struct.exchangeMics.add(_elem171);
          }
        }
        struct.setExchangeMicsIsSet(true);
      }
    }
  }

}

