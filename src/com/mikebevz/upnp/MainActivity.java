package com.mikebevz.upnp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    ControlPoint ctrlPoint;
    DeviceListAdapter devListAdapter;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView devicesList = (ListView) findViewById(R.id.devices_list);
        
        
        
        ctrlPoint = new ControlPoint();
        
        
        ctrlPoint.addNotifyListener(this);
        ctrlPoint.addDeviceChangeListener(this);
        ctrlPoint.start();
        
        devListAdapter = new DeviceListAdapter(this);
        devListAdapter.setDeviceList(ctrlPoint.getDeviceList());
        
        devicesList.setAdapter(devListAdapter);
        devicesList.setOnItemClickListener(this);
         
        UpnpBrowserApp app = (UpnpBrowserApp) getApplication();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrlPoint.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ctrlPoint.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ctrlPoint.start();
    }

    public void deviceNotifyReceived(SSDPPacket ssdpp) {
        
        Runnable task = new Runnable() {

            public void run() {
                
                devListAdapter.setDeviceList(ctrlPoint.getDeviceList());
                System.out.println("Device Notify Received");
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
    
    
    
    
    
    
    
    
}
