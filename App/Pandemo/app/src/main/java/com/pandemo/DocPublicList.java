package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocPublicList extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user,dist;
    RecyclerView view1;
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_public_list);

        SharedPreferences sharedPref = getSharedPreferences("pandemo", Context.MODE_PRIVATE);
        user = sharedPref.getString("logid", "");
        dist = sharedPref.getString("dist", "");
        view1 = findViewById(R.id.view1);
        empty = findViewById(R.id.v1);
        getdata();

    }

    private void getdata() {
        try {
            progressDoalog = new ProgressDialog(DocPublicList.this);
            progressDoalog.setMessage("Loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            MainApi service = Connection.getcon().create(MainApi.class);
            Call<List<publicDAO>> call = service.getPublicList(
                    dist
            );

            call.enqueue(new Callback<List<publicDAO>>() {
                @Override
                public void onResponse(Call<List<publicDAO>> call, Response<List<publicDAO>> response) {
                    Log.i("onResponse", response.message());
                    int i = 0;
                    try {
                        List<publicDAO> userList = response.body();

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        view1.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

                        if(userList.size()==0)
                        {
                            empty.setVisibility(View.VISIBLE);
                        }else{
                            empty.setVisibility(View.GONE);
                        }

                        docUserAdapter recyclerViewAdapter = new docUserAdapter(DocPublicList.this, userList);

                        view1.setAdapter(recyclerViewAdapter);


                    } catch (Exception e) {
                        Varii.p(getApplicationContext(), e + " xxxxxxxxxxx");
                    }
                    progressDoalog.dismiss();
                    //  Log.i("autolog", "List<User> userList = response.body();");

                    //  Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                }

                @Override
                public void onFailure(Call<List<publicDAO>> call, Throwable t) {

                    Varii.p(getApplicationContext(), "Bad Network!... Please try again later.");
                }
            });
        } catch (Exception e) {
            Varii.p(getApplicationContext(),e+"");
        }
    }

}