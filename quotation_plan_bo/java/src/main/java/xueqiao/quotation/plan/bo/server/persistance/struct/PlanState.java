package xueqiao.quotation.plan.bo.server.persistance.struct;

import xueqiao.quotation.plan.bo.EGenPreviewStatus;

public class PlanState {
    private EGenPreviewStatus mPreviewStatus;
    private String mPreviewStateMsg;
    private long mLastUpdateTimestampMs;
    
    private Short mCurrentSCClassIndex;
    private long  mSwitchSCClassTimestampMs;
    
    private Short mCurrentSCItemIndex;
    private long mSwitchSCItemTimestampMs;
    private Boolean mNeedInitSCItems;
    
    public EGenPreviewStatus getPreviewStatus() {
        return mPreviewStatus;
    }
    public void setPreviewStatus(EGenPreviewStatus previewStatus) {
        this.mPreviewStatus = previewStatus;
    }
    
    public String getPreviewStateMsg() {
        return mPreviewStateMsg;
    }
    public void setPreviewStateMsg(String previewStateMsg) {
        this.mPreviewStateMsg = previewStateMsg;
    }
    
    public long getLastUpdateTimestampMs() {
        return mLastUpdateTimestampMs;
    }
    public void setLastUpdateTimestampMs(long lastUpdateTimestampMs) {
        this.mLastUpdateTimestampMs = lastUpdateTimestampMs;
    }
    
    public Short getCurrentSCClassIndex() {
        return mCurrentSCClassIndex;
    }
    public void setCurrentSCClassIndex(Short currentSCClassIndex) {
        this.mCurrentSCClassIndex = currentSCClassIndex;
    }
    
    public long getSwitchSCClassTimestampMs() {
        return mSwitchSCClassTimestampMs;
    }
    public void setSwitchSCClassTimestampMs(long switchSCClassTimestampMs) {
        this.mSwitchSCClassTimestampMs = switchSCClassTimestampMs;
    }
    
    public Short getCurrentSCItemIndex() {
        return mCurrentSCItemIndex;
    }
    public void setCurrentSCItemIndex(Short mCurrentSCItemIndex) {
        this.mCurrentSCItemIndex = mCurrentSCItemIndex;
    }
    
    public long getSwitchSCItemTimestampMs() {
        return mSwitchSCItemTimestampMs;
    }
    public void setSwitchSCItemTimestampMs(long switchSCItemTimestampMs) {
        this.mSwitchSCItemTimestampMs = switchSCItemTimestampMs;
    }
    
    public Boolean getNeedInitSCItems() {
        return mNeedInitSCItems;
    }
    public void setNeedInitSCItems(Boolean needInitSCItems) {
        this.mNeedInitSCItems = needInitSCItems;
    }
    
}
