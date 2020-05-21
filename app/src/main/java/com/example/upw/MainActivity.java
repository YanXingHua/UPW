package com.example.upw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.upw.ui.aboutme.AboutMeActivity;
//import com.example.upw.ui.exhibitions_events.ExhEventActivity;
import com.example.upw.ui.exhibitions_events.ExhEventActivity;
import com.example.upw.ui.favoritelist.FavoriteListActivity;
import com.example.upw.ui.myorders.MyOrdersActivity;
import com.example.upw.ui.overview.OverViewActivity;
import com.example.upw.ui.registerLogin.RegisterLoginActivity;
import com.example.upw.ui.shopcart.ShopActivity;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏显示
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_stockstore, R.id.nav_company,
                R.id.nav_sustainability, R.id.nav_u_lab, R.id.nav_exhibitions_events,R.id.nav_language,R.id.nav_register_login)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SharedPreferences sp = getSharedPreferences("loginInfo", 0);
        MenuItem item = menu.findItem(R.id.action_Login_register);
        if (sp.getString("token","").isEmpty()) {
            item.setTitle("Sign in");
        }else{
            item.setTitle("Sign out");
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=null;
        SharedPreferences sp = getSharedPreferences("loginInfo", 0);
        if(item.getItemId()==R.id.action_Login_register){
            if (sp.getString("token","").isEmpty()){
                startActivity(new Intent(this,RegisterLoginActivity.class));
            }else{

                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Sign Out");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = getSharedPreferences("loginInfo", 0).edit();
                        //存入登录状态时的用户名
                        editor.putString("token", "");
                        //提交修改
                        editor.apply();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        }
            if(item.getItemId()==R.id.action_shop_cart){
                if (sp.getString("token", "").isEmpty()) {
                    startActivity(new Intent(this, RegisterLoginActivity.class));
                }else {
                    startActivity(new Intent(this, ShopActivity.class));
                }
            }
            if(item.getItemId()==R.id.actin_over_view){
                String a=sp.getString("token", "");
                if (sp.getString("token", "").isEmpty()) {
                    startActivity(new Intent(this, RegisterLoginActivity.class));
                }else {
                    intent=new Intent(this, OverViewActivity.class);
                    intent.putExtra("token",sp.getString("token",""));
                    startActivity(intent);
                }
            }
            if(item.getItemId()==R.id.active_about_me){
                if (sp.getString("token", "").isEmpty()) {
                    startActivity(new Intent(this, RegisterLoginActivity.class));
                }else {
                    intent=new Intent(this, AboutMeActivity.class);
                    intent.putExtra("token",sp.getString("token",""));
                    startActivity(intent);
                }
            }
            if(item.getItemId()==R.id.action_my_order){
                if (sp.getString("token", "").isEmpty()) {
                    startActivity(new Intent(this, RegisterLoginActivity.class));
                }else {
                    startActivity(new Intent(this, MyOrdersActivity.class));
                }
            }
            if(item.getItemId()==R.id.action_exbt_events){
                if (sp.getString("token", "").isEmpty()) {
                    startActivity(new Intent(this, RegisterLoginActivity.class));
                }else {
                    startActivity(new Intent(this, ExhEventActivity.class));
                }
            }
            if(item.getItemId()==R.id.action_fav_list){
                if (sp.getString("token", "").isEmpty()) {
                    startActivity(new Intent(this, RegisterLoginActivity.class));
                }else {
                    startActivity(new Intent(this, FavoriteListActivity.class));
                }
            }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
