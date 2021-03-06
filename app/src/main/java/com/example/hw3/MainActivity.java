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

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        //Create threads when you click the button
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

    public class DogThread extends Thread{
        int imageIndex;
        int state;
        ArrayList<Integer> images = new ArrayList<Integer>();

        //DogThread Constructor
        public DogThread(int idx){
            imageIndex = idx;

            //Insert dog images into the images list
            images.add(R.drawable.dog1);
            images.add(R.drawable.dog2);
            images.add(R.drawable.dog3);
        }
        public void run(){
            state = 0;
            while(true){
                //Post a Runnable using a handler
                handler.post(new Runnable(){
                   public void run(){
                       //Check the image Index and Load image that fits state value
                       if(imageIndex == 0){
                           imageView1.setImageResource(images.get(state));
                       }
                       else{
                           imageView2.setImageResource(images.get(state));
                       }
                       //Log current status
                       editText.append("dog #"+imageIndex+" state: "+state+"\n");
                   }
                });

                try {
                    //Setting sleep time. Minimum time = 1sec, Maximum time = 5sec
                    int sleepTime = getRandomTime(1000, 5000);
                    Thread.sleep(sleepTime);
                } catch (Exception ex) {}

                state++; //Change state
                if(state == images.size()) //If the state is equal to the length of the images, reset the state to zero
                    state = 0;
            }
        }

        //Get Random Time between min and max
        public int getRandomTime(int min, int max){
            return min+(int)(Math.random()*(max-min));
        }
    }
}
