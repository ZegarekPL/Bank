package com.example.app.objects;

public class History {
    private String id_sender;
    private String id_reciver;
    private String history_date;
    private String history_amount;
    private String history_number_sender;
    private String history_number_reciver;
    private String history_type;

    public History(){
        this.id_sender="";
        this.id_reciver="";
        this.history_date="";
        this.history_amount="";
        this.history_number_sender="";
        this.history_number_reciver="";
        this.history_type="";
    }

    public String getId_sender() {
        return id_sender;
    }
    public void setId_sender(String id_sender) {
        this.id_sender = id_sender;
    }

    public String getId_reciver() {
        return id_reciver;
    }
    public void setId_reciver(String id_reciver) {
        this.id_reciver = id_reciver;
    }

    public String getHistory_date() {
        return history_date;
    }
    public void setHistory_date(String history_date) {
        this.history_date = history_date;
    }

    public String getHistory_amount() {
        return history_amount;
    }
    public void setHistory_amount(String history_amount) {
        this.history_amount = history_amount;
    }

    public String getHistory_number_sender() {
        return history_number_sender;
    }
    public void setHistory_number_sender(String history_number_sender) {
        this.history_number_sender = history_number_sender;
    }

    public String getHistory_number_reciver() {
        return history_number_reciver;
    }
    public void setHistory_number_reciver(String history_number_reciver) {
        this.history_number_reciver = history_number_reciver;
    }

    public String getHistory_type() {
        return history_type;
    }
    public void setHistory_type(String history_type) {
        this.history_type = history_type;
    }
}
