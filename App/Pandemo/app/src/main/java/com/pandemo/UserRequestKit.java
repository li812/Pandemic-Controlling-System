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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRequestKit extends AppCompatActivity {

    ProgressDialog progressDoalog;
    EditText lreqdate;
    Spinner category;
    String user, dist, ldate, cat, pty = "";
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_kit);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        lreqdate = findViewById(R.id.lreqdate);
        category = (Spinner) findViewById(R.id.category);
        view1 = findViewById(R.id.view1);

        getCategory();
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    String country = (String) parentView.getSelectedItem();
                    String g[] = country.split(" - ");
                    cat = g[0];
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        getKitStatus();
    }

    public void getCategory() {
        try {

            String url = Varii.URL;
            Retrofit retrofit = null;
            // Log.i("autolog", "retrofit");

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .client(Varii.okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //    Log.i("autolog", "build();");
            }


            MainApi service = retrofit.create(MainApi.class);
            Call<List<categoryDAO>> call = service.getCategory();

            progressDoalog = new ProgressDialog(UserRequestKit.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            progressDoalog.show();

            call.enqueue(new Callback<List<categoryDAO>>() {
                @Override
                public void onResponse(Call<List<categoryDAO>> call, Response<List<categoryDAO>> response) {
                    Log.i("onResponse", response.message());
                    // Log.i("autolog", "onResponse");
                    //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    List<categoryDAO> st = response.body();

                    ArrayList<String> l = new ArrayList<String>();
                    l.add("SELECT CATEGORY");
                    for (categoryDAO y : st) {
                        l.add(y.getCategory_id() + " - " + y.getCategoryname());

                    }
                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, l) {
                        public boolean isEnabled(int position) {
                            if (position == 0) {
                                pty = "";
                                return false;
                            } else {
                                pty = l.get(position);
                                return true;
                            }
                        }
                    };
                    category.setAdapter(aa);
                    progressDoalog.dismiss();
                }

                @Override
                public void onFailure(Call<List<categoryDAO>> call, Throwable t) {
                    Varii.p(getApplicationContext(), "Bad Network!.. Please try again later: " + t.getMessage());
                    progressDoalog.dismiss();
                    onBackPressed();
                }
            });

        } catch (Exception e) {
            Varii.p(getApplicationContext(), "Bad Network!.. Please try again later :c2----" + e);
            onBackPressed();
        }
    }

    public void request(View view) {
        try {
            if (category.getSelectedItem().toString().equals("SELECT JOB SECTOR")) {
                category.requestFocus();
                Varii.p(getApplicationContext(), "Select a category");
            } else {
                progressDoalog = new ProgressDialog(UserRequestKit.this);
                progressDoalog.setMessage("Verifying Access ..");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                ldate = lreqdate.getText().toString();

                progressDoalog.show();

                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.userRequestKit(

                        ldate,
                        user,
                        dist,
                        cat

                );
                call.enqueue(new Callback<List<Result>>() {
                    @Override
                    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                        Log.i("onResponse", response.message());
                        // Log.i("autolog", "onResponse");

                        //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        try {

                            List<Result> userList = response.body();

//Varii.p(getApplicationContext(),response.body().toString());
                            for (Result n : userList) {
                                SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                if (n.getResult().contains("ok")) {
                                    Varii.p(getApplicationContext(), "Test kit requested");
                                    finish();
                                    startActivity(getIntent());
                                } else {
                                    Varii.p(getApplicationContext(), n.getResult() + "");
                                }
                                //    Log.e("error","6");
                            }
                            Log.e("error", "123");
                            progressDoalog.dismiss();
                        } catch (Exception e) {
                            progressDoalog.dismiss();
                            Varii.p(getApplicationContext(), " r" + e);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Result>> call, Throwable t) {
                        Varii.p(getApplicationContext(), t.getMessage() + "Bad network.. Please try again later");
                        progressDoalog.dismiss();
                    }
                });
            }

        } catch (Exception e) {
            progressDoalog.dismiss();
            Varii.p(getApplicationContext(), "v" + e);
        }
    }

    public void getKitStatus(){
        try {
            progressDoalog = new ProgressDialog(UserRequestKit.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<reqKitDAO>> call = service.getKitDetails(
                    user
            );

            call.enqueue(new Callback<List<reqKitDAO>>() {
                @Override
                public void onResponse(Call<List<reqKitDAO>> call, Response<List<reqKitDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<reqKitDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView



                        reqKitAdapter recyclerViewAdapter = new reqKitAdapter(UserRequestKit.this, userList);

                        view1.setAdapter(recyclerViewAdapter);

                        progressDoalog.dismiss();
                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                        progressDoalog.dismiss();
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<reqKitDAO>> call, Throwable t) {
                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                    progressDoalog.dismiss();
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
            progressDoalog.dismiss();
        }
    }
}