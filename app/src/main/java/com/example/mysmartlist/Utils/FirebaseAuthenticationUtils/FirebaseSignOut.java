package com.example.mysmartlist.Utils.FirebaseAuthenticationUtils;

/**
 * Created by E610 on 2/24/2018.
 */

public class FirebaseSignOut extends FirebaseHelper {

    public FirebaseSignOut(){

    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }


}
