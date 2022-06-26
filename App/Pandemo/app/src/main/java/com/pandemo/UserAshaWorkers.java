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

public class UserAshaWorkers extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user;
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_asha_workers);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        view1 = findViewById(R.id.view1);

        getdata();

    }

    private void getdata() {
        try {
            progressDoalog = new ProgressDialog(UserAshaWorkers.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<workerDAO>> call = service.getAshaworkers(
                    user
            );

            call.enqueue(new Callback<List<workerDAO>>() {
                @Override
                public void onResponse(Call<List<workerDAO>> call, Response<List<workerDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<workerDAO> userList = response.body();
                        // Varii.p(getApplicationContext(),response.body()+"");
  /*                      if (userList.size() == 0) {
                            v1.setVisibility(View.VISIBLE);
                        } else {
                            v1.setVisibility(View.GONE);
                        }
*/
                        // Log.i("autolog", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");

                        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        // set a GridLayoutManager with default vertical orientation and 2 number of columns
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        //      LinearLayoutManager lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

                        //  Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

                        userDoctorAdapter recyclerViewAdapter = new userDoctorAdapter(UserAshaWorkers.this, userList);
                        // Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
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