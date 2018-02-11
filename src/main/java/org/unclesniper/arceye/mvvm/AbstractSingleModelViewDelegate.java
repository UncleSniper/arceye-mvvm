package org.unclesniper.arceye.mvvm;

import org.unclesniper.arceye.utils.EmptyObjectIterable;
import org.unclesniper.arceye.utils.SingleObjectIterable;

public abstract class AbstractSingleModelViewDelegate<
	ModelConstraintT extends Model<? extends View<ModelConstraintT>>,
	ModelAdapterT
> extends AbstractViewDelegate<ModelConstraintT, ModelAdapterT> {

	private class SetModelUndoAction implements Runnable {

		private final ModelConstraintT originalModel;

		private final ModelAdapterT originalAdapter;

		public SetModelUndoAction(ModelConstraintT originalModel, ModelAdapterT originalAdapter) {
			this.originalModel = originalModel;
			this.originalAdapter = originalAdapter;
		}

		public void run() {
			theModel = originalModel;
			theModelAdapter = originalAdapter;
		}

	}

	private ModelConstraintT theModel;

	private ModelAdapterT theModelAdapter;

	public AbstractSingleModelViewDelegate(Class<ModelAdapterT> adapterClass) {
		this(adapterClass, null);
	}

	public AbstractSingleModelViewDelegate(
		Class<ModelAdapterT> adapterClass,
		ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge
	) {
		super(adapterClass, modelBridge);
	}

	public boolean attachModel(ModelConstraintT model) {
		if(model == null)
			return false;
		ModelConstraintT originalModel = theModel;
		ModelAdapterT originalAdapter = theModelAdapter;
		ModelAdapterT adapter;
		if(adapterClass.isInstance(model))
			adapter = adapterClass.cast(model);
		else {
			adapter = modelBridge == null ? null : modelBridge.bridgeModel(model);
			if(adapter == null)
				return false;
		}
		if(theModel != null) {
			theModel = null;
			theModelAdapter = null;
			modelAttachListeners.fireRevocableEvent(
				new ModelAttachListener.ModelDetachedEventFireClosure<ModelConstraintT>(true),
				new ModelDetachedEvent<ModelConstraintT>(originalModel),
				new SetModelUndoAction(originalModel, originalAdapter),
				new ModelAttachListener.ModelAttachedEventFireClosure<ModelConstraintT>(false),
				new ModelAttachedEvent<ModelConstraintT>(originalModel)
			);
		}
		theModel = model;
		theModelAdapter = adapter;
		modelAttachListeners.fireRevocableEvent(
			new ModelAttachListener.ModelAttachedEventFireClosure<ModelConstraintT>(true),
			new ModelAttachedEvent<ModelConstraintT>(model),
			new SetModelUndoAction(null, null),
			new ModelAttachListener.ModelDetachedEventFireClosure<ModelConstraintT>(false),
			new ModelDetachedEvent<ModelConstraintT>(model)
		);
		return true;
	}

	public boolean detachModel(ModelConstraintT model) {
		if(model == null || theModel != model)
			return false;
		ModelAdapterT originalAdapter = theModelAdapter;
		theModel = null;
		theModelAdapter = null;
		modelAttachListeners.fireRevocableEvent(
			new ModelAttachListener.ModelDetachedEventFireClosure<ModelConstraintT>(true),
			new ModelDetachedEvent<ModelConstraintT>(model),
			new SetModelUndoAction(model, originalAdapter),
			new ModelAttachListener.ModelAttachedEventFireClosure<ModelConstraintT>(false),
			new ModelAttachedEvent<ModelConstraintT>(model)
		);
		return true;
	}

	public Iterable<ModelConstraintT> getModels() {
		if(theModel == null)
			return new EmptyObjectIterable<ModelConstraintT>();
		else
			return new SingleObjectIterable<ModelConstraintT>(theModel);
	}

}
