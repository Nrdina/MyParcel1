package com.example.hp.MyParcel;

/**
 * Created by HP on 5/21/2018.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    //private static final String LOGIN_REQUEST_URL = "http://inkakiu.000webhostapp.com/Login.php"; //using host website
    private static final String LOGIN_REQUEST_URL = "http://192.168.43.171/myParcel/Login.php"; //using wifi rumah
    private Map<String, String> params;

    public LoginRequest(String user_email, String user_password, Response.Listener<String> listener)
    {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_email", user_email);
        params.put("user_password", user_password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
