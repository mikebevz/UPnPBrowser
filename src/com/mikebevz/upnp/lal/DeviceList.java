package com.mikebevz.upnp.lal;

import java.util.ArrayList;

public class DeviceList extends ArrayList<Device> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4385292342053410933L;

	public DeviceList() {
	}

	public Device getDevice(int pos) {

		return get(pos);

	}
}
