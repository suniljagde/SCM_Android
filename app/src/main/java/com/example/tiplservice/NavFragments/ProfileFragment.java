package com.example.tiplservice.NavFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiplservice.Activities.HomeActivity;
import com.example.tiplservice.Activities.LoginActivity;
import com.example.tiplservice.ModelResponse.LoginResponse;
import com.example.tiplservice.R;
import com.example.tiplservice.RetrofitClient;
import com.example.tiplservice.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener{

  EditText etuserName,etuserEmail;
  Button updateUserAccountBtn,btn;
  SharedPrefManager sharedPrefManager;

  int userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etuserName=view.findViewById(R.id.userName);
        etuserEmail=view.findViewById(R.id.userEmail);
        updateUserAccountBtn=view.findViewById(R.id.btnupdateAccount);
//        btn=view.findViewById(R.id.btnlogout);
//
//        sharedPrefManager=new SharedPrefManager(getContext());



        sharedPrefManager= new SharedPrefManager(getActivity());
       userId=sharedPrefManager.getUser().getId();


        updateUserAccountBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnupdateAccount) {
            updateUserAccount();
        }

    }

    private void updateUserAccount() {

        String username=etuserName.getText().toString().trim();
        String email=etuserEmail.getText().toString().trim();


        if (username.isEmpty()){
            etuserName.setError("Please enter user name");
            etuserName.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etuserEmail.requestFocus();
            etuserEmail.setError("Please enter correct email");
            return;
        }

        Call<LoginResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .updateUserAccount(userId,username,email);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse updateResponse=response.body();
                if (response.isSuccessful()){

                    if (updateResponse.getError().equals("200")){
                        sharedPrefManager.saveUser(updateResponse.getUser());
                        Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });




    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//
//        getActivity().getMenuInflater().inflate(R.menu.logoutmenu,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == R.id.logout) {
//            logoutuser();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void logoutuser() {
//        sharedPrefManager.logout();
//        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
//
//    }
}