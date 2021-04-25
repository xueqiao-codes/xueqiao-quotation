/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xueqiao.quotation.subscriber;

public class Topic {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected Topic(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Topic obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        QuotationSubscriberJNI.delete_Topic(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public Topic() {
    this(QuotationSubscriberJNI.new_Topic__SWIG_0(), true);
  }

  public Topic(String platform, String contract_symbol) {
    this(QuotationSubscriberJNI.new_Topic__SWIG_1(platform, contract_symbol), true);
  }

  public String Platform() {
    return QuotationSubscriberJNI.Topic_Platform(swigCPtr, this);
  }

  public void setPlatform(String platform) {
    QuotationSubscriberJNI.Topic_setPlatform(swigCPtr, this, platform);
  }

  public String contractSymbol() {
    return QuotationSubscriberJNI.Topic_contractSymbol(swigCPtr, this);
  }

  public void setContractSymbol(String contract_symbol) {
    QuotationSubscriberJNI.Topic_setContractSymbol(swigCPtr, this, contract_symbol);
  }

}