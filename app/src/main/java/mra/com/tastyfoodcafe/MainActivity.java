package mra.com.tastyfoodcafe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int splash_Screen = 3200;
        new Handler().postDelayed(() -> {
            Intent h=new Intent(MainActivity.this,
                    LoginPage.class);
            startActivity(h);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();

        }, splash_Screen);
    }
}