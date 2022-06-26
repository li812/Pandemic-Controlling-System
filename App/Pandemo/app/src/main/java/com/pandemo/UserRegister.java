package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserRegister extends AppCompatActivity {

    EditText fullname, aadhaar, phone,  email, address, job, jobsector, jobdesc, password;
    TextView dob;
    Spinner district, location;
    List<districtDAO> st = null;
    String pty = "", fnm = "", flag1 = "", flag2 = "", faadhaar = "", fphoto = "";
    String user;
    String dist = "", loc = "";
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        fullname = findViewById(R.id.fullname);
        aadhaar = findViewById(R.id.aadhaar);
        phone = findViewById(R.id.phone);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        job = findViewById(R.id.job);
        jobsector = findViewById(R.id.jobsector);
        jobdesc = findViewById(R.id.jobdesc);
        password = findViewById(R.id.password);

        district = (Spinner) findViewById(R.id.district);
        location = (Spinner) findViewById(R.id.location);

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    String country = (String) parentView.getSelectedItem();
                    String g[] = country.split("-");
                    dist = g[0];
                    fillloc(dist);
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    String country = (String) parentView.getSelectedItem();
                    String g[] = country.split("-");
                    loc = g[0];
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        getDisrict();

    }

    public void fillloc(String x) {
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
            Call<List<loctionDAO>> call = service.getlocation(
                    x
            );

            progressDoalog = new ProgressDialog(UserRegister.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            progressDoalog.show();

            call.enqueue(new Callback<List<loctionDAO>>() {
                @Override
                public void onResponse(Call<List<loctionDAO>> call, Response<List<loctionDAO>> response) {
                    Log.i("onResponse", response.message());
                    // Log.i("autolog", "onResponse");
                    //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    List<loctionDAO> st = response.body();

                    ArrayList<String> l = new ArrayList<String>();
                    l.add("Select Location");
                    for (loctionDAO y : st) {
                        l.add(y.getLocation_id() + " - " + y.getLocation());
                    }
                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, l);

                    location.setAdapter(aa);
                    progressDoalog.dismiss();
                }

                @Override
                public void onFailure(Call<List<loctionDAO>> call, Throwable t) {
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

    public void getDisrict() {
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
            Call<List<districtDAO>> call = service.getdistrict();

            progressDoalog = new ProgressDialog(UserRegister.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            progressDoalog.show();

            call.enqueue(new Callback<List<districtDAO>>() {
                @Override
                public void onResponse(Call<List<districtDAO>> call, Response<List<districtDAO>> response) {
                    Log.i("onResponse", response.message());
                    // Log.i("autolog", "onResponse");
                    //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    List<districtDAO> st = response.body();

                    ArrayList<String> l = new ArrayList<String>();
                    l.add("Select District");
                    for (districtDAO y : st) {
                        l.add(y.getDistrict_id() + " - " + y.getDistrict());

                    }
                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, l);

                    district.setAdapter(aa);
                    progressDoalog.dismiss();
                }

                @Override
                public void onFailure(Call<List<districtDAO>> call, Throwable t) {
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
                dob.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.setTitle("Select your DOB");
        datePickerDialog.show();
    }

    public void userSignup(View view) {

        if (fullname.getText().toString().equals("")) {
            fullname.requestFocus();
            Varii.p(getApplicationContext(), "Type your fullname");
        } else if (aadhaar.getText().toString().equals("")) {
            aadhaar.requestFocus();
            Varii.p(getApplicationContext(), "Type your aadhaar");
        } else if (aadhaar.getText().toString().length() != 16) {
            aadhaar.requestFocus();
            Varii.p(getApplicationContext(), "Invalid aadhaar number");
        } else if (phone.getText().toString().equals("")) {
            phone.requestFocus();
            Varii.p(getApplicationContext(), "Type your phone number");
        } else if (phone.getText().toString().length() != 10) {
            phone.requestFocus();
            Varii.p(getApplicationContext(), "Invalid phone number");
        } else if (dob.getText().toString().equals("DOB")) {
            dob.requestFocus();
            Varii.p(getApplicationContext(), "Select you DOB");
        } else if (email.getText().toString().equals("")) {
            email.requestFocus();
            Varii.p(getApplicationContext(), "Type your e-mail id");
        } else if (!Varii.chee(email.getText().toString())) {
            email.requestFocus();
            Varii.p(getApplicationContext(), "Type a valid e-mail id");
        } else if (address.getText().toString().equals("")) {
            address.requestFocus();
            Varii.p(getApplicationContext(), "Type your address");
        } else if (job.getText().toString().equals("")) {
            job.requestFocus();
            Varii.p(getApplicationContext(), "Type your job");
        } else if (jobsector.getText().toString().equals("")) {
            jobsector.requestFocus();
            Varii.p(getApplicationContext(), "Type your job sector");
        } else if (jobdesc.getText().toString().equals("")) {
            jobdesc.requestFocus();
            Varii.p(getApplicationContext(), "Type your job description");
        } else if (district.getSelectedItem().toString().equals("SELECT DISTRICT")) {
            district.requestFocus();
            Varii.p(getApplicationContext(), "Select your district");
        } else if (location.getSelectedItem().toString().equals("SELECT DISTRICT")) {
            location.requestFocus();
            Varii.p(getApplicationContext(), "Select your location");
        } else {

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setMessage("Loading..");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            try {
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.userregister(
                        fullname.getText().toString(),
                        aadhaar.getText().toString(),
                        phone.getText().toString(),
                        dob.getText().toString(),
                        email.getText().toString(),
                        address.getText().toString(),
                        job.getText().toString(),
                        jobsector.getText().toString(),
                        jobdesc.getText().toString(),
                        dist,
                        loc,
                        password.getText().toString()
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
                                    editor.putString("logid", g[1]);
                                    editor.commit();

                                    user = sharedPref.getString("logid", "");
                                    Varii.p(getApplicationContext(), "Step 1 Complete");
                                    startActivity(new Intent(getApplicationContext(), UserRegister2.class));
                                } else if (n.getResult().contains("no")) {
                                    Varii.p(getApplicationContext(), "Something went wrong. Try again later.");
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
}