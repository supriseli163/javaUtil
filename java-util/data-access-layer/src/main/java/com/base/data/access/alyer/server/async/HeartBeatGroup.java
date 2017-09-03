package com.base.data.access.alyer.server.async;

import com.base.data.access.alyer.allinone.DBConfType;
import com.base.data.access.alyer.allinone.DBConnectionInfo;

import java.util.Comparator;
import java.util.Objects;
import java.util.logging.Logger;

public class HeartBeatGroup {
    private static Logger logger = Logger.getLogger(HeartBeat.class.getName());


    private static class DBConnHBId {
        private final String id;
        private final boolean isShadowDB;

        private DBConnHBId(DBConnectionInfo info) {
            Objects.requireNonNull(info, () -> "DBConnHbId : DBConnection must be null, info = " + info);
            this.id = id;
            this.isShadowDB = isShadowDB;
        }

        private DBConnHBId buildId(DBConnectionInfo dbConnectionInfo) {
            return new DBConnHBId(dbConnectionInfo);
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (isShadowDB ? 1 : 0);
            return result;
        }

        private static String buildConnectionCfg(DBConnectionInfo info) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(info.getGroup());
            stringBuilder.append(info.getId());
            stringBuilder.append(info.getRole());
            stringBuilder.append(info.getDatabse());
            stringBuilder.append(info.getHost());
            stringBuilder.append(info.getPort());
            stringBuilder.append(info.getUser());
            stringBuilder.append(info.getPword().hashCode());
            return stringBuilder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DBConnHBId that = (DBConnHBId) o;

            if (isShadowDB != that.isShadowDB) return false;
            return id != null ? id.equals(that.id) : that.id == null;
        }

        @Override
        public String toString() {
            return "DBConnHId [id=" + id + ", isShadowDB=" + isShadowDB + "]";
        }
    }
}
