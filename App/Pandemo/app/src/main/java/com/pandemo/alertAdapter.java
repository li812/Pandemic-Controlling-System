package com.pandemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class alertAdapter extends RecyclerView.Adapter<alertAdapter.ViewHolder> {

    private List<alertDAO> item;
    private Context context;

    String type, color;


    public alertAdapter(Context context, List<alertDAO> item) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;

    }

    @Override
    public alertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_alert, null);

        return new alertAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(alertAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final alertDAO item1 = item.get(position);

            String ccc = "#444";
            String color = item1.getAlerttype().trim().toLowerCase();
            if (color.equals("orange")) {
                ccc = "#ffa31a";
                holder.img.setColorFilter(Color.argb(255, 255, 163, 26));
            } else if (color.equals("yellow")) {
                ccc = "#e6e600";
                holder.img.setColorFilter(Color.argb(255, 230, 230, 0));
            } else if (color.equals("green")) {
                ccc = "#5cd65c";
                holder.img.setColorFilter(Color.argb(255, 92, 214, 92));
            } else if (color.equals("red")) {
                ccc = "#ff3333";
                holder.img.setColorFilter(Color.argb(255, 255, 51, 51));
            } else {
                holder.img.setColorFilter(Color.argb(255, 64, 64, 64));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.t1.setText(Html.fromHtml("<span>" + item1.getCategory().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                holder.t2.setText(Html.fromHtml("<span style='color: " + ccc + "'>" + item1.getAlerttype().trim() + " alert</span><br/>" +
                        "<span>Updated on : "+item1.getPostdt().trim()+"</span><br>" +
                        "<span>Started on : "+item1.getStartdt().trim()+"</span><br>" +
                        "<span>" + item1.getAlertinstrction().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.t1.setText(Html.fromHtml("<span>" + item1.getCategory().trim() + "</span>"));
                holder.t2.setText(Html.fromHtml("<span style='color: " + ccc + "'>" + item1.getAlerttype().trim() + "</span><br/>" +
                        "<span>Updated on : "+item1.getPostdt().trim()+"</span><br>" +
                        "<span>Started on : "+item1.getStartdt().trim()+"</span><br>" +
                        "<span>" + item1.getAlertinstrction().trim() + "</span>"));
            }
//
//            holder.t1.setText(item1.getName().trim()+" , "+item1.getPhone().trim()+" , "+item1.getEmail().trim());
//            holder.t2.setText(item1.getPhone().trim());
//            holder.t3.setText(item1.getEmail().trim());
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
        public TextView t1, t2, t3, t4;
        public ImageView img;
        ImageView im;
        LinearLayout b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");

                t1 = (TextView) itemView.findViewById(R.id.t1a);
                t2 = (TextView) itemView.findViewById(R.id.t2);
                img = (ImageView) itemView.findViewById(R.id.alert);
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
                InputStream ip = new URL(ul).openStream();
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