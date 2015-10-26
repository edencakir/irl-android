package tr.edu.iyte.irl.irl.Network;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.WakefulBroadcastReceiver;

/*
 * Created by Jones on 24-Jan-15.
 */
public class MSGReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Intent update = new Intent("UPDATE");
        Intent check = new Intent("REFRESH");
        switch (extras.getString("cmd")) {
            case "refresh":
                LocalBroadcastManager.getInstance(context).sendBroadcast(check);
                break;
            default://todo duyuru
                LocalBroadcastManager.getInstance(context).sendBroadcast(update);
                break;
        }


        ComponentName componentName = new ComponentName(context.getPackageName(), MSGService.class.getName());
        startWakefulService(context, (intent.setComponent(componentName)));
        setResultCode(Activity.RESULT_OK);
    }
}

