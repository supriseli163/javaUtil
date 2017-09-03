package com.base.data.access.alyer.sharding;

import java.util.Comparator;

public final class ShardingTable {
    public String database;
    public String table;


    public ShardingTable(String database, String table) {
        super();
        this.database = database;
        this.table = table;
    }

    public ShardingTable() {

    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((database == null) ? 0 : database.hashCode());
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }

        ShardingTable other = (ShardingTable)obj;
        if(database == null) {
            if(other.database != null) {
                return false;
            }
        }else if(!database.equals(other.database)) {
            return false;
        }
        if(table == null) {
            if(other.table != null) {
                return false;
            }
        }else if(!table.equals(other.table)) {
            return false;
        }
        return true;
    }


    public static final class ShardingTableCompator implements Comparator<ShardingTable>{

        @Override
        public int compare(ShardingTable o1, ShardingTable o2) {
            int rt1 = o1.table.compareTo(o2.table);
            if (rt1!=0) {
                return rt1;
            }
            return o1.database.compareTo(o2.database);
        }
    }
}
