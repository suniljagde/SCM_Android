package com.example.tiplservice.Activities;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.tiplservice.R;

import com.example.tiplservice.NavFragments.DashboardFragment;
import com.example.tiplservice.NavFragments.ProfileFragment;
import com.example.tiplservice.NavFragments.UserFragment;
import com.example.tiplservice.SharedPrefManager;
import com.example.tiplservice.getcall.CallDataFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        bottomNavigationView=findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());

        sharedPrefManager=new SharedPrefManager(getApplicationContext());





        }

//        bottomNavigationView=findViewById(R.id.bottomnav);
//       implements BottomNavigationView.OnNavigationItemSelectedListener
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        loadFragment(new DashboardFragment());




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment= null;

        int itemId = menuItem.getItemId();
        if (itemId == R.id.dashboard) {
            fragment = new DashboardFragment();
        } else if (itemId == R.id.users) {
            fragment = new UserFragment();
        } else if (itemId == R.id.profile) {
            fragment = new ProfileFragment();
        } else if (itemId == R.id.cdata) {
           fragment = new CallDataFragment();
        }

        if (fragment!=null){
            loadFragment(fragment);
        }
        return true;
    }
    void loadFragment(Fragment fragment){

        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logoutmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.logout) {
            logoutUser();
        } else if (itemId == R.id.deleteAccount) {
            deleteAccount();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAccount() {
    }

    private void logoutUser() {

        sharedPrefManager.logout();
        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
       startActivity(intent);

        Toast.makeText(this, "You have been Logeed out", Toast.LENGTH_SHORT).show();
    }
}