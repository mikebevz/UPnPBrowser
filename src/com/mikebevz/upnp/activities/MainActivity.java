package com.mikebevz.upnp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import com.bugsense.trace.BugSenseHandler;
import com.mikebevz.upnp.DeviceListAdapter;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Device;
import com.mikebevz.upnp.device_browser.activities.DeviceBrowserUIActivity;
import com.google.ads.*;
import com.mikebevz.upnp.ActivityState;



/**
 * 
 * @author mikebevz
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private ActivityState state;
    
    
    DeviceListAdapter devListAdapter = null;
    Boolean controlPointStatus = false; // false = stopped, true = running
    ListView devicesList;
    UpnpBrowserApp app;
    private AdView av;
    
    public final ActivityState STATE_WIFI_DISABLED = new StateWifiDisabled(this);
    public final ActivityState STATE_WIFI_NOTCONNECTED = new StateWifiNotConnected(this);
    public final ActivityState STATE_APP_STARTED = new StateAppStarted(this);
    public final ActivityState STATE_CONTROL_POINT_STOPPED = new StateControlPointStopped(this);
    public final ActivityState STATE_CONTROL_POINT_STARTED = new StateControlPointStarted(this);
    
    // States
    // 1 - wifi disabled
    // 3 - wifi is not connected
    // 5 - control point started
    // 6 - control point stopped
    

    /** Called when the activity is first created.
     * @param savedInstanceState 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //setState(STATE_APP_STARTED);
        
        app = ((UpnpBrowserApp)getApplication());
        
        if (app.isDebug()) {
            BugSenseHandler.setup(this, "a8c5f7db");
        }

        
        
        /*
        if (app.isAdEnabled()) {
            av = (AdView)findViewById(R.id.ad_view);
            AdRequest request = new AdRequest();
            request.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");    
            av.loadAd(request);
        }
        */
        
        setState(STATE_CONTROL_POINT_STARTED);

    }

    /**
     * 
     */
    //@Override
    //protected void onStart() {
    //    super.onStart();  
    //}

    

    /**
     * 
     */
    @Override
    protected void onPause() {
        super.onPause();
        //Log.d("ControlPoint", "Pause Activity");
        //devListAdapter.stopControlPoint();
        
        setState(STATE_CONTROL_POINT_STOPPED);
        
    }

    /**
     * 
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ControlPoint", "Restart Activity");
        
        setState(STATE_CONTROL_POINT_STARTED);
        
    }

    /**
     * 
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ControlPoint", "Resume Activity");
        
        setState(STATE_CONTROL_POINT_STARTED);
        
        /*
        try {
            devListAdapter.resumeControlPoint();
        } catch (Exception ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            Toast toast = Toast.makeText(getApplicationContext(), R.string.wifi_isnt_connected_refresh, Toast.LENGTH_LONG);
            toast.show();
            devListAdapter.cancel();
        }*/


    }
    
    /**
     * 
     */
    @Override
    protected void onDestroy() {
        Log.d("ControlPoint", "Destroying Activity");
        //devListAdapter.stopControlPoint();
        setState(STATE_CONTROL_POINT_STOPPED);
        super.onDestroy();
    }

    /**
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device dev = (Device) devListAdapter.getItem(position);

        Intent intent = new Intent(this, DeviceBrowserUIActivity.class);
        intent.putExtra("device", position);
        startActivity(intent);

    }

    /**
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Device dev = (Device) devListAdapter.getItem(position);

        Log.d("Device", "Show Device Details " + dev.getFriendlyName());
    }

    /**
     * 
     * @param arg0
     */
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    /**
     * 
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);

    }

    /**
     * 
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu, menu);
        return true;
    }

    /**
     * 
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_network:
                
                setState(STATE_CONTROL_POINT_STOPPED);
                setState(STATE_CONTROL_POINT_STARTED);
                return true;

            case R.id.help:
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * @return the state
     */
    public ActivityState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(ActivityState state) {
        Log.d("State: ", state.getClass().getName());
        if (this.state != state) {
            Log.d("Setting State: ", state.getClass().getName());
            this.state = state;
            this.state.setSettings();
        }
    }

    public void onWifiNotConnected() {
        setState(STATE_WIFI_NOTCONNECTED);
    }

    void onWifiDisabled() {
        setState(STATE_WIFI_DISABLED);
    }
}