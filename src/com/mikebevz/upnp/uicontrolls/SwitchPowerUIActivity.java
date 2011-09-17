/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

import android.widget.RadioGroup;
import com.mikebevz.upnp.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.ServiceList;
import org.cybergarage.upnp.StateVariable;
import org.cybergarage.upnp.UPnPStatus;

/**
 *
 * @author mikebevz
 */
public class SwitchPowerUIActivity extends Activity implements SeekBar.OnSeekBarChangeListener, OnCheckedChangeListener {
    
    Action setTargetAction;
    Action setStatusAction; 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here  
        
        setContentView(R.layout.network_light_dashboard);
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("device");
        Device device = (Device) ((UpnpBrowserApp)getApplication()).getDeviceList().get(position);
        this.setTitle(device.getFriendlyName());
        
        setTargetAction = device.getAction("SetLoadLevelTarget");
        
        
        SeekBar slider = (SeekBar)findViewById(R.id.slider);
        slider.setOnSeekBarChangeListener(this);
        
        // Set level of the slider
        StateVariable loadlevel = device.getStateVariable("LoadLevelTarget");
        if (loadlevel.postQuerylAction() == true) {
            int value = Integer.parseInt(loadlevel.getValue());
            slider.setProgress(value);
        } else {
            UPnPStatus err = loadlevel.getQueryStatus();
            Log.e("PostUPnPValue", err.getCode() + ": "+ err.getDescription());
        }
        
        
        setStatusAction = device.getAction("SetTarget");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.power_switch);
        radioGroup.setOnCheckedChangeListener(this);
        
        RadioButton switchOnBtn = (RadioButton)findViewById(R.id.power_switch_on);
        RadioButton switchOffBtn = (RadioButton)findViewById(R.id.power_switch_off);
        
        
        StateVariable switch_status = device.getStateVariable("Target");
        if (switch_status.postQuerylAction() == true) {
            int status = Integer.parseInt(switch_status.getValue());
            if (status == 1) {
                switchOnBtn.setChecked(true);
                switchOffBtn.setChecked(false);
            } else {
                switchOffBtn.setChecked(true);
                switchOnBtn.setChecked(false);
            }
        } else {
            UPnPStatus err = loadlevel.getQueryStatus();
            Log.e("PostUPnPValue", err.getCode() + ": "+ err.getDescription());
        }
        
        
        
        Log.d("SwitchPower", device.getDeviceType());
        
        ServiceList serviceList = device.getServiceList(); 
        
        for(int i=0; i<serviceList.size();i++) {
            Service serv = serviceList.getService(i);
            ActionList actionList = serv.getActionList();
            Log.d("Service", serv.getControlURL());
            
            for (int j=0;j<actionList.size();j++) {
                Action action = actionList.getAction(j);
                Log.d("Action", action.getName());
                ArgumentList argList = action.getArgumentList();
                
                for (int e=0;e<argList.size();e++) {
                    Argument arg = argList.getArgument(e);
                    Log.d("Argument name", arg.getName());
                    
                    Log.d("Argument value", arg.getValue());
                }
                
            }
            
        }
        
    }

    public void onProgressChanged(SeekBar slider, int value, boolean fromUser) {
        //Log.d("Changed", String.valueOf(value));
        
        setTargetAction.setArgumentValue("newLoadlevelTarget", value);
        
        if (setTargetAction.postControlAction() == true) {
            
        } else {
            UPnPStatus err = setTargetAction.getControlStatus();
            Log.e("PostUPnPValue", err.getCode() + ": "+ err.getDescription());
        }
    }

    public void onStartTrackingTouch(SeekBar slider) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onStopTrackingTouch(SeekBar slider) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.power_switch_on) {
            setStatusAction.setArgumentValue("NewTargetValue", 1);
            
        }
        
        if (checkedId == R.id.power_switch_off) {
            setStatusAction.setArgumentValue("NewTargetValue", 0);
        }
        
        if (setStatusAction.postControlAction() == true) {
            
        } else {
            UPnPStatus err = setStatusAction.getControlStatus();
            Log.e("PostUPnPValue", err.getCode() + ": "+ err.getDescription());
        }
        
    }
}
