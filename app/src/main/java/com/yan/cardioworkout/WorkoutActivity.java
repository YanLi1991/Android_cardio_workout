package com.yan.cardioworkout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.yan.cardioworkout.Other.WorkoutInfo;

public class WorkoutActivity extends AppCompatActivity {

    private TextView timeLeft;
    private TextView rounds;
    private WorkoutInfo workoutInfo;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        workoutInfo = WorkoutInfo.getInstance();
        timeLeft = findViewById(R.id.workout_timeleft);
        rounds = findViewById(R.id.workout_rounds);
        workoutInfo.reset();
        refreshText();

        countDownTimer = new CountDownTimer(workoutInfo.getROUNDTIME() * 1000, 1000) {

            private boolean isRefresh = true;

            @Override
            public void onTick(long millisUntilFinished) {
                if(isRefresh){
                    refreshText();
                    isRefresh = false;
                }
                int secLeft = (int)millisUntilFinished / 1000;
                if(secLeft >= 10){
                    timeLeft.setText(secLeft + "");
                }else{
                    timeLeft.setText("0" + secLeft);
                }
            }

            @Override
            public void onFinish() {
                isRefresh = true;
                workoutInfo.incRound();
                if(workoutInfo.isEndWorkout()){
                    showCongraFragment();
                }else{
                    showDialogFragment();
                }


            }
        };


        Countdown();


    }

    private void Countdown(){
        countDownTimer.start();

    }

    public void refreshText(){
        String roundsInfo = workoutInfo.getCurrentRound() + "/" + workoutInfo.getRoundNum();
        rounds.setText(roundsInfo);
    }

    public void showDialogFragment(){
        FragmentTransaction mFragTransaction = getFragmentManager().beginTransaction();
        Fragment fragment =  getFragmentManager().findFragmentByTag("restFragment");
        if(fragment!=null){
            mFragTransaction.remove(fragment);
        }
        fragment =  getFragmentManager().findFragmentByTag("congraFragment");

        if(fragment!=null){
            mFragTransaction.remove(fragment);
        }
        RestFragment dialogFragment = new RestFragment();
        dialogFragment.setCountDownTimer(countDownTimer);
        dialogFragment.show(mFragTransaction, "restFragment");
    }

    public void showCongraFragment(){
        FragmentTransaction mFragTransaction = getFragmentManager().beginTransaction();
        Fragment fragment =  getFragmentManager().findFragmentByTag("congraFragment");

        if(fragment!=null){
            mFragTransaction.remove(fragment);
        }
        fragment =  getFragmentManager().findFragmentByTag("restFragment");
        if(fragment!=null){
            mFragTransaction.remove(fragment);
        }
        CongraFragment congraFragment = new CongraFragment();
        congraFragment.show(mFragTransaction, "congraFragment");
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        countDownTimer = null;
        finish();
    }
}
