package com.example.upw.ui.sustainability;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SustainabilityViewModel extends ViewModel {

    private MutableLiveData<List<Sustainability>>allSLiveData;

//    public SustainabilityViewModel() {
//        allSLiveData = new MutableLiveData<>();
//        mText.setValue("This is sustainability fragment");
//    }

    public LiveData<List<Sustainability>> getallSLiveData() {
        if (allSLiveData==null){
            allSLiveData=new MutableLiveData<>();
        }
        return allSLiveData;
    }

    public void insertSustainability(List<Sustainability>sustainabilities){
        allSLiveData.setValue(sustainabilities);
    }
}