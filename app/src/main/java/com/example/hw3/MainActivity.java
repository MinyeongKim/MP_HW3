package com.example.hw3;

import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.dog1);
        images.add(R.drawable.dog2);
        images.add(R.drawable.dog3);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DogThread thread1 = new DogThread(0);
                thread1.start();

                DogThread thread2 = new DogThread(1);
                thread2.start();
            }
        });
    }

    class DogThread extends Thread{

        public DogThread(int num){

        }
        public void run(){
            try{
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);

            }catch (Exception ex){}
        }

        public int getRandomTime(int min, int max){
            return min+(int)(Math.random()*(max-min));
        }
    }
}
