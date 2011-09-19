/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.tasks.GetServiceActionsTask;
import com.mikebevz.upnp.tasks.OnServiceActionsList;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public class ActionListActivity extends Activity implements OnServiceActionsList, OnItemClickListener {
    
    private ActionList sList;
    private ActionListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_view);  
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        Service service = (Service) ((UpnpBrowserApp)getApplication()).getServiceList().get(position);
        this.setTitle("Actions at "+service.getServiceID());
        
        //GetDeviceServicesTask getServiceTask = new GetDeviceServicesTask();
        //getServiceTask.setOnDeviceServiceListHandler(this);
        //getServiceTask.execute(device);
        GetServiceActionsTask getActionsTask = new GetServiceActionsTask();
        getActionsTask.setOnServiceActionListHandler(this);
        getActionsTask.execute(service);
                
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        adapter = new ActionListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
    }
    


    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void OnServiceActionsListSuccess(ActionList aList) {
        this.sList = aList;
        Log.d("ServiceList", String.valueOf(sList.size()));
        adapter.setActions(sList);
        adapter.notifyDataSetChanged();
    }

    public void OnServiceActionsListProgressUpdate(Integer value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
