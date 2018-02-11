package org.unclesniper.arceye.mvvm;

import java.util.Set;
import java.util.HashSet;

public class MultiModelBridge<ModelConstraintT, ModelAdapterT>
		implements ModelBridge<ModelConstraintT, ModelAdapterT> {

	private final Set<ModelBridge<ModelConstraintT, ModelAdapterT>> bridges = new HashSet<>();

	public boolean addModelBridge(ModelBridge<ModelConstraintT, ModelAdapterT> bridge) {
		return bridge != null && bridges.add(bridge);
	}

	public boolean removeModelBridge(ModelBridge<ModelConstraintT, ModelAdapterT> bridge) {
		return bridge != null && bridges.remove(bridge);
	}

	public ModelAdapterT bridgeModel(ModelConstraintT model) {
		for(ModelBridge<ModelConstraintT, ModelAdapterT> bridge : bridges) {
			ModelAdapterT adapter = bridge.bridgeModel(model);
			if(adapter != null)
				return adapter;
		}
		return null;
	}

}
