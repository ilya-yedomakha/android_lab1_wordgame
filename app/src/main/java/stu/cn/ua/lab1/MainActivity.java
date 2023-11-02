package stu.cn.ua.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int RQ_CODE = 1;
    public static final String DIFFICULTY_SAVED = "DIFFICULTY_SAVED";
    public static final String TIMER_SAVED = "TIMER_SAVED";

    private int difficulty = 0;
    private int timer_minutes = 2;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView = findViewById(R.id.textView);

        if (savedInstanceState != null){
            difficulty = savedInstanceState.getInt(DIFFICULTY_SAVED, 0);
            timer_minutes = savedInstanceState.getInt(TIMER_SAVED, 2);
        }

        findViewById(R.id.PlayButton).setOnClickListener(v -> {

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(GameActivity.DIFFICULTY,difficulty);
            intent.putExtra(GameActivity.TIMER_MINUTES,timer_minutes);
            startActivity(intent);
        });

        findViewById(R.id.settingsButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra(SettingsActivity.SET_DIFFICULTY,difficulty);
            intent.putExtra(SettingsActivity.SET_TIMER_MINUTES,timer_minutes);
            startActivityForResult(intent, RQ_CODE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQ_CODE && resultCode == RESULT_OK) {
            assert data != null;
            difficulty = data.getIntExtra(SettingsActivity.DIFFICULTY,0);
            timer_minutes = data.getIntExtra(SettingsActivity.TIMER_MINUTES,2);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DIFFICULTY_SAVED,difficulty);
        outState.putInt(TIMER_SAVED,timer_minutes);
    }
}