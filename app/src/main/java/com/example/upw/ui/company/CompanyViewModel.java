package com.example.upw.ui.company;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CompanyViewModel extends ViewModel {

    private MutableLiveData<List<Company>>allCompanLive;

    public LiveData<List<Company>> getAllCompanLive() {

        if(allCompanLive == null){
            allCompanLive = new MutableLiveData<>();
        }
        return allCompanLive;
    }
    public void inserCompany(List<Company>companies) {
        allCompanLive.setValue(companies);
        //this.allCompanLive=companies;
    }
    //private LiveData<List<Company>>allCompanLive;


    //private CompanyDao companyDao;
//    public CompanyViewModel(Application application){
//        super(application);
//        CompanyDataBase companyDataBase=CompanyDataBase.getDatabase(application);
//        companyDao=companyDataBase.getCompanyDao();
//        allCompanLive=companyDao.getAllCompanyLive();
//    }


//    public MutableLiveData<List<Company>> getAllCompanLive() {
//                if (allCompanLive==null) {
//            allCompanLive = new MutableLiveData<>();
//            //allCompanLive.setValue("name","image");
//        }
//        return allCompanLive;
//    }


//    static class InsertAsyncTask extends AsyncTask<Company, Void, Void> {
//        private CompanyDao companyDao;
//
//        InsertAsyncTask(CompanyDao companyDao) {
//            this.companyDao = companyDao;
//        }
//
//        @Override
//        protected Void doInBackground(Company... companies) {
//
//            companyDao.insertCompany(companies);
//            return null;
//        }
//    }
}
