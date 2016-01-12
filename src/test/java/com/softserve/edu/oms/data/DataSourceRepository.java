package com.softserve.edu.oms.data;

import com.softserve.edu.dao.DataSource;

public final class DataSourceRepository {
    //private final static String FAILED_JDBC_DRIVER = "Failed to create JDBC Driver";

    private DataSourceRepository() {
    }

    public static DataSource getJtdsMsSqlLocal() {
        return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
                "jdbc:jtds:sqlserver://CLASS86/_097_OMS;instance=SQLEXPRESS2;", "097db", "097db");
    }

    public static DataSource getJtdsMsSqlRemote() {
        return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
                "jdbc:jtds:sqlserver://ssu-sql12.training.local/ssu-oms;instance=tc;", "ssu-oms", "ssu-oms");
    }

    // TODO Read from properties

}
