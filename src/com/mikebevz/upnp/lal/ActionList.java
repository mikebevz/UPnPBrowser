package com.mikebevz.upnp.lal;

import java.util.ArrayList;
import java.util.List;

public class ActionList extends ArrayList<Action> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2727721468423176680L;
	private List<Action> actions;

	public ActionList() {
	}

	public Action getAction(int pos) {

		return get(pos);

	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

}
