package com.example.mysmartlist.Utils.FirebaseAuthenticationUtils;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by E610 on 2/23/2018.
 */

public class FirebaseResetPassword extends FirebaseHelper {

    public FirebaseResetPassword(){

    }


    public void resetPasswordWithFirebase(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.OnSuccess(task);
                        } else {
                            callback.OnFailure(task);
                        }
                    }
                });
    }
}
