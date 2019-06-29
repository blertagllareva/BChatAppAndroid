package com.android.BluetoothChatApp;

public class HistoryData {
    private long date;
    private String message,device;

    public HistoryData(int date, String message, String device) {
        this.date = date;
        this.message = message;
        this.device = device;
    }
    public HistoryData()
    {

    }
    public long getDate() {

        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }


}
