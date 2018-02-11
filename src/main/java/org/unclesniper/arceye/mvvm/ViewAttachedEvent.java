package org.unclesniper.arceye.mvvm;

public class ViewAttachedEvent<ViewConstraintT> extends ViewAttachEvent<ViewConstraintT> {

	private final ViewConstraintT view;

	public ViewAttachedEvent(ViewConstraintT view) {
		this.view = view;
	}

	public ViewConstraintT getView() {
		return view;
	}

}
