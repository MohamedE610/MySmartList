package com.example.mysmartlist.Utils.FirebaseAuthenticationUtils;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseSignIn extends FirebaseHelper {

    private FirebaseUser firebaseUser;

    public FirebaseSignIn(){
        firebaseUser=auth.getCurrentUser();
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void signInWithFirebase(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // there was an error
                    callback.OnSuccess(task);
                } else {
                    callback.OnFailure(task);
                }
            }
        });

    }

}
