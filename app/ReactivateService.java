import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class ReactivateService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent serviceIntent = new Intent(context, SensorService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }
    }
}
