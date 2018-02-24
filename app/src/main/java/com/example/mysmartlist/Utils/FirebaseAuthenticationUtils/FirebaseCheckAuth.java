package com.example.mysmartlist.Utils.FirebaseAuthenticationUtils;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by E610 on 2/24/2018.
 */

public class FirebaseCheckAuth extends FirebaseHelper {

    private FirebaseAuth.AuthStateListener authListener;

    public FirebaseCheckAuth(){

    }

    public void checkFirebaseAuth(){
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    callback.OnSuccess(user);
                }
            }
        };
    }

}
