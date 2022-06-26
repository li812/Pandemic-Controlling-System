package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserShopList extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user,dist;
    RecyclerView view1;
    EditText g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop_list);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist","");
        view1 = findViewById(R.id.view1);
g=findViewById(R.id.srch);
        getdata();

    }

    private void getdata() {
        try {
            progressDoalog = new ProgressDialog(UserShopList.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<shopDAO>> call = service.getShopList(
                    dist
            );

            call.enqueue(new Callback<List<shopDAO>>() {
                @Override
                public void onResponse(Call<List<shopDAO>> call, Response<List<shopDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<shopDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView



                        userShopAdapter recyclerViewAdapter = new userShopAdapter(UserShopList.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<shopDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }

    public void serch(View view) {
        try {
            progressDoalog = new ProgressDialog(UserShopList.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
       String msg=g.getText().toString();
            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<shopDAO>> call = service.getShopList1(
                    dist,msg
            );

            call.enqueue(new Callback<List<shopDAO>>() {
                @Override
                public void onResponse(Call<List<shopDAO>> call, Response<List<shopDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<shopDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView



                        userShopAdapter recyclerViewAdapter = new userShopAdapter(UserShopList.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<shopDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }
}