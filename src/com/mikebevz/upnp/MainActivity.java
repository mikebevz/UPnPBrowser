package com.mikebevz.upnp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

public class MainActivity extends Activity implements NotifyListener, DeviceChangeListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener
{
    ControlPoint ctrlPoint = null;
    DeviceListAdapter devListAdapter;
    Boolean controlPointStatus = false; // false = stopped, true = running
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView devicesList = (ListView) findViewById(R.id.devices_list);
        
        setTitle("Available UPnP Devices");
        
        
        startControlPoint();
        
        devListAdapter = new DeviceListAdapter(this);
        devListAdapter.setDeviceList(ctrlPoint.getDeviceList());
        
        devicesList.setAdapter(devListAdapter);
        devicesList.setOnItemClickListener(this);
         
        UpnpBrowserApp app = (UpnpBrowserApp) getApplication();
        
    }
    
    private void startControlPoint() {
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
            controlPointStatus = true;
        }
    }
    
    private void resumeControlPoint() {
        Log.d("ControlPoint", "Resuming ControlPoint");
        if (controlPointStatus == false) {
            Log.d("ControlPoint", "Resume - Start ControlPoint");
            ctrlPoint.start();
            controlPointStatus = true;
        }
        
    }
    
    private void stopControlPoint() {
        Log.d("ControlPoint", "Stopping ControlPoint");
        if (controlPointStatus == true) {
            ctrlPoint.stop();
            controlPointStatus = false;
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
        resumeControlPoint();
        
    }

    public void deviceNotifyReceived(final SSDPPacket ssdpp) {
        
        Runnable task = new Runnable() {

            public void run() {
                
                devListAdapter.setDeviceList(ctrlPoint.getDeviceList());
                System.out.println("Device Notify Received: "+ ssdpp.getUSN());
                devListAdapter.notifyDataSetChanged();
            }
            
        };
        
        runOnUiThread(task);
        
    }

    public void deviceAdded(final Device device) {
        
        Runnable task = new Runnable() {

            public void run() {
                System.out.println("Device Added "+ device.getFriendlyName());
                devListAdapter.addDevice(device);
            }
            
        };
        
        runOnUiThread(task);
        
    }

    public void deviceRemoved(final Device device) {
        
        Runnable task = new Runnable() {

            public void run() {
                System.out.println("Device Deleted "+ device.getFriendlyName());
                devListAdapter.deleteDevice(device);
            }
            
        };
        
        runOnUiThread(task);
        
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device dev = (Device) devListAdapter.getItem(position);
        
        ((UpnpBrowserApp)getApplication()).setDeviceList(ctrlPoint.getDeviceList());
        ControlUIFactory factory = new ControlUIFactory();
        
        Intent intent = factory.getIntent(this, dev.getDeviceType());
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
    
    
    
    
    
    
    
    
}
