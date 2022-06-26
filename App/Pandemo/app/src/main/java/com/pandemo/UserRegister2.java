package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserRegister2 extends AppCompatActivity {

    String flag1 = "", flag2 = "", fnm = "";
    String user;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register2);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");

    }

    //-------------------------------------------------- TO SIGN IN PAGE --------------------------------------------------

    public void loginPage(View view) {
        if(!flag1.equals("") && !flag2.equals("")) {

            Varii.p(getApplicationContext(),"Please wait for admin verification");

            Intent i = new Intent(getApplicationContext(), Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
        } else {
            Varii.p(getApplicationContext(),"Please upload your photo and aadhaar");
        }
    }

    //--------------------------------------------------- AADHAAR BUTTON ---------------------------------------------------

    public void submitit1(View view) {
        if (ContextCompat.checkSelfPermission(
                UserRegister2.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            UserRegister2.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select your image"), 1);
        }
    }

    //--------------------------------------------------- PHOTO BUTTON ---------------------------------------------------

    public void submitit2(View view) {
        if (ContextCompat.checkSelfPermission(
                UserRegister2.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            UserRegister2.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            2);

        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select your image"), 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
//            Varii.p(getApplicationContext(),"1");
            // check if the request code is same as what is passed  here it is 2
            if (requestCode == 1) {
//                Varii.p(getApplicationContext(),"11");
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    String uriString = uri.toString();
//                    Varii.p(getApplicationContext(), "15sdsdf.file name :" + uriString);
                    File myFile = new File(uriString);
                    fnm = myFile.getName();
                    // myfile.get
                    String path = uploads.getFilePathFromURI(getApplicationContext(), uri);
                    Log.d("ioooo", path);
                    uploadAadhaar(path);
                }
            } else if (requestCode == 2) {
//                Varii.p(getApplicationContext(),"2");
                if (resultCode == RESULT_OK) {
//                    Varii.p(getApplicationContext(),"22");
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    String uriString = uri.toString();
//                    Varii.p(getApplicationContext(), "15sdsdf.file name :" + uriString);
                    File myFile = new File(uriString);
                    fnm = myFile.getName();
                    // myfile.get
                    String path = uploads.getFilePathFromURI(getApplicationContext(), uri);
                    Log.d("ioooo", path);
                    uploadImage(path);
                }
            }
        } catch (Exception e) {
        }
        {
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void uploadAadhaar(String path) {
//        Varii.p(getApplicationContext(),"3");

        // String pdfname = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PDFInterface.IMAGEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Varii.p(getApplicationContext(),"31");

        //Create a file object using file path
        File file = new File(path);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("filename", user + ".jpg", requestBody);
//        Varii.p(getApplicationContext(),"32");

        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), user);
//        Varii.p(getApplicationContext(),"33");


        PDFInterface getResponse = retrofit.create(PDFInterface.class);
//        Varii.p(getApplicationContext(),"34");

        Call<String> call = getResponse.uploadAadhaar(fileToUpload, filename, user);
//        Varii.p(getApplicationContext(),"35");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                // Log.d("mullllll", response.body().toString());

                try {
                    Varii.p(getApplicationContext(), response.body().toString());
                    flag1 = "123";
//                    JSONObject jsonObject = new JSONObject(response.body());
//                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//
//                    jsonObject.toString().replace("\\\\", "");
//
//                    if (jsonObject.getString("status").equals("true")) {
//
//                        Varii.p(getApplicationContext(), "Uploaded");
//
//                    } else {
//                        Varii.p(getApplicationContext(), "please Try again");
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Varii.p(getApplicationContext(), "frm here " + e);
                }
            }


            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("gttt", call.toString());
                Varii.p(getApplicationContext(), "frm here 2 " + t.getMessage());
            }
        });

    }

    private void uploadImage(String path) {
//        Varii.p(getApplicationContext(),"4");

        // String pdfname = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PDFInterface.IMAGEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //Create a file object using file path
        File file = new File(path);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("filename", user + ".jpg", requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), user);

        PDFInterface getResponse = retrofit.create(PDFInterface.class);
        Call<String> call = getResponse.uploadPhoto(fileToUpload, filename, user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                // Log.d("mullllll", response.body().toString());

                try {
                    Varii.p(getApplicationContext(), response.body().toString());
                    flag2 = "123";
//                    JSONObject jsonObject = new JSONObject(response.body());
//                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//
//                    jsonObject.toString().replace("\\\\", "");
//
//                    if (jsonObject.getString("status").equals("true")) {
//
//                        Varii.p(getApplicationContext(), "Uploaded");
//
//                    } else {
//                        Varii.p(getApplicationContext(), "please Try again");
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Varii.p(getApplicationContext(), "frm here " + e);
                }
            }


            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("gttt", call.toString());
                Varii.p(getApplicationContext(), "frm here 2 " + t.getMessage());
            }
        });

    }

}