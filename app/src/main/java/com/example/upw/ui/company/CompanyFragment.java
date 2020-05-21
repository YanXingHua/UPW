package com.example.upw.ui.company;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    private String Tag = "TextView";
    private List<String> environmentList = null;
    private CompanyViewModel companyViewModel;
    private RecyclerView recyclerView;
    private CompanyAdapt companyAdapt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        companyViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(requireActivity()
                .getApplication(), this)).get(CompanyViewModel.class);
        recyclerView = view.findViewById(R.id.recycleView);
        companyAdapt = new CompanyAdapt(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(companyAdapt);
        sendRequestForListData();
        companyViewModel.getAllCompanLive().observe(requireActivity(), new Observer<List<Company>>() {
            @Override
            public void onChanged(List<Company> companyList) {
                companyAdapt.setAllCompanies(companyList);
                companyAdapt.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void sendRequestForListData() {
        String strUrl="http://10.10.150.240:88/api/company";
        try {
            final MainAsynctask companyAsyncTask = new MainAsynctask(getActivity(), strUrl,  "Company");
            companyAsyncTask.execute();
            companyAsyncTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String str) {
                    try {
                        Company company1=new Company(new JSONObject(str).getString("summary"),new JSONObject(str).getString("img1"),"#FFFFFF");
                        Company company2=new Company(new JSONObject(str).getString("stockstore"),new JSONObject(str).getString("img2"),"#000000");
                        Company company3=new Company(new JSONObject(str).getString("creativity_summary"),new JSONObject(str).getString("img3"),"#FFFFFF");
                        List<Company>companies=new ArrayList<>();
                        companies.add(company1);
                        companies.add(company2);
                        companies.add(company3);
                        companyViewModel.inserCompany(companies);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onDataReceivedFailed() {
                    Toast.makeText(getContext(),"data received failed!",Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
