package com.community.jboss.leadmanagement;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {
    private static final int ID = 47981;
    @Override
    public void onReceive(Context context, Intent intent) {

        try{
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String state = extras.getString(TelephonyManager.EXTRA_STATE);
                String number = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                if(state!=null){
                    if(number!=null && state.equals(TelephonyManager.EXTRA_STATE_RINGING) ){
                        showNotification(context, number);
                    }else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                        hideNotification(context);
                    }else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                        showNotification(context,number);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void hideNotification(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancel(ID);
        }
    }


    public void showNotification(Context context, String number){

        Intent notificationIntent = new Intent(context, AddContactActivity.class);
        notificationIntent.putExtra(AddContactActivity.INTENT_EXTRA_NUMBER,number);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_call_black_24dp)
                .setContentTitle("Calling")
                .setTicker("Lead Management")
                .setContentIntent(contentIntent)
                .setContentText("Number: "+number);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancel(ID);
            manager.notify(ID,notification.build());
        }
    }
}
