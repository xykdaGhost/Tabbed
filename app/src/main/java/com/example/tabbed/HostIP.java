//package com.example.tabbed;
//
//import android.content.Context;
//import android.net.DhcpInfo;
//import android.net.InetAddresses;
//import android.net.wifi.WifiManager;
//import android.nfc.Tag;
//import android.util.Log;
//
//import java.net.InetAddress;
//import java.text.Format;
//import java.util.Formatter;
//
//
//
//public class HostIP {
//
//
//    private String address;
//
//    public HostIP() {
//        address = null;
//
//    }
//
//
//    private static String getWifiMasterAddress(Context ) {
//        WifiManager wifi_service = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        DhcpInfo dhcpInfo = wifi_service.getDhcpInfo();
//        return address;
//    }
//
//    public String getAddres() {
//        address = getWifiMasterAddress();
//        return address;
//    }
//}
