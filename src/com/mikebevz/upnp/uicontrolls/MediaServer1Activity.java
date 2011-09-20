/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.mediaserver.content_directory.Container;
import com.mikebevz.upnp.mediaserver.content_directory.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver1.BrowseTask;
import com.mikebevz.upnp.mediaserver1.ContainerListActivity;
import com.mikebevz.upnp.mediaserver1.OnTaskFactory;
import com.mikebevz.upnp.mediaserver1.TaskFactory;
import com.mikebevz.upnp.tasks.GetDeviceTask;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import java.util.List;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.ServiceList;

/**
 *
 * @author mikebevz
 */
public class MediaServer1Activity extends Activity implements OnDeviceDetails, OnTaskFactory, OnItemClickListener {

    // Connection Manager Service
    Action getProtocolInfoAction;
    Action getCurrentConnectionIDsAction;
    Action getCurrentConnectionInfoAction;
    // ContentDirectory Service
    Action action;
    Action getSortCapabilitiesAction;
    Action getSystemUpdateIDAction;
    Action getSearchCapabilitiesAction;
    ServiceList sList;
    private ProgressDialog dialog;
    private List<Container> containerList;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here   
        requestWindowFeature(Window.PROGRESS_VISIBILITY_ON);
        requestWindowFeature(Window.PROGRESS_START);
        setProgressBarVisibility(true);

        setContentView(R.layout.media_server_frontpage);


        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("device");


        GetDeviceTask task = new GetDeviceTask((UpnpBrowserApp) getApplication());
        task.setOnDeviceDetailsHandler(this);
        task.execute(position);
        
    }
    
    public void OnDeviceDetailsPreExecute() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
        
    }

    public void OnDeviceDetailsSuccess(Device device) {
        setTitle(device.getFriendlyName());
        
        BrowseTask task = new BrowseTask();
        task.setOnTaskFactoryHandler(this);
        task.execute(device.getService(TaskFactory.CONTENT_DIRECTORY_SERVICE).getAction(TaskFactory.BROWSE_ACTION));

        dialog.dismiss();
    }

    public void OnDeviceDetailsProgressUpdate(Integer integer) {
    }

    
    
    public void onTaskFactoryPreExecute() {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
        
    }

    public void onTaskFactorySuccess(List<Container> result) {
        this.containerList = result;
        ContainerListAdapter adapter = new ContainerListAdapter(this);
        adapter.setContainers(result);
        
        ListView cList = (ListView) findViewById(R.id.container_list);
        cList.setAdapter(adapter);
        cList.setOnItemClickListener(this);

        Log.d("Container: ", String.valueOf(result.size()));
        dialog.dismiss();
    }

    

    public void onItemClick(AdapterView<?> av, View view, int position, long id) {
        
        Intent intent = new Intent(this, ContainerListActivity.class);
        
        intent.putExtra("objectId", containerList.get(position).getObjectId());
        startActivity(intent);
        
    }
}


       