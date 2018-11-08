package com.example.hp.MyParcel;

/**
 * Created by HP on 5/21/2018.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    //private static final String REGISTER_REQUEST_URL = "http://inkakiu.000webhostapp.com/Register.php"; //using host website
    private static final String REGISTER_REQUEST_URL = "http://192.168.43.171/myParcel/Register.php"; //using wifi rumah

    private Map<String, String> params;

    public RegisterRequest (String user_name, String user_email, String user_password, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_name", user_name);
        params.put("user_email", user_email);
        params.put("user_password", user_password);
    }

    public RegisterRequest(String user_name,  Response.Listener<String>listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_name", user_name);
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
