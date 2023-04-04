package com.example.enhanzerassignment;

import android.provider.BaseColumns;

public final class DatabaseMaster {

    private  DatabaseMaster(){

    }

    public static final class Customer implements BaseColumns {

        public static final String TABLE_NAME = "customer";
        public static final String COLUMN1 = "name";
        public static final String COLUMN2 = "address";
        public static final String COLUMN3 = "state";

    }

}