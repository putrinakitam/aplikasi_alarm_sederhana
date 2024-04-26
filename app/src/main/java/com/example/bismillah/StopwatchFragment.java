package com.example.bismillah;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.MessageFormat;
import java.util.Locale;

public class StopwatchFragment extends Fragment {
    TextView textView;
    MaterialButton reset, start, stop;
    private int detik, menit, miliDetik;
    private long milidetik, waktuMulai, timeBuff, updateTime = 0L;

    Handler handler;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            milidetik = SystemClock.uptimeMillis() - waktuMulai;
            updateTime = timeBuff + milidetik;
            detik = (int) (updateTime / 1000);
            menit = detik / 60;
            detik = detik % 60;
            miliDetik = (int) (updateTime % 1000);

            textView.setText(MessageFormat.format("{0}:{1}:{2}", menit, String.format(Locale.getDefault(), "%02d", detik), String.format(Locale.getDefault(), "%02d", miliDetik)));
            handler.postDelayed(this, 0);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stopwatch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.tv_stopwatch);
        start = view.findViewById(R.id.btn_start);
        stop = view.findViewById(R.id.btn_stop);
        reset = view.findViewById(R.id.btn_reset);

        handler = new Handler(Looper.getMainLooper());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waktuMulai = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);
                stop.setEnabled(true);
                start.setEnabled(false);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBuff += milidetik;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                stop.setEnabled(false);
                start.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milidetik = 0L;
                waktuMulai = 0L;
                timeBuff = 0L;
                updateTime = 0L;
                detik = 0;
                menit = 0;
                miliDetik = 0;
                textView.setText("00:00:00");
            }
        });

        textView.setText("00:00:00");

    }
}


