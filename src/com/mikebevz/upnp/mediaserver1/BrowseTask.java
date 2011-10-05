package com.mikebevz.upnp.mediaserver1;

import android.os.AsyncTask;
import android.util.Log;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;

/**
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

  if (getAction().postControlAction()) {
    //ArgumentList outArgList = action.getOutputArgumentList();


    res.setResult(getAction().getArgument("Result").getValue());
    res.setNumberReturned(getAction().getArgument("NumberReturned").getValue());
    res.setTotalMatches(getAction().getArgument("TotalMatches").getValue());
    res.setUpdateID(getAction().getArgument("UpdateID").getValue());

    //String result = ;
    //String numberReturned = action.getArgument("NumberReturned").getValue();
    //String totalMatches = action.getArgument("TotalMatches").getValue();
    //String updateID = action.getArgument("UpdateID").getValue();
    Log.d("XML Parsing Result", res.getResult());
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
OnTaskFactory getDelegate() {
  return delegate;
}

/**
 * @param delegate the delegate to set
 */
void setDelegate(OnTaskFactory delegate) {
  this.delegate = delegate;
}

/**
 * @return the action
 */
Action getAction() {
  return action;
}

/**
 * @param action the action to set
 */
void setAction(Action action) {
  this.action = action;
}

/**
 * @return the objectId
 */
String getObjectId() {
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
String getBrowseFlag() {
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
String getFilter() {
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
String getStartingIndex() {
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
String getRequestedCount() {
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
String getSortCriteria() {
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
Device getDevice() {
  return device;
}

/**
 * @param device the device to set
 */
void setDevice(Device device) {
  this.device = device;
}

public void setService(Service service) {
  this.service = service;
}

public Service getService() {
  return this.service;
}

void setServiceName(String serviceName) {
  this.serviceName = serviceName;
}

String getServiceName() {
  return this.serviceName;
}

void setActionName(String actionName) {
  this.actionName = actionName;
}

String getActionName() {
  return this.actionName;
}
}
