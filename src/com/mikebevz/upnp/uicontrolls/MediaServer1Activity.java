/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.mediaserver.content_directory.Container;
import com.mikebevz.upnp.mediaserver.content_directory.ContainerListAdapter;
import com.mikebevz.upnp.mediaserver.content_directory.SaxContentParser;
import com.mikebevz.upnp.mediaserver1.BrowseTask;
import com.mikebevz.upnp.mediaserver1.OnTaskFactory;
import com.mikebevz.upnp.mediaserver1.TaskFactory;
import com.mikebevz.upnp.tasks.GetDeviceTask;
import com.mikebevz.upnp.tasks.OnDeviceDetails;
import java.util.List;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.ServiceList;

/**
 *
 * @author mikebevz
 */
public class MediaServer1Activity extends Activity implements OnDeviceDetails, OnTaskFactory {

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
        //Device device = (Device) ((UpnpBrowserApp)getApplication()).getDeviceList().get(position);
        //this.setTitle(device.getFriendlyName());

        /*
        Service contentDirectory = device.getService("urn:upnp-org:serviceId:ContentDirectory");
        
        ActionList list = contentDirectory.getActionList();
        
        for(int i=0;i<list.size();i++) {
        Log.d("Action: ", list.getAction(i).getName()); 
        }
         */

        //GetDeviceServicesTask getServiceTask = new GetDeviceServicesTask();
        //getServiceTask.setOnDeviceServiceListHandler(this);
        //getServiceTask.execute(device);




        /*
        action = contentDirectory.getAction("Browse");
        
        
        action.setArgumentValue("ObjectID", "0");
        action.setArgumentValue("BrowseFlag", "BrowseDirectChildren");
        action.setArgumentValue("Filter", "*");
        action.setArgumentValue("StartingIndex", "0");
        action.setArgumentValue("RequestedCount", "10");
        action.setArgumentValue("SortCriteria", "");
        
        if (action.postControlAction() == true) {
        ArgumentList outArgList = action.getOutputArgumentList();
        
        String result = action.getArgument("Result").getValue();
        String numberReturned = action.getArgument("NumberReturned").getValue();
        String totalMatches = action.getArgument("TotalMatches").getValue();
        String updateID = action.getArgument("UpdateID").getValue();
        
        SaxContentParser parser = new SaxContentParser();
        parser.setMessage(result);
        List<Container> containers = parser.parse();
        
        ContainerListAdapter adapter = new ContainerListAdapter(this);
        adapter.setContainers(containers);
        
        ListView containerList = (ListView)findViewById(R.id.container_list);
        containerList.setAdapter(adapter);
        
        Log.d("Container: ", String.valueOf(containers.size()));
        
        
        }
        
        
         */
        //getSortCapabilitiesAction = device.getAction("getSortCapabilities");
        //getSystemUpdateIDAction = device.getAction("getSystemUpdateIdAction");

    }

    public void OnDeviceDetailsSuccess(Device device) {

        BrowseTask task = new BrowseTask();
        task.setOnTaskFactoryHandler(this);
        task.execute(device.getService(TaskFactory.CONTENT_DIRECTORY_SERVICE).getAction(TaskFactory.BROWSE_ACTION));

        dialog.dismiss();
    }

    public void OnDeviceDetailsProgressUpdate(Integer integer) {
    }

    public void OnDeviceDetailsPreExecute() {
        dialog = ProgressDialog.show(this, "", "Downloading...", true);
    }

    public void onTaskFactorySuccess(List<Container> result) {
        //throw new UnsupportedOperationException("Not supported yet.");

        ContainerListAdapter adapter = new ContainerListAdapter(this);
        adapter.setContainers(result);

        ListView containerList = (ListView) findViewById(R.id.container_list);
        containerList.setAdapter(adapter);

        Log.d("Container: ", String.valueOf(result.size()));
    }

    public void onTaskFactoryPreExecute() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
