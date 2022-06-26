package com.pandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class DocImageView extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String user,dist;
    RecyclerView view1;
    ImageView imagePanel;

    docTestCasesDAO ob;
    String v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_image_view);

        imagePanel = findViewById(R.id.imagePanel);

        Intent data = getIntent();
        v = data.getStringExtra("myJson");
        Gson gson = new Gson();
        ob = gson.fromJson(v,docTestCasesDAO.class);

        new DocImageView.getpic(imagePanel).execute(Varii.URL + "media/" + ob.getInfectionhistory().trim());
    }


    public class getpic extends AsyncTask<String, Void, Bitmap> {
        ImageView inv;

        public getpic(ImageView in) {
            this.inv = in;
        }

        @Override
        protected Bitmap doInBackground(String... para) {
            try {
                String ul = para[0];
                InputStream ip = new java.net.URL(ul).openStream();
                Bitmap bimp = BitmapFactory.decodeStream(ip);
                return bimp;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            this.inv.setImageBitmap(bitmap);
        }
    }

    public void goBack(View view) {
        onBackPressed();
    }
}