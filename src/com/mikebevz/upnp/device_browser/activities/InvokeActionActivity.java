/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.device_browser.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpBrowserApp;
import java.util.ArrayList;
import java.util.List;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.AllowedValueList;
import org.cybergarage.upnp.AllowedValueRange;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;

/**
 *
 * @author mikebevz
 */
public class InvokeActionActivity extends Activity implements OnClickListener {

    private Action action;
    private int actionPosition;
    private ArgumentList argumentList;
    private LinearLayout layout;
    private static final String DIRECTION_IN = "in";
    private static final String DIRECTION_OUT = "out";
    private static final String STRING = "string";
    private static final String UI4 = "ui4";

    /** Called when the activity is first created.
     * @param icicle 
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.invoke_action);
        layout = (LinearLayout) findViewById(R.id.main_layout);

        actionPosition = getIntent().getExtras().getInt("position");
        action = (Action) ((UpnpBrowserApp) getApplication()).getActionList().get(actionPosition);
        argumentList = ((UpnpBrowserApp) getApplication()).getArgumentList();

        Button invokeBtn = (Button) findViewById(R.id.invoke_btn);
        invokeBtn.setOnClickListener(this);

        Button resetBtn = (Button) findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(this);

        setTitle("Invoke " + action.getName() + " at " + action.getService().getServiceID());

        this.buildUI(argumentList);
    }

    private void buildUI(ArgumentList argumentList) {

        for (int i = 0; i < argumentList.size(); i++) {
            Argument arg = argumentList.getArgument(i);
            if (arg.getDirection().equals(DIRECTION_IN)) {

                String name = arg.getName();
                Log.d("Argument Name", name);
                String type = arg.getRelatedStateVariable().getDataType();
                Log.d("Argument Data Type", type);

                AllowedValueList allowedValueList = arg.getRelatedStateVariable().getAllowedValueList();
                final List<String> allowedValues = new ArrayList<String>();

                if (allowedValueList != null) {
                    for (int j = 0; j < allowedValueList.size(); j++) {
                        Log.d("AllowedValue", allowedValueList.getAllowedValue(j).getValue());
                        allowedValues.add(allowedValueList.getAllowedValue(j).getValue());
                    }

                }
                AllowedValueRange allowedValueRange = arg.getRelatedStateVariable().getAllowedValueRange();
                if (allowedValueRange != null) {
                    Log.d("Allowed Range: Min", allowedValueRange.getMinimum());
                    Log.d("Allowed Range: Max", allowedValueRange.getMaximum());
                    Log.d("Allowed Range: Step", allowedValueRange.getStep());

                }

                // Start adding ui elements

                TextView label = new TextView(this);
                label.setText(name);
                layout.addView(label);

                // Adding latest element of the group first 
                if (allowedValues.isEmpty()) {
                    EditText te = new EditText(this);
                    if (type.equalsIgnoreCase(STRING)) {
                        te.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    }

                    if (type.equalsIgnoreCase(UI4)) {
                        te.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    }


                    te.setId(i);
                    te.setText(arg.getValue());

                    layout.addView(te);

                } else { // Add drop down
                    Spinner spinner = new Spinner(this);
                    SpinnerAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, allowedValues);
                    spinner.setAdapter(adapter);
                    spinner.setId(i);

                    layout.addView(spinner);
                }








            }
        }
    }

    /**
     * 
     * @param view
     */
    public void onClick(View view) {

        if (view.getId() == R.id.reset_btn) {
        }


        if (view.getId() == R.id.invoke_btn) {



            for (int i = 0; i < argumentList.size(); i++) {
                Argument arg = argumentList.getArgument(i);
                //View element = (View)findViewById(i);
                String argValue = "";
                if (findViewById(i) instanceof EditText) {
                    EditText et = (EditText) findViewById(i);
                    argValue = et.getText().toString();

                }

                if (findViewById(i) instanceof Spinner) {
                    Spinner et = (Spinner) findViewById(i);
                    argValue = ((ArrayAdapter<String>) et.getAdapter()).getItem(et.getSelectedItemPosition());
                }

                Log.d(arg.getName() + " Value =", argValue);
                action.setArgumentValue(arg.getName(), argValue);

            }

            if (action.postControlAction() == true) {
                ArgumentList outArgList = action.getOutputArgumentList();
                List<Object> result = new ArrayList<Object>();
                
                layout.removeAllViews();
                
                for (int i=0;i<outArgList.size();i++) {
                    String value = action.getArgument(outArgList.getArgument(i).getName()).getValue();
                    Log.d("Invoke Result", value);
                    result.add(value);
                    
                    TextView argName = new TextView(this);
                    argName.setText("Argument Name: \n" + outArgList.getArgument(i).getName());
                    argName.setTextSize(20);
                    layout.addView(argName);
                    
                    TextView argValueLabel = new TextView(this);
                    argValueLabel.setText("Response");
                    argValueLabel.setTextSize(20);
                    layout.addView(argValueLabel);
                    
                    TextView argValue = new TextView(this);
                    argValue.setText(value);
                    layout.addView(argValue);
                    
                }
                //String result = getAction().getArgument("Result").getValue();
                //String numberReturned = action.getArgument("NumberReturned").getValue();
                //String totalMatches = action.getArgument("TotalMatches").getValue();
                //String updateID = action.getArgument("UpdateID").getValue();
                //System.out.println(result);
                //parser.setMessage(result);
                
            }

        }



    }
}
