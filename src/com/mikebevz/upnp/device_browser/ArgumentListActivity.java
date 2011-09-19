/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.tasks.GetActionArgumentsTask;
import com.mikebevz.upnp.tasks.OnActionArgumentsList;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;

/**
 *
 * @author mikebevz
 */
public class ArgumentListActivity extends Activity implements OnActionArgumentsList, OnItemClickListener {
    
    private ArgumentList aList;
    private ArgumentListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_view);  
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        Action action = (Action) ((UpnpBrowserApp)getApplication()).getActionList().get(position);
        
        //List<String> list = Arrays.asList(service.getServiceID().split(":"));
        //Collections.reverse(list);
                
        this.setTitle(action.getName() + " arguments");
        
        //GetDeviceServicesTask getServiceTask = new GetDeviceServicesTask();
        //getServiceTask.setOnDeviceServiceListHandler(this);
        //getServiceTask.execute(device);
        //GetServiceActionsTask getActionsTask = new GetServiceActionsTask();
        //getActionsTask.setOnServiceActionListHandler(this);
        //getActionsTask.execute(service);
        GetActionArgumentsTask getArgumentsTask = new GetActionArgumentsTask();
        getArgumentsTask.setOnActionArgumentsListHandler(this);        
        getArgumentsTask.execute(action);
        
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        
        adapter = new ArgumentListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
    }
    


    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        //TODO Forward to ArgumentListAction
        
        
        
        //Intent intent = new Intent(this, ArgumentListActivity.class);
        //intent.putExtra("position", position);
        //startActivity(intent);
    }



    public void OnActionArgumentsListSuccess(ArgumentList aList) {
        this.aList = aList;
        Log.d("ArgumentsList", String.valueOf(aList.size()));
        adapter.setArguments(aList);
        adapter.notifyDataSetChanged();
    }

    public void OnActionArgumentsProgressUpdate(Integer integer) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

   
}
