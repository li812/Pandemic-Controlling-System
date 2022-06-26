package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRequestService extends AppCompatActivity {

    ProgressDialog progressDoalog;
    DatePicker datePicker;
    EditText req, reason;
    TextView date;
    Calendar calendar;
    int year, month, day;
    String user, dist, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_service);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        date = findViewById(R.id.date);
        req = findViewById(R.id.req);
        reason = findViewById(R.id.reason);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public void sendRequest(View view) {

        if (reason.getText().toString().equals("")) {
            reason.requestFocus();
            Varii.p(getApplicationContext(), "Write reason for service");
        } else if (req.getText().toString().equals("")) {
            req.requestFocus();
            Varii.p(getApplicationContext(), "Write service requirements");
        } else if(date.getText().toString().equals("Service date")){
            date.requestFocus();
            Varii.p(getApplicationContext(), "Select a date for service");
        } else {

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setMessage("Please wait..");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            try {
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.userSendServiceReq(
                        user,
                        reason.getText().toString(),
                        req.getText().toString(),
                        date.getText().toString(),
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
                                    Varii.p(getApplicationContext(), "Request send");
                                    onBackPressed();
                                } else if (n.getResult().contains("no")) {
                                    Varii.p(getApplicationContext(), "Something went wrong.");
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

    }

    public void setDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Locale id = new Locale("in", "ID");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", id);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.setTitle("Select date for service");
        datePickerDialog.show();
    }
}