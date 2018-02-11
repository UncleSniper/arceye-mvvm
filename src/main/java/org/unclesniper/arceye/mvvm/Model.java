package org.unclesniper.arceye.mvvm;

public interface Model<ViewConstraintT extends View<? extends Model<ViewConstraintT>>> extends ModelAdapter {

	boolean attachView(ViewConstraintT view);

	boolean detachView(ViewConstraintT view);

	Iterable<ViewConstraintT> getViews();

	boolean addViewAttachListener(ViewAttachListener<ViewConstraintT> listener);

	boolean removeViewAttachListener(ViewAttachListener<ViewConstraintT> listener);

}
