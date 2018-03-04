package com.ryzen.ryan.carbonzero;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.cursorwheel.CursorWheelLayout;

public class EarthActivity extends AppCompatActivity implements CursorWheelLayout.OnMenuSelectedListener {


    CursorWheelLayout earthWheel;
    List<EarthWheelData> list;

    LinearLayout wheelCenter;

    TextView goalNum;
    String selectedItem;
    Fragment selectedFragment = null;

    Bundle totalBundle;
    int startTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        goalNum = findViewById(R.id.goalNum);
        String initialGoal = getIntent().getStringExtra("startGoal");
        goalNum.setText(initialGoal);

        // Wheel
        earthWheel = findViewById(R.id.wheel_image);
        loadData();
        earthWheel.setOnMenuSelectedListener(this);
        wheelCenter = findViewById(R.id.goalLayout);


        startTotal = getIntent().getIntExtra("startTotal", 1000);
        totalBundle = new Bundle();
        totalBundle.putInt("totalCarbon", startTotal);

        Toast.makeText(getBaseContext(), "Selected: "+ "travel", Toast.LENGTH_SHORT).show();
        CarbonFragment carbonFragment = new CarbonFragment();
        carbonFragment.setArguments(totalBundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        selectedFragment = carbonFragment;
        transaction.replace(R.id.frameLayout, selectedFragment);
        transaction.commit();


        wheelCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(selectedItem){
                    case "pedometer":
                        PedometerFragment pedometerFragment = new PedometerFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        selectedFragment = pedometerFragment;
                        transaction.replace(R.id.frameLayout, selectedFragment);
                        transaction.commit();
                        Toast.makeText(getBaseContext(), "Selected: "+ "pedometer", Toast.LENGTH_SHORT).show();
                        break;
                    case "food":
                        Toast.makeText(getBaseContext(), "Selected: "+ "food", Toast.LENGTH_SHORT).show();
                        SliderFragment sliderFragment = new SliderFragment();
                        sliderFragment.setArguments(totalBundle);
                        transaction = getSupportFragmentManager().beginTransaction();
                        selectedFragment = sliderFragment;

                        transaction.replace(R.id.frameLayout,selectedFragment);
                        transaction.commit();
                        break;
                    case "carbon":
                        Toast.makeText(getBaseContext(), "Selected: "+ "travel", Toast.LENGTH_SHORT).show();
                        CarbonFragment carbonFragment = new CarbonFragment();
                        carbonFragment.setArguments(totalBundle);
                        transaction = getSupportFragmentManager().beginTransaction();
                        selectedFragment = carbonFragment;
                        transaction.replace(R.id.frameLayout, selectedFragment);
                        transaction.commit();
                        break;
                    case "washing_machine":
                        Toast.makeText(getBaseContext(), "Selected: "+ "washing_mac", Toast.LENGTH_SHORT).show();

                        break;
                    case "graph":
                        Toast.makeText(getBaseContext(), "Selected: "+ "graphs", Toast.LENGTH_SHORT).show();
                        GraphFragment graphFragment = new GraphFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        selectedFragment = graphFragment;
                        transaction.replace(R.id.frameLayout, selectedFragment);
                        transaction.commit();
                        break;
                    case "settings":
                        Toast.makeText(getBaseContext(), "Selected: "+ "graphs", Toast.LENGTH_SHORT).show();
                        SettingsFragment settingsFragment = new SettingsFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        selectedFragment = settingsFragment;
                        transaction.replace(R.id.frameLayout, selectedFragment);
                        transaction.commit();
                        break;
                    default:
                }
            }
        });

    }

    // Wheel data loaded
    private void loadData(){
        list = new ArrayList<>();
        list.add(new EarthWheelData(R.drawable.footprints, "carbon"));
        list.add(new EarthWheelData(R.drawable.carrot, "food"));
        list.add(new EarthWheelData(R.drawable.walking, "pedometer"));
        list.add(new EarthWheelData(R.drawable.washingmachine, "washing_machine"));
//        list.add(new EarthWheelData(R.drawable.waterbottle, "water"));
        list.add(new EarthWheelData(R.drawable.linechart, "graph"));
        list.add(new EarthWheelData(R.drawable.settings, "settings"));

        EarthWheelAdapter earthWheelAdapter = new EarthWheelAdapter(getBaseContext(), list);
        earthWheel.setAdapter(earthWheelAdapter);
    }

    //Wheel
    @Override
    public void onItemSelected(CursorWheelLayout parent, View view, int pos) {
        if (parent.getId() == R.id.wheel_image){
//            Toast.makeText(getBaseContext(), "Selected: "+ list.get(pos).imageDescription, Toast.LENGTH_SHORT).show();
            selectedItem = list.get(pos).imageDescription;
        }
    }

}
