package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPlaceOrder extends AppCompatActivity {

    ProgressDialog progressDoalog;
    DatePicker datePicker;
    EditText holder, cvv, accno;
    TextView expdate;
    Calendar calendar;
    int year, month, day;
    String user,dist,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_place_order);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        Intent i = getIntent();
        total = i.getStringExtra("total");

        expdate = findViewById(R.id.expdate);
        holder = findViewById(R.id.holder);
        cvv = findViewById(R.id.cvv);
        accno = findViewById(R.id.accno);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month + 1);

    }

    public void setExpDate(View view) {
        final Calendar today =  Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(UserPlaceOrder.this, new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                String cmonth = String.valueOf((selectedMonth+1));
                if(cmonth.length() == 1){
                    cmonth = "0"+cmonth;
                }
                expdate.setText((cmonth)+"/"+selectedYear);
            }
        }, today.get(Calendar.YEAR),today.get(Calendar.MONTH));
        builder.setActivatedMonth(Calendar.JULY).setMinYear(year).setActivatedYear(today.get(Calendar.YEAR)).setMaxYear(year+10).setTitle("Select expiry month and year").build().show();
    }

    public void placeorder(View view) {
        if (holder.getText().toString().equals("")) {
            holder.requestFocus();
            Varii.p(getApplicationContext(), "Type account holder's name");
        } else if (cvv.getText().toString().equals("")) {
            cvv.requestFocus();
            Varii.p(getApplicationContext(), "Type CVV");
        } else if(cvv.getText().toString().length() != 3 ){
            cvv.requestFocus();
            Varii.p(getApplicationContext(), "Invalid CVV");
        } else if(!(cvv.getText().toString().matches("\\d+(?:\\.\\d+)?")) ){
            cvv.requestFocus();
            Varii.p(getApplicationContext(), "CVV contains invalid characters");
        } else if(expdate.getText().toString().equals("Expiry")){
            expdate.requestFocus();
            Varii.p(getApplicationContext(), "Select expiry month");
        } else if(accno.getText().toString().equals("")) {
            accno.requestFocus();
            Varii.p(getApplicationContext(), "Type account number");
        } else if(!(accno.getText().toString().matches("\\d+(?:\\.\\d+)?")) ){
            accno.requestFocus();
            Varii.p(getApplicationContext(), "Account number contains invalid characters");
        } else if (accno.getText().toString().length() < 9 || accno.getText().toString().length() > 18){
            accno.requestFocus();
            Varii.p(getApplicationContext(), "Invalid account number");
        } else {

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setMessage("Loading..");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            try {
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.userPlaceOrder(
                        user,
                        holder.getText().toString(),
                        cvv.getText().toString(),
                        expdate.getText().toString(),
                        accno.getText().toString(),
                        total
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
                                    Varii.p(getApplicationContext(), "Order placed");
                                    startActivity(new Intent(getApplicationContext(), UserMedicineBookings.class));
                                } else if (n.getResult().contains("no")) {
                                    Varii.p(getApplicationContext(), "Not enough balance in your account.");
                                } else if (n.getResult().contains("invalid")) {
                                    Varii.p(getApplicationContext(), "Invalid account details.");
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

//    @SuppressWarnings("deprecation")
//    public void setDate(View view) {
//        showDialog(999);
//        Toast.makeText(getApplicationContext(), "ca",
//                Toast.LENGTH_SHORT)
//                .show();
//    }

//    @Override
//    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
//        if (id == 999) {
//            return new DatePickerDialog(this,
//                    myDateListener, year, month, day);
//        }
//        return null;
//    }

//    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
//
//            showDate(arg1, arg2 + 1);
//        }
//    };
//
//    private void showDate(int year, int month) {
//        expdate.setText(new StringBuilder().append(month).append("/").append(year));
//    }
}