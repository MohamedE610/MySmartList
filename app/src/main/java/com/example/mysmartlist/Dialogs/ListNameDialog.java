package com.example.mysmartlist.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysmartlist.Activities.ListsActivity;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.addListRequest;

import java.util.HashMap;


/**
 * Created by abdallah on 12/14/2017.
 */
public class ListNameDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;

    Button okBtn;
    Button cancelBtn;

    EditText editText;
    private addListRequest listRequest;

    public ListNameDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_name_dialog);

        /**************** make background transparent *********************/
        //make background transparent
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        editText=(EditText) findViewById(R.id.list_name_txt);
        okBtn=(Button)findViewById(R.id.btn_ok);
        cancelBtn=(Button)findViewById(R.id.btn_cancel);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.btn_ok:
                okBtnWork();
                break;
            case R.id.btn_cancel:
                cancelBtnWork();
                break;
            default:
                break;
        }
    }

    private void cancelBtnWork() {
        this.cancel();
    }

    private void okBtnWork() {

        if(!TextUtils.isEmpty(editText.getText())) {
            HashMap<String, String> hashMap = new HashMap<>();
            String name = editText.getText().toString();
            hashMap.put("name", name);
            MySharedPreferences.setUpMySharedPreferences(c);
            int id = Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            listRequest = new addListRequest(id, hashMap);
            listRequest.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    ListsActivity.addListsFragment();
                }

                @Override
                public void OnFailure(Object obj) {

                }
            });

            listRequest.start();
            ListNameDialog.this.cancel();
        }else {
            Toast.makeText(c, "الرجاء ادخال اسم القائمه", Toast.LENGTH_SHORT).show();
        }
    }

    // final int facebookId=R.id.facebook_img;


}
