package com.codunite.bhagyamatka;

public class SmsModel {
    private String smsOriginating, smsFrom, smsBody, smsDisplaySms, smsTimeInMillis, smsMessage;

    public SmsModel(String originating, String smsFrom, String smsBody, String smsDisplaySms, String smsTimeInMillis, String smsMessage) {
        this.smsOriginating = originating;
        this.smsFrom = smsFrom;
        this.smsBody = smsBody;
        this.smsDisplaySms = smsDisplaySms;
        this.smsTimeInMillis = smsTimeInMillis;
        this.smsMessage = smsMessage;
    }

    public String getSmsOriginating() {
        return smsOriginating;
    }

    public void setSmsOriginating(String smsOriginating) {
        this.smsOriginating = smsOriginating;
    }

    public String getSmsFrom() {
        return smsFrom;
    }

    public void setSmsFrom(String smsFrom) {
        this.smsFrom = smsFrom;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public String getSmsDisplaySms() {
        return smsDisplaySms;
    }

    public void setSmsDisplaySms(String smsDisplaySms) {
        this.smsDisplaySms = smsDisplaySms;
    }

    public String getSmsTimeInMillis() {
        return smsTimeInMillis;
    }

    public void setSmsTimeInMillis(String smsTimeInMillis) {
        this.smsTimeInMillis = smsTimeInMillis;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }
}
