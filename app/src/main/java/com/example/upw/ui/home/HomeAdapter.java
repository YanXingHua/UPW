package com.example.upw.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.upw.R;

import org.json.JSONObject;

import java.util.List;

public class HomeAdapter extends PagerAdapter {
    private List<String> adpPics=null;
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition=position%adpPics.size();
        ImageView imageView=new ImageView(container.getContext());
        Glide.with(container.getContext()).load(adpPics.get(realPosition))
                .into(imageView);
//       imageView.setBackgroundResource(R.mipmap.zpp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);

        //5.设置颜色后添加到容器
        container.addView(imageView);
        return imageView;
    }
    public float getPageWidth(int position) { if (position == 0 || position == 2) { return 0.8f; } return 1f; }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(adpPics!=null){
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setData(List<String> adpPics) {
        this.adpPics=adpPics;
    }

    public  int getDataRealSize(){
        if (adpPics!=null){
            return adpPics.size();
        }
        return 0;
    }
}
