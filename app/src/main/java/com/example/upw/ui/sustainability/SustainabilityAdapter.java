package com.example.upw.ui.sustainability;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.upw.R;

import java.util.ArrayList;
import java.util.List;

public class SustainabilityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Sustainability> sustainabilities = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;

    public SustainabilityAdapter(Context mContext) {
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getItemViewType(int position) {
        Sustainability sustainability = sustainabilities.get(position);
        boolean isTextCount = sustainability.getTitle().size() == 2;
        int viewType = isTextCount ? 1 : 0;
        return viewType;
    }

    public void setSustainabilities(List<Sustainability> sustainabilities) {
        this.sustainabilities = sustainabilities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.sustainability_cell2, parent, false);
            MyViewHolder1 myViewHolder1=new MyViewHolder1(view);
                    return myViewHolder1;
            //return new SustainabilityAdapter.MyViewHolder1(view);
            //return new SustainabilityAdapter.MyViewHolder1(layoutInflater.from(parent.getContext()).inflate(R.layout.sustainability_cell2, parent, false));
        } else {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.sustainability_cell, parent, false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
            return myViewHolder;
            //return new SustainabilityAdapter.MyViewHolder(layoutInflater.inflate(R.layout.sustainability_cell, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Sustainability sustainability = sustainabilities.get(position);
        if(holder.getItemViewType()==1){
            MyViewHolder1 holder1=(MyViewHolder1)holder;

            holder1.textView1.setText(sustainability.getTitle().get(0));
            holder1.textView4.setText(sustainability.getTitle().get(1));
            holder1.textView2.setText(sustainability.getText());

            Glide.with(mContext).load(sustainability.getImage())
                    .into(holder1.imageView);
            holder1.textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Uri uri = Uri.parse("http://baidu.com");
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(uri);
//                    mContext.startActivity(intent);
                    NavController navController= Navigation.findNavController(v);
                    navController.navigate(R.id.action_nav_sustainability_to_materialFragment);

                }
            });
        }else{
            //Sustainability sustainability0 = sustainabilities.get(position);
            MyViewHolder holder0=(MyViewHolder)holder;
            holder0.textView1.setText(sustainability.getTitle().get(0));
            holder0.textView2.setText(sustainability.getText());

            Glide.with(mContext).load(sustainability.getImage())
                    .into(holder0.imageView);
            holder0.textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController= Navigation.findNavController(v);
                    if (position==0) {
                        navController.navigate(R.id.action_nav_sustainability_to_sustainableFragment);
                    }else if(position==1){
                        navController.navigate(R.id.action_nav_sustainability_to_naturalFragment);
                    }else if(position==3){
                        navController.navigate(R.id.action_nav_sustainability_to_traceFragment);
                    }else if(position==4){
                        navController.navigate(R.id.action_nav_sustainability_to_dyesFragment2);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return sustainabilities.size();
    }

    //    第一个样式
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textSustainabilityTitle);
            textView2 = itemView.findViewById(R.id.textviewSustainability);
            textView3 = itemView.findViewById(R.id.textGoSustainability);
            imageView = itemView.findViewById(R.id.sustainabilityImage);
        }
    }

    //第二个样式
    static class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4;
        ImageView imageView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textSustainabilityTitle1);
            textView2 = itemView.findViewById(R.id.textviewSustainability);
            textView3 = itemView.findViewById(R.id.textGoSustainability);
            imageView = itemView.findViewById(R.id.imageView2);
            textView4 = itemView.findViewById(R.id.textSustainabilityTitle2);
        }
    }
}
