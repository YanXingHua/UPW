package com.example.upw.ui.aboutme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.upw.R;

import java.util.ArrayList;
import java.util.List;

public class AboutMeExableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGroup;//父视图
    private List<List<String>> FChildList;//第一个子视图
    //List<List<HashMap<String,String>>> mshipAdds,mbillAddsList,mmarkets,mcountries,mprovinces;
    private ArrayList<AboutMe>list1=new ArrayList<>();//第2，3个子视图
    private ArrayList<AboutMe>list2=new ArrayList<>();
    private List<ImageView> ivList;

    //第一层不用改，第二层不用判断切换视图，但要根据不同的position显示不同视图
//    public AboutMeExableListViewAdapter(Context context, List<String> group, List<List<String>> ChildList,
//                                        List<List<HashMap<String,String>>> shipAdds, List<List<HashMap<String,String>>> billAdds,
//                                        List<List<HashMap<String,String>>> markets,List<List<HashMap<String,String>>> countries,
//                                        List<List<HashMap<String,String>>> provinces) {
    public AboutMeExableListViewAdapter(Context context,List<String>group,List<List<String>> FChildList, ArrayList<AboutMe> list1, ArrayList<AboutMe> list2) {
        this.mContext = context;
        this.list1=list1;
        this.list2=list2;
        this.mGroup = group;
        this.FChildList = FChildList;
//        this.mshipAdds=shipAdds;
//        this.mbillAddsList=billAdds;
//        this.mcountries=countries;
//        this.mmarkets=markets;
//        this.mprovinces=provinces;
        ivList = new ArrayList<>();
        //
    }

    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size=0;
        if (groupPosition==0) {
            size= FChildList.size();//
        }else if(groupPosition==1){
            if(list1.size()!=0){
            ArrayList<AboutMeinfo> chList = list1.get(0).getItems();
            size= chList.size();}
            else {
                size=1;
            }
        }else if (groupPosition==2){
            if(list2.size()!=0) {
                ArrayList<AboutMeinfo> chList = list2.get(0).getItems();
                size = chList.size();
            }else {
                size=1;
            }
        }
        else if (groupPosition==3){
            size=1;
        }
        return size;


    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return 1;
        //mItemList.get(groupPosition).get(childPosition);
        Object child=null;
        if(groupPosition==0){
            child= FChildList.get(groupPosition).get(childPosition);
        }else if(groupPosition==1){
            ArrayList<AboutMeinfo> chList = list1.get(0).getItems();
            child= chList.get(childPosition);
        }else if (groupPosition==2){
            ArrayList<AboutMeinfo> chList = list2.get(0).getItems();
            child= chList.get(childPosition);
        }else if (groupPosition==3){

        }

        return child;

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
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_parent_item, parent, false);
        }
        String group = mGroup.get(groupPosition);
        TextView tvGroup = convertView.findViewById(R.id.textShoppingAddress);
        tvGroup.setText(group);
        ImageView ivIndicator = convertView.findViewById(R.id.iv_indicator);
        //      把位置和图标添加到list
        ivList.add(groupPosition, ivIndicator);
        //      根据分组状态设置Indicator
        setIndicatorState(groupPosition, isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {

        if(groupPosition==0){
            final List<String> child = FChildList.get(groupPosition);
            convertView = LayoutInflater.from(parent.getContext()).inflate(R .layout.aboutme_drop_person, parent, false);
            EditText person_comp = convertView.findViewById(R.id.person_comp);
            person_comp.setText(child.get(0));
            EditText person_title = convertView.findViewById(R.id.person_title);
            person_title.setText(child.get(1));
            EditText person_fname = convertView.findViewById(R.id.person_fname);
            person_fname.setText(child.get(2));
            EditText person_lname = convertView.findViewById(R.id.person_lname);
            person_lname.setText(child.get(3));
            EditText person_email = convertView.findViewById(R.id.person_remail);
            person_email.setText(child.get(4));
            EditText person_tel = convertView.findViewById(R.id.person_tel);
            person_tel.setText(child.get(5));
            EditText person_salereg = convertView.findViewById(R.id.person_salereg);
            person_salereg.setText(child.get(6));
            CheckBox person_chk = convertView.findViewById(R.id.person_chk);
            person_chk.setChecked(Boolean.valueOf(child.get(7)));
        }else if(groupPosition==1 || groupPosition==2){
            if ((groupPosition==1 && list1.size()==0) || (groupPosition==2 && list2.size()==0)){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutme_drop_add_ads, parent, false);
            }else {
                AboutMeinfo child = (AboutMeinfo) getChild(groupPosition, childPosition);
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.overview_child_item, parent, false);
                if (childPosition==0) {
                    TextView tvAddads = convertView.findViewById(R.id.tvAddads);
                    tvAddads.setVisibility(View.VISIBLE);
                }
                TextView tvCompany = convertView.findViewById(R.id.tvshopCompany);
                tvCompany.setText(child.getCompany());
                TextView tvshopName = convertView.findViewById(R.id.tvshopName);
                tvshopName.setText(child.getReceiver());
                TextView tvshopPhone = convertView.findViewById(R.id.tvshopPhone);
                tvshopPhone.setText(child.getTel());
                TextView tvshopAddress = convertView.findViewById(R.id.tvshopAddress);
                tvshopAddress.setText(child.getAddr());
//        final ImageButton imageButton=convertView.findViewById(R.id.imageButton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(parent.getContext(), "编辑地址", Toast.LENGTH_SHORT).show();
//            }
//        });
            }
        }else if (groupPosition==3){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutme_drop_changepsd, parent, false);
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
