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

public class docTestCasesAdapter extends RecyclerView.Adapter<docTestCasesAdapter.ViewHolder> {

    private List<docTestCasesDAO> item;
    private Context context;


    public docTestCasesAdapter(Context context, List<docTestCasesDAO> item) {
        Log.i("autolog", "subjectAdapter");
        this.item = item;
        this.context = context;
    }

    @Override
    public docTestCasesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.resourse_test_cases, null);

        return new docTestCasesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(docTestCasesAdapter.ViewHolder holder, int position) {
        //  Log.i("autolog", "onBindViewHolder");
        try {
            final docTestCasesDAO item1 = item.get(position);

            if (item1.getCasestatus().trim().toString().equals("waiting")) {

                if (item1.getInfectionhistory() != null && !item1.getInfectionhistory().equals("")) {
                    new docTestCasesAdapter.getpic(holder.img).execute(Varii.URL + "media/" + item1.getInfectionhistory().trim());

                    //         getpic c= new getpic(holder.im);
                    //           c.doInBackground(Varii.URL + "Photo/" + item1.getMenu_image());
                    //Picasso.with(context).load(Varii.URL + "Photo/" + item1.getMenu_image()).into(holder.im);

                } else {
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getPubname().trim() + "</span><br>" +
                            "<span>" + item1.getPubcontact() + "</span><br>" +
                            "<span>" + item1.getPubemail() + "</span><br>" +
                            "<span>"+item1.getCasedate()+"</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getPubname().trim() + "</span><br>" +
                            "<span>" + item1.getPubcontact() + "</span><br>" +
                            "<span>" + item1.getPubemail() + "</span><br>" +
                            "<span>"+item1.getCasedate()+"</span>"));
                }
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            Gson gson = new Gson();
                            String myJson = gson.toJson(item1);

                            Intent i = new Intent(context, DocImageView.class);
                            i.putExtra("myJson", myJson);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                    }
                });

                holder.b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            Gson gson = new Gson();
                            String myJson = gson.toJson(item1);

                            Intent i = new Intent(context, DocAddTestReport.class);
                            i.putExtra("myJson", myJson);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                    }
                });
            } else if (item1.getCasestatus().trim().toString().equals("published")) {
                if (item1.getInfectionhistory() != null && !item1.getInfectionhistory().equals("")) {
                    new docTestCasesAdapter.getpic(holder.img).execute(Varii.URL + "media/" + item1.getInfectionhistory().trim());

                    //         getpic c= new getpic(holder.im);
                    //           c.doInBackground(Varii.URL + "Photo/" + item1.getMenu_image());
                    //Picasso.with(context).load(Varii.URL + "Photo/" + item1.getMenu_image()).into(holder.im);

                } else {
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>", Html.FROM_HTML_MODE_COMPACT));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getPubname().trim() + "</span><br>" +
                            "<span>" + item1.getPubcontact() + "</span><br>" +
                            "<span>" + item1.getPubemail() + "</span><br>" +
                            "<span>Result : " + item1.getInfectiontype() + "</span><br>" +
                            "<span>"+item1.getCasedate()+"</span>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.t1.setText(Html.fromHtml("<span>" + item1.getCategoryname().trim() + " Kit</span>"));
                    holder.t2.setText(Html.fromHtml("<span>" + item1.getPubname().trim() + "</span><br>" +
                            "<span>" + item1.getPubcontact() + "</span><br>" +
                            "<span>" + item1.getPubemail() + "</span>" +
                            "<span>Result : " + item1.getInfectiontype() + "</span><br>" +
                            "<span>"+item1.getCasedate()+"</span>"));
                }
                holder.b.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            Toast.makeText(context, "FFFFF" + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        //   Log.i("autolog", "getItemCount");
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t1, t2, t3, t4;
        ImageView im, img;
        TextView b;

        public ViewHolder(View itemView) {

            super(itemView);

            try {

                Log.i("autolog", "ViewHolder");
                t1 = (TextView) itemView.findViewById(R.id.t1a);
                t2 = (TextView) itemView.findViewById(R.id.t2);
                b = itemView.findViewById(R.id.report);

                img = (ImageView) itemView.findViewById(R.id.img);

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
