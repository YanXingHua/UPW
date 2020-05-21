package com.example.upw.ui.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.upw.R;

import java.util.ArrayList;
import java.util.List;

public class ExtendableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<String> mGroup;
    private ArrayList<ArrayList<String>> mItemList;
    private List<ImageView> ivList;
    public ExtendableListViewAdapter(Context context, ArrayList<String> group, ArrayList<ArrayList<String>> itemList){
        this.mContext = context;
        this.mGroup = group;
        this.mItemList = itemList;
        ivList = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return  mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
                //mItemList.get(groupPosition).size();//
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return 1;
                //mItemList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_parent_item,parent,false);
        }
        String group = mGroup.get(groupPosition);
        TextView tvGroup =convertView.findViewById(R.id.textShoppingAddress);
        tvGroup.setText(group);
        ImageView ivIndicator=convertView.findViewById(R.id.iv_indicator);
        //      把位置和图标添加到list
        ivList.add(groupPosition, ivIndicator);
        //      根据分组状态设置Indicator
        setIndicatorState(groupPosition, isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        final List<String> child = mItemList.get(groupPosition);
        if (child.get(0)=="null"){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_add,parent,false);
        }else{
        //if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_child_item,parent,false);
        //}
        TextView tvCompany = convertView.findViewById(R.id.tvshopCompany);
        tvCompany.setText(child.get(0));
        TextView tvshopName = convertView.findViewById(R.id.tvshopName);
        tvshopName.setText(child.get(1));
        TextView tvshopPhone = convertView.findViewById(R.id.tvshopPhone);
        tvshopPhone.setText(child.get(2));
        TextView tvshopAddress = convertView.findViewById(R.id.tvshopAddress);
        tvshopAddress.setText(child.get(3));
        final ImageButton imageButton=convertView.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "编辑地址", Toast.LENGTH_SHORT).show();
        }
    });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    // 根据分组的展开闭合状态设置指示器
    public void setIndicatorState(int groupPosition, boolean isExpanded) {
        if (isExpanded) {
            ivList.get(groupPosition).setImageResource(R.drawable.ic_expand_more);
        } else {
            ivList.get(groupPosition).setImageResource(R.drawable.ic_expand_less);
        }
    }
}
