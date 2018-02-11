package org.unclesniper.arceye.mvvm;

public abstract class AbstractModelBridge<
	ModelConstraintT extends Model<? extends View<ModelConstraintT>>,
	IntermediateModelT extends Model<?>,
	ModelAdapterT
> implements ModelBridge<ModelConstraintT, ModelAdapterT> {

	private Class<IntermediateModelT> bridgeClass;

	public AbstractModelBridge(Class<IntermediateModelT> bridgeClass) {
		this.bridgeClass = bridgeClass;
	}

	public Class<IntermediateModelT> getBridgeClass() {
		return bridgeClass;
	}

	public void setBridgeClass(Class<IntermediateModelT> bridgeClass) {
		this.bridgeClass = bridgeClass;
	}

	public abstract ModelAdapterT wrapModelBridge(IntermediateModelT bridge);

	public ModelAdapterT bridgeModel(ModelConstraintT model) {
		IntermediateModelT bridge;
		if(bridgeClass.isInstance(model))
			bridge = bridgeClass.cast(model);
		else {
			bridge = model.adaptModel(bridgeClass);
			if(bridge == null)
				return null;
		}
		return wrapModelBridge(bridge);
	}

}
