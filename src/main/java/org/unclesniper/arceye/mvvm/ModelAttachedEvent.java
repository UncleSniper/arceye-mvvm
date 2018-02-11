package org.unclesniper.arceye.mvvm;

public class ModelAttachedEvent<ModelConstraintT> extends ModelAttachEvent<ModelConstraintT> {

	private final ModelConstraintT model;

	public ModelAttachedEvent(ModelConstraintT model) {
		this.model = model;
	}

	public ModelConstraintT getModel() {
		return model;
	}

}
