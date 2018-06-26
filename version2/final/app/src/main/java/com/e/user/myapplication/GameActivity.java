package com.e.user.myapplication;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;

import java.lang.ref.WeakReference;

public class GameActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private ImageView mImgComputer;
    private ImageButton mImgBtnPlayer;
    private Button mBtnBig;
    private Button mBtnSmall;
    private boolean mJudge = true;  //ture為比大，false為比小，預設為比大
    private int mWin;

    private int mCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mImgBtnPlayer = (ImageButton)findViewById(R.id.btnPlayerPoker);
        mImgComputer = (ImageView)findViewById(R.id.imgComputerPoker);
        mBtnBig = (Button)findViewById(R.id.btnBig);
        mBtnSmall = (Button)findViewById(R.id.btnSmall);

        mBtnBig.setOnClickListener(mBtnBigOnClick);
        mBtnSmall.setOnClickListener(mBtnSmallOnClick);
        mImgBtnPlayer.setOnClickListener(mImgBtnPlayerOnClick);
    }

    private void commercial (){
        new AlertDialog.Builder(GameActivity.this)
                .setTitle("廣告訊息")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("老師拜託讓我們過啦")
                .setPositiveButton("關掉廣告", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                    }
                })
                .show();
    }

    private class StaticHandler extends Handler {
        private final WeakReference<GameActivity> mActivity;

        public StaticHandler(GameActivity activity) {
            mActivity = new WeakReference<GameActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            GameActivity activity = mActivity.get();
            if (activity == null) return;
            int player_Rand = (int)(Math.random()*13+1);
            int comuter_Rand = (int)(Math.random()*13 + 1);

            if(mJudge == true){
                if(player_Rand > comuter_Rand) {
                    mWin = 1;
                    Toast.makeText(GameActivity.this, R.string.player_win, Toast.LENGTH_LONG).show();
                }
                else if(player_Rand == comuter_Rand){
                    mWin = 0;
                    Toast.makeText(GameActivity.this, R.string.player_draw, Toast.LENGTH_LONG).show();
                }
                else {
                    mWin = -1;
                    Toast.makeText(GameActivity.this, R.string.player_lose, Toast.LENGTH_LONG).show();
                }
            }
            else {
                if(player_Rand > comuter_Rand) {
                    mWin = -1;
                    Toast.makeText(GameActivity.this, R.string.player_lose, Toast.LENGTH_LONG).show();
                }
                else if(player_Rand == comuter_Rand) {
                    mWin = 0;
                    Toast.makeText(GameActivity.this, R.string.player_draw, Toast.LENGTH_LONG).show();
                }
                else{
                    mWin = 1;
                    Toast.makeText(GameActivity.this, R.string.player_win, Toast.LENGTH_LONG).show();
                }
            }

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
            if(mCount%10 == 1){
                commercial();
            }
        }
    }


    public final GameActivity.StaticHandler mHandler = new GameActivity.StaticHandler(this);

    private View.OnClickListener mBtnBigOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJudge = true;
            Toast.makeText(GameActivity.this, "比大", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener mBtnSmallOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJudge = false;
            Toast.makeText(GameActivity.this, "比小", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener mImgBtnPlayerOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mCount ++;

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
                        Thread.sleep(2000);
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

    private void showResult() {
        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();
    }
}
