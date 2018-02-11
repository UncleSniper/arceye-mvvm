package org.unclesniper.arceye.mvvm;

public interface View<ModelConstraintT extends Model<? extends View<ModelConstraintT>>> extends ViewAdapter {

	boolean attachModel(ModelConstraintT model);

	boolean detachModel(ModelConstraintT model);

	Iterable<ModelConstraintT> getModels();

	boolean addModelAttachListener(ModelAttachListener<ModelConstraintT> listener);

	boolean removeModelAttachListener(ModelAttachListener<ModelConstraintT> listener);

}
