package com.pandemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userItemAdapter extends RecyclerView.Adapter<userItemAdapter.ViewHolder> {

    private List<itemDAO> item;
    private Context context;
    ProgressDialog progressDoalog;
    TextView tv;

    public userItemAdapter(Context context, List<itemDAO> item, TextView to) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;
        this.tv = to;
    }

    @Override
    public userItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_item, null);
        return new userItemAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(userItemAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final itemDAO item1 = item.get(position);
            if (item1.getStatus().trim().equals("waiting")) {
                int amt = Integer.parseInt(tv.getText().toString());
                int subt = amt + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim());

                tv.setText(subt + "");
//                Varii.p(context, Varii.total + "");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getMedicinename().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getMedprice().trim() + "Rs x " + item1.getQty() + "</span><br>" +
                            "<span>Subtotal : " + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim()) + "Rs</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getMedicinename().trim() + "</span>"));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getMedprice().trim() + "Rs x " + item1.getQty() + "</span><br>" +
                            "<span>Subtotal : " + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim()) + "Rs</span>"));
                }

                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            MainApi service = Connection.getcon().create(MainApi.class);
                            Call<List<Result>> call = service.userRemoveItem(
                                    item1.getItemid().trim()
                            );
                            call.enqueue(new Callback<List<Result>>() {
                                @Override
                                public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                                    Log.i("onResponse", response.message());
                                    try {
                                        List<Result> userList = response.body();
                                        for (Result n : userList) {
                                            if (n.getResult().contains("ok")) {
                                                Varii.p(context, "Item removed");

                                                Intent i = new Intent(context, UserMedicineBookings.class);
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
                });

            } else if (item1.getStatus().trim().equals("paid")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getMedicinename().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>Date : "+item1.getDate().trim()+"<br></span>" +
                            "<span>" + item1.getMedprice().trim() + "Rs x " + item1.getQty() + "</span><br>" +
                            "<span>Total : " + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim()) + "Rs</span><br>" +
                            "<span>Status : Amount paid. Will be delivered soon</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getMedicinename().trim() + "</span>"));
                    holder.t2.setText(Html.fromHtml("<span>Date : "+item1.getDate().trim()+"<br></span>" +
                            "<span>" + item1.getMedprice().trim() + "Rs x " + item1.getQty() + "</span><br>" +
                            "<span>Total : " + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim()) + "Rs</span><br>" +
                            "<span>Status : Amount paid. Will be delivered soon</span>"));
                }
                holder.icon.setVisibility(View.GONE);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getMedicinename().trim() + "</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>Date : "+item1.getDate().trim()+"<br></span>" +
                            "<span>" + item1.getMedprice().trim() + "Rs x " + item1.getQty() + "</span><br>" +
                            "<span>Total : " + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim()) + "Rs</span><br>" +
                            "<span>Status : "+item1.getStatus().trim()+"</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getMedicinename().trim() + "</span>"));
                    holder.t2.setText(Html.fromHtml("<span>Date : "+item1.getDate().trim()+"<br></span>" +
                            "<span>" + item1.getMedprice().trim() + "Rs x " + item1.getQty() + "</span><br>" +
                            "<span>Total : " + Integer.parseInt(item1.getMedprice().trim()) * Integer.parseInt(item1.getQty().trim()) + "Rs</span><br>" +
                            "<span>Status : "+item1.getStatus().trim()+"</span>"));
                }
                holder.icon.setVisibility(View.GONE);
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
        CardView im, icon;
        LinearLayout b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");
                t1 = (TextView) itemView.findViewById(R.id.t1a);
                t2 = (TextView) itemView.findViewById(R.id.t2);
                b = itemView.findViewById(R.id.b);
                icon = itemView.findViewById(R.id.icon);

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
