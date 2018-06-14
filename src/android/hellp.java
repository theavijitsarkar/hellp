package cordova.plugin.hellp;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.net.wifi.WifiManager;

/**
 * This class echoes a string called from JavaScript.
 */
public class hellp extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }

        if(action.equals("startWifiTracker")){
/*
            BroadcastReceiver broadcastReceiver = new WifiBroadcastReceiver();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.putExtra("mac",args.getString(0));
            intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
            context.registerReceiver(broadcastReceiver, intentFilter);

*/


            BroadcastReceiver broadcastReceiver = new WifiChange();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
            getApplicationContext().registerReceiver(broadcastReceiver, intentFilter);
        }


        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}

