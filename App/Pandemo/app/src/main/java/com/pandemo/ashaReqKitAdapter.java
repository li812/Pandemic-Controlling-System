package com.pandemo;

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

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ashaReqKitAdapter extends RecyclerView.Adapter<ashaReqKitAdapter.ViewHolder> {

    private List<ashaReqKitDAO> item;
    private Context context;


    public ashaReqKitAdapter(Context context, List<ashaReqKitDAO> item) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;
    }

    @Override
    public ashaReqKitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_kit_details, null);
        return new ashaReqKitAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ashaReqKitAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final ashaReqKitDAO item1 = item.get(position);

            if (item1.getKitstaus().trim().toString().equals("waiting")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>Status : " + item1.getWorkstatus().trim() + "</span><br>" +
                            "<span>Kit status : " + item1.getKitstaus().trim() + "</span><br><span>Request from : " + item1.getPubname() + "</span><br>" +
                            "<span>Contact : " + item1.getPubcontact() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                    holder.t2.setText(Html.fromHtml("<span>Status : " + item1.getWorkstatus().trim() + "</span><br>" +
                            "<span>Kit status : " + item1.getKitstaus().trim() + "</span><br><span>Asha worker : " + item1.getPubname() + "</span><br>" +
                            "<span>Contact : " + item1.getPubcontact() + "</span>"));
                }

                holder.qrimg.setVisibility(View.VISIBLE);
                holder.b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            Gson gson = new Gson();
                            String myJson = gson.toJson(item1);

                            Intent i = new Intent(context, AshaQrcodeScan.class);
                            i.putExtra("myJson", myJson);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                    }
                });
            } else {

                if (item1.getKitstaus().trim().toString().equals("announced")) {

                    String result = "Waiting for result", kitstatus = item1.getKitstaus().trim();
                    if(!item1.getInfectiontype().trim().equals("waiting")){
                        result = item1.getInfectiontype().trim();
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                        holder.t2.setText(Html.fromHtml("<span>Status : " + result + "</span><br>" +
                                "<span>Name : " + item1.getPubname() + "</span><br>" +
                                "<span>Contact : " + item1.getPubcontact() + "</span><br>" +
                                "<span>Date : " + item1.getAllotedate().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                        holder.t2.setText(Html.fromHtml("<span>Status : " + result + "</span><br>" +
                                "<span>Name : " + item1.getPubname() + "</span><br>" +
                                "<span>Contact : " + item1.getPubcontact() + "</span><br>" +
                                "<span>Date : " + item1.getAllotedate().trim() + "</span>"));
                    }

                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                        holder.t2.setText(Html.fromHtml("<span>Status : Test not submitted</span><br>" +
                                "<span>Name : " + item1.getPubname() + "</span><br>" +
                                "<span>Contact : " + item1.getPubcontact() + "</span><br>" +
                                "<span>Date : " + item1.getAllotedate().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                        holder.t2.setText(Html.fromHtml("<span>Status : Test not submitted</span><br>" +
                                "<span>Name : " + item1.getPubname() + "</span><br>" +
                                "<span>Contact : " + item1.getPubcontact() + "</span><br>" +
                                "<span>Date : " + item1.getAllotedate().trim() + "</span>"));
                    }
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
        ImageView im, qrimg;
        LinearLayout b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");
                t1 = (TextView) itemView.findViewById(R.id.t1a);
                t2 = (TextView) itemView.findViewById(R.id.t2);
                b = itemView.findViewById(R.id.b);
                qrimg = itemView.findViewById(R.id.qrimg);

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
