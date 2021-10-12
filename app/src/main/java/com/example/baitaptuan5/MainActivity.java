package com.example.baitaptuan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    RecyclerView recyclerV;
    CustomAdapter adt;
    ArrayList<Music> mMusics;
    ImageButton btnPlay;
    MediaPlayer mp;
    int count = 1;
    int totalTime;
    TextView txtTotalTime;
    TextView txtProgress;
    ProgressBar progressB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTotalTime = findViewById(R.id.txtTotalTime);
        txtProgress = findViewById(R.id.txtProgress);
        progressB = findViewById(R.id.progressB);
        totalTime = MediaPlayer.create(getBaseContext(), R.raw.overhit).getDuration();
        Date date = new Date(totalTime);
        String minutes = date.getMinutes() > 9 ? date.getMinutes() + "" : "0" + date.getMinutes();
        String second = date.getSeconds() > 9 ? date.getSeconds() + "" : "0" + date.getSeconds();
        txtTotalTime.setText("" + milliSecondsToTimer((long) totalTime));


        btnPlay = findViewById(R.id.btnPlay);
        mMusics = new ArrayList<>();
        recyclerV = findViewById(R.id.recyclerV);
        mMusics.add(new Music("IMAGINE DRAGON", "Believer", R.drawable.belivermusic));
        mMusics.add(new Music("RYAN GRIGDRY", "Shortwave", R.drawable.maskgroup));
        mMusics.add(new Music("ROGER TERRY", "Dream On", R.drawable.dreamon));
        mMusics.add(new Music("IMAGINE DRAGON", "Origins", R.drawable.origin));
        adt = new CustomAdapter(mMusics, this);

        recyclerV.setAdapter(adt);
        recyclerV.setHasFixedSize(true);
        recyclerV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count++ % 2 == 0) mp.stop();
                else {
                    mp = MediaPlayer.create(getBaseContext(), R.raw.overhit);
                    mp.setLooping(false);
                    mp.start();
                    if(mp.isPlaying()) {
                        txtProgress.post(mUpdateTime);
                        progressB.post(mUpdateProgress);
                    }
                }
            }
        });
    }

    private Runnable mUpdateProgress = new Runnable() {
        public void run() {
            int currentDuration;
            if (mp.isPlaying()) {
                currentDuration = mp.getCurrentPosition();
                double time = currentDuration * 1.0 / totalTime * 100;
                progressB.setProgress((int) time);
                progressB.postDelayed(this, 1000);
            }else {
                progressB.removeCallbacks(this);
            }
        }
    };

    private Runnable mUpdateTime = new Runnable() {
        public void run() {
            int currentDuration;
            if (mp.isPlaying()) {
                currentDuration = mp.getCurrentPosition();
                updatePlayer(currentDuration);
                txtProgress.postDelayed(this, 1000);
            }else {
                txtProgress.removeCallbacks(this);
            }
        }
    };

    private void updatePlayer(int currentDuration){
        txtProgress.setText("" + milliSecondsToTimer((long) currentDuration));
    }

    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) finalTimerString += (hours > 9 ? hours : "0" + hours) + ":";
        finalTimerString += (minutes > 9 ? minutes : "0" + minutes) + ":";
        finalTimerString += (seconds > 9 ? seconds : "0" + seconds);

        // return timer string
        return finalTimerString;
    }

    @Override
    public void itemClick(Music music) {

    }
}