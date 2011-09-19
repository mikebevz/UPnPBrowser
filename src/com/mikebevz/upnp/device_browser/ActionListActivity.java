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
import com.mikebevz.upnp.tasks.GetServiceActionsTask;
import com.mikebevz.upnp.tasks.OnServiceActionsList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        
        List<String> list = Arrays.asList(service.getServiceID().split(":"));
        Collections.reverse(list);
                
        this.setTitle("Actions at " + list.toArray()[0].toString());

        GetServiceActionsTask getActionsTask = new GetServiceActionsTask();
        getActionsTask.setOnServiceActionListHandler(this);
        getActionsTask.execute(service);
                
        
        ListView listView = (ListView)findViewById(R.id.list_view);
        adapter = new ActionListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
    }
    


    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        //TODO Forward to ArgumentListAction
        
        Intent intent = new Intent(this, ArgumentListActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void OnServiceActionsListSuccess(ActionList aList) {
        this.sList = aList;
        ((UpnpBrowserApp)getApplication()).setActionList(aList);
        Log.d("ServiceList", String.valueOf(sList.size()));
        adapter.setActions(sList);
        adapter.notifyDataSetChanged();
    }

    public void OnServiceActionsListProgressUpdate(Integer value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
