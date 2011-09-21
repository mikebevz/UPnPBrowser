/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser;

import android.view.View;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.tasks.GetServiceTask;
import com.mikebevz.upnp.tasks.OnServiceDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public class ServiceDetailsActivity extends Activity implements OnServiceDetails, OnClickListener {

    private GenericKeyValueAdapter adapter;
    private Service service;
    private ProgressDialog dialog;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        setContentView(R.layout.service_details);
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
       
        
        GetServiceTask task = new GetServiceTask((UpnpBrowserApp)this.getApplication());
        task.setOnServiceDetailsHandler(this);
        task.execute(position);
        
        Button servicesBtn = (Button) findViewById(R.id.services_btn);
        servicesBtn.setOnClickListener(this);
        
        
        adapter = new GenericKeyValueAdapter(this);
        
        ArrayList<String> properties = new ArrayList<String>();
        adapter.setData(properties);
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
    }
    

    public void OnServiceDetailsSuccess(Service result) {
        this.service = result;
        List<String> serviceNameList = Arrays.asList(service.getServiceID().split(":"));
        Collections.reverse(serviceNameList);
        this.setTitle("Service: " + serviceNameList.toArray()[0].toString());
        
        
        ArrayList<String> properties = new ArrayList<String>();
        properties.add("ControlURL : " +service.getControlURL());
        properties.add("DescriptionURL : " +service.getDescriptionURL());
        properties.add("EventSubURL : " +service.getEventSubURL());
        properties.add("SCPDURL : " +service.getSCPDURL());
        properties.add("SID : " +service.getSID());
        properties.add("ServiceID : " +service.getServiceID());
        properties.add("ServiceType : " +service.getServiceType());
        
        adapter.setData(properties);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    public void OnServiceDetailsPreExecute() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
    }
    
    public void onClick(View view) {
        
        Intent intent = new Intent(this, ActionListActivity.class);
        intent.putExtra("position", getIntent().getExtras().getInt("position"));
        startActivity(intent);
        
    }
}
