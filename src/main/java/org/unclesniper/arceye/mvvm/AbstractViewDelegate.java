package org.unclesniper.arceye.mvvm;

import org.unclesniper.arceye.utils.ListenerSet;

public abstract class AbstractViewDelegate<
	ModelConstraintT extends Model<? extends View<ModelConstraintT>>,
	ModelAdapterT
> implements View<ModelConstraintT> {

	protected final Class<ModelAdapterT> adapterClass;

	protected final ListenerSet<ModelAttachListener<ModelConstraintT>> modelAttachListeners = new ListenerSet<>();

	protected ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge;

	public AbstractViewDelegate(Class<ModelAdapterT> adapterClass) {
		this(adapterClass, null);
	}

	public AbstractViewDelegate(
		Class<ModelAdapterT> adapterClass,
		ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge
	) {
		this.adapterClass = adapterClass;
		this.modelBridge = modelBridge;
	}

	public Class<ModelAdapterT> getAdapterClass() {
		return adapterClass;
	}

	public ModelBridge<ModelConstraintT, ModelAdapterT> getModelBridge() {
		return modelBridge;
	}

	public void setModelBridge(ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge) {
		this.modelBridge = modelBridge;
	}

	public boolean addModelAttachListener(ModelAttachListener<ModelConstraintT> listener) {
		return modelAttachListeners.addListener(listener);
	}

	public boolean removeModelAttachListener(ModelAttachListener<ModelConstraintT> listener) {
		return modelAttachListeners.removeListener(listener);
	}

}
