package org.unclesniper.arceye.mvvm;

import java.util.Set;
import java.util.HashSet;

public class MultiViewBridge<ViewConstraintT, ViewAdapterT> implements ViewBridge<ViewConstraintT, ViewAdapterT> {

	private final Set<ViewBridge<ViewConstraintT, ViewAdapterT>> bridges = new HashSet<>();

	public MultiViewBridge() {}

	public boolean addViewBridge(ViewBridge<ViewConstraintT, ViewAdapterT> bridge) {
		return bridge != null && bridges.add(bridge);
	}

	public boolean removeViewBridge(ViewBridge<ViewConstraintT, ViewAdapterT> bridge) {
		return bridge != null && bridges.remove(bridge);
	}

	public ViewAdapterT bridgeView(ViewConstraintT view) {
		for(ViewBridge<ViewConstraintT, ViewAdapterT> bridge : bridges) {
			ViewAdapterT adapter = bridge.bridgeView(view);
			if(adapter != null)
				return adapter;
		}
		return null;
	}

}
