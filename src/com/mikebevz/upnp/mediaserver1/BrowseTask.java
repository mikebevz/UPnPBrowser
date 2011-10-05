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
    res.setResult(getAction().getArgument("Result").getValue());
    res.setNumberReturned(getAction().getArgument("NumberReturned").getValue());
    res.setTotalMatches(getAction().getArgument("TotalMatches").getValue());
    res.setUpdateID(getAction().getArgument("UpdateID").getValue());
    Log.d("XML Parsing Result", res.getResult());
    parser.setMessage(res.getResult());
  }

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


OnTaskFactory getDelegate() {
  return delegate;
}


void setDelegate(OnTaskFactory delegate) {
  this.delegate = delegate;
}


Action getAction() {
  return action;
}


void setAction(Action action) {
  this.action = action;
}


String getObjectId() {
  return objectId;
}


public void setObjectId(String objectId) {
  this.objectId = objectId;
}


String getBrowseFlag() {
  return browseFlag;
}


public void setBrowseFlag(String browseFlag) {
  this.browseFlag = browseFlag;
}


String getFilter() {
  return Filter;
}


public void setFilter(String Filter) {
  this.Filter = Filter;
}


String getStartingIndex() {
  return startingIndex;
}


public void setStartingIndex(String startingIndex) {
  this.startingIndex = startingIndex;
}

String getRequestedCount() {
  return requestedCount;
}

public void setRequestedCount(String requestedCount) {
  this.requestedCount = requestedCount;
}

String getSortCriteria() {
  return sortCriteria;
}

public void setSortCriteria(String sortCriteria) {
  this.sortCriteria = sortCriteria;
}

Device getDevice() {
  return device;
}

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
