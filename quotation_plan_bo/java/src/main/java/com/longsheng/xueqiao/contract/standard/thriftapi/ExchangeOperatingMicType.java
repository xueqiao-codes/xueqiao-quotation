/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.contract.standard.thriftapi;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum ExchangeOperatingMicType implements org.apache.thrift.TEnum {
  OPERATING_MIC(0),
  SEGMENT_MIC(1);

  private final int value;

  private ExchangeOperatingMicType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ExchangeOperatingMicType findByValue(int value) { 
    switch (value) {
      case 0:
        return OPERATING_MIC;
      case 1:
        return SEGMENT_MIC;
      default:
        return null;
    }
  }
}
