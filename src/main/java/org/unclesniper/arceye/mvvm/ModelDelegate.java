package org.unclesniper.arceye.mvvm;

public class ModelDelegate<
	ViewConstraintT extends View<? extends Model<ViewConstraintT>>,
	ViewAdapterT
> extends AbstractModelDelegate<ViewConstraintT, ViewAdapterT> {

	private ModelAdapter modelAdapter;

	public ModelDelegate(Class<ViewAdapterT> adapterClass) {
		this(adapterClass, null, null);
	}

	public ModelDelegate(Class<ViewAdapterT> adapterClass, ViewBridge<ViewConstraintT, ViewAdapterT> viewBridge) {
		this(adapterClass, viewBridge, null);
	}

	public ModelDelegate(Class<ViewAdapterT> adapterClass, ModelAdapter modelAdapter) {
		this(adapterClass, null, modelAdapter);
	}

	public ModelDelegate(
		Class<ViewAdapterT> adapterClass,
		ViewBridge<ViewConstraintT, ViewAdapterT> viewBridge,
		ModelAdapter modelAdapter
	) {
		super(adapterClass, viewBridge);
		this.modelAdapter = modelAdapter;
	}

	public ModelAdapter getModelAdapter() {
		return modelAdapter;
	}

	public void setModelAdapter(ModelAdapter modelAdapter) {
		this.modelAdapter = modelAdapter;
	}

	public <T extends Model<?>> T adaptModel(Class<T> desiredModel) {
		return modelAdapter == null ? null : modelAdapter.adaptModel(desiredModel);
	}

}
