package com.example.thedicegame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener, TextToSpeech.OnInitListener{
    private SensorManager mSensorManger;
    private Sensor mAccelerometer;
    private static int SHAKE_THRESHOLD = 3;
    private TextToSpeech mTts;
    private TextView mNumber;
    private ImageView imageView;
    static RadioButton radioButton, radioButton2, radioButton3, radioButton4;
    private RadioGroup radioGroup;
    static int var;
    Animation dice_roll;
    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumber = (TextView) findViewById(R.id.numbur);
        imageView = (ImageView) findViewById(R.id.imageNumber);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mSensorManger = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mTts = new TextToSpeech(this, this);
        radioButton = (RadioButton) findViewById(R.id.radioButton); // 사면체
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2); //육면체
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3); //팔면체
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4); //Egg_Game
        dice_roll = AnimationUtils.loadAnimation(this,R.anim.dice_roll_animation);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                       var = 4;

                        break;
                    case R.id.radioButton2:
                        var = 6;

                        break;
                    case R.id.radioButton3:
                        var = 8;

                        break;
                    case R.id.radioButton4:

                        Intent intent = new Intent(getApplicationContext(),game.class);
                        startActivity(intent);

                       break;
                }
            }
        });

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


    @Override
    public void onInit(int status) { // TTS 말할떄쓰는거 초기화? 정확히모름
        if (status != TextToSpeech.ERROR) {
            mTts.setLanguage(getResources().getConfiguration().locale);
        }

    }

    @Override
    protected void onDestroy() {
        if (mTts != null) {         //말을 끝까장하게만드는 방법
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();     // 말계쏙시킬려고 1번으로안끝내
    }
    private void generateRandomNumberFour() {  //사용자선언
        Random randomGenerator = new Random();   //랜덤함수를 사용하기위해서 선언
        int randomNum = randomGenerator.nextInt(4) + 1;  // 1~ 6까장나옴
        mTts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null);  //말시키는거 randomNum 숫자나온걸 읽으라고 QUEUE_FLUSH는 계속 값을 초기화시켜주는거

        if (randomNum == 1) {                          //숫자 1이뜨면
            imageView.setImageResource(R.drawable.four_1);
        } else if (randomNum == 2) {
            imageView.setImageResource(R.drawable.four_2);
        } else if (randomNum == 3) {
            imageView.setImageResource(R.drawable.four_3);
        } else if (randomNum == 4) {
            imageView.setImageResource(R.drawable.four_4);
        }
    }

    private void generateRandomNumberSix() {  //사용자선언
        Random randomGenerator = new Random();   //랜덤함수를 사용하기위해서 선언
        int randomNum = randomGenerator.nextInt(6) + 1;  // 1~ 6까장나옴
        mTts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null);  //말시키는거 randomNum 숫자나온걸 읽으라고 QUEUE_FLUSH는 계속 값을 초기화시켜주는거

        if (randomNum == 1) {                          //숫자 1이뜨면
            imageView.setImageResource(R.drawable.six_1);
        } else if (randomNum == 2) {
            imageView.setImageResource(R.drawable.six_2);
        } else if (randomNum == 3) {
            imageView.setImageResource(R.drawable.six_3);
        } else if (randomNum == 4) {
            imageView.setImageResource(R.drawable.six_4);
        } else if (randomNum == 5) {
            imageView.setImageResource(R.drawable.six_5);
        } else if (randomNum == 6) {
            imageView.setImageResource(R.drawable.six_6);
        }
    }

    private void generateRandomNumberEight() {  //사용자선언
        Random randomGenerator = new Random();   //랜덤함수를 사용하기위해서 선언
        int randomNum = randomGenerator.nextInt(8) + 1;  // 1~ 6까장나옴
        mTts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null);  //말시키는거 randomNum 숫자나온걸 읽으라고 QUEUE_FLUSH는 계속 값을 초기화시켜주는거

        if (randomNum == 1) {                          //숫자 1이뜨면
            imageView.setImageResource(R.drawable.eig_1);
        } else if (randomNum == 2) {
            imageView.setImageResource(R.drawable.eig_2);
        } else if (randomNum == 3) {
            imageView.setImageResource(R.drawable.eig_3);
        } else if (randomNum == 4) {
            imageView.setImageResource(R.drawable.eig_4);
        } else if (randomNum == 5) {
            imageView.setImageResource(R.drawable.eig_5);
        } else if (randomNum == 6) {
            imageView.setImageResource(R.drawable.eig_6);
        }else if (randomNum == 7) {
            imageView.setImageResource(R.drawable.eig_7);
        }else if (randomNum == 8) {
            imageView.setImageResource(R.drawable.eig_8);
        }
    }

    private void generateRandomNumberTen() {  //사용자선언
        Random randomGenerator = new Random();   //랜덤함수를 사용하기위해서 선언
        int randomNum = randomGenerator.nextInt(6) + 1;  // 1~ 6까장나옴
        mTts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null);  //말시키는거 randomNum 숫자나온걸 읽으라고 QUEUE_FLUSH는 계속 값을 초기화시켜주는거

        if (randomNum == 1) {                          //숫자 1이뜨면
            imageView.setImageResource(R.drawable.six_1);
        } else if (randomNum == 2) {
            imageView.setImageResource(R.drawable.six_2);
        } else if (randomNum == 3) {
            imageView.setImageResource(R.drawable.six_3);
        } else if (randomNum == 4) {
            imageView.setImageResource(R.drawable.six_4);
        } else if (randomNum == 5) {
            imageView.setImageResource(R.drawable.six_5);
        } else if (randomNum == 6) {
            imageView.setImageResource(R.drawable.six_6);
        }
    }

    private void playDiceSound() {
        MediaPlayer diceSound;
        diceSound = MediaPlayer.create(getApplicationContext(), R.raw.dice_rolling_sound);
        diceSound.start();
    }

    @Override

    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();
        long gabOfTime = (currentTime - lastTime);

        if (gabOfTime > 700) {
            lastTime = currentTime;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float acceleration = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
            if (var == 4) {
                if (acceleration > SHAKE_THRESHOLD) {
                    playDiceSound();
                    imageView.setAnimation(dice_roll);
                    generateRandomNumberFour();

                }
            } else if (var == 6) {
                if (acceleration > SHAKE_THRESHOLD) {  //  SHAKE는 뭐그냥 숫자 3이상의 움직임일시 작동하게한건데 3도별로 안크다
                    playDiceSound();
                    imageView.setAnimation(dice_roll);
                    generateRandomNumberSix();

                }
            } else if (var == 8) {
                if (acceleration > SHAKE_THRESHOLD) {  //  SHAKE는 뭐그냥 숫자 3이상의 움직임일시 작동하게한건데 3도별로 안크다
                    playDiceSound();
                    imageView.setAnimation(dice_roll);
                    generateRandomNumberEight();

                }
            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { // 정확도 따지는 메소드


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { //모름

    }



}