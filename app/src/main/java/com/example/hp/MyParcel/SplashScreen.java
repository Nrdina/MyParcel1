package com.example.hp.MyParcel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.anim_logo);
        ImageView imageSplash = (ImageView) findViewById(R.id.imageSplash);
        AnimationSet logoanim = new AnimationSet(false);
        logoanim.addAnimation(animation);
        imageSplash.startAnimation(animation);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);//timer to sleep
                    Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException ae) {
                    ae.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
