package com.example.mysmartlist.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mysmartlist.Activities.ResetPasswordActivity;
import com.example.mysmartlist.Activities.SignupActivity;
import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getClientByIDRequest;


public class ProfileFragment extends Fragment implements Callbacks, View.OnClickListener {


    TextView userName;
    TextView gender;
    TextView salary;
    TextView budget;
    TextView familyNum;
    TextView phoneNum;
    TextView email;
    TextView changePassword;
    LinearLayout editLinearLayout;

    getClientByIDRequest clientByIDRequest;


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        userName=(TextView) view.findViewById(R.id.username_text);
        gender=(TextView) view.findViewById(R.id.gender_text);
        salary=(TextView) view.findViewById(R.id.salary_text);
        budget=(TextView) view.findViewById(R.id.budget_text);
        familyNum=(TextView) view.findViewById(R.id.family_num_text);
        phoneNum=(TextView) view.findViewById(R.id.phone_num_text);
        email=(TextView) view.findViewById(R.id.email_text);
        changePassword=(TextView) view.findViewById(R.id.change_password_text);
        editLinearLayout=(LinearLayout)view.findViewById(R.id.linear_edit);


        MySharedPreferences.setUpMySharedPreferences(getActivity());
        int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));

        clientByIDRequest=new getClientByIDRequest(uid);
        clientByIDRequest.setCallbacks(this);
        clientByIDRequest.start();


        changePassword.setOnClickListener(this);
        editLinearLayout.setOnClickListener(this);



        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        Client client=(Client)obj;

        int Uid=client.data.id;
        int clientSalary=client.data.salary;
        String clientBudget=client.data.budget;

        MySharedPreferences.setUpMySharedPreferences(getActivity());
        MySharedPreferences.setUserSetting("uid",Uid+"");
        MySharedPreferences.setUserSetting("clientSalary",clientSalary+"");
        MySharedPreferences.setUserSetting("clientBudget",clientBudget+"");

        userName.setText(client.data.name);
        email.setText(client.data.email);
        gender.setText(client.data.gender);
        phoneNum.setText(client.data.phone);
        familyNum.setText(client.data.familyMembers);
        salary.setText(client.data.salary+"");

        budget.setText((clientSalary/4)+"");

    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id){

            case R.id.change_password_text:
                changePasswordMethod();
                break;

            case R.id.linear_edit:
                linearEditMethod();
                break;
        }

    }

    private void linearEditMethod() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_profile_container,new EditProfileFragment()).commit();
    }

    private void changePasswordMethod() {
        getActivity().startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
    }
}
