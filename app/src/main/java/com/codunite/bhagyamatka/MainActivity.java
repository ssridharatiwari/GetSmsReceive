package com.codunite.bhagyamatka;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.codunite.bhagyamatka.Retrofit.ApiInterface;
import com.codunite.bhagyamatka.Retrofit.WebService;
import com.codunite.bhagyamatka.Retrofit.WebServiceListener;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements MessageListener, WebServiceListener {
    private TextView txtSms;
    private CheckInternet checkNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        requestSmsPermission();
        //Register sms listener
        MessageReceiver.bindListener(this);
        txtSms = (TextView)findViewById(R.id.txt_sms);
    }

    @Override
    public void messageReceived(SmsModel message) {
        txtSms.setText(message.getSmsBody()+"\n"+message.getSmsDisplaySms()+"\n"+
                message.getSmsFrom()+"\n"+message.getSmsMessage()+"\n"+
                message.getSmsOriginating()+"\n"+message.getSmsTimeInMillis());

        checkNetwork = new CheckInternet(MainActivity.this);
        boolean isNetWorkAvailable = checkNetwork.isConnectingToInternet();


        if (isNetWorkAvailable) {
            LinkedList lstUploadData = new LinkedList<>();
            lstUploadData.add(message.getSmsOriginating());
            lstUploadData.add(message.getSmsMessage());
            callWebServiceWithoutLoader(ApiInterface.SENDSMS, lstUploadData);
        }else {

        }

    }

    private void callWebServiceWithoutLoader(String postUrl, LinkedList<String> lstUploadData) {
        WebService webService = new WebService(MainActivity.this, postUrl, lstUploadData, this);
        webService.LoadDataRetrofit(webService.callReturn());
    }

    @Override
    public void onWebServiceActionComplete(String result, String url) {
        System.out.println(result + ".........jsonresponse....." + url);
        if (url.contains(ApiInterface.SENDSMS)) {
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onWebServiceError(String result, String url) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    private static final int PERMISSION_BROADCAST_SMS = 123;

    private void requestSmsPermission() {
        // check permission is given
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    PERMISSION_BROADCAST_SMS);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_BROADCAST_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                } else {
                    // permission denied
                }
                return;
            }
        }
    }

    private void sendSms(String phoneNumber, String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}