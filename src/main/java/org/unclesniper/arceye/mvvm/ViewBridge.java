package org.unclesniper.arceye.mvvm;

public interface ViewBridge<ViewConstraintT, ViewAdapterT> {

	ViewAdapterT bridgeView(ViewConstraintT view);

}
