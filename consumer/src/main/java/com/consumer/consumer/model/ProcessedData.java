package com.consumer.consumer.model;

public class ProcessedData {
    private int id;
    private String data;

    public ProcessedData(){

    }

    public ProcessedData(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProcessedData {" +
                "id=" + id +
                ", data='" + data + "\'" +
                "}" ;
    }
}
