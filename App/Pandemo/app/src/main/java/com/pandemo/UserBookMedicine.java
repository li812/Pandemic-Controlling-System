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
import android.view.SurfaceView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBookMedicine extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user,dist;
    RecyclerView view1;

    shopDAO ob;
    String v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book_medicine);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist","");
        view1 = findViewById(R.id.view1);

        Intent data = getIntent();
        v = data.getStringExtra("myJson");
        Gson gson = new Gson();
        ob = gson.fromJson(v,shopDAO.class);

        getMedicines();

    }

    private void getMedicines() {
        try {
            progressDoalog = new ProgressDialog(UserBookMedicine.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<medicineDAO>> call = service.getMedicines(
                    ob.getMedshop_id()
            );

            call.enqueue(new Callback<List<medicineDAO>>() {
                @Override
                public void onResponse(Call<List<medicineDAO>> call, Response<List<medicineDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<medicineDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView



                        userMedicineAdapter recyclerViewAdapter = new userMedicineAdapter(UserBookMedicine.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<medicineDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }
}