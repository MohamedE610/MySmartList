package com.example.mysmartlist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.Models.Client.Fav;
import com.example.mysmartlist.Models.Client.Pin;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.AlarmManagerUtils;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FirebaseAuthenticationUtils.FirebaseSignUp;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.createClientAccountRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getClientByIDRequest;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    createClientAccountRequest createClient;
    HashMap clientDetails;

    /*public static void getClient(int id){

        getClientByIDRequest clientByIDRequest=new getClientByIDRequest(id);
        clientByIDRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                client=(Client)obj;
                MainActivity.addHomeFragment();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        clientByIDRequest.start();

    }*/

    //public static Client client;
    public Client client;
    Callbacks callbacks = new Callbacks() {
        @Override
        public void OnSuccess(Object obj) {
            try {
                client = (Client) obj;
                int Uid = client.data.id;
                int clientSalary = client.data.salary;
                String clientBudget = client.data.budget;

                MySharedPreferences.setUpMySharedPreferences(SignupActivity.this);
                MySharedPreferences.setUserSetting("uid", Uid + "");
                MySharedPreferences.setUserSetting("clientSalary", clientSalary + "");
                MySharedPreferences.setUserSetting("clientBudget", clientBudget + "");

                AlarmManagerUtils alarmManagerUtils = new AlarmManagerUtils(SignupActivity.this);
                if (clientBudget.equals("weekly")) {
                    //alarmManagerUtils.setWeeklyAlarm();
                } else if (clientBudget.equals("monthly")) {
                    //alarmManagerUtils.setMonthlyAlarm();
                }

                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            } catch (Exception e) {
            }
        }

        @Override
        public void OnFailure(Object obj) {
            Toast.makeText(SignupActivity.this, "Please, try to Sign Up again", Toast.LENGTH_SHORT).show();
        }
    };

    String name, phone, email, gender, familyNum, salaryRang, reportType;

    Spinner spinnerFamilyMember;
    //Spinner spinnerSalaryRang;
    String familyMemberStr;
    String salaryRangStr;

    RadioButton maleRadioBtn, femaleRadioBtn, weeklyRadioBtn, monthlyRadioBtn;

    private EditText inputEmail, inputPassword, inuptUserName, inputPhoneNum, inputPasswordConfirm, inputSalary;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    //private FirebaseAuth auth;
    FirebaseSignUp firebaseSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //a a1=new a();
        // Log.d("asdasdasd",a1.i+"");
        //Get Firebase auth instance
        //auth = FirebaseAuth.getInstance();
        firebaseSignUp = new FirebaseSignUp();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        inuptUserName = (EditText) findViewById(R.id.user_name);
        inputPhoneNum = (EditText) findViewById(R.id.phone_num);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputPasswordConfirm = (EditText) findViewById(R.id.password_confirm);
        inputSalary = (EditText) findViewById(R.id.salary_text);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        maleRadioBtn = (RadioButton) findViewById(R.id.male_rb);
        femaleRadioBtn = (RadioButton) findViewById(R.id.female_rb);
        weeklyRadioBtn = (RadioButton) findViewById(R.id.weekly_rb);
        monthlyRadioBtn = (RadioButton) findViewById(R.id.monthly_rb);

        maleRadioBtn.setOnCheckedChangeListener(this);
        femaleRadioBtn.setOnCheckedChangeListener(this);
        weeklyRadioBtn.setOnCheckedChangeListener(this);
        monthlyRadioBtn.setOnCheckedChangeListener(this);


        //spinnerSalaryRang=(Spinner)findViewById(R.id.spinner_salary_rang);
        spinnerFamilyMember = (Spinner) findViewById(R.id.spinner_family_member);
        //createSalaryRangSpinner();
        createFamilyMemberSpinner();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, SignInActivity.class));
                finish();
            }
        });

        clientDetails = new HashMap();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = inuptUserName.getText().toString().trim();
                phone = inputPhoneNum.getText().toString().trim();
                email = inputEmail.getText().toString().trim();
                salaryRangStr = inputSalary.getText().toString().trim();

                String password = inputPassword.getText().toString().trim();

                String passwordConfirm = inputPasswordConfirm.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "ادخل البريد الالكترونى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "ادخل كلمه السر", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "ادخل رقم الهاتف", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "ادخل اسم المستخدم", Toast.LENGTH_SHORT).show();
                    return;
                }

               /* if (TextUtils.isEmpty(salaryRangStr)) {
                    Toast.makeText(getApplicationContext(), R.string.enter_password, Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(), "تاكيد كلمه السر غير صحيح", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), R.string.password_too_short_enter_minimum_6_characters, Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseSignUp.setCallback(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        Task<AuthResult> task = (Task<AuthResult>) obj;

                        Toast.makeText(SignupActivity.this, getString(R.string.createUserwithemailoncomplete) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        try {
                            clientDetails = new HashMap();
                            clientDetails.put("name", name);
                            clientDetails.put("phone", phone);
                            clientDetails.put("email", email);
                            clientDetails.put("gender", gender);
                            clientDetails.put("firebase_id", task.getResult().getUser().getUid());
                            clientDetails.put("budget_type", reportType);
                            clientDetails.put("family_count", familyNum);
                            clientDetails.put("salary", salaryRangStr);
                        } catch (Exception e) {
                        }

                        createClient = new createClientAccountRequest(clientDetails);
                        createClient.setCallbacks(callbacks);
                        createClient.start();

                        /*startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finish();*/
                    }

                    @Override
                    public void OnFailure(Object obj) {

                        Task<AuthResult> task = (Task<AuthResult>) obj;
                        Toast.makeText(SignupActivity.this, getString(R.string.createUserwithemailoncomplete) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(SignupActivity.this, getString(R.string.authentication_failed) + task.getException(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
                firebaseSignUp.signUpWithFirebase(email, password);

                progressBar.setVisibility(View.VISIBLE);
                //create user
                /*auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, getString(R.string.createUserwithemailoncomplete) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, getString(R.string.authentication_failed) + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


    String[] spinnerFamilyMemberData = {" ","فرد","عائلة"};
    String[] familyMemberValues = {"1", "2", "3", "3+"};

    private void createFamilyMemberSpinner() {
        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerFamilyMemberData);
        spinnerFamilyMember.setAdapter(SpinnerAdapter);
        spinnerFamilyMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                familyMemberStr = spinnerFamilyMemberData[position];
                if (position > 0)
                    familyNum = familyMemberValues[position - 1];

                if (salaryRangStr != null && salaryRangStr.equals("")) {
                    if ( familyNum != null&&familyNum.equals(familyMemberValues[0])) {
                        salaryRangStr = "2262";
                    } else {
                        salaryRangStr = "13610";
                    }

                }else  if (salaryRangStr == null ){
                    if (familyNum != null&& familyNum.equals(familyMemberValues[0])) {
                        salaryRangStr = "2262";
                    } else {
                        salaryRangStr = "13610";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*String[] spinnerSalaryRangData= {"","من 2000 الى 5000","من 6000 الى 15000","من 16000 الى 25000"};
    String[] salaryRangValues={"2000:5000","6000:15000","16000:25000"};
    private void createSalaryRangSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,spinnerSalaryRangData);
        spinnerSalaryRang.setAdapter(SpinnerAdapter);
        spinnerSalaryRang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                salaryRangStr=spinnerSalaryRangData[position];
                if(position>0)
                   salaryRang=salaryRangValues[position-1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();

        switch (id) {
            case R.id.male_rb:
                if (b)
                    gender = "male";
                break;

            case R.id.female_rb:
                if (b)
                    gender = "female";
                break;

            case R.id.weekly_rb:
                if (b)
                    reportType = "weekly";
                break;

            case R.id.monthly_rb:
                if (b)
                    reportType = "monthly";
                break;
        }

    }
}