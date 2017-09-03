package com.base.data.access.alyer;

public class GreySwitch {
    public static final GreySwitch GREY_SWITCH = new GreySwitch();
    public static GreySwitch getInstance() {
        return GREY_SWITCH;
    }

    private GreySwitch() {

    }

    /**
     * 用于是否打开zoneId的orderId
     */
    private volatile boolean supporeZoneOrderId = true;
    private volatile boolean supportShareIdEmbedded = true;
    private volatile boolean allowGlobalIdPartion = true;
    private volatile boolean allowSendAuditSqlToRmp = false;
    private volatile long overflowTraceFrequence = -1;

    private volatile boolean rejectZeroShardId = false;

    public boolean isSupportZoneOrderId() {
        return supporeZoneOrderId;
    }

    public void setSupportZonedOrderId(boolean supporteZoneOrderId) {
        this.supporeZoneOrderId = supporteZoneOrderId;
    }

    public void setAllowGlobalIdPartion(boolean allowGlobalIdPartion) {
        this.allowGlobalIdPartion = allowGlobalIdPartion;
    }

    public void setSupporteShardIdEmbedded(boolean supporteShardIdEmbedded) {
        this.supportShareIdEmbedded = supporteShardIdEmbedded;
    }

    public boolean isSupporeZoneOrderId() {
        return supporeZoneOrderId;
    }

    public void setSupporeZoneOrderId(boolean supporeZoneOrderId) {
        this.supporeZoneOrderId = supporeZoneOrderId;
    }

    public boolean isSupportShareIdEmbedded() {
        return supportShareIdEmbedded;
    }

    public void setSupportShareIdEmbedded(boolean supportShareIdEmbedded) {
        this.supportShareIdEmbedded = supportShareIdEmbedded;
    }

    public boolean isAllowGlobalIdPartion() {
        return allowGlobalIdPartion;
    }

    public boolean isAllowSendAuditSqlToRmp() {
        return allowSendAuditSqlToRmp;
    }

    public void setAllowSendAuditSqlToRmp(boolean allowSendAuditSqlToRmp) {
        this.allowSendAuditSqlToRmp = allowSendAuditSqlToRmp;
    }

    public long getOverflowTraceFrequence() {
        return overflowTraceFrequence;
    }

    public void setOverflowTraceFrequence(long overflowTraceFrequence) {
        this.overflowTraceFrequence = overflowTraceFrequence;
    }

    public boolean isRejectZeroShardId() {
        return rejectZeroShardId;
    }

    public void setRejectZeroShardId(boolean rejectZeroShardId) {
        this.rejectZeroShardId = rejectZeroShardId;
    }
}
