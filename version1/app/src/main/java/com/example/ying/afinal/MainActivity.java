package com.example.ying.afinal;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private ImageView mImgComputer;
    private ImageButton mImgBtnPlayer;
    private Button mBtnBig;
    private Button mBtnSmall;
    private boolean mJudge;
    private int mWin;

    // 新增統計遊戲局數和輸贏的變數
    private int miCountSet = 0;
    private static int miCountPlayerWin = 0;
    private static int miCountComWin = 0;
    private static int miCountDraw = 0;

    private Button mBtnShowResult;

    private class StaticHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public StaticHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) return;
            int player_Rand = (int)(Math.random()*13+1);
            int comuter_Rand = (int)(Math.random()*13 + 1);

            if(mJudge == true){
                if(player_Rand > comuter_Rand) {
                    mWin = 1;
                    Toast.makeText(MainActivity.this, R.string.player_win, Toast.LENGTH_LONG).show();
                }
                else if(player_Rand == comuter_Rand){
                    mWin = 0;
                    Toast.makeText(MainActivity.this, R.string.player_draw, Toast.LENGTH_LONG).show();
                }
                else {
                    mWin = -1;
                    Toast.makeText(MainActivity.this, R.string.player_lose, Toast.LENGTH_LONG).show();
                }
            }
            else {
                if(player_Rand > comuter_Rand) {
                    mWin = -1;
                    Toast.makeText(MainActivity.this, R.string.player_lose, Toast.LENGTH_LONG).show();
                }
                else if(player_Rand == comuter_Rand) {
                    mWin = 0;
                    Toast.makeText(MainActivity.this, R.string.player_draw, Toast.LENGTH_LONG).show();
                }
                else{
                    mWin = 1;
                    Toast.makeText(MainActivity.this, R.string.player_win, Toast.LENGTH_LONG).show();
                }
            }

            //String s = activity.getString(R.string.result);
            //activity.mTxtResult.setText(s);
            switch (player_Rand) {
                case 1:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.ah);
                    break;
                case 2:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h2);
                    break;
                case 3:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h3);
                    break;
                case 4:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h4);
                    break;
                case 5:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h5);
                    break;
                case 6:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h6);
                    break;
                case 7:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h7);
                    break;
                case 8:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h8);
                    break;
                case 9:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h9);
                    break;
                case 10:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.h10);
                    break;
                case 11:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.jh);
                    break;
                case 12:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.qh);
                    break;
                case 13:
                    activity.mImgBtnPlayer.setImageResource(R.drawable.kh);
                    break;
            }

            switch (comuter_Rand) {
                case 1:
                    activity.mImgComputer.setImageResource(R.drawable.as);
                    break;
                case 2:
                    activity.mImgComputer.setImageResource(R.drawable.s2);
                    break;
                case 3:
                    activity.mImgComputer.setImageResource(R.drawable.s3);
                    break;
                case 4:
                    activity.mImgComputer.setImageResource(R.drawable.s4);
                    break;
                case 5:
                    activity.mImgComputer.setImageResource(R.drawable.s5);
                    break;
                case 6:
                    activity.mImgComputer.setImageResource(R.drawable.s6);
                    break;
                case 7:
                    activity.mImgComputer.setImageResource(R.drawable.s7);
                    break;
                case 8:
                    activity.mImgComputer.setImageResource(R.drawable.s8);
                    break;
                case 9:
                    activity.mImgComputer.setImageResource(R.drawable.s9);
                    break;
                case 10:
                    activity.mImgComputer.setImageResource(R.drawable.s10);
                    break;
                case 11:
                    activity.mImgComputer.setImageResource(R.drawable.js);
                    break;
                case 12:
                    activity.mImgComputer.setImageResource(R.drawable.qs);
                    break;
                case 13:
                    activity.mImgComputer.setImageResource(R.drawable.ks);
                    break;
            }

        }
    }

    public final StaticHandler mHandler = new StaticHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTxtResult = (TextView)findViewById(R.id.txtResult);
        mImgBtnPlayer = (ImageButton)findViewById(R.id.btnPlayerPoker);
        mImgComputer = (ImageView) findViewById(R.id.imgComputerPoker);
        mBtnBig = (Button) findViewById(R.id.btnBig);
        mBtnSmall = (Button) findViewById(R.id.btnSmall); 
        //mBtnShowResult = (Button)findViewById(R.id.btnShowResult);
        //mBtnShowResult.setOnClickListener(btnShowResultOnClick);
        //mImgBtnPlayer.setOnClickListener(mImgBtnPlayerOnClick);
        mBtnBig.setOnClickListener(mBtnBigOnClick);
        mBtnSmall.setOnClickListener(mBtnSmallOnClick);
        mImgBtnPlayer.setOnClickListener(mImgBtnPlayerOnClick);
        
    }

    private View.OnClickListener mBtnBigOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJudge = true;
            Toast.makeText(MainActivity.this, "比大", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener mBtnSmallOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJudge = false;
            Toast.makeText(MainActivity.this, "比小", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener mImgBtnPlayerOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // Decide computer play.
            //String s = getString(R.string.result);
            //miCountSet++;
//            mEdtCountSet.setText(String.valueOf(miCountSet));

            // 從程式資源中取得動畫檔，設定給ImageView物件，然後開始播放。
            Resources res = getResources();
            final AnimationDrawable animDraw1 =
                    (AnimationDrawable) res.getDrawable(R.drawable.player_poker);
            final AnimationDrawable animDraw2 =
                    (AnimationDrawable) res.getDrawable(R.drawable.computer_poker);
            mImgBtnPlayer.setImageDrawable(animDraw1);
            mImgComputer.setImageDrawable(animDraw2);
            animDraw1.start();
            animDraw2.start();

            // 啟動background thread進行計時。
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw1.stop();
                    animDraw2.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();


        }
    };

    /*private View.OnClickListener btnShowResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent it = new Intent();
            it.setClass(MainActivity.this, GameResultActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt("KEY_COUNT_SET", miCountSet);
            bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
            bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
            bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
            it.putExtras(bundle);

            startActivity(it);
        }*/
    };