package com.mobileprogramming.calcuwater.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetData {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Data> listDataNotes;

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

    public List<Data> getListDataNotes() {
        return listDataNotes;
    }

    public void setListDataNotes(List<Data> listDataNotes) {
        this.listDataNotes = listDataNotes;
    }
}
