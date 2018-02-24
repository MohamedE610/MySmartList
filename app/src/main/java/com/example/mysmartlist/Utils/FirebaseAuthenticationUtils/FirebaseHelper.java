package com.example.mysmartlist.Utils.FirebaseAuthenticationUtils;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.mysmartlist.Activities.MainActivity;
import com.example.mysmartlist.Activities.SignInActivity;
import com.example.mysmartlist.Utils.Callbacks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseHelper {

    protected Callbacks callback;
    protected FirebaseAuth auth;


    public FirebaseHelper(){
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    public void setCallback(Callbacks callback) {
        this.callback = callback;
    }


}
