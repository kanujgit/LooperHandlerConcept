package com.test.pilot;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SimpleWorker simpleWorker;
    private TextView view;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            view.setText((CharSequence) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.txt_msg);

        simpleWorker = new SimpleWorker();

        simpleWorker.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "task 1 completed";
                handler.sendMessage(message);


            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "task 2 completed";
                handler.sendMessage(message);
            }
        }).execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "task 3 completed";
                handler.sendMessage(message);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleWorker.quit();
    }
}
