package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DocAddTestReport extends AppCompatActivity {

    ProgressDialog progressDoalog;
    Spinner result;
    EditText precaution;
    String user, dist, ldate, cat, pty = "";
    TextView name,phone,email,category;
    docTestCasesDAO ob;
    String v, caseid, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_add_test_report);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        category = findViewById(R.id.category);
        result = findViewById(R.id.result);
        precaution = findViewById(R.id.precaution);


        String[] items = new String[]{"SELECT RESULT", "Positive", "Negative"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items) {
            public boolean isEnabled(int position) {
                if (position == 0) {
                    pty = "";
                    return false;
                } else {
                    pty = items[position];
                    return true;
                }
            }
        };
        result.setAdapter(adapter);

        result.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (result.getSelectedItem().toString().equals("Positive")) {
                    precaution.setVisibility(View.VISIBLE);
                } else {
                    precaution.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Intent data = getIntent();
        v = data.getStringExtra("myJson");
        Gson gson = new Gson();
        ob = gson.fromJson(v,docTestCasesDAO.class);

        name.setText(ob.getPubname());
        phone.setText(ob.getPubcontact());
        email.setText(ob.getPubemail());
        category.setText("Test category : "+ob.getCategoryname());

        caseid = ob.getCaseid();

    }

    public void addreport(View view) {

        if(result.getSelectedItem().toString().equals("SELECT RESULT")){
            result.requestFocus();
            Varii.p(getApplicationContext(), "Type result");
        } else {

            if(result.getSelectedItem().toString().equals("Positive")){
                message = precaution.getText().toString();
            } else {
                message ="";
            }

            progressDoalog = new ProgressDialog(this);
            progressDoalog.setMessage("Loading..");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            try {
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.docAddreport(
                        caseid,
                        result.getSelectedItem().toString(),
                        message,
                        user
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
                                    Varii.p(getApplicationContext(), "Report added");
                                    onBackPressed();
                                } else if (n.getResult().contains("no")) {
                                    Varii.p(getApplicationContext(), "Something went wrong. Try again later.");
                                    onBackPressed();
                                } else {
                                    Varii.p(getApplicationContext(), n.getResult() + "");
                                    onBackPressed();
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), DocTestCases.class));
        finish();
    }

}