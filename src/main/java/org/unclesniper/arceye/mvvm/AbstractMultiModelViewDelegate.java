package org.unclesniper.arceye.mvvm;

import java.util.IdentityHashMap;

public abstract class AbstractMultiModelViewDelegate<
	ModelConstraintT extends Model<? extends View<ModelConstraintT>>,
	ModelAdapterT
> extends AbstractViewDelegate<ModelConstraintT, ModelAdapterT> {

	private class RemoveModelUndoAction implements Runnable {

		private final ModelConstraintT model;

		public RemoveModelUndoAction(ModelConstraintT model) {
			this.model = model;
		}

		public void run() {
			models.remove(model);
		}

	}

	private class PutModelUndoAction implements Runnable {

		private final ModelConstraintT model;

		private final ModelAdapterT adapter;

		public PutModelUndoAction(ModelConstraintT model, ModelAdapterT adapter) {
			this.model = model;
			this.adapter = adapter;
		}

		public void run() {
			models.put(model, adapter);
		}

	}

	private final IdentityHashMap<ModelConstraintT, ModelAdapterT> models = new IdentityHashMap<>();

	public AbstractMultiModelViewDelegate(Class<ModelAdapterT> adapterClass) {
		this(adapterClass, null);
	}

	public AbstractMultiModelViewDelegate(
		Class<ModelAdapterT> adapterClass,
		ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge
	) {
		super(adapterClass, modelBridge);
	}

	public boolean attachModel(ModelConstraintT model) {
		if(model == null)
			return false;
		if(adapterClass.isInstance(model))
			models.put(model, adapterClass.cast(model));
		else {
			ModelAdapterT adapter = modelBridge == null ? null : modelBridge.bridgeModel(model);
			if(adapter == null)
				return false;
			models.put(model, adapter);
		}
		modelAttachListeners.fireRevocableEvent(
			new ModelAttachListener.ModelAttachedEventFireClosure<ModelConstraintT>(true),
			new ModelAttachedEvent<ModelConstraintT>(model),
			new RemoveModelUndoAction(model),
			new ModelAttachListener.ModelDetachedEventFireClosure<ModelConstraintT>(false),
			new ModelDetachedEvent<ModelConstraintT>(model)
		);
		return true;
	}

	public boolean detachModel(ModelConstraintT model) {
		if(model == null || !models.containsKey(model))
			return false;
		ModelAdapterT adapter = models.remove(model);
		modelAttachListeners.fireRevocableEvent(
			new ModelAttachListener.ModelDetachedEventFireClosure<ModelConstraintT>(true),
			new ModelDetachedEvent<ModelConstraintT>(model),
			new PutModelUndoAction(model, adapter),
			new ModelAttachListener.ModelAttachedEventFireClosure<ModelConstraintT>(false),
			new ModelAttachedEvent<ModelConstraintT>(model)
		);
		return true;
	}

	public Iterable<ModelConstraintT> getModels() {
		return models.keySet();
	}

}
