package com.wanching.orderitems;

import android.provider.BaseColumns;

/**
 * Created by WanChing on 19/8/2017.
 */

public class DbContract {

    public DbContract(){}

    public static class DbEntry implements BaseColumns{
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PRODUCTNAME = "productname";
        public static final String COLUMN_QUANTITY = "quantity";
    }
}
