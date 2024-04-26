package com.example.bismillah;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TimerFragment extends Fragment {

    private EditText editTextHours;
    private EditText editTextMinutes;
    private EditText editTextSeconds;
    private Button startButton;
    private TextView timeRemaining;
    private CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextHours = view.findViewById(R.id.editTextHours);
        editTextMinutes = view.findViewById(R.id.editTextMinutes);
        editTextSeconds = view.findViewById(R.id.editTextSeconds);
        startButton = view.findViewById(R.id.startButton);
        timeRemaining = view.findViewById(R.id.timeRemaining);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer hours = parseInteger(editTextHours.getText().toString());
                Integer minutes = parseInteger(editTextMinutes.getText().toString());
                Integer seconds = parseInteger(editTextSeconds.getText().toString());

                if (hours == null) hours = 0;
                if (minutes == null) minutes = 0;
                if (seconds == null) seconds = 0;

                long totalSeconds = (hours * 3600) + (minutes * 60) + seconds;

                if (totalSeconds <= 0) {
                    Toast.makeText(requireContext(), "Masukkan waktu yang valid", Toast.LENGTH_SHORT).show();
                } else {
                    startTimer(totalSeconds * 1000L);
                }
            }
        });
    }

    private Integer parseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void startTimer(long timeInMillis) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long hours = (millisUntilFinished / 1000) / 3600;
                long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                timeRemaining.setText(String.format("Waktu tersisa: %02d:%02d:%02d", hours, minutes, seconds));
            }

            @Override
            public void onFinish() {
                timeRemaining.setText("Waktu habis!");
            }
        };

        countDownTimer.start();
    }
}