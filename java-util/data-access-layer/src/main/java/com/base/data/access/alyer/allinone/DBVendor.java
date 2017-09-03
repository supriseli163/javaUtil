package com.base.data.access.alyer.allinone;

public enum  DBVendor {
    MYSQL, PG;

    public static DBVendor fromVendor(String name) {
        for (DBVendor vendor : values()) {
            if(vendor.name().equalsIgnoreCase(name)) {
                return vendor;
            }
        }
        return MYSQL;
    }
}
