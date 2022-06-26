package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    ProgressDialog progressDoalog;
    TextView t, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11;
    String user = "", role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        role = sharedPref.getString("role", "");
        t = findViewById(R.id.fullname);
        t1 = findViewById(R.id.address);
        t2 = findViewById(R.id.email);
        t3 = findViewById(R.id.phone);
        t4 = findViewById(R.id.aadhaar);
        t7 = findViewById(R.id.job);
        t5 = findViewById(R.id.jobsector);
        t6 = findViewById(R.id.jobdesc);
        getdata();

    }

    private void getdata() {
        try {
            progressDoalog = new ProgressDialog(UserProfile.this);
            //progressDoalog.setMax(100);
            progressDoalog.setMessage("Updating ..");
            // progressDoalog.setTitle("Cyberia ias");
            // progressDoalog.setIcon();
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it


            progressDoalog.show();
            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<publicDAO>> call = service.getUserProfile(
                    user
            );
            call.enqueue(new Callback<List<publicDAO>>() {
                @Override
                public void onResponse(Call<List<publicDAO>> call, Response<List<publicDAO>> response) {
                    Log.i("onResponse", response.message());
                    // Log.i("autolog", "onResponse");
//                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    try {
                        progressDoalog.dismiss();
                        // Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT).show();
                        List<publicDAO> userList = response.body();
                        for (publicDAO n : userList) {

                            t.setText("" + n.getPubname());
                            t1.setText("" + n.getPubaddress());
                            t2.setText("" + n.getPubemail());
                            t3.setText("" + n.getPubcontact());
                            t4.setText("" + n.getAddharno());
                            t7.setText("" + n.getPubjob());
                            t5.setText("Sector : " + n.getPubjobsector());
                            t6.setText("Description : " + n.getPubjobdescrp());

                        }
                    } catch (Exception e) {
                        progressDoalog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<publicDAO>> call, Throwable t) {
                    Varii.p(getApplicationContext(), "Bad network.. Please try again later.");
                    progressDoalog.dismiss();
                }
            });


        } catch (Exception e) {
            progressDoalog.dismiss();
            Varii.p(getApplicationContext(), e + "");
        }
    }

    public void signout(View view) {
        SharedPreferences sharedPref =getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        Intent id = new Intent(getApplicationContext(), Login.class);
        startActivity(id);
    }
}