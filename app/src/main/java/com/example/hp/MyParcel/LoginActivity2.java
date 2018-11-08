package com.example.hp.MyParcel;

/**
 * Created by HP on 5/21/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity2 extends AppCompatActivity {

    EditText edtEmail, edtPwd;
    Button btnLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);

        final EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        final EditText edtPwd = (EditText) findViewById(R.id.edtPwd);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        TextView txtRegister = (TextView) findViewById(R.id.txtRegister);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity2.this, SignUp.class);
                LoginActivity2.this.startActivityForResult(registerIntent, 0);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_email = edtEmail.getText().toString();
                final String user_password = edtPwd.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                //String user_name = jsonResponse.getString("user_name");
                                //String user_phone = jsonResponse.getString("user_phone");

                                Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                                String user_name = intent.getStringExtra("user_name");

                                intent.putExtra("user_name", String.valueOf(user_name));
                                //intent.putExtra("user_email", user_email);
                                startActivityForResult(intent, 0);

                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity2.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(user_email, user_password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity2.this);
                queue.add(loginRequest);
            }
        });
    }
}