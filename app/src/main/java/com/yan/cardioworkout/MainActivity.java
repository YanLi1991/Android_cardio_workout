package com.yan.cardioworkout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yan.cardioworkout.Other.WorkoutInfo;

public class MainActivity extends AppCompatActivity {

    private Spinner restSpinner;
    private Spinner roundsSpinner;
    private WorkoutInfo workoutInfo;
    private TextView startWorkOutText;
    private RelativeLayout startWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restSpinner = findViewById(R.id.restTimeSpinner);
        roundsSpinner = findViewById(R.id.roundsSpinner);
        startWorkOutText = findViewById(R.id.startworkout_text);
        startWorkout = findViewById(R.id.startworkout_layout);
        workoutInfo = WorkoutInfo.getInstance();

        setWorkoutText(workoutInfo);

        startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        WorkoutActivity.class); // jump to the second splash activity
                startActivity(intent);
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner
        String[] restTimes = getResources().getStringArray(R.array.rest_array);
        ArrayAdapter<String> restSpinAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_text, restTimes);

        // Specify the layout to use when the list of choices appears
        restSpinAdapter.setDropDownViewResource(R.layout.spinner_text);

        // Apply the adapter to the spinner
        restSpinner.setAdapter(restSpinAdapter);

        String[] rounds = getResources().getStringArray(R.array.rounds_array);
        ArrayAdapter<String> roundSpinAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_text, rounds);

        // Specify the layout to use when the list of choices appears
        roundSpinAdapter.setDropDownViewResource(R.layout.spinner_text);

        // Apply the adapter to the spinner
        roundsSpinner.setAdapter(roundSpinAdapter);
//
        restSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String choosen = (String) parent.getItemAtPosition(position);
                int restTime = Integer.parseInt(choosen.substring(0, choosen.indexOf(" ")));
                workoutInfo.setRestTime(restTime);
                setWorkoutText(workoutInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
//
        roundsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                int roundsNum = Integer.parseInt((String) parent.getItemAtPosition(position));
                workoutInfo.setRoundNum(roundsNum);
                setWorkoutText(workoutInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                showCongraFragment();
                return true;
            case R.id.exit:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setWorkoutText(WorkoutInfo workoutInfo){
        startWorkOutText.setText(workoutInfo.getNewTextContent());
    }

    public void showCongraFragment(){
        FragmentTransaction mFragTransaction = getFragmentManager().beginTransaction();
        Fragment fragment =  getFragmentManager().findFragmentByTag("aboutFragment");

        if(fragment!=null){
            mFragTransaction.remove(fragment);
        }

        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.show(mFragTransaction, "aboutFragment");
    }
}
