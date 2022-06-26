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

public class AshaVerifiedKits extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user, dist, ldate, cat, pty = "";
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha_verified_kits);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        view1 = findViewById(R.id.view1);
        getVerifiedKitRequests();

    }

    private void getVerifiedKitRequests() {
        try {
            progressDoalog = new ProgressDialog(AshaVerifiedKits.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<ashaReqKitDAO>> call = service.ashaVerifiedKitDetails(
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


                        ashaReqKitAdapter recyclerViewAdapter = new ashaReqKitAdapter(AshaVerifiedKits.this, userList);

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
}