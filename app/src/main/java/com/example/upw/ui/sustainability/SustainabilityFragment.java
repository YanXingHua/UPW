package com.example.upw.ui.sustainability;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;
import com.example.upw.ui.company.Company;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SustainabilityFragment extends Fragment {

    private SustainabilityViewModel sustainabilityViewModel;
    private SustainabilityAdapter sustainabilityAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sustainabilityViewModel =
                ViewModelProviders.of(this).get(SustainabilityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sustainability, container, false);
        sustainabilityAdapter=new SustainabilityAdapter(getActivity());
        recyclerView=root.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sustainabilityAdapter);
        getRequestData();
        sustainabilityViewModel.getallSLiveData().observe(requireActivity(), new Observer<List<Sustainability>>() {
            @Override
            public void onChanged(List<Sustainability> sustainabilities) {
                sustainabilityAdapter.setSustainabilities(sustainabilities);
                sustainabilityAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }
    private void getRequestData(){
        String strUrl="http://10.10.150.240:88/api/sustainability/index";
        try {
            final MainAsynctask sustainabilityAsyncTask = new MainAsynctask(getActivity(), strUrl,  "Sustainability");
            sustainabilityAsyncTask.execute();
            sustainabilityAsyncTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String str) {
                    try {
                        Sustainability sustainability1=new Sustainability(Arrays.asList(new JSONObject(str).getString("title")),new JSONObject(str).getString("desc"),new JSONObject(str).getString("img2"));
                        Sustainability sustainability2=new Sustainability(Arrays.asList(new JSONObject(str).getString("fibers")),new JSONObject(str).getString("fibers_desc"),new JSONObject(str).getString("img3"));
                        Sustainability sustainability3=new Sustainability(Arrays.asList(new JSONObject(str).getString("man_made"),new JSONObject(str).getString("recycled")),new JSONObject(str).getString("recycled_desc"),new JSONObject(str).getString("img4"));
                        Sustainability sustainability4=new Sustainability(Arrays.asList(new JSONObject(str).getString("responsible_sub")),new JSONObject(str).getString("responsible_desc"),new JSONObject(str).getString("img5"));
                        Sustainability sustainability5=new Sustainability(Arrays.asList(new JSONObject(str).getString("dyes")),new JSONObject(str).getString("dyes_desc"),new JSONObject(str).getString("img6"));
                        List<Sustainability>sustainabilities=new ArrayList<>();
                        sustainabilities.add(sustainability1);
                        sustainabilities.add(sustainability2);
                        sustainabilities.add(sustainability3);
                        sustainabilities.add(sustainability4);
                        sustainabilities.add(sustainability5);
                        sustainabilityViewModel.insertSustainability(sustainabilities);
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