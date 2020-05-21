package com.example.upw.ui.exhibitions_events;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.upw.R;

import java.util.List;

public class ExhAdapter extends PagerAdapter {
    private List<String> exhPic,subTitleList,contentList,reserveList=null;


    public void setExhPic(List<String> exhPic, List<String>subTitleList,List<String>contentList,List<String>reserveList) {

        this.exhPic = exhPic;
        this.subTitleList=subTitleList;
        this.contentList=contentList;
        this.reserveList=reserveList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition=position%exhPic.size();
        ImageView imageView=new ImageView(container.getContext());
        Glide.with(container.getContext()).load(exhPic.get(realPosition))
                .into(imageView);
        TextView textSubTitle=container.getRootView().findViewById(R.id.textsubTitle);
        TextView textContent=container.getRootView().findViewById(R.id.textContent);
        TextView textReserve=container.getRootView().findViewById(R.id.textReserve);
        textSubTitle.setText(subTitleList.get(realPosition));
        textContent.setText(contentList.get(realPosition));
        textReserve.setText(reserveList.get(realPosition));
//       imageView.setBackgroundResource(R.mipmap.zpp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);

        //5.设置颜色后添加到容器
        container.addView(imageView);
        return imageView;
//        return super.instantiateItem(container, position);
    }

    @Override
    public float getPageWidth(int position) {
        if (position == 0 || position == 2) { return 0.8f; } return 1f;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((View)object));
       //super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        if(exhPic!=null){
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    public  int getDataRealSize(){
        if (exhPic!=null){
            return exhPic.size();
        }
        return 0;
    }
}
