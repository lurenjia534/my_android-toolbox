package com.lurenjia534.myapplication.notificationnotification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lurenjia534.myapplication.MainActivity
import com.lurenjia534.myapplication.R

fun sendNotification(
    context:Context,title:String,content:String
) {
    val channelID = "persistent_notification_channel"
    val channelName = "Persistent Notification Channel"
    val notificationID = 1

    val channel = NotificationChannel(
        channelID,
        channelName,
        NotificationManager.IMPORTANCE_DEFAULT
    ).apply {
        description = "This is the persistent notification channel"
    }

    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent,
        PendingIntent.FLAG_IMMUTABLE)

    val builder = NotificationCompat.Builder(context, channelID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setOngoing(true) // This makes the notification persistent

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(notificationID, builder.build())
    }

}