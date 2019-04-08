package com.yan.cardioworkout;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.yan.cardioworkout.Other.WorkoutInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class CongraFragment extends DialogFragment {

    private ImageView congraImage;
    private Button goBack;
    private WorkoutInfo workoutInfo;


    public CongraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.setCancelable(false);
        View v = inflater.inflate(R.layout.fragment_congra, container, false);
        congraImage = v.findViewById(R.id.congraImage);
        goBack = v.findViewById(R.id.goBackBtn);
        workoutInfo = WorkoutInfo.getInstance();
        congraImage.setAlpha(60);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutInfo.reset();
                getActivity().finish();
                Intent intent = new Intent(getActivity(),
                        MainActivity.class); // jump to the second splash activity
                startActivity(intent);
            }
        });
        return v;
    }

}
