/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import com.mikebevz.upnp.mediaserver1.models.Entity;
import android.os.AsyncTask;
import android.util.Log;
import java.util.List;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;

/**
 *
 * @author mikebevz
 */
public final class BrowseTask extends AsyncTask<Void, Void, BrowserTaskResult> implements TaskFactoryTask {

    private OnTaskFactory delegate;
    private Action action;
    private String objectId = "";
    private String browseFlag = "BrowseDirectChildren";
    private String Filter = "*";
    private String startingIndex = "0";
    private String requestedCount = "10";
    private String sortCriteria = "";
    private Device device;
    private Service service;
    private String serviceName = "";
    private String actionName = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.delegate.onTaskFactoryPreExecute();
    }
    
    public BrowseTask(Device device, String serviceName, String actionName) {
        setDevice(device);
        setServiceName(serviceName);
        setActionName(actionName);
    }
    
    
    @Override
    protected BrowserTaskResult doInBackground(Void... params) {
        
        setAction(getDevice().getService(getServiceName()).getAction(getActionName()));
        
        getAction().setArgumentValue("ObjectID", getObjectId());
        getAction().setArgumentValue("BrowseFlag", getBrowseFlag());
        getAction().setArgumentValue("Filter", getFilter());
        getAction().setArgumentValue("StartingIndex", getStartingIndex());
        getAction().setArgumentValue("RequestedCount", getRequestedCount());
        getAction().setArgumentValue("SortCriteria", getSortCriteria());

        SaxContentParser parser = new SaxContentParser();
        BrowserTaskResult res = new BrowserTaskResult();
        
        if (getAction().postControlAction() == true) {
            //ArgumentList outArgList = action.getOutputArgumentList();

            
            res.setResult(getAction().getArgument("Result").getValue());
            res.setNumberReturned(getAction().getArgument("NumberReturned").getValue());
            res.setTotalMatches(getAction().getArgument("TotalMatches").getValue());
            res.setUpdateID(getAction().getArgument("UpdateID").getValue());
            
            //String result = ;
            //String numberReturned = action.getArgument("NumberReturned").getValue();
            //String totalMatches = action.getArgument("TotalMatches").getValue();
            //String updateID = action.getArgument("UpdateID").getValue();
            Log.d("XML Parsing Result",res.getResult());
            parser.setMessage(res.getResult());


        }
        
        
        //List<Entity> containers = parser.parse();
        res.setEntities(parser.parse());
            
        return res;
    }
    
   

    @Override
    protected void onPostExecute(BrowserTaskResult result) {
        super.onPostExecute(result);
        this.getDelegate().onTaskFactorySuccess(result);
    }

    public void setOnTaskFactoryHandler(OnTaskFactory delegate) {
        this.setDelegate(delegate);
    }

    /**
     * @return the delegate
     */
    public OnTaskFactory getDelegate() {
        return delegate;
    }

    /**
     * @param delegate the delegate to set
     */
    public void setDelegate(OnTaskFactory delegate) {
        this.delegate = delegate;
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the browseFlag
     */
    public String getBrowseFlag() {
        return browseFlag;
    }

    /**
     * @param browseFlag the browseFlag to set
     */
    public void setBrowseFlag(String browseFlag) {
        this.browseFlag = browseFlag;
    }

    /**
     * @return the Filter
     */
    public String getFilter() {
        return Filter;
    }

    /**
     * @param Filter the Filter to set
     */
    public void setFilter(String Filter) {
        this.Filter = Filter;
    }

    /**
     * @return the startingIndex
     */
    public String getStartingIndex() {
        return startingIndex;
    }

    /**
     * @param startingIndex the startingIndex to set
     */
    public void setStartingIndex(String startingIndex) {
        this.startingIndex = startingIndex;
    }

    /**
     * @return the requestedCount
     */
    public String getRequestedCount() {
        return requestedCount;
    }

    /**
     * @param requestedCount the requestedCount to set
     */
    public void setRequestedCount(String requestedCount) {
        this.requestedCount = requestedCount;
    }

    /**
     * @return the sortCriteria
     */
    public String getSortCriteria() {
        return sortCriteria;
    }

    /**
     * @param sortCriteria the sortCriteria to set
     */
    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    public Service getService() {
        return this.service;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getServiceName() {
        return this.serviceName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    
    public String getActionName() {
        return this.actionName;
    }
}
