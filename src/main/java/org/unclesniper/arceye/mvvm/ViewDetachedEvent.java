package org.unclesniper.arceye.mvvm;

public class ViewDetachedEvent<ViewConstraintT> extends ViewAttachEvent<ViewConstraintT> {

	private final ViewConstraintT view;

	public ViewDetachedEvent(ViewConstraintT view) {
		this.view = view;
	}

	public ViewConstraintT getView() {
		return view;
	}

}
