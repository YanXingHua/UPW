package com.example.upw.ui.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.upw.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<List<String>> favLists = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;

    public RecycleViewAdapter(Context mContext,List<List<String>>favList) {
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
        favLists=favList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_overviewfav, parent, false);
        MyViewHolder myViewHolder1=new MyViewHolder(view);
        return myViewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder0, int position) {
        List<String> favList=favLists.get(position);
        MyViewHolder holder=(MyViewHolder)holder0;
        holder.tvunit.setText(favList.get(0));
        holder.tvname.setText(favList.get(1));
        holder.tvcolorno.setText(favList.get(2));
        holder.tvyarn.setText(favList.get(3));
        holder.tvcolorname.setText(favList.get(4));
        Glide.with(mContext).load(favList.get(5))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favLists.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvunit, tvname, tvcolorno,tvyarn,tvcolorname;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvunit = itemView.findViewById(R.id.tvunit);
            tvname = itemView.findViewById(R.id.tvname);
            tvcolorno = itemView.findViewById(R.id.tvcolorno);
            tvyarn= itemView.findViewById(R.id.tvyarn);
            tvcolorname= itemView.findViewById(R.id.tvcolorname);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
