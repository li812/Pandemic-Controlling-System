package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    ProgressDialog progressDoalog;
    EditText uname, pass;
    String user = "", role = "";
    CheckBox c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        c1 = findViewById(R.id.c1);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        role = sharedPref.getString("role", "");
        if (!role.equals("") && !user.equals("")) {
            if (role.equals("user")) {
                startActivity(new Intent(getApplicationContext(), UserHome.class));
                finish();
            } else if (role.equals("doc")) {
                startActivity(new Intent(getApplicationContext(), DocHome.class));
                finish();
            } else if (role.equals("asha")) {
                startActivity(new Intent(getApplicationContext(), AshaHome.class));
                finish();
            }
        }
    }

    /*----------------------------------------------USER SIGN IN---------------------------------------------*/

    public void login(View view) {

//        Intent i = new Intent(getApplicationContext(), UserHome.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        startActivity(i);

        try {
            progressDoalog = new ProgressDialog(Login.this);
            progressDoalog.setMessage("Verifying Access ..");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it

            if (uname.getText().toString().equals("")) {
                uname.requestFocus();
                Varii.p(getApplicationContext(), "Please enter your e-mail");
            } else if (pass.getText().toString().equals("")) {
                Varii.p(getApplicationContext(), "Please enter password");
                pass.requestFocus();
            } else {
                progressDoalog.show();
                MainApi service = Connection.getcon().create(MainApi.class);
                Call<List<Result>> call = service.login(

                        //Passing the values by getting it from editTexts
                        uname.getText().toString(),
                        pass.getText().toString()


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
                                    //   Log.e("error","3");
                                    String g[] = n.getResult().split(":");
                                    editor.putString("logid", g[1]);
                                    editor.putString("role", g[2]);
                                    editor.putString("cname", g[3]);
                                    editor.putString("dist", g[4]);
                                    editor.putString("loc", g[5]);
                                    editor.putString("username", uname.getText().toString());

                                    editor.commit();

                                    if(sharedPref.getString("role", "").equals("user")){
                                        startActivity(new Intent(getApplicationContext(), UserHome.class));
                                        finish();
                                    } else if(sharedPref.getString("role", "").equals("doc")){
                                        startActivity(new Intent(getApplicationContext(), DocHome.class));
                                        finish();
                                    } else if(sharedPref.getString("role", "").equals("asha")){
                                        startActivity(new Intent(getApplicationContext(), AshaHome.class));
                                        finish();
                                    }

                                } else if (n.getResult().contains("invalid") ) {
                                    Varii.p(getApplicationContext(), "Invalid username or password");
                                } else {
                                    Varii.p(getApplicationContext(), n.getResult() + "");
                                }
                                //    Log.e("error","6");
                            }
                            Log.e("error", "123");
                            progressDoalog.dismiss();
                        } catch (Exception e) {
                            progressDoalog.dismiss();
                              Varii.p(getApplicationContext(), " r"+e );
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

    /*----------------------------------------------USER SIGN UP---------------------------------------------*/

    public void userSignup(View view) {
        Intent i = new Intent(getApplicationContext(), UserRegister.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    /*-----------------------------------------------BACK PRESS----------------------------------------------*/

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void sp(View view)
    {
       // Varii.p(getApplicationContext(),pass.getInputType()+"");
        if(c1.isChecked()){
            pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        else {
            pass.setInputType(129);

        }
    }
}