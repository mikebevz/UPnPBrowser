/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

import android.view.View;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.mikebevz.upnp.ControlUIFactory;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.device_browser.GenericKeyValueAdapter;
import com.mikebevz.upnp.device_browser.ServiceListActivity;
import com.mikebevz.upnp.tasks.GetDeviceTask;
import java.util.ArrayList;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class DeviceBrowserUIActivity extends Activity implements OnDeviceDetails, OnClickListener {

    private GenericKeyValueAdapter adapter;
    private Device device;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        setContentView(R.layout.device_details);
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("device");
       
        
        GetDeviceTask task = new GetDeviceTask((UpnpBrowserApp)this.getApplication());
        task.setOnDeviceDetailsHandler(this);
        task.execute(position);
        
        Button servicesBtn = (Button) findViewById(R.id.services_btn);
        servicesBtn.setOnClickListener(this);
        
        Button contentBtn = (Button) findViewById(R.id.content_btn);
        contentBtn.setOnClickListener(this);
        
        adapter = new GenericKeyValueAdapter(this);
        
        ArrayList<String> properties = new ArrayList<String>();
        adapter.setData(properties);
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
    }

    public void OnDeviceDetailsSuccess(Device device) {
        this.device = device;
        this.setTitle(device.getFriendlyName());
        
        ArrayList<String> properties = new ArrayList<String>();
        properties.add("FrendlyName : " +device.getFriendlyName());
        properties.add("DeviceType : " +device.getDeviceType());
        properties.add("InterfaceAddress : " +device.getInterfaceAddress());
        properties.add("Location : " +device.getLocation());
        properties.add("Manufacture : " +device.getManufacture());
        properties.add("LocatManufactureURL : " +device.getManufactureURL());
        properties.add("ModelDescription : " +device.getModelDescription());
        properties.add("ModelName : " +device.getModelName());
        properties.add("ModelNumber : " +device.getModelNumber());
        properties.add("ModelURL : " +device.getModelURL());
        properties.add("IP (v4) : " +device.getMulticastIPv4Address());
        properties.add("IP (v6) : " +device.getMulticastIPv6Address());
        properties.add("PresentationURL : " +device.getPresentationURL());
        properties.add("SSDPIPv4MulticastAddress : " +device.getSSDPIPv4MulticastAddress());
        properties.add("SSDPIPv6MulticastAddress : " +device.getSSDPIPv6MulticastAddress());
        properties.add("SerialNumber : " +device.getSerialNumber());
        properties.add("UDN : " +device.getUDN());
        properties.add("UPC : " +device.getUPC());
        properties.add("URLBase : " +device.getURLBase());
        
        
        
        adapter.setData(properties);
        adapter.notifyDataSetChanged();
        
        
        
    }

    public void OnDeviceDetailsProgressUpdate(Integer integer) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }


    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.content_btn:
                ControlUIFactory factory = new ControlUIFactory();
                intent = factory.getIntent(this, device.getDeviceType());
                break;    
            case R.id.services_btn:
                intent = new Intent(this, ServiceListActivity.class);
                break;
            default:
                intent = new Intent(this, ServiceListActivity.class);
                
        }
        
        
        intent.putExtra("device", getIntent().getExtras().getInt("device"));
        startActivity(intent);
        
    }

    public void OnDeviceDetailsPreExecute() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
