package org.unclesniper.arceye.mvvm;

public abstract class AbstractViewBridge<
	ViewConstraintT extends View<? extends Model<ViewConstraintT>>,
	IntermediateViewT extends View<?>,
	ViewAdapterT
> implements ViewBridge<ViewConstraintT, ViewAdapterT> {

	private Class<IntermediateViewT> bridgeClass;

	public AbstractViewBridge(Class<IntermediateViewT> bridgeClass) {
		this.bridgeClass = bridgeClass;
	}

	public Class<IntermediateViewT> getBridgeClass() {
		return bridgeClass;
	}

	public void setBridgeClass(Class<IntermediateViewT> bridgeClass) {
		this.bridgeClass = bridgeClass;
	}

	public abstract ViewAdapterT wrapViewBridge(IntermediateViewT bridge);

	public ViewAdapterT bridgeView(ViewConstraintT view) {
		IntermediateViewT bridge;
		if(bridgeClass.isInstance(view))
			bridge = bridgeClass.cast(view);
		else {
			bridge = view.adaptView(bridgeClass);
			if(bridge == null)
				return null;
		}
		return wrapViewBridge(bridge);
	}

}
