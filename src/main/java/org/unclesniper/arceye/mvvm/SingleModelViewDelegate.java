package org.unclesniper.arceye.mvvm;

public class SingleModelViewDelegate<
	ModelConstraintT extends Model<? extends View<ModelConstraintT>>,
	ModelAdapterT
> extends AbstractSingleModelViewDelegate<ModelConstraintT, ModelAdapterT> {

	private ViewAdapter viewAdapter;

	public SingleModelViewDelegate(Class<ModelAdapterT> adapterClass) {
		this(adapterClass, null, null);
	}

	public SingleModelViewDelegate(
		Class<ModelAdapterT> adapterClass,
		ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge
	) {
		this(adapterClass, modelBridge, null);
	}

	public SingleModelViewDelegate(Class<ModelAdapterT> adapterClass, ViewAdapter viewAdapter) {
		this(adapterClass, null, viewAdapter);
	}

	public SingleModelViewDelegate(
		Class<ModelAdapterT> adapterClass,
		ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge,
		ViewAdapter viewAdapter
	) {
		super(adapterClass, modelBridge);
		this.viewAdapter = viewAdapter;
	}

	public ViewAdapter getViewAdapter() {
		return viewAdapter;
	}

	public void setViewAdapter(ViewAdapter viewAdapter) {
		this.viewAdapter = viewAdapter;
	}

	public <T extends View<?>> T adaptView(Class<T> desiredView) {
		return viewAdapter == null ? null : viewAdapter.adaptView(desiredView);
	}

}
