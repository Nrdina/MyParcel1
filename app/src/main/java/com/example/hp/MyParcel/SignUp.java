package com.example.hp.MyParcel;

/**
 * Created by HP on 5/21/2018.
 */

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    EditText edtName, edtPhone, edtEmail, edtPwd;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText edtName = (EditText) findViewById(R.id.edtName);
        final EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        final EditText edtPwd = (EditText) findViewById(R.id.edtPwd);

        final Button btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_name = edtName.getText().toString();
                final String user_email = edtEmail.getText().toString();
                final String user_password = edtPwd.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(SignUp.this, LoginActivity2.class);
                                intent.putExtra("user_name", String.valueOf(user_name));
                                intent.putExtra("user_email", String.valueOf(user_email));
                                intent.putExtra("user_password", String.valueOf(user_password));

                                SignUp.this.startActivityForResult(intent, 0);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(user_name, user_email, user_password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                queue.add(registerRequest);
            }
        });
    }
}
