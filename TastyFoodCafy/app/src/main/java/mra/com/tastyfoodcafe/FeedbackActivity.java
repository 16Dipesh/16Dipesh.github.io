package mra.com.tastyfoodcafe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public static class Feedback extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_feedback);


            Button feed=(Button)findViewById(R.id.FeedSend);


            feed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Feedback.this,"Feed Sended Thankyou For Feed Back",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}