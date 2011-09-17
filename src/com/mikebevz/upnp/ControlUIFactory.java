/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.mikebevz.upnp.uicontrolls.DeviceBrowserUIActivity;
import com.mikebevz.upnp.uicontrolls.SwitchPowerUIActivity;

/**
 *
 * @author mikebevz
 */
public class ControlUIFactory {
    
    final public static int SWITCH_POWER = 1000;
    final public static String SWITCH_POWER_UPC = "urn:schemas-upnp-org:device:DimmableLight:1";
    
    
    
    public Intent getIntent(Context context, String upc) throws RuntimeException {
        
        System.out.println("INPUT: " + upc);
        
        int devId = 0;
        
        if (upc.equals(SWITCH_POWER_UPC)) {
            devId = SWITCH_POWER;
        }
        
        
        switch (devId) {
            case SWITCH_POWER:
                return new Intent(context, SwitchPowerUIActivity.class);
            
                
            default:
                return new Intent(context, DeviceBrowserUIActivity.class);
                
        }
        
        
    }
}
