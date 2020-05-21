package com.example.upw.ui.exhibitions_events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Exhibitions_EventsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Exhibitions_EventsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is exhibitions_events fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}