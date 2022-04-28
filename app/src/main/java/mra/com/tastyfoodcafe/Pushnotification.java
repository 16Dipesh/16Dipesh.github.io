package mra.com.tastyfoodcafe;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

public class Pushnotification extends AppCompatActivity {

    Button openActivityBtn, openBigContent;
    EditText e1, e2;

    public String CHANNEL_ID="001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushnotification);

        openActivityBtn = (Button) findViewById(R.id.btnNotificationActivity);
        openBigContent = (Button) findViewById(R.id.bigcontent);



    }

    public void showNotification(View v)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"001",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("This Is DESC");

            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);


            Notification.Builder builder=new Notification.Builder(this,CHANNEL_ID);
            builder.setSmallIcon(R.drawable.logo)
                    .setContentText("Your Order Has Been Sucessfully Placed")
                    .setContentTitle("Order Conformation Message")
                    .setPriority(Notification.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(001,builder.build());
        }
        else
        {

            Notification.Builder builder=new Notification.Builder(this);

            builder.setSmallIcon(R.drawable.logo)
                    .setContentTitle("svxvsh")
                    .setContentText("xshxsh")
                    .setPriority(Notification.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);

            notificationManagerCompat.notify(001,builder.build());

        }
    }
}
