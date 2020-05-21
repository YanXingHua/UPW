package com.example.upw.ui.stockstore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StockStoreViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StockStoreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is stockstore fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}