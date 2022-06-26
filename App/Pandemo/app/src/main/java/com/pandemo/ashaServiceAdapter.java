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

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ashaServiceAdapter extends RecyclerView.Adapter<ashaServiceAdapter.ViewHolder> {

    private List<serviceDAO> item;
    private Context context;
    ProgressDialog progressDoalog;


    public ashaServiceAdapter(Context context, List<serviceDAO> item) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;
    }

    @Override
    public ashaServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_service, null);
        return new ashaServiceAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ashaServiceAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final serviceDAO item1 = item.get(position);

            if (item1.getRequeststatus().trim().toString().equals("assigned")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getIsolationreason().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getRequirement().trim() + "</span><br>" +
                            "<span>Request from : "+item1.getPubname().trim()+" ("+item1.getPubcontact().trim()+")</span><br>" +
                            "<span>Address : " + item1.getPubaddress().trim() + "</span><br>" +
                            "<span>Requested for : "+item1.getReqdate().trim()+"</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getIsolationreason().trim() + "</span>"));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getRequirement().trim() + "</span><br>" +
                            "<span>Request from : "+item1.getPubname().trim()+" ("+item1.getPubcontact().trim()+")</span><br>" +
                            "<span>Address : " + item1.getPubaddress().trim() + "</span><br>" +
                            "<span>Requested for : "+item1.getReqdate().trim()+"</span>"));
                }

                holder.report.setVisibility(View.VISIBLE);
                holder.report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            try {
                                MainApi service = Connection.getcon().create(MainApi.class);
                                Call<List<Result>> call = service.ashaCompleteService(
                                        item1.getServiceid().trim()
                                );
                                call.enqueue(new Callback<List<Result>>() {
                                    @Override
                                    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                                        Log.i("onResponse", response.message());
                                        try {
                                            List<Result> userList = response.body();
                                            for (Result n : userList) {
                                                if (n.getResult().contains("ok")) {
                                                    Varii.p(context, "Service completed");

                                                    Intent i = new Intent(context, AshaServiceRequests.class);
                                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    context.startActivity(i);
                                                } else {
                                                    Varii.p(context, n.getResult() + "");
                                                }

                                            }
                                        } catch (Exception e) {

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<List<Result>> call, Throwable t) {
                                        Varii.p(context, "Bad network.. Please try again later.");
                                        progressDoalog.dismiss();
                                    }
                                });
                            } catch (Exception e) {
                                progressDoalog.dismiss();
                            }
                        }
                    }
                });

            } else if (item1.getRequeststatus().trim().toString().equals("completed")){

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getIsolationreason().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getRequirement().trim() + "</span><br>" +
                            "<span>Request from : "+item1.getPubname().trim()+" ("+item1.getPubcontact().trim()+")</span><br>" +
                            "<span>Address : " + item1.getPubaddress().trim() + "</span><br>" +
                            "<span>Requested for : "+item1.getReqdate().trim()+"</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getIsolationreason().trim() + "</span>"));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getRequirement().trim() + "</span><br>" +
                            "<span>Request from : "+item1.getPubname().trim()+" ("+item1.getPubcontact().trim()+")</span><br>" +
                            "<span>Address : " + item1.getPubaddress().trim() + "</span><br>" +
                            "<span>Requested for : "+item1.getReqdate().trim()+"</span>"));
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
        public TextView t1, t2, t3, report;
        ImageView im, qrimg;
        LinearLayout b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");
                t1 = (TextView) itemView.findViewById(R.id.t1a);
                t2 = (TextView) itemView.findViewById(R.id.t2);
                b = itemView.findViewById(R.id.b);
                report = itemView.findViewById(R.id.report);

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
