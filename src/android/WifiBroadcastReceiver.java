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


public class WifiBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION .equals(action)) {
            SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            if (SupplicantState.isValidState(state) 
                    && state == SupplicantState.COMPLETED) {

            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

                WifiInfo wifi = wifiManager.getConnectionInfo();
                if (wifi != null) {
                    // get current router Mac address
                    String bssid = wifi.getBSSID();
                    Toast.makeText(getApplicationContext(),bssid,Toast.LENGTH_SHORT).show();  
                // connected = desiredMacAddress.equals(bssid);
                }


        

           /*     boolean connected = checkConnectedToDesiredWifi(intent.getStringExtra("mac"));

             Bundle b = new Bundle();
                b.putString( "connected", connected);
                intent.putExtras( b);
                LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent);
*/
            }
        }
    }

    /** Detect you are connected to a specific network. */
    private boolean checkConnectedToDesiredWifi(String desiredMacAddress) {
        boolean connected = false;

        /*String desiredMacAddress = "00:17:7C:6B:C1:83"; */

        WifiManager wifiManager = 
            (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifi = wifiManager.getConnectionInfo();
        if (wifi != null) {
            // get current router Mac address
            String bssid = wifi.getBSSID();
            connected = desiredMacAddress.equals(bssid);
        }

        return connected;
    }
}