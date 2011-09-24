/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser.activities;

import android.view.View;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.device_browser.ServiceDetailsAdapter;
import java.util.ArrayList;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public class ServiceDetailsActivity extends Activity implements OnClickListener {

    private ServiceDetailsAdapter adapter;
    private Service service;
    private ProgressDialog dialog;
    
    /** Called when the activity is first created.
     * @param icicle 
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        setContentView(R.layout.service_details);
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        
        Button servicesBtn = (Button) findViewById(R.id.services_btn);
        servicesBtn.setOnClickListener(this);
        
        
        adapter = new ServiceDetailsAdapter(this, position);
        
        ArrayList<String> properties = new ArrayList<String>();
        adapter.setData(properties);
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
    }
    

 
    
    /**
     * 
     * @param view
     */
    public void onClick(View view) {
        
        Intent intent = new Intent(this, ActionListActivity.class);
        intent.putExtra("position", getIntent().getExtras().getInt("position"));
        startActivity(intent);
        
    }
}
