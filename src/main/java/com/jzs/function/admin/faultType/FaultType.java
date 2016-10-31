package com.jzs.function.admin.faultType;

public class FaultType {

    private int faultTypeId;
    private String faultTypeName;
    private String faultSugestion;

    public int getFaultTypeId() {
        return faultTypeId;
    }

    public void setFaultTypeId(int faultTypeId) {
        this.faultTypeId = faultTypeId;
    }

    public String getFaultTypeName() {
        return faultTypeName;
    }

    public void setFaultTypeName(String faultTypeName) {
        this.faultTypeName = faultTypeName;
    }

    public String getFaultSugestion() {
        return faultSugestion;
    }

    public void setFaultSugestion(String faultSugestion) {
        this.faultSugestion = faultSugestion;
    }
}
