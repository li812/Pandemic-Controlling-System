package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;

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

public class UserPrivacy extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText email, cpass, npass, cnpass;
    String user, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_privacy);

        email = findViewById(R.id.email);
        cpass = findViewById(R.id.cpass);
        npass = findViewById(R.id.npass);
        cnpass = findViewById(R.id.cnpass);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        username = sharedPref.getString("username", "");

    }

    public void changePassword(View view) {

        if (email.getText().toString().equals("")) {
            email.requestFocus();
            Varii.p(getApplicationContext(), "Type your email id");
        } else if (cpass.getText().toString().equals("")) {
            cpass.requestFocus();
            Varii.p(getApplicationContext(), "Type your current password");
        } else if (npass.getText().toString().equals("")) {
            npass.requestFocus();
            Varii.p(getApplicationContext(), "Type your new password");
        } else if (cnpass.getText().toString().equals("")) {
            cnpass.requestFocus();
            Varii.p(getApplicationContext(), "Confirm your new password");
        } else if (!cnpass.getText().toString().equals(npass.getText().toString())) {
            cnpass.requestFocus();
            Varii.p(getApplicationContext(), "Confirm password mismatch");
        } else {

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            try {
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.changePassword(
                        cpass.getText().toString(),
                        npass.getText().toString(),
                        user
                );
                call.enqueue(new Callback<List<Result>>() {
                    @Override
                    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                        Log.i("onResponse", response.message());
                        // Log.i("autolog", "onResponse");

                        //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        try {
                            List<Result> userList = response.body();
                            for (Result n : userList) {
                                SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                if (n.getResult().contains("ok")) {
                                    Varii.p(getApplicationContext(), "Password updated");
                                    onBackPressed();
                                } else if (n.getResult().contains("no")) {
                                    Varii.p(getApplicationContext(), "Current password mismatch.");
                                } else {
                                    Varii.p(getApplicationContext(), n.getResult() + "");
                                }
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            Varii.p(getApplicationContext(),""+e);
                            progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Result>> call, Throwable t) {
                        Varii.p(getApplicationContext(), "Bad network connection.. Please try again later.");
                        progressDialog.dismiss();
                    }
                });
            } catch (Exception e) {
                progressDialog.dismiss();
            }
        }
    }

}