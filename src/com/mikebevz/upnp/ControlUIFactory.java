package com.mikebevz.upnp;

import android.content.Context;
import android.content.Intent;
import com.mikebevz.upnp.activities.MediaServer2Activity;
import com.mikebevz.upnp.device_browser.activities.DeviceBrowserUIActivity;
import com.mikebevz.upnp.mediaserver1.activities.MediaServer1Activity;
import com.mikebevz.upnp.networklight.activities.SwitchPowerUIActivity;

/**
 * @author mikebevz
 */
public class ControlUIFactory {

/**
 *
 */
private final static int SWITCH_POWER = 1000;
/**
 *
 */
private final static String SWITCH_POWER_UPC = "urn:schemas-upnp-org:device:DimmableLight:1";
/**
 *
 */
private final static int MEDIA_SERVER1 = 2000;
/**
 *
 */
private final static String MEDIA_SERVER1_UPC = "urn:schemas-upnp-org:device:MediaServer:1";
/**
 *
 */
private final static int MEDIA_SERVER2 = 3000;
/**
 *
 */
private final static String MEDIA_SERVER2_UPC = "urn:schemas-upnp-org:device:MediaServer:2";


/**
 * @param context
 * @param upc
 * @return
 * @throws RuntimeException
 */
public Intent getIntent(Context context, String upc) throws RuntimeException {

  System.out.println("INPUT: " + upc);

  int devId = 0;

  if (upc.equals(SWITCH_POWER_UPC)) {
    devId = SWITCH_POWER;
  }

  if (upc.equals(MEDIA_SERVER1_UPC)) {
    devId = MEDIA_SERVER1;
  }

  if (upc.equals(MEDIA_SERVER2_UPC)) {
    devId = MEDIA_SERVER2;
  }


  switch (devId) {
    case SWITCH_POWER:
      return new Intent(context, SwitchPowerUIActivity.class);

    case MEDIA_SERVER1:
      return new Intent(context, MediaServer1Activity.class);

    case MEDIA_SERVER2:
      return new Intent(context, MediaServer2Activity.class);

    default:
      return new Intent(context, DeviceBrowserUIActivity.class);

  }


}
}
