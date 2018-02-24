package com.example.mysmartlist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FirebaseAuthenticationUtils.FirebaseSignIn;


public class SignInActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    //private FirebaseAuth auth;
    private ProgressBar progressBar;
    private TextView btnSignup,btnReset;
    private Button  btnLogin ;
    private CheckBox checkBox;
    FirebaseSignIn firebaseSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        //auth = FirebaseAuth.getInstance();
        firebaseSignIn=new FirebaseSignIn();

        //if (auth.getCurrentUser() != null) {
        if(firebaseSignIn.getFirebaseUser()!=null){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_sginin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkBox=(CheckBox)findViewById(R.id.checkbox);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (TextView) findViewById(R.id.btn_reset_password);
        btnSignup = (TextView) findViewById(R.id.btn_signup);
        btnSignup.setClickable(true);
        btnReset.setClickable(true);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // TODO use SharedPref to Save email
            }
        });

        //Get Firebase auth instance
        //auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignupActivity.class));
                finish();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin.setEnabled(false);
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.enter_email_address, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), R.string.enter_password, Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseSignIn.setCallback(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        btnLogin.setEnabled(true);
                        finish();
                    }

                    @Override
                    public void OnFailure(Object obj) {

                        btnLogin.setEnabled(true);

                        if (password.length() < 6) {
                            inputPassword.setError(getString(R.string.minimum_password));
                        } else {
                            Toast.makeText(SignInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                firebaseSignIn.signInWithFirebase(email,password);


                //authenticate user
                /*auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(SignInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });*/

            }
        });
    }
}

