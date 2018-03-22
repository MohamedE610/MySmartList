package com.example.mysmartlist.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.getClientByIDRequest;


public class EditProfileFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;

    String name,phone,email,gender,familyNum,salaryRang,reportType;

    Spinner spinnerFamilyMember;
    //Spinner spinnerSalaryRang;
    String familyMemberStr;
    String salaryRangStr;

    RadioButton maleRadioBtn,femaleRadioBtn,weeklyRadioBtn,monthlyRadioBtn;

    private EditText inputEmail, inputPassword,inuptUserName,inputPhoneNum,inputPasswordConfirm,inputSalary;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_edit_profile, container, false);

        inuptUserName = (EditText) view.findViewById(R.id.user_name);
        inputPhoneNum = (EditText) view.findViewById(R.id.phone_num);
        inputEmail = (EditText) view.findViewById(R.id.email);
        inputPassword = (EditText) view.findViewById(R.id.password);
        inputPasswordConfirm = (EditText) view.findViewById(R.id.password_confirm);
        inputSalary = (EditText) view.findViewById(R.id.salary_text);


        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        maleRadioBtn = (RadioButton) view.findViewById(R.id.male_rb);
        femaleRadioBtn = (RadioButton) view.findViewById(R.id.female_rb);
        weeklyRadioBtn = (RadioButton) view.findViewById(R.id.weekly_rb);
        monthlyRadioBtn = (RadioButton) view.findViewById(R.id.monthly_rb);

        tabLayout = (TabLayout) view.findViewById(R.id.bottom_tablayout);
        tabLayout.addOnTabSelectedListener(this);

        maleRadioBtn.setOnCheckedChangeListener(this);
        femaleRadioBtn.setOnCheckedChangeListener(this);
        weeklyRadioBtn.setOnCheckedChangeListener(this);
        monthlyRadioBtn.setOnCheckedChangeListener(this);


        //spinnerSalaryRang=(Spinner)findViewById(R.id.spinner_salary_rang);
        spinnerFamilyMember=(Spinner)view.findViewById(R.id.spinner_family_member);
        //createSalaryRangSpinner();
        createFamilyMemberSpinner();


        getClientData();


        return view;
    }

    private void getClientData() {
        MySharedPreferences.setUpMySharedPreferences(getActivity());
        int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        getClientByIDRequest clientByIDRequest=new getClientByIDRequest(uid);
        clientByIDRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Client client=(Client) obj;
                inuptUserName.setText(client.data.name);
                inputPhoneNum.setText(client.data.phone);
                inputEmail.setText(client.data.email);
                //inputSalary.setText(client.data.salary);
                inputSalary.setText("10000");
                if(client.data.gender.equals("male")) {
                    maleRadioBtn.setSelected(true);
                    femaleRadioBtn.setSelected(false);
                }else {
                    maleRadioBtn.setSelected(false);
                    femaleRadioBtn.setSelected(true);
                }

                if(client.data.budget.equals("weekly")){
                    weeklyRadioBtn.setSelected(true);
                    monthlyRadioBtn.setSelected(false);
                }else {
                    weeklyRadioBtn.setSelected(false);
                    monthlyRadioBtn.setSelected(true);
                }

                if(client.data.familyMembers.equals("1")){
                    spinnerFamilyMember.setSelection(1);
                }else if(client.data.familyMembers.equals("2")){
                    spinnerFamilyMember.setSelection(2);

                }else if(client.data.familyMembers.equals("3")){

                    spinnerFamilyMember.setSelection(3);
                }else if(client.data.familyMembers.equals("3+")) {
                    spinnerFamilyMember.setSelection(4);

                }

            }

            @Override
            public void OnFailure(Object obj) {

            }
        });

        clientByIDRequest.start();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        int id=compoundButton.getId();

        switch (id){
            case R.id.male_rb:
                if(b)
                    gender="male";
                break;

            case R.id.female_rb:
                if(b)
                    gender="female";
                break;

            case R.id.weekly_rb:
                if(b)
                    reportType="weekly";
                break;

            case R.id.monthly_rb:
                if(b)
                    reportType="monthly";
                break;
        }


    }

    String[] spinnerFamilyMemberData= {"","1","2","3","أكثر من 3"};
    String[] familyMemberValues={"1","2","3","3+"};
    private void createFamilyMemberSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,spinnerFamilyMemberData);
        spinnerFamilyMember.setAdapter(SpinnerAdapter);
        spinnerFamilyMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                familyMemberStr=spinnerFamilyMemberData[position];
                if(position>0)
                    familyNum=familyMemberValues[position-1];

                if(salaryRangStr!=null&&salaryRangStr.equals("")){
                    if(familyNum.equals(familyMemberValues[1])){
                        salaryRangStr="5000";
                    }else {
                        salaryRangStr="10000";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabPosition=tab.getPosition();

        switch (tabPosition){
            case 0:saveMethod();
                break;

            case 1:cancelMethod();
                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    private void saveMethod() {

    }

    private void cancelMethod() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_profile_container,new ProfileFragment()).commit();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
