package com.codunite.bhagyamatka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MessageReceiver extends BroadcastReceiver {
    private static MessageListener mListener;
    private SmsModel model;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String message = "Sender : " + smsMessage.getDisplayOriginatingAddress()
                    + "Email From: " + smsMessage.getEmailFrom()
                    + "Emal Body: " + smsMessage.getEmailBody()
                    + "Display message body: " + smsMessage.getDisplayMessageBody()
                    + "Time in millisecond: " + smsMessage.getTimestampMillis()
                    + "Message: " + smsMessage.getMessageBody();

            model = new SmsModel(smsMessage.getDisplayOriginatingAddress(),
                    smsMessage.getEmailFrom(), smsMessage.getEmailBody(), smsMessage.getDisplayMessageBody(),
                    smsMessage.getTimestampMillis()+"", smsMessage.getMessageBody());

            mListener.messageReceived(model);
        }
    }

    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
}