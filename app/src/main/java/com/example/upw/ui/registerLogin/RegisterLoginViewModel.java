package com.example.upw.ui.registerLogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterLoginViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public RegisterLoginViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is u_lab fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
