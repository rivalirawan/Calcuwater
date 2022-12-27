package com.mobileprogramming.calcuwater.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelData {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Data mData;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Data getData() {
        return mData;
    }
    public void setData(Data Data) {
        mData = Data;
    }
}
