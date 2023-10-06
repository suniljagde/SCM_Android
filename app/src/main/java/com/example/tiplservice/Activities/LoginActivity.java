package com.example.tiplservice.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiplservice.ModelResponse.LoginResponse;
import com.example.tiplservice.R;
import com.example.tiplservice.RetrofitClient;
import com.example.tiplservice.SharedPrefManager;
//import com.example.tiplservice.SharedPrefManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login;
    TextView registerlink;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

  //       getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnlogin);
        registerlink = findViewById(R.id.registerlink);

        registerlink.setOnClickListener(this);
        login.setOnClickListener(this);

        sharedPrefManager= new SharedPrefManager(getApplicationContext());

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnlogin) {
            userLogin();
        }

            if (view.getId() == R.id.registerlink) {
                switchOnRegister();
            }
    }

    private void userLogin() {


        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (userEmail.isEmpty()) {
            email.requestFocus();
            email.setError("Please enter your email");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.requestFocus();
            email.setError("Please enter correct email");
            return;
        }
        if (userPassword.isEmpty()) {
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }
        if (userPassword.length() < 8) {
            password.requestFocus();
            password.setError("Please enter your password");
            return;


        }

        Call<LoginResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .login(userEmail,userPassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                 LoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {



                    if (loginResponse.getError().equals("200")){

                           sharedPrefManager.saveUser(loginResponse.getUser());
                           Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);
                           Toast.makeText(LoginActivity.this, "Login successful as " + email.getText().toString(), Toast.LENGTH_SHORT).show();

                       }



                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void switchOnRegister() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLoggedIn()){
            Intent intent= new Intent(LoginActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}