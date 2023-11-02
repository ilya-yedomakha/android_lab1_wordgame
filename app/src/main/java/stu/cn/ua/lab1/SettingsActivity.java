package stu.cn.ua.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final String SET_DIFFICULTY = "SET_DIFFICULTY";

    public static final String SET_TIMER_MINUTES = "SET_TIMER_MINUTES";

    public static final String DIFFICULTY = "DIFFICULTY";
    public static final String TIMER_MINUTES = "TIMER_MINUTES";

    private static int difficulty = 0;
    private static int timer_minutes = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        RadioGroup difficultyGroup = findViewById(R.id.difficultyGroup);
        RadioGroup timerGroup = findViewById(R.id.timer_difficultyGroup);

        if (savedInstanceState != null){
            difficulty = savedInstanceState.getInt(SET_DIFFICULTY,0);
            timer_minutes = savedInstanceState.getInt(SET_TIMER_MINUTES, 2);
            updateView(difficultyGroup, timerGroup, difficulty, timer_minutes);
        }

        if (intent != null) {
            difficulty = getIntent().getIntExtra(SET_DIFFICULTY, 0);
            timer_minutes = getIntent().getIntExtra(SET_TIMER_MINUTES, 2);
            updateView(difficultyGroup, timerGroup, difficulty, timer_minutes);
        }

        difficultyGroup.setOnCheckedChangeListener((difgroup, checkedId) -> {
            if (checkedId == R.id.normal_difficulty) {
                difficulty = 0;
            } else if (checkedId == R.id.hard_difficulty) {
                difficulty = 1;
            }
        });

        timerGroup.setOnCheckedChangeListener((timegroup, checkedId) -> {
            if (checkedId == R.id.easy_timer){
                timer_minutes = 5;
            } else if (checkedId == R.id.normal_timer) {
                timer_minutes = 2;
            } else if (checkedId == R.id.hard_timer){
                timer_minutes = 1;
            }
        });


        findViewById(R.id.saveButton).setOnClickListener(v -> {
                intent.putExtra(DIFFICULTY, difficulty);
                intent.putExtra(TIMER_MINUTES, timer_minutes);
                setResult(RESULT_OK, intent);

            finish();
        });
    }

    private void updateView(RadioGroup difficultyGroup, RadioGroup timerGroup, int difficulty, int timer_minutes){
        if (difficulty == 0) {
            difficultyGroup.check(R.id.normal_difficulty);
        } else {
            difficultyGroup.check(R.id.hard_difficulty);
        }


        if (timer_minutes == 1) {
            timerGroup.check(R.id.hard_timer);
        } else if (timer_minutes == 2) {
            timerGroup.check(R.id.normal_timer);
        } else {
            timerGroup.check(R.id.easy_timer);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SET_DIFFICULTY,difficulty);
        outState.putInt(TIMER_MINUTES,timer_minutes);
    }
}
