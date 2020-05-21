package com.example.upw.ui.registerLogin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.upw.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private View view;

    String[] titleData = new String[]{"Mr", "Ms", "Mrs", "Sir", "Lord", "Dr", "Prof", "Rev", "Other"};
    //String[] salesData = new String[]{"Australia", "Canada", "China", "Europe", "Japan", "Korea", "India", "Russia", "SOuth America", "Taiwan", "UK","US", "North America", "Others"};
    Map<String, String> salesData = new HashMap<>();
    CheckBox[] checkBoxes = new CheckBox[10];
    private Button buttonreg;
    private EditText edittextcompany, edittextfirstname, edittextlastname, edittextemail, edittexttel;
    private String strcompany, strtitle, strfirstname, strlastname, stremail, strtel, strsales;
    private List<String> Business_act = new ArrayList<>();
    //public WebView webView;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        view = inflater.inflate(R.layout.fragment_register, container, false);
        buttonreg = view.findViewById(R.id.btn_reg);
        edittextcompany = view.findViewById(R.id.txt_reg_company);
        edittextfirstname = view.findViewById(R.id.txt_reg_firstname);
        edittextlastname = view.findViewById(R.id.txt_reg_lastname);
        edittextemail = view.findViewById(R.id.txt_reg_email);
        edittexttel = view.findViewById(R.id.txt_reg_tel);
        //webView=view.findViewById(R.id.webView);

        salesData.put("1", "Australia");
        salesData.put("3", "China");
        salesData.put("4", "Europe");
        salesData.put("5", "Japan");
        salesData.put("6", "Korea");
        salesData.put("9", "SOuth America");
        salesData.put("13", "North America");
        salesData.put("15", "Others");
        buttonreg.setEnabled(false);
        Spinner spinner1 = view.findViewById(R.id.spinner1);
        spinner1.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, titleData));
        //spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener());
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strtitle = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //                String sInfo ="Nothing";
//                Toast.makeText(getContext(),sInfo,Toast.LENGTH_LONG).show();
            }
        });

        //dropDownList( spinner1, titleData);
        Spinner spinner2 = view.findViewById(R.id.spinner2);
        List<String> value = new ArrayList<>(salesData.values());
        spinner2.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, value));
        //spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener());
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Object key : salesData.keySet()) {
                    if (salesData.get(key).equals(parent.getItemAtPosition(position).toString())) {
                        strsales = key.toString();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //                String sInfo ="Nothing";
//                Toast.makeText(getContext(),sInfo,Toast.LENGTH_LONG).show();
            }
        });
        //dropDownList( spinner2, salesData);

        //region 用户注册checkbox选择
        checkBoxes[0] = view.findViewById(R.id.checkBox1);
        checkBoxes[1] = view.findViewById(R.id.checkBox2);
        checkBoxes[2] = view.findViewById(R.id.checkBox3);
        checkBoxes[3] = view.findViewById(R.id.checkBox4);
        checkBoxes[4] = view.findViewById(R.id.checkBox5);
        checkBoxes[5] = view.findViewById(R.id.chkremember);
        checkBoxes[6] = view.findViewById(R.id.checkBox7);
        checkBoxes[7] = view.findViewById(R.id.checkBox8);
        checkBoxes[8] = view.findViewById(R.id.checkBox9);
        checkBoxes[9] = view.findViewById(R.id.checkBox10);

        checkBoxes[0].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[1].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[2].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[3].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[4].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[5].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[6].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[7].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[8].setOnCheckedChangeListener(checkedChangeListener);
        checkBoxes[9].setOnCheckedChangeListener(checkedChangeListener);

        //endregion

        //region 注册按钮是否亮起

        TextWatcher textWatcher = new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strcompany = edittextcompany.getText().toString().trim();
                //strtitle = textviewtitle.getText().toString().trim();
                strfirstname = edittextfirstname.getText().toString().trim();
                strlastname = edittextlastname.getText().toString().trim();
                stremail = edittextemail.getText().toString().trim();
                strtel = edittexttel.getText().toString().trim();
                //strsales = textviewsales.getText().toString().trim();
                buttonreg.setEnabled(!strcompany.isEmpty() && !strfirstname.isEmpty() && !strlastname.isEmpty()
                                && !stremail.isEmpty() && !strtel.isEmpty()
                        //&& !strsales.isEmpty()
                );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edittextcompany.addTextChangedListener(textWatcher);
        edittextfirstname.addTextChangedListener(textWatcher);
        edittextlastname.addTextChangedListener(textWatcher);
        edittextemail.addTextChangedListener(textWatcher);
        edittexttel.addTextChangedListener(textWatcher);

        //endregion

        //region 注册
        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regFuntion(v);
            }
        });
        //endregion

        return view;
    }


    //region checkbox赋值
    private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {

                Business_act.add(buttonView.getText().toString());
                //+=(Business_act).isEmpty()?buttonView.getText().toString():","+buttonView.getText().toString();
            } else if (!isChecked) {
                Business_act.remove(buttonView.getText().toString());
//                        = Business_act.length()>buttonView.getText().toString().length()?
//                        Business_act.replace(","+buttonView.getText().toString(),""):
//                        Business_act.replace(buttonView.getText().toString(),"");
            }
        }
    };
    //endregion

    //region 注册入口
    private void regFuntion(View view) {
        try {
            String strUrlPath = "http://10.10.150.240:88/api/join";

            regBean regBean = new regBean(new regBean.DataBean(strcompany, strfirstname, strlastname, strtitle, stremail, strtel, strsales, Business_act));
            //regBean regbean=new regBean.DataBean(strcompany,strfirstname,strlastname,"Mr",stremail,strtel,"1");
            Gson gson = new Gson();
            String jsonStr = gson.toJson(regBean);
            System.out.println(jsonStr);
            RegAsyncTask regAsyncTask = new RegAsyncTask(getActivity(), strUrlPath, jsonStr,"register");
            regAsyncTask.execute();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }
    //endregion


}
