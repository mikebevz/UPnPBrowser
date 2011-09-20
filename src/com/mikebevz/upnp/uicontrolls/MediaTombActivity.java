/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import com.mikebevz.upnp.tasks.GetDeviceServicesTask;
import com.mikebevz.upnp.tasks.OnDeviceServiceList;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.ServiceList;

/**
 *
 * @author mikebevz
 */
public class MediaTombActivity extends Activity implements OnDeviceServiceList {
    
    
    // Connection Manager Service
    Action getProtocolInfoAction;
    Action getCurrentConnectionIDsAction;
    Action getCurrentConnectionInfoAction;
    
    // ContentDirectory Service
    Action browseAction;
    Action getSortCapabilitiesAction;
    Action getSystemUpdateIDAction;
    Action getSearchCapabilitiesAction;
    
    ServiceList sList;
    
    
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
        Device device = (Device) ((UpnpBrowserApp)getApplication()).getDeviceList().get(position);
        this.setTitle(device.getFriendlyName());
        
        /*
        Service contentDirectory = device.getService("urn:upnp-org:serviceId:ContentDirectory");
        
        ActionList list = contentDirectory.getActionList();
        
        for(int i=0;i<list.size();i++) {
           Log.d("Action: ", list.getAction(i).getName()); 
        }
        */
        
        GetDeviceServicesTask getServiceTask = new GetDeviceServicesTask();
        getServiceTask.setOnDeviceServiceListHandler(this);
        getServiceTask.execute(device);
        
        
        /*
        browseAction = contentDirectory.getAction("Browse");
        
        
        browseAction.setArgumentValue("ObjectID", "0");
        browseAction.setArgumentValue("BrowseFlag", "BrowseDirectChildren");
        browseAction.setArgumentValue("Filter", "*");
        browseAction.setArgumentValue("StartingIndex", "0");
        browseAction.setArgumentValue("RequestedCount", "10");
        browseAction.setArgumentValue("SortCriteria", "");
        
        if (browseAction.postControlAction() == true) {
            ArgumentList outArgList = browseAction.getOutputArgumentList();
            
            String result = browseAction.getArgument("Result").getValue();
            String numberReturned = browseAction.getArgument("NumberReturned").getValue();
            String totalMatches = browseAction.getArgument("TotalMatches").getValue();
            String updateID = browseAction.getArgument("UpdateID").getValue();
            
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

    public void OnDeviceServiceListSuccess(ServiceList sList) {
        this.sList = sList;
        Log.d("ServiceList", String.valueOf(sList.size()));
    }

    public void OnDeviceServiceListProgressUpdate(Integer value) {
        Log.d("Progress", String.valueOf(value));
        setProgress(value);
    }

    public void OnDeviceServiceListPreExecute() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
