package com.example.user.navigationdrawer;

import android.net.Network;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public  class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            NavigationView navigationView=findViewById(R.id.navview);
            setupDrawerContent(navigationView);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame,new dashboard()).commit();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
           if(toggle.onOptionsItemSelected(item)){
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
        public void selectItemDrawer(MenuItem menuItem){
        Fragment fragment=null;
        Class FragmentClass;
        switch (menuItem.getItemId()){
            case R.id.db:
                FragmentClass= dashboard.class;
                break;
            case R.id.event:
                FragmentClass=events.class;
                break;
            case R.id.activities:
                FragmentClass=activities.class;
                break;
            case R.id.search:
                FragmentClass=search.class;
                break;
            case R.id.settings:
                FragmentClass=settings.class;
                break;
            case R.id.logout:
                 finish();
                 System.exit(0);
                default:
                    FragmentClass=dashboard.class;
        }
        try {
            fragment=(Fragment) FragmentClass.newInstance();
        }
        catch (Exception E){
            E.printStackTrace();
        }
            FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame,fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
        }
        public void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
        }

    }




