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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMedicineBookings extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user, dist;
    RecyclerView view1, view2;
    TextView total, empty,empty2;

    LinearLayout orderpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_medicine_bookings);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        total = findViewById(R.id.total);

        empty = findViewById(R.id.empty);
        empty2 = findViewById(R.id.empty2);
        orderpanel = findViewById(R.id.orderpanel);

        Varii.total = 0;
        getCartItems();
        getOrderHistory();


    }

    private void getCartItems() {
        try {
            progressDoalog = new ProgressDialog(UserMedicineBookings.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<itemDAO>> call = service.getCartItems(
                    user
            );

            call.enqueue(new Callback<List<itemDAO>>() {
                @Override
                public void onResponse(Call<List<itemDAO>> call, Response<List<itemDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<itemDAO> userList = response.body();

                        if(userList.size()==0)
                        {
                            orderpanel.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        }else{
                            orderpanel.setVisibility(View.VISIBLE);
                            empty.setVisibility(View.GONE);
                        }

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


                        userItemAdapter recyclerViewAdapter = new userItemAdapter(UserMedicineBookings.this, userList, total);

                        view1.setAdapter(recyclerViewAdapter);

                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<itemDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(), e + "");
        }
    }

    private void getOrderHistory() {

        try {
            progressDoalog = new ProgressDialog(UserMedicineBookings.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<itemDAO>> call = service.getUserOrderItems(
                    user
            );

            call.enqueue(new Callback<List<itemDAO>>() {
                @Override
                public void onResponse(Call<List<itemDAO>> call, Response<List<itemDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<itemDAO> userList = response.body();

                        if(userList.size()==0)
                        {
                            empty2.setVisibility(View.VISIBLE);
                        }else{
                            empty2.setVisibility(View.GONE);
                        }

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view2.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


                        userItemAdapter recyclerViewAdapter = new userItemAdapter(UserMedicineBookings.this, userList, total);

                        view2.setAdapter(recyclerViewAdapter);

                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                }

                @Override
                public void onFailure(Call<List<itemDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(), e + "");
        }

    }

    public void placeorder(View view) {

        if(total.getText().toString().equals("0")){
            Varii.p(getApplicationContext(),"Cart is empty");
        }else {
            Intent a = new Intent(getApplicationContext(), UserPlaceOrder.class);
            a.putExtra("total", "" + total.getText().toString());
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(getApplicationContext(), UserHome.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}