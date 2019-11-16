package com.example.thedicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class game extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManger;
    private Sensor mAccelerometer;
    private static int SHAKE_THRESHOLD = 3;
    int count = 999;
    private ImageView imageView2;
    int a;
    Animation egg_ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        mSensorManger = (SensorManager) getSystemService(SENSOR_SERVICE);                //선언문인데 센서사용하기위한  센서매니저 선언문
        mAccelerometer = mSensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);      //윗줄말대로 선언햇으니 매니저. 하고사용 TYPE 뭐시기는 가속도센서
        egg_ani = AnimationUtils.loadAnimation(this,R.anim.egg_ani);
    }

    @Override
    protected void onResume() { //딜레이설정이라고하는데 나도잘모름 일단 센서를 사용하기위한 기본셋팅
        super.onResume();
        mSensorManger.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManger.unregisterListener(this); // un 붙어서 그냥 가속센서 종료시키기 화면위에 화면이 다른게생기면 종료된다는거임
    }

    private int eggNumber(int i){

        int val = i;
        if (val >= 900) {                          //숫자 1이뜨면
            imageView2.setImageResource(R.drawable.r);

        } else if (val >= 800) {
            imageView2.setImageResource(R.drawable.r2);

        }else if (val >= 700) {
            imageView2.setImageResource(R.drawable.r3);
        }else if (val >= 600) {
            imageView2.setImageResource(R.drawable.r4);
        }else if (val >= 500) {
            imageView2.setImageResource(R.drawable.r5);
        }else if (val >= 400) {
            imageView2.setImageResource(R.drawable.r6);
        }else if (val >= 300) {
            imageView2.setImageResource(R.drawable.r7);
        }else if (val >= 200) {
            imageView2.setImageResource(R.drawable.r8);
        }else if (val >= 100) {
            imageView2.setImageResource(R.drawable.r9);
        }else if (val <= 0) {
            imageView2.setImageResource(R.drawable.r9);
        }
        return(i);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];


        float acceleration = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;



        if (acceleration > SHAKE_THRESHOLD) {
            imageView2.startAnimation(egg_ani);
          a = eggNumber(count-1);
          count = a;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
