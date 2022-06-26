package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.LinearLayout;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserIssuedTestKits extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user, dist, ldate, cat, pty = "", fnm = "", kitid = "", flag = "";
    RecyclerView view1;
    LinearLayout uploadpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_issued_test_kits);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");

        uploadpanel = findViewById(R.id.imagePanel);

        Intent i = getIntent();
        String code = i.getStringExtra("code");
        if (code.equals("2")) {
            String bk = i.getStringExtra("bardata");
            String acode = i.getStringExtra("alotcode");

            if (bk.equals(acode)) {
                kitid = i.getStringExtra("kitid");
                uploadpanel.setVisibility(View.VISIBLE);
            } else {
                Varii.p(getApplicationContext(), "QR Code not matching");
            }
        }

        view1 = findViewById(R.id.view1);
        getIssuedKitDetails();

    }

    private void getIssuedKitDetails() {
        try {
            progressDoalog = new ProgressDialog(UserIssuedTestKits.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<userIssuedKitDAO>> call = service.userIssuedKitdetails(
                    user
            );

            call.enqueue(new Callback<List<userIssuedKitDAO>>() {
                @Override
                public void onResponse(Call<List<userIssuedKitDAO>> call, Response<List<userIssuedKitDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<userIssuedKitDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


                        userIssuedKitAdapter recyclerViewAdapter = new userIssuedKitAdapter(UserIssuedTestKits.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<userIssuedKitDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(), e + "");
        }
    }

    //--------------------------------------------------------------------------------------------------------------

    public void submitit(View view) {
        if (ContextCompat.checkSelfPermission(
                UserIssuedTestKits.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            UserIssuedTestKits.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select image"), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            // check if the request code is same as what is passed  here it is 2
            if (requestCode == 1) {
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
                    uploadImage(path);
                }
            }
        } catch (Exception e) {
        }
        {
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void uploadImage(String path) {

        // String pdfname = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PDFInterface.IMAGEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //Create a file object using file path
        File file = new File(path);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("filename", kitid + ".jpg", requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), kitid);

        PDFInterface getResponse = retrofit.create(PDFInterface.class);
        Call<String> call = getResponse.uploadImage(fileToUpload, filename, kitid, user, dist);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                // Log.d("mullllll", response.body().toString());

                try {
                    uploadpanel.setVisibility(View.GONE);
                    Varii.p(getApplicationContext(), response.body().toString());
                    flag = "123";
                    Intent i = new Intent(getApplicationContext(), UserIssuedTestKits.class);
                    i.putExtra("code","0");
                    startActivity(i);
                    finish();
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

    public void hidepanel(View view) {
        uploadpanel.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), UserHome.class));
        finish();
    }
}