package com.base.data.access.alyer.allinone;

import java.util.Objects;

public class DBStatus {
    private String group;
    private String id;
    private boolean active = true;

    public DBStatus(String group, String id, boolean active) {
        super();
        this.group = group;
        this.active = active;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public String toString() {
        return "DBStatus [group=" + group + ", id=" + id + ", active=" + active + "]";
    }

    public int hashCode() {
        return Objects.hash(active, group, id);
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || !(obj instanceof DBStatus)) {
            return false;
        }

        DBStatus other = (DBStatus)obj;
        return (Objects.equals(active, other.active) && Objects.equals(group, other.group) && Objects.equals(id, other.id));
    }

    public String getQualifiedDBId() {
        return this.group + ":" + id;
    }
}
