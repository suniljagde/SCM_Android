package com.example.tiplservice.NavFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiplservice.DActivity.support_call;
import com.example.tiplservice.R;
import com.example.tiplservice.SharedPrefManager;
//import com.example.tiplservice.SharedPrefManager;


public class DashboardFragment extends Fragment implements View.OnClickListener{

   public CardView card1 ;

   TextView etname,etemail;
   SharedPrefManager sharedPrefManager;

//date 26 new after get call update



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        etname=view.findViewById(R.id.etname);
        etemail=view.findViewById(R.id.etemail);

        //callsupport
        card1=view.findViewById(R.id.supportcall);
        card1.setOnClickListener(this);


        sharedPrefManager = new SharedPrefManager(getActivity());

        String userName="Hey! "+ sharedPrefManager.getUser().getUsername();
        etname.setText(userName);
        etemail.setText(sharedPrefManager.getUser().getEmail());

      return view;


    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.supportcall) {
          Intent intent = new Intent(getActivity(),support_call.class);
            startActivity(intent);
        }


    }
}