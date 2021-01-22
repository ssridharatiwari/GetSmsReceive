package com.codunite.bhagyamatka.Retrofit;

import android.content.Context;
import android.util.Log;
import java.util.LinkedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebService {
    private Context context;
    private String postUrl;
    private String[] imagePath;
    private WebServiceListener listener;

    private LinkedList<String> lstUploadData = new LinkedList<>();
    private String bodyString;

    public WebService(Context context, WebServiceListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public WebService(Context context, String postUrl, LinkedList<String> lstUploadData, WebServiceListener listener) {
        this.context = context;
        this.postUrl = postUrl;
        this.lstUploadData = lstUploadData;
        this.listener = listener;
    }

    public void LoadDataRetrofit(Call<String> call) {
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    System.out.println(response.toString()+"......webservice response..........");
                    int code = response.code();
//                    if (response.isSuccessful()) {
                    if (code == 200 || code == 300) {
                        bodyString = response.body();
                    }else {
                        bodyString = response.message();
                        listener.onWebServiceError(bodyString, postUrl);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listener.onWebServiceActionComplete(bodyString, postUrl);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("SMSPOST", t.toString());
            }
        });
    }

    public Call<String> callReturn() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        switch (postUrl) {
            case ApiInterface.SENDSMS:
                return apiService.SendSms(lstUploadData.get(0), lstUploadData.get(1));
        }
        return null;
    }
}
