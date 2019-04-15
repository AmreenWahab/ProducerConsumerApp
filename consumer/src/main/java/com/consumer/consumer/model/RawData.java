package com.consumer.consumer.model;

public class RawData {
    private int id;
    private String target;
    private String data;
    private String processed;

    public RawData() {

    }

    public RawData(int id, String target, String data) {
        this.id = id;
        this.target = target;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "RawData {" +
                "id=" + id +
                ", target='"+target + "\'" +
                ", data='" + data + "\'" +
                ", processed='" + processed + "\'" +
                "}" ;
    }
}
