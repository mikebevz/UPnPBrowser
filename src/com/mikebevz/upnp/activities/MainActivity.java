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
import android.widget.Toast;
import com.mikebevz.upnp.DeviceListAdapter;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cybergarage.upnp.Device;
import com.mikebevz.upnp.device_browser.activities.DeviceBrowserUIActivity;
import com.google.ads.*;

/**
 * 
 * @author mikebevz
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    //ControlPoint ctrlPoint = null;
    DeviceListAdapter devListAdapter;
    Boolean controlPointStatus = false; // false = stopped, true = running
    ListView devicesList;
    UpnpBrowserApp app;
    private AdView av;

    /** Called when the activity is first created.
     * @param savedInstanceState 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.main);

       // BugSenseHandler.setup(this, "a8c5f7db");

        devicesList = (ListView) findViewById(R.id.devices_list);
        av = (AdView)findViewById(R.id.ad_view);
        
        
        AdRequest request = new AdRequest();
        request.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");    
        av.loadAd(request);
        
        
        devListAdapter = new DeviceListAdapter(this);

        try {
            setTitle(R.string.scanning_for_devices);
            devListAdapter.startControlPoint();

            devicesList.setAdapter(devListAdapter);
            devicesList.setOnItemClickListener(this);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.connect_to_wifi, Toast.LENGTH_LONG);
            toast.show();
        }

    }

    /**
     * 
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (devListAdapter.getControlPointStatus() == true) {
            setTitle(R.string.available_upnp_devices);
            //this.setProgressBarIndeterminate(true);
            //this.setProgressBarIndeterminateVisibility(true);
        }
        

    }

    /**
     * 
     */
    @Override
    protected void onDestroy() {
        Log.d("ControlPoint", "Destroying Activity");
        devListAdapter.stopControlPoint();
        super.onDestroy();
    }

    /**
     * 
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ControlPoint", "Pause Activity");
        devListAdapter.stopControlPoint();
    }

    /**
     * 
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ControlPoint", "Restart Activity");
    }

    /**
     * 
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ControlPoint", "Resume Activity");
        try {
            devListAdapter.resumeControlPoint();
        } catch (Exception ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            Toast toast = Toast.makeText(getApplicationContext(), R.string.wifi_isnt_connected_refresh, Toast.LENGTH_LONG);
            toast.show();
        }


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

                if (controlPointStatus == true) {
                    devListAdapter.stopControlPoint();
                }

                try {

                    devListAdapter.startControlPoint();

                    devicesList.setAdapter(devListAdapter);
                    devicesList.setOnItemClickListener(this);

                    Toast toast = Toast.makeText(this, R.string.restarting_scanning, Toast.LENGTH_SHORT);
                    toast.show();

                    return true;
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.wifi_isnt_connected_refresh, Toast.LENGTH_LONG);
                    toast.show();
                    return false;
                }

            case R.id.help:
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}