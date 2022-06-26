package com.pandemo;

import android.content.Context;
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

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class reqKitAdapter extends RecyclerView.Adapter<reqKitAdapter.ViewHolder> {

    private List<reqKitDAO> item;
    private Context context;



    public reqKitAdapter(Context context, List<reqKitDAO> item) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;
    }

    @Override
    public com.pandemo.reqKitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_kit_details, null);

        return new com.pandemo.reqKitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.pandemo.reqKitAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final reqKitDAO item1 = item.get(position);

            if(item1.getWorkstatus().trim().toString().equals("waiting")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>Status : " + item1.getWorkstatus().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                    holder.t2.setText(Html.fromHtml("<span>Status : " + item1.getWorkstatus().trim() + "</span>"));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>Status : " + item1.getWorkstatus().trim() + "</span><br>" +
                            "<span>Kit status : "+item1.getKitstaus().trim()+"</span><br><span>Asha worker : "+item1.getEmpname()+"</span><br>" +
                            "<span>Contact : "+item1.getEmpcontact()+"</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                    holder.t2.setText(Html.fromHtml("<span>Status : " + item1.getWorkstatus().trim() + "</span><br>" +
                            "<span>Kit status : "+item1.getKitstaus().trim()+"</span><br><span>Asha worker : "+item1.getEmpname()+"</span><br>" +
                            "<span>Contact : "+item1.getEmpcontact()+"</span>"));
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
        public TextView t1, t2, t3, t4;
        ImageView im;
        LinearLayout b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");
                t1 = (TextView) itemView.findViewById(R.id.t1a);
                t2 = (TextView) itemView.findViewById(R.id.t2);

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
