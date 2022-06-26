package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AshaKitRequests extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user, dist, ldate, cat, pty = "";
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha_kit_requests);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        Intent i =getIntent();
        String code = i.getStringExtra("code");
        if(code.equals("1"))
        {
            String bk=i.getStringExtra("bardata");
            String kitid = i.getStringExtra("kitid");
            issueKitRequest(bk,kitid);
        }
        
        view1 = findViewById(R.id.view1);
        getKitRequests();

    }

    private void issueKitRequest(String bk, String kitid) {
        try {
            progressDoalog = new ProgressDialog(AshaKitRequests.this);
            //progressDoalog.setMax(100);
            progressDoalog.setMessage("Updating ..");
            // progressDoalog.setTitle("Cyberia ias");
            // progressDoalog.setIcon();
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it


            progressDoalog.show();
            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<Result>> call = service.issueRequest(
                    bk,
                    kitid
            );
            call.enqueue(new Callback<List<Result>>() {
                @Override
                public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                    Log.i("onResponse", response.message());
                    // Log.i("autolog", "onResponse");
//                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    try {
                        List<Result> userList = response.body();
                        for (Result n : userList) {
                            SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            if (n.getResult().contains("ok")) {
                                Varii.p(getApplicationContext(), "Kit issued");
                            } else if (n.getResult().contains("no")) {
                                Varii.p(getApplicationContext(), "Something went wrong");
                            } else {
                                Varii.p(getApplicationContext(), n.getResult() + "");
                            }

                        }
                        progressDoalog.dismiss();
                    } catch (Exception e) {
                        progressDoalog.dismiss();
                        Varii.p(getApplicationContext(),"OOPS some thing went wrong!");
                    }
                }

                @Override
                public void onFailure(Call<List<Result>> call, Throwable t) {
                    Varii.p(getApplicationContext(), "Bad network.. Please try again later.");
                    progressDoalog.dismiss();
                }
            });
        } catch (Exception e) {
            progressDoalog.dismiss();
            Varii.p(getApplicationContext(), e + "");
        }
    }


    public void getKitRequests(){
        try {
            progressDoalog = new ProgressDialog(AshaKitRequests.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<ashaReqKitDAO>> call = service.ashaGetKitDetails(
                    user
            );

            call.enqueue(new Callback<List<ashaReqKitDAO>>() {
                @Override
                public void onResponse(Call<List<ashaReqKitDAO>> call, Response<List<ashaReqKitDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<ashaReqKitDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


                        ashaReqKitAdapter recyclerViewAdapter = new ashaReqKitAdapter(AshaKitRequests.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<ashaReqKitDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), AshaHome.class));
        finish();
    }

}