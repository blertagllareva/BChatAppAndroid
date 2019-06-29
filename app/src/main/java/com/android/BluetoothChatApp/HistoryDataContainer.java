package com.android.BluetoothChatApp;

import android.provider.BaseColumns;


public class HistoryDataContainer {
    public static final class HistoryFields implements BaseColumns
    {
        public static final String TABLE_NAME = "history";

        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_MESSAGE= "message";

        public static final String COLUMN_DEVICE = "device";
    }
}
