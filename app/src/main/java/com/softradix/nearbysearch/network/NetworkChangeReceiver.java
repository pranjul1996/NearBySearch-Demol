package com.softradix.nearbysearch.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.softradix.nearbysearch.utils.Utilities;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        String status = NetworkUtil.getConnectivityStatusString(context);

        if (status.equals("3")){
            Utilities.INSTANCE.showInternetDialog(context);
        }else{
            Utilities.INSTANCE.hideInternetDialog();
            Utilities.INSTANCE.getLocationService(context);
        }
    }
}
