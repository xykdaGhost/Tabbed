package com.example.tabbed;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class SocketClient extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String hostAddress;

    public static int PORT = 8008;
    private static final int WEATHERREQUEST = 0;
    private static final int TESTREQUEST = 1;
    private static final int FAILURE = -1;

    public SocketClient(String address) {
        socket = null;
        hostAddress = address;
        reader = null;
        writer = null;
    }

    @Override
    public void run() {
        super.run();

        Socket socket = null;
        while (true) {
            try {
                Log.d("Main", "start linking");
                socket = new Socket("192.168.141.124", PORT);
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                String str = "That's cool";
                writer.writeUTF(str);

                String input = reader.readUTF();
                Log.d("Main:", input);

                if ("OK".equals(input)) {
                    Log.d("Main", "close client");
                    Thread.sleep(500);
                    break;
                }

                writer.close();
                reader.close();


            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof SocketTimeoutException) {
                    Log.e("main", "timeout");
                } else {
                    Log.e("main", "disconnected");
                }
            } finally {
                try {
                    socket.close();
                    Log.d("main", "stop");
                } catch (IOException e) {
                    socket = null;
                    e.printStackTrace();
                }
            }
        }
    }
}
