package com.mikebevz.upnp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import com.bugsense.trace.BugSenseHandler;
import com.mikebevz.upnp.uicontrolls.DeviceBrowserUIActivity;

public class MainActivity extends Activity implements NotifyListener, DeviceChangeListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    ControlPoint ctrlPoint = null;
    DeviceListAdapter devListAdapter;
    Boolean controlPointStatus = false; // false = stopped, true = running
    ListView devicesList;
    UpnpBrowserApp app;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        
        setContentView(R.layout.main);
        
        BugSenseHandler.setup(this, "a8c5f7db");
        
        devicesList = (ListView) findViewById(R.id.devices_list);

        setTitle("Available UPnP Devices");
        
        devListAdapter = new DeviceListAdapter(this);   

        try {
            startControlPoint();

            devListAdapter.setDeviceList(ctrlPoint.getDeviceList());

            devicesList.setAdapter(devListAdapter);
            devicesList.setOnItemClickListener(this);

            app = (UpnpBrowserApp) getApplication();
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Connect to a WiFi network", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (controlPointStatus == true) {
            this.setProgressBarIndeterminate(true);
            this.setProgressBarIndeterminateVisibility(true);
        }
    }
    
    

    private void startControlPoint() throws Exception {
        app = (UpnpBrowserApp) getApplication();

        if (!app.IsWifiConnected()) {
            Log.d("WifiCheck", "Wifi Is not Connected!");
            setTitle("WiFi is not connected!");
            throw new Exception("WiFi is not Connected"); 
        } else {

            Log.d("ControlPoint", "Start ControlPoint");
            if (ctrlPoint == null) {
                Log.d("ControlPoint", "Start - Create New ControlPoint");
                ctrlPoint = new ControlPoint();
                ctrlPoint.addNotifyListener(this);
                ctrlPoint.addDeviceChangeListener(this);

            }
            Log.d("ControlPoint", "Start - Starting ControlPoint");
            if (controlPointStatus == false) {
                ctrlPoint.start();
                this.setProgressBarIndeterminate(true);
                this.setProgressBarIndeterminateVisibility(true);
                controlPointStatus = true;
            }
        }
    }

    private void resumeControlPoint() throws Exception {
        Log.d("ControlPoint", "Resuming ControlPoint");
        if (!app.IsWifiConnected()) {
            throw new Exception("WiFi is not Connected"); 
        } else {
            if (controlPointStatus == false) {
                Log.d("ControlPoint", "Resume - Start ControlPoint");
                ctrlPoint.start();
                ctrlPoint.addNotifyListener(this);
                ctrlPoint.addDeviceChangeListener(this);
                ctrlPoint.removeExpiredDevices();
                
                this.setProgressBarIndeterminate(true);
                this.setProgressBarIndeterminateVisibility(true);
                
                controlPointStatus = true;
            }
        }

    }

    private void stopControlPoint() {
        Log.d("ControlPoint", "Stopping ControlPoint");
        if (controlPointStatus == true) {
            ctrlPoint.stop();
            ctrlPoint.removeNotifyListener(this);
            ctrlPoint.removeDeviceChangeListener(this);
            controlPointStatus = false;
            this.setProgressBarIndeterminate(false);
            this.setProgressBarIndeterminateVisibility(false);
        }
        //ctrlPoint = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ControlPoint", "Destroying Activity");
        stopControlPoint();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ControlPoint", "Pause Activity");
        stopControlPoint();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ControlPoint", "Restart Activity");
        //startControlPoint();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ControlPoint", "Resume Activity");
        try {
            resumeControlPoint();
        } catch (Exception ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            Toast toast = Toast.makeText(getApplicationContext(), "WiFi network is not connected. Try to refresh.", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void deviceNotifyReceived(final SSDPPacket ssdpp) {

        Runnable task = new Runnable() {

            public void run() {

                devListAdapter.setDeviceList(ctrlPoint.getDeviceList());
                System.out.println("Device Notify Received: " + ssdpp.getUSN());
                devListAdapter.notifyDataSetChanged();
            }
        };

        runOnUiThread(task);

    }

    public void deviceAdded(final Device device) {

        Runnable task = new Runnable() {

            public void run() {
                System.out.println("Device Added " + device.getFriendlyName());
                devListAdapter.addDevice(device);
            }
        };

        runOnUiThread(task);

    }

    public void deviceRemoved(final Device device) {

        Runnable task = new Runnable() {

            public void run() {
                System.out.println("Device Deleted " + device.getFriendlyName());
                devListAdapter.deleteDevice(device);
            }
        };

        runOnUiThread(task);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device dev = (Device) devListAdapter.getItem(position);

        app.setDeviceList(ctrlPoint.getDeviceList());
        //ControlUIFactory factory = new ControlUIFactory();

        Intent intent = new Intent(this, DeviceBrowserUIActivity.class);//factory.getIntent(this, dev.getDeviceType());
        intent.putExtra("device", position);
        startActivity(intent);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Device dev = (Device) devListAdapter.getItem(position);

        Log.d("Device", "Show Device Details " + dev.getFriendlyName());
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_network:
                
               if (controlPointStatus == true) {
                   stopControlPoint();
               }
                
               try {
                    
                    startControlPoint();
                    
                    devListAdapter.setDeviceList(ctrlPoint.getDeviceList());
                    devicesList.setAdapter(devListAdapter);
                    devicesList.setOnItemClickListener(this);
                    
                    Toast toast = Toast.makeText(this, "Restarting scanning...", Toast.LENGTH_SHORT);
                    toast.show();
                    
                    return true;
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "WiFi network is not connected. Try to refresh.", Toast.LENGTH_LONG);
                    toast.show();
                    return false;
                } 
                
            default:
                return super.onOptionsItemSelected(item);
        }
        
    }
    
    
    
    
}
