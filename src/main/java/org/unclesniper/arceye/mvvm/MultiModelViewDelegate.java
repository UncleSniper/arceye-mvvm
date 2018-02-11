package org.unclesniper.arceye.mvvm;

public class MultiModelViewDelegate<
	ModelConstraintT extends Model<? extends View<ModelConstraintT>>,
	ModelAdapterT
> extends AbstractMultiModelViewDelegate<ModelConstraintT, ModelAdapterT> {

	private ViewAdapter viewAdapter;

	public MultiModelViewDelegate(Class<ModelAdapterT> adapterClass) {
		this(adapterClass, null, null);
	}

	public MultiModelViewDelegate(
		Class<ModelAdapterT> adapterClass,
		ModelBridge<ModelConstraintT, ModelAdapterT> modelBridge
	) {
		this(adapterClass, modelBridge, null);
	}

	public MultiModelViewDelegate(Class<ModelAdapterT> adapterClass, ViewAdapter viewAdapter) {
		this(adapterClass, null, viewAdapter);
	}

	public MultiModelViewDelegate(
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
