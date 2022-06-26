package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDoctorsList extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user;
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_doctors_list);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        view1 = findViewById(R.id.view1);

        getdata();

    }

    private void getdata() {
        try {
            progressDoalog = new ProgressDialog(UserDoctorsList.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<workerDAO>> call = service.getDoctorList(
                    user
            );

            call.enqueue(new Callback<List<workerDAO>>() {
                @Override
                public void onResponse(Call<List<workerDAO>> call, Response<List<workerDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<workerDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView



                        userDoctorAdapter recyclerViewAdapter = new userDoctorAdapter(UserDoctorsList.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<workerDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }
}