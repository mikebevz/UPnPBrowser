/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.mediaserver.content_directory.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver.content_directory.Entity;
import java.util.List;
import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public class ContainerListActivity extends Activity implements OnTaskFactory, OnItemClickListener {
    
    private String objectId;
    private ProgressDialog dialog;
    private Integer deviceNumber;
    private List<Entity> containerList;
    
    

    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        setContentView(R.layout.media_server_frontpage);

        objectId = getIntent().getExtras().getString("objectId");
        //Log.d("ObjectID", objectId);
        deviceNumber = getIntent().getExtras().getInt("device");
        Device device = ((UpnpBrowserApp)getApplication()).getDeviceList().getDevice(deviceNumber);
        
        BrowseTask task = new BrowseTask(device, TaskFactory.CONTENT_DIRECTORY_SERVICE, TaskFactory.BROWSE_ACTION);
        task.setOnTaskFactoryHandler(this);
        task.setObjectID(objectId);
        task.execute();
        
        
    }

    public void onTaskFactoryPreExecute() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
    }
    
    public void onTaskFactorySuccess(List<Entity> result)  {
            
        //if (result.get(0).getCclass().matches(CONTAINER_CCLASS)) {
            
            this.containerList = result;
            ContainerListAdapter adapter = new ContainerListAdapter(this);
            adapter.setContainers(result);
        
        //}

        ListView cList = (ListView) findViewById(R.id.container_list);
        cList.setAdapter(adapter);
        cList.setOnItemClickListener(this);
        
        
        dialog.dismiss();
    }

    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        
        Intent intent = new Intent(this, ContainerListActivity.class);
        intent.putExtra("objectId", containerList.get(position).getObjectId());
        intent.putExtra("device", deviceNumber);
        startActivity(intent);
        
    }

    
}
