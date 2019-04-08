package com.yan.cardioworkout;


import android.app.DialogFragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yan.cardioworkout.Other.WorkoutInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestFragment extends DialogFragment {
    private TextView restCounter;
    private WorkoutInfo workoutInfo;
    private ImageView restImage;
    private CountDownTimer countDownTimer;

    public RestFragment(){

    }

    public void setCountDownTimer(CountDownTimer timer){
        countDownTimer = timer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rest, container, false);
        restCounter = v.findViewById(R.id.restCounter);
        restImage = v.findViewById(R.id.restImage);
        this.setCancelable(false);
        restImage.setAlpha(70);
        workoutInfo = WorkoutInfo.getInstance();
        new CountDownTimer(workoutInfo.getRestTime() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secLeft = (int)millisUntilFinished / 1000;
                if(secLeft >= 10){
                    restCounter.setText(secLeft + "");
                }else{
                    restCounter.setText("0" + secLeft);
                }
            }

            @Override
            public void onFinish() {
                dismiss();
                onDestroy();


                countDownTimer.start();


            }
        }.start();
        return v;
    }



}
