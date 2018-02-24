package com.example.mysmartlist.Utils.FirebaseAuthenticationUtils;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseSignUp extends FirebaseHelper {

    public FirebaseSignUp(){

    }

    public void signUpWithFirebase(String email, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            callback.OnSuccess(task);
                        } else {
                            callback.OnFailure(task);
                        }
                    }
                });
    }
}
