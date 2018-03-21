package com.example.mysmartlist.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mysmartlist.Activities.ContactUsActivity;
import com.example.mysmartlist.Activities.OldListsActivity;
import com.example.mysmartlist.Activities.ProfileActivity;
import com.example.mysmartlist.Activities.QuestionsActivity;
import com.example.mysmartlist.Activities.SignInActivity;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.FirebaseAuthenticationUtils.FirebaseSignOut;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    public SettingsFragment() {
    }

    View view;
    ImageView oldListsImg;
    ImageView reportsImg;
    ImageView profileImg;
    ImageView questionsImg;
    ImageView contactUsImg;
    ImageView logOutImg;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_setting, container, false);
        oldListsImg=(ImageView)view.findViewById(R.id.old_list);
        reportsImg=(ImageView)view.findViewById(R.id.report_img);
        profileImg=(ImageView)view.findViewById(R.id.profile_img);
        questionsImg=(ImageView)view.findViewById(R.id.questions_img);
        contactUsImg=(ImageView)view.findViewById(R.id.contact_us_img);
        logOutImg=(ImageView)view.findViewById(R.id.logout_img);

        oldListsImg.setOnClickListener(this);
        reportsImg.setOnClickListener(this);
        questionsImg.setOnClickListener(this);
        profileImg.setOnClickListener(this);
        contactUsImg.setOnClickListener(this);
        logOutImg.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {

        int id =view.getId();

        switch (id){

            case R.id.old_list:
                oldListsMethod();
                break;
            case R.id.report_img:
                 reportsMethod();
                break;
            case R.id.profile_img:
                profileMethod();
                break;
            case R.id.questions_img:
                questionsMethod();
                break;
            case R.id.contact_us_img:
                contactUsMethod();
                break;
            case R.id.logout_img:
                logOutMethod();
                break;
        }
    }

    FirebaseSignOut firebaseSignOut;
    private void logOutMethod() {
        firebaseSignOut=new FirebaseSignOut();
        firebaseSignOut.signOut();
        startActivity(new Intent(getActivity(), SignInActivity.class));
        getActivity().finish();
    }

    private void contactUsMethod() {
        startActivity(new Intent(getActivity(), ContactUsActivity.class));
    }

    private void questionsMethod() {
        startActivity(new Intent(getActivity(), QuestionsActivity.class));
    }

    private void profileMethod() {
        startActivity(new Intent(getActivity(), ProfileActivity.class));
    }

    private void reportsMethod() {
    }

    private void oldListsMethod() {
        startActivity(new Intent(getActivity(), OldListsActivity.class));
    }
}
