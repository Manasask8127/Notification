package com.example.notificationexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    public static final String CHANNEL_ID="Channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Notification")
                        .setContentText("You have one notification .......")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                Intent notificationIntent = new Intent(MainActivity.this, NotifivationView.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //notification message will get at NotificationView
                //notificationIntent.putExtra("message", "This is a notification message");

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.createNotificationChannel(getChannel());
                manager.notify(0, builder.build());

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static NotificationChannel getChannel(){
        return new NotificationChannel(CHANNEL_ID,"Channel 1",NotificationManager.IMPORTANCE_HIGH);
    }
}