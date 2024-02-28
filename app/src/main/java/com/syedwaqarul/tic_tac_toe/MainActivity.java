package com.syedwaqarul.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int button_click_sound,win_sound,draw_sound;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
//    private MediaPlayer mediaPlayer;
    String name;
    boolean click=true,click1=true;
    ArrayList<Button> buttonList;
//    Animation translate;
    ArrayList<Button> buttonListcopy;
    LottieAnimationView winAnimation;

    String b1,b2,b3,b4,b5,b6,b7,b8,b9;
    TextView player_X_tv,player_O_tv,wintv,tv;
    ImageView roboiv,back_iv,setting_iv,line_iv;
    RelativeLayout player_X_layout,player_o_layout;
    int flag=0;
    int count=0;
    int x_win=0,o_win=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         buttonList=new ArrayList<>();//ArrayList
         buttonListcopy=new ArrayList<>();//ArrayList
         init();
//        MobileAds.initialize(this);

//         Internet Check:
//        if(isConnected())
//        {
//            Toast.makeText(this, "Internet Available", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this, "Internet NOt Available", Toast.LENGTH_SHORT).show();
//        }


         //Ads
//        AdView mAdView1 = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView1.loadAd(adRequest);

//         onPause();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.blue));//Change the status bar color
      name=getIntent().getStringExtra("Name");
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//
//
//            }
//        });

//        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
//        String retrievedValue = preferences.getString("key", "default_value");
//    if(retrievedValue.equals("sound_off"))
//                        {
//                            Toast.makeText(this, "sound off", Toast.LENGTH_SHORT).show();
//                        }
//                        else if(retrievedValue.equals("sound_on"))
//                        {
//                            Toast.makeText(this, "sound on", Toast.LENGTH_SHORT).show();
//
//                        }

      soundpol_run();

        if (name != null) {
            roboiv.setImageResource(R.drawable.baseline_computer_24);
            player_O_tv.setText("Comp O  :0");

        }
    }

    public void soundpol_run()
    {        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
        AudioAttributes audioAttributes=new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool=new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
    }
    else {
        soundPool=new SoundPool(3, AudioManager.STREAM_MUSIC,0);
    }

//        All sound:
        button_click_sound=soundPool.load(this,R.raw.click_sound,1);
        draw_sound=soundPool.load(this,R.raw.bigfood1,1);
        win_sound=soundPool.load(this,R.raw.win_sound,1);

    }

    private static int generateRandomIndex(int size) {
        // Create a Random object
        Random random = new Random();

        // Generate a random index within the size of the ArrayList
        return random.nextInt(size);
    }
    private void init()//Initialization
    {
// Initialize buttons and add them to the ArrayList
        btn1 = findViewById(R.id.btn1);
        buttonList.add(btn1);

        btn2 = findViewById(R.id.btn2);
        buttonList.add(btn2);

        btn3 = findViewById(R.id.btn3);
        buttonList.add(btn3);

        btn4 = findViewById(R.id.btn4);
        buttonList.add(btn4);

        btn5 = findViewById(R.id.btn5);
        buttonList.add(btn5);

        btn6 = findViewById(R.id.btn6);
        buttonList.add(btn6);

        btn7 = findViewById(R.id.btn7);
        buttonList.add(btn7);

        btn8 = findViewById(R.id.btn8);
        buttonList.add(btn8);

        btn9 = findViewById(R.id.btn9);
        buttonList.add(btn9);

//        Animqtion
        winAnimation = findViewById(R.id.win_animation);
      winAnimation.setSpeed(1.5f); // Adjust the speed value as needed
        tv=findViewById(R.id.animtv);


        buttonListcopy.addAll(buttonList);
        player_O_tv=findViewById(R.id.Player_O_tv);
        player_X_tv= findViewById(R.id.player_X_tv);
        wintv=findViewById(R.id.wintv);
        player_X_layout=findViewById(R.id.player_x_cons);
        player_o_layout=findViewById(R.id.player_o_cons);
        player_o_layout.setBackgroundResource(R.drawable.yellow_obesity_background);


        roboiv=findViewById(R.id.robotiv);

//        Imageview
        back_iv=findViewById(R.id.Back_Image_view);
        setting_iv=findViewById(R.id.setting_Image_view);
        line_iv=findViewById(R.id.line_imageView);
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,StartGame.class);
                intent.putExtra("Name", "computer");
                startActivity(intent);

            }
        });

        setting_iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog_layout);
                dialog.setCancelable(false);

                ImageView close=dialog.findViewById(R.id.close_dialog_iv);

                ImageView volume=dialog.findViewById(R.id.volume_iv);
                ImageView vibrate=dialog.findViewById(R.id.vibrate_iv);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                volume.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        if (volume.getDrawable() != null && volume.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_volume_up_24).getConstantState())) {
                        if(click)
                        {
                        volume.setImageResource(R.drawable.baseline_volume_off_24);
                            volume.setBackgroundResource(R.drawable.restart_background);
                            click=false;
                            soundPool=null;
                        }
                        else {
                            volume.setImageResource(R.drawable.baseline_volume_up_24);
                            volume.setBackgroundResource(R.drawable.refresh_background);
                          soundpol_run();
                          click=true;
                        }
                    }
                });
                vibrate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(click1) {
                            vibrate.setImageResource(R.drawable.baseline_vibration_24);
                            vibrate.setBackgroundResource(R.drawable.yellow_background);
                            click1=false;
                        }
                        else {
                            vibrate.setImageResource(R.drawable.baseline_phone_android_24);
                            vibrate.setBackgroundResource(R.drawable.phone_background);

                            click1=true;
                        }


                    }
                });

                dialog.show();

            }
        });
    }
    public void Check(View view)
    {
        Button btnCurrent=(Button) view;
//        If you use Check function on TextView or EditView then the runtime exception will occur because of typecasting.

        if(btnCurrent.getText().toString().equals(""))
        {
     if(flag==0)
    {
        playSound(1);
        player_X_layout.setBackgroundResource(R.drawable.red_obesity_background);
        player_o_layout.setBackgroundResource(R.drawable.yellow_background);
        btnCurrent.setTextColor(getResources().getColor(R.color.red));
//        btnCurrent.setShadowLayer(50,0,0, Color.RED);
        flag=1;
        btnCurrent.setText("X");
        buttonList.remove(btnCurrent);
        if (name != null) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (flag == 1) {
                      set();
                        check_all_conditions(view);

                    }
                }
            }, 700);
        }
    }
     else {
         if (name == null) {
             playSound(1);
             player_o_layout.setBackgroundResource(R.drawable.yellow_obesity_background);
             player_X_layout.setBackgroundResource(R.drawable.red_background);
//             btnCurrent.setShadowLayer(50,0,0, Color.YELLOW);//It will set the shadow

             btnCurrent.setTextColor(getResources().getColor(R.color.yellow));
             flag = 0;
             btnCurrent.setText("O");

         }
     }
            count++;
     check_all_conditions(view);

     }
     }


     public void set()
     {
         playSound(1);
         int a = generateRandomIndex(buttonList.size());
         Button btn = buttonList.get(a);

         player_o_layout.setBackgroundResource(R.drawable.yellow_obesity_background);
         player_X_layout.setBackgroundResource(R.drawable.red_background);
         btn.setTextColor(getResources().getColor(R.color.yellow));
         btn.setText("O");
         buttonList.remove(btn);
         flag=0;
         count++;
     }



     public void check_all_conditions(View view)
     {
         if(count>=5) {
             b1 = btn1.getText().toString();
             b2 = btn2.getText().toString();
             b3 = btn3.getText().toString();
             b4 = btn4.getText().toString();
             b5 = btn5.getText().toString();
             b6 = btn6.getText().toString();
             b7 = btn7.getText().toString();
             b8 = btn8.getText().toString();
             b9 = btn9.getText().toString();


             if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                 line_iv.setImageResource(R.drawable.horizontal_top);
                 line_iv.setVisibility(View.VISIBLE);

                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b1);

                     }
                 }, 550);

             } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                 line_iv.setImageResource(R.drawable.horizontalline);
                 line_iv.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b4);

                     }
                 }, 550);
             } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                 line_iv.setImageResource(R.drawable.horizontalline_bottom);
                 line_iv.setVisibility(View.VISIBLE);

                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b7);

                     }
                 }, 550);
             } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                 line_iv.setImageResource(R.drawable.verticalline_start);
                 line_iv.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b1);

                     }
                 }, 550);
             } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                 line_iv.setImageResource(R.drawable.verticalline);
                 line_iv.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b2);

                     }
                 }, 550);
             } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                 line_iv.setImageResource(R.drawable.verticalline_end);
                 line_iv.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b3);

                     }
                 }, 550);
             } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                 line_iv.setImageResource(R.drawable.diagonalright);
                 line_iv.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b1);

                     }
                 }, 550);
             } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                 line_iv.setImageResource(R.drawable.diagonalleft);
                 line_iv.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win(b3);

                     }
                 }, 550);
             } else if (count == 9) {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         blank(view);
                         win("Draw");

                     }
                 }, 550);
             }
         }
     }
    private void win(String b)
    {

       winAnimation.setVisibility(View.VISIBLE);
        wintv.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wintv.setVisibility(View.INVISIBLE);

            }
        }, 1500);
        if(b.equals("O"))
        {
            player_X_layout.setBackgroundResource(R.drawable.red_obesity_background);
            player_o_layout.setBackgroundResource(R.drawable.yellow_background);

            o_win++;
            if (name == null) {
                winAnimation.playAnimation();
                playSound(2);
                player_O_tv.setText("Player O  :" + o_win);
                wintv.setText("Player O win!!");
                flag=1;//providing next turn to whom  who win this match!!

            }
            else {
                winAnimation.setVisibility(View.INVISIBLE);
                playSound(3);
                player_O_tv.setText("Comp O  :" + o_win);
                wintv.setText("Computer O win!!");


                wintv.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        set();
                    }
                }, 1500);

                flag=0;
            }

            wintv.setBackgroundResource(R.drawable.yellow_background);
        }
        else if(b.equals("X")) {
            winAnimation.playAnimation();
            playSound(2);
            x_win++;
            player_X_tv.setText("Player X  :"+x_win);
            wintv.setText("Player X win!!");
            wintv.setBackgroundResource(R.drawable.red_background);
            player_o_layout.setBackgroundResource(R.drawable.yellow_obesity_background);
            player_X_layout.setBackgroundResource(R.drawable.red_background);
            flag=0;//providing next turn to whom  who win this match!!

        }
        else {
            winAnimation.setVisibility(View.INVISIBLE);
            playSound(3);
            wintv.setText("Match is Draw!!");
            wintv.setBackgroundResource(R.drawable.black_draw_game);
        }

    }

    public void blank(View view) {
        // Clear text on buttons
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        // Reset count
        count = 0;
        buttonList.clear();
        buttonList.addAll(buttonListcopy);
        line_iv.setVisibility(View.INVISIBLE);
    }


    public void restart(View view) {
        blank(view);
        if (name != null) {
            roboiv.setImageResource(R.drawable.baseline_computer_24);
            player_O_tv.setText("Comp O  :0");

        }
        else {
            player_O_tv.setText("Player O  :0");
        }
        player_X_tv.setText("Player X  :0");
        x_win=o_win=0;
    }




    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        soundPool.release();
        soundPool=null;
    }
    public void playSound(int choose)
    {

        switch (choose){
            case 1:
            {
                if(soundPool!=null) {
                    soundPool.autoPause();
                    soundPool.play(button_click_sound, 1, 1, 0, 0, 1);
                    break;
                }
            }
            case 2:
            {
                if(soundPool!=null) {
                    soundPool.autoPause();
                    soundPool.play(win_sound, 1, 1, 0, 0, 1);
                    break;
                }
            }
            case 3:
            {
                if(soundPool!=null) {
                    soundPool.autoPause();
                    soundPool.play(draw_sound, 1, 1, 0, 0, 1);
                    break;
                }
            }

        }


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        // Save changes using SharedPreferences
//        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        if(soundPool!=null) {
//            editor.putString("key", "sound_off");  // Replace with your data
//        }
//        else {
//            editor.putString("key", "sound_on");  // Replace with your data
//        }
//            editor.apply();
//    }

    boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(MainActivity.this,StartGame.class);
//        if(soundPool!=null) {
//            intent.putExtra("Run", "run");
//        }
        intent.putExtra("Name", "computer");
        startActivity(intent);
        super.onBackPressed();
    }
}





//12 2 to 1 52

