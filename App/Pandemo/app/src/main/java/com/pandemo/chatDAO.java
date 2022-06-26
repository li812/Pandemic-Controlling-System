package com.pandemo;

public class chatDAO {
    String workertype,sendertype,message,senddate,sendtime,status;

    public chatDAO(String workertype, String sendertype, String message, String senddate, String sendtime, String status) {
        this.workertype = workertype;
        this.sendertype = sendertype;
        this.message = message;
        this.senddate = senddate;
        this.sendtime = sendtime;
        this.status = status;
    }

    public String getWorkertype() {
        return workertype;
    }

    public void setWorkertype(String workertype) {
        this.workertype = workertype;
    }

    public String getSendertype() {
        return sendertype;
    }

    public void setSendertype(String sendertype) {
        this.sendertype = sendertype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
