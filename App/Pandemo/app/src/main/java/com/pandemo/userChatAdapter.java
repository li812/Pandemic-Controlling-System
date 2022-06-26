package com.pandemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userChatAdapter extends RecyclerView.Adapter<userChatAdapter.ViewHolder> {

    private List<chatDAO> item;
    private Context context;
    ProgressDialog progressDoalog;


    public userChatAdapter(Context context, List<chatDAO> item) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;
    }

    @Override
    public userChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_chat, null);
        return new userChatAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(userChatAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final chatDAO item1 = item.get(position);

            if (item1.getSendertype().trim().toString().equals("s") && item1.getWorkertype().trim().toString().equals("r")) {
                holder.y1.setVisibility(View.GONE);
                holder.y2.setVisibility(View.GONE);
                holder.m1.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.m2.setText(Html.fromHtml("<span>" + item1.getMessage().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.m2.setText(Html.fromHtml("<span>" + item1.getMessage().trim() + "</span>"));
                }

            } else if (item1.getSendertype().trim().toString().equals("r") && item1.getWorkertype().trim().toString().equals("s")){

                holder.m1.setVisibility(View.GONE);
                holder.m1.setVisibility(View.GONE);
                holder.y1.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.y2.setText(Html.fromHtml("<span>" + item1.getMessage().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.y2.setText(Html.fromHtml("<span>" + item1.getMessage().trim() + "</span>"));
                }

            }


        } catch (Exception e) {
            Toast.makeText(context, e + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        //   Log.i("autolog", "getItemCount");
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView m2, y2;
        CardView m1,y1;
        ImageView im, qrimg;
        LinearLayout b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");
                m1 = (CardView) itemView.findViewById(R.id.m1);
                m2= (TextView) itemView.findViewById(R.id.m2);
                y1= (CardView) itemView.findViewById(R.id.y1);
                y2= (TextView) itemView.findViewById(R.id.y2);


            } catch (Exception e) {
                Varii.p(context.getApplicationContext(), e + "");
            }

        }
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
}
