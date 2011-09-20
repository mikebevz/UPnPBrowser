/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import android.os.AsyncTask;
import com.mikebevz.upnp.mediaserver.content_directory.Container;
import com.mikebevz.upnp.mediaserver.content_directory.SaxContentParser;
import java.util.List;
import org.cybergarage.upnp.Action;

/**
 *
 * @author mikebevz
 */
public class BrowseTask extends AsyncTask<Action, Void, List<Container>> implements TaskFactoryTask {

    private OnTaskFactory delegate;
    private Action action;
    private String objectId = "";
    private String browseFlag = "BrowseDirectChildren";
    private String Filter = "*";
    private String startingIndex = "0";
    private String requestedCount = "10";
    private String sortCriteria = "";

    @Override
    protected List<Container> doInBackground(Action... actions) {
        setAction(actions[0]);//device.getService(TaskFactory.CONTENT_DIRECTORY_SERVICE).getAction(TaskFactory.BROWSE_ACTION);
        getAction().setArgumentValue("ObjectID", getObjectID());
        getAction().setArgumentValue("BrowseFlag", getBrowseFlag());
        getAction().setArgumentValue("Filter", getFilter());
        getAction().setArgumentValue("StartingIndex", getStartingIndex());
        getAction().setArgumentValue("RequestedCount", getRequestedCount());
        getAction().setArgumentValue("SortCriteria", getSortCriteria());

        SaxContentParser parser = new SaxContentParser();

        if (getAction().postControlAction() == true) {
            //ArgumentList outArgList = action.getOutputArgumentList();

            String result = getAction().getArgument("Result").getValue();
            //String numberReturned = action.getArgument("NumberReturned").getValue();
            //String totalMatches = action.getArgument("TotalMatches").getValue();
            //String updateID = action.getArgument("UpdateID").getValue();
            System.out.println(result);
            parser.setMessage(result);


        }

        
        List<Container> containers = parser.parse();
            
        return containers;
    }
    
    public void setObjectID(String objectId) {
        this.setObjectId(objectId);
    }
    
    public String getObjectID() {
        return this.getObjectId();
    }

    @Override
    protected void onPostExecute(List<Container> result) {
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
}
