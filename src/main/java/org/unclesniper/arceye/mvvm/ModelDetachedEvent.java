package org.unclesniper.arceye.mvvm;

public class ModelDetachedEvent<ModelConstraintT> extends ModelAttachEvent<ModelConstraintT> {

	private final ModelConstraintT model;

	public ModelDetachedEvent(ModelConstraintT model) {
		this.model = model;
	}

	public ModelConstraintT getModel() {
		return model;
	}

}
