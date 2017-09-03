package com.base.data.access.alyer.util.delpoy;

public class DALgroup {
    private String name = "";
    private String org = "";
    private Boolean isAutoCommitAllowed = null;
    private Boolean batchAllowed = false;
    private String dbGroup = "";

    public DALgroup(String name, String org, Boolean isAutoCommitAllowed, String dbGroup) {
        super();
        this.name = name;
        this.org = org;
        this.isAutoCommitAllowed = isAutoCommitAllowed;
        this.dbGroup = dbGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Boolean getIsAutoCommitAllowed() {
        this.isAutoCommitAllowed = isAutoCommitAllowed;
    }

    public Boolean isBatchAllowed() {
        return isBatchAllowed();
    }

    public String getDbGroup() {
        return this.dbGroup;
    }

    public String toString() {

    }
}
