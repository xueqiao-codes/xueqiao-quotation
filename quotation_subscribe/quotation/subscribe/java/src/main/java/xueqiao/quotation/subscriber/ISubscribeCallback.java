/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xueqiao.quotation.subscriber;

public class ISubscribeCallback {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected ISubscribeCallback(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ISubscribeCallback obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        QuotationSubscriberJNI.delete_ISubscribeCallback(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    QuotationSubscriberJNI.ISubscribeCallback_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    QuotationSubscriberJNI.ISubscribeCallback_change_ownership(this, swigCPtr, true);
  }

  public void onReceivedItemData(Topic topic, byte[] data) {
    QuotationSubscriberJNI.ISubscribeCallback_onReceivedItemData(swigCPtr, this, Topic.getCPtr(topic), topic, data);
  }

  protected ISubscribeCallback() {
    this(QuotationSubscriberJNI.new_ISubscribeCallback(), true);
    QuotationSubscriberJNI.ISubscribeCallback_director_connect(this, swigCPtr, swigCMemOwn, true);
  }

}
