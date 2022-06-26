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
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHome extends AppCompatActivity {

    ProgressDialog progressDoalog;
    TextView nameview;
    String user,cname,dist;
    RecyclerView view1;
    TextView tpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        nameview = findViewById(R.id.user);
        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        cname = sharedPref.getString("cname", "");
        dist = sharedPref.getString("dist", "");
        nameview.setText("Signed in as "+cname);
        tpr = findViewById(R.id.tpr);

        view1 = findViewById(R.id.view1);
        getAlerts();
        getTPR();

    }

    private void getTPR() {
        try {
            MainApi service = Connection.getcon().create(MainApi.class);
            // Varii.p(getApplicationContext(),user);
            Call<List<Result>> call = service.getTPR(
                    dist
            );
            call.enqueue(new Callback<List<Result>>() {
                @Override
                public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                    Log.i("onResponse", response.message());
                    try {
                        List<Result> userList = response.body();
                        for (Result n : userList) {
                            SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            if (n.getResult().contains("ok")) {
                                String g[] = n.getResult().split(":");
                                tpr.setText("TPR Today : "+g[1]);
                            } else {
                                Varii.p(getApplicationContext(), n.getResult() + "");
                            }
                            progressDoalog.dismiss();
                        }
                    } catch (Exception e) {
                        progressDoalog.dismiss();
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
        }
    }

    public void getAlerts(){
        try {
            progressDoalog = new ProgressDialog(UserHome.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            // Varii.p(getApplicationContext(),user);
            Call<List<alertDAO>> call = service.getAlerts(
                    dist
            );

            call.enqueue(new Callback<List<alertDAO>>() {
                @Override
                public void onResponse(Call<List<alertDAO>> call, Response<List<alertDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<alertDAO> userList = response.body();

                        // set a GridLayoutManager with default vertical orientation and 2 number of columns
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        //      LinearLayoutManager lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

                        //  Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

                        alertAdapter recyclerViewAdapter = new alertAdapter(UserHome.this, userList);
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
                public void onFailure(Call<List<alertDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }

    public void profile(View view) {
        Intent i = new Intent(getApplicationContext(), UserProfile.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void privacy(View view) {
        Intent i = new Intent(getApplicationContext(), UserPrivacy.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void signout(View view) {
        SharedPreferences sharedPref =getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        Intent id = new Intent(getApplicationContext(), Login.class);
        startActivity(id);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void doctors(View view) {
        Intent i = new Intent(getApplicationContext(), UserDoctorsList.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void ashaworkers(View view) {
        Intent i = new Intent(getApplicationContext(), UserAshaWorkers.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void requestkit(View view) {
        Intent i = new Intent(getApplicationContext(), UserRequestKit.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void issuedTestKits(View view) {
        Intent i = new Intent(getApplicationContext(), UserIssuedTestKits.class);
        i.putExtra("code","0");
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void bookmedicine(View view) {
        Intent i = new Intent(getApplicationContext(), UserShopList.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void medicineBookings(View view) {
        Intent i = new Intent(getApplicationContext(), UserMedicineBookings.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void requestService(View view) {
        Intent i = new Intent(getApplicationContext(), UserRequestService.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    public void serviceBookings(View view) {
        Intent i = new Intent(getApplicationContext(), UserServiceBookings.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
}