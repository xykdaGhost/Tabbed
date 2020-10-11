package com.example.tabbed;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.tabbed.ui.main.SectionsPagerAdapter;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        ImageButton imageButton = findViewById(R.id.network_button);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
                int ipAddressInt = wm.getDhcpInfo().gateway;
                String ipAddress = String.format(Locale.getDefault(), "%d.%d.%d.%d", (ipAddressInt & 0xff), (ipAddressInt >> 8 & 0xff), (ipAddressInt >> 16 & 0xff), (ipAddressInt >> 24 & 0xff));
                Log.d("MainActivity", "ip: " + ipAddress);

                new SocketClient(ipAddress).start();

            }
        });
//        FloatingActionButton fab = findViewById(R.id.fab);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}