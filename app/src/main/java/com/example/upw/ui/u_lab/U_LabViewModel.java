package com.example.upw.ui.u_lab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class U_LabViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public U_LabViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is u_lab fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}