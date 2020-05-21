package com.example.upw.ui.company;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.sustainability.Sustainability;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompanyAdapt extends RecyclerView.Adapter<CompanyAdapt.MyViewHolder> {
    List<Company> allCompanies = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;

    public CompanyAdapt(Context context) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setAllCompanies(List<Company> allCompanies) {
        this.allCompanies = allCompanies;
    }


    public int getItemViewType(int position) {
        Company sustainability = allCompanies.get(position);

        return position;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
//        View itemView=layoutInflater.inflate(R.layout.company_cell,parent,false);
//        return new MyViewHolder(itemView);
        return new MyViewHolder(layoutInflater.inflate(R.layout.company_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Company company = allCompanies.get(position);
        holder.textView.setText(company.getName());
        holder.textView.setTextColor(Color.parseColor(company.getTextcolor()));
        Glide.with(mContext).load(company.getImg())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return allCompanies.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textcompany);
            imageView = itemView.findViewById(R.id.imageViewcompany);
        }
    }
}
