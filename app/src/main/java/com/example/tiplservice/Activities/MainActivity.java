package com.example.tiplservice.Activities;

import androidx.appcompat.app.AppCompatActivity;

//import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiplservice.ModelResponse.RegisterResponse;
import com.example.tiplservice.R;
import com.example.tiplservice.RetrofitClient;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    TextView loginlink;
    EditText Name, email, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



   // getSupportActionBar().hide();

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        register = findViewById(R.id.btnregister);
        loginlink = findViewById(R.id.loginlink);

        loginlink.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

            if (id == R.id.btnregister) {
                registerUser();
            } else if (id == R.id.loginlink) {
                switchonLogin();
            }


    }

    private void registerUser() {

        String userName=Name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        if (userName.isEmpty()){
            Name.requestFocus();
            Name.setError("Please enter your name");
            return;
        }

        if (userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please enter your email");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Please enter correct email");
            return;
        }
        if (userPassword.isEmpty()){
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }
        if (userPassword.length()<8){
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }


        Call<RegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .register(userName,userEmail,userPassword);

           call.enqueue(new Callback<RegisterResponse>() {
               @Override
               public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                   RegisterResponse registerResponse=response.body();
                   if (response.isSuccessful()){

                       Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                       finish();
                       Toast.makeText(MainActivity.this, registerResponse.toString(), Toast.LENGTH_SHORT).show();

                   }
                   else {
                       Toast.makeText(MainActivity.this, registerResponse.toString(), Toast.LENGTH_SHORT).show();
                   }

               }

               @Override
               public void onFailure(Call<RegisterResponse> call, Throwable t) {

                   Toast.makeText(MainActivity.this,t.toString(), Toast.LENGTH_SHORT).show();
               }
           });

    }

    private void switchonLogin() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);

    }
}