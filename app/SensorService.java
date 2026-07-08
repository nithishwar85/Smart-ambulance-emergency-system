@RequiresApi(Build.VERSION_CODES.O)
private void startMyOwnForeground() {

    final String CHANNEL_ID = "example.permanence";
    final String CHANNEL_NAME = "Background Service";

    NotificationChannel channel = new NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_MIN
    );

    NotificationManager manager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    if (manager != null) {
        manager.createNotificationChannel(channel);
    }

    Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setOngoing(true)
            .setContentTitle("You are protected")
            .setContentText("We are there for you")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build();

    startForeground(2, notification);
}
