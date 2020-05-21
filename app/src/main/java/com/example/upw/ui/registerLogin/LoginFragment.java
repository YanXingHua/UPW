package com.example.upw.ui.registerLogin;

import androidx.lifecycle.ViewModelProviders;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.upw.MainActivity;
import com.example.upw.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private Button btnlogin;
    private CheckBox chkremember;
    private EditText edittextemail, edittextpsd;
    private String stremail, strpsd;
    private boolean mIsChecked = false;
    private SharedPreferences sharedPreferences;
    private String SP_IS_REMEMBER_PSD = "sp_is_remember_psd";
    private String SP_EMAIL = "sp_email";
    private String SP_PSD = "sp_psd";

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        InitUI(view);
        inItData(view);
        return view;
    }

    private void inItData(View view) {
        if (sharedPreferences == null) {
            sharedPreferences = getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        }
        edittextemail.setText(sharedPreferences.getString(SP_EMAIL, ""));
        edittextpsd.setText(sharedPreferences.getString(SP_PSD, ""));
        mIsChecked = sharedPreferences.getBoolean(SP_IS_REMEMBER_PSD, false);
        chkremember.setChecked(mIsChecked);
    }

    private void InitUI(final View view) {
        btnlogin = view.findViewById(R.id.btn_login);
        edittextemail = view.findViewById(R.id.txt_login_email);
        edittextpsd = view.findViewById(R.id.txt_login_psd);
        chkremember = view.findViewById(R.id.chkremember);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                stremail = edittextemail.getText().toString().trim();
                strpsd = edittextpsd.getText().toString().trim();
                btnlogin.setEnabled(!stremail.isEmpty() && !strpsd.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIsChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getActivity().getSharedPreferences("logininfo", Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_EMAIL, edittextemail.getText().toString().trim());
                    editor.putString(SP_PSD, edittextpsd.getText().toString().trim());
                    editor.apply();
                }
            }
        };
        edittextemail.addTextChangedListener(textWatcher);
        edittextpsd.addTextChangedListener(textWatcher);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFuntion(v);
            }


        });
        chkremember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPreferences = getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                    //获取编辑器
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //存入登录状态时的用户名
                    editor.putString(SP_EMAIL, edittextemail.getText().toString().trim());
                    editor.putString(SP_PSD, edittextpsd.getText().toString().trim());
                    editor.putBoolean(SP_IS_REMEMBER_PSD, isChecked);
                    //提交修改
                    editor.apply();
                }

            }
        });
    }

    private void loginFuntion(View v) {
        try {
            JSONObject data = new JSONObject();
            String strUrlPath = "http://10.10.150.240:88/api/login";

            data.put("email", stremail);
            data.put("password", strpsd);
//            regBean regBean = new regBean(new regBean.DataBean(strcompany, strfirstname, strlastname, strtitle, stremail, strtel, strsales, Business_act));
//            Gson gson = new Gson();
//            String jsonStr = gson.toJson(regBean);
            System.out.println(data);
            RegAsyncTask regAsyncTask = new RegAsyncTask(getActivity(), strUrlPath, data.toString(), "login");
            regAsyncTask.execute();
            regAsyncTask.setOnAsyncResponse(new AsyncResponse() {
                @Override
                public void onDataReceivedSuccess() {

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

                // @Override
//                public void onDataReceivedFailed() {
//
//                }
            });


        } catch (Exception e) {
            Toast.makeText(getActivity(), "请稍后重试", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

}
