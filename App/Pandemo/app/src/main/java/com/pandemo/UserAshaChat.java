package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAshaChat extends AppCompatActivity {

    workerDAO ob;
    TextView ashaname;
    ProgressDialog progressDoalog;
    String user;
    RecyclerView view1;
    EditText msg;
    int total = 0;
    //-------------------------------
    Parcelable recyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_asha_chat);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        view1 = findViewById(R.id.view1);
        msg = findViewById(R.id.msg);

        Intent i = getIntent();
        String v = i.getStringExtra("myJson");
        Gson gson = new Gson();
        ob = gson.fromJson(v, workerDAO.class);

        ashaname = findViewById(R.id.docname);
        ashaname.setText("Chat with " + ob.getEmpname());


//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
//        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
//
//
//        final Handler handler = new Handler();
//        final int delay = 2000; // 1000 milliseconds == 1 second
//
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                getdata();
//                handler.postDelayed(this, delay);
//            }
//        }, delay);

    }

//    private void getdata() {
//        try {
//            MainApi service = Connection.getcon().create(MainApi.class);
//            Call<List<chatDAO>> call = service.getUserChat(
//                    user,
//                    ob.getWorkerid()
//            );
//
//            call.enqueue(new Callback<List<chatDAO>>() {
//                @Override
//                public void onResponse(Call<List<chatDAO>> call, Response<List<chatDAO>> response) {
//                    Log.i("onResponse", response.message());
//                    int i = 0;
//                    try {
//                        List<chatDAO> userList = response.body();
//
//                        userChatAdapter recyclerViewAdapter = new userChatAdapter(userDoctorChat.this, userList);
//
//                        // Save state
//                        recyclerViewState = view1.getLayoutManager().onSaveInstanceState();
//                        // Restore state
//                        view1.getLayoutManager().onRestoreInstanceState(recyclerViewState);
//
//                        view1.setAdapter(recyclerViewAdapter);
//
//                        if(userList.size()>total)                       //get new message
//                        {
//                            total = userList.size();
//                            view1.scrollToPosition(userList.size() - 1);   //scroll to bottom
//                        } else {
//
//                        }
//
//
//                    } catch (Exception e) {
//                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<List<chatDAO>> call, Throwable t) {
//
//                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
//                }
//            });
//        } catch (Exception e) {
//            Varii.p(getApplicationContext(), e + "");
//        }
//    }
//
//    public void send(View view) {
//
//        try {
//            if (msg.getText().toString().equals("")) {
//                msg.requestFocus();
//            } else {
//                progressDoalog = new ProgressDialog(userDoctorChat.this);
//                progressDoalog.setMessage("Verifying Access ..");
//                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//                progressDoalog.show();
//
//                MainApi service = Connection.getcon().create(MainApi.class);
//                Call<List<Result>> call = service.userSendMessage(
//
//                        user,
//                        ob.getWorkerid(),
//                        msg.getText().toString()
//
//                );
//                call.enqueue(new Callback<List<Result>>() {
//                    @Override
//                    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
//                        Log.i("onResponse", response.message());
//                        // Log.i("autolog", "onResponse");
//
//                        //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
//                        try {
//
//                            List<Result> userList = response.body();
//
////Varii.p(getApplicationContext(),response.body().toString());
//                            for (Result n : userList) {
//                                SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPref.edit();
//                                if (n.getResult().contains("ok")) {
//                                    msg.setText("");
//                                } else {
//                                    Varii.p(getApplicationContext(), n.getResult() + "");
//                                }
//                                //    Log.e("error","6");
//                            }
//                            Log.e("error", "123");
//                            progressDoalog.dismiss();
//                        } catch (Exception e) {
//                            progressDoalog.dismiss();
//                            Varii.p(getApplicationContext(), " r" + e);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Result>> call, Throwable t) {
//                        Varii.p(getApplicationContext(), t.getMessage() + "Bad network.. Please try again later");
//                        progressDoalog.dismiss();
//                    }
//                });
//            }
//
//        } catch (Exception e) {
//            progressDoalog.dismiss();
//            Varii.p(getApplicationContext(), "v" + e);
//        }
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        Intent a = new Intent(getApplicationContext(), UserHome.class);
//        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(a);
//        finishAndRemoveTask();
//    }

}