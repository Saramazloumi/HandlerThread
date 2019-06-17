package com.midterm.lasalle.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private HandlerThread handlerThread;
    private Handler handler;
    int count = 0;

    TextView textView1, textView2, textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        handler = new Handler(handlerThread.getLooper());
    }

    public void send(View view){
        //simple handler running
        for (int i = 0; i <= 10 ; i++) {
            count = i;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView1.setText("Handler Running for i = " + count);
                }
            });
            SystemClock.sleep(1000);
        }
        //handler with delay
        for (int i = 0; i <= 10 ; i++) {
            count = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView2.setText("Handler Running with delay for i = " + count);
                }
            }, 8000);
            SystemClock.sleep(1000);
        }

        //handler in front of the q
        for (int i = 0; i <=10 ; i++) {
            count = i;
            handler.postAtFrontOfQueue(new Runnable() {
                @Override
                public void run() {
                    textView3.setText("Handlet fron of the Q for i = " + count);
                }
            });
            SystemClock.sleep(1000);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handlerThread.quit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (handlerThread.getLooper() == null){
            handlerThread = new HandlerThread("MainHandlerThread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}
