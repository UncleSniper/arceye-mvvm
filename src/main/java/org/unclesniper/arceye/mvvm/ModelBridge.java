package org.unclesniper.arceye.mvvm;

public interface ModelBridge<ModelConstraintT, ModelAdapterT> {

	ModelAdapterT bridgeModel(ModelConstraintT model);

}
