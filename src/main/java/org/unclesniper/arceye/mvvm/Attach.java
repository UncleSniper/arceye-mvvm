package org.unclesniper.arceye.mvvm;

public class Attach {

	public enum Result {
		OK,
		MODEL_REJECTED_VIEW,
		VIEW_REJECTED_MODEL
	}

	public static <
		ModelConstraintT extends Model<ViewConstraintT>,
		ViewConstraintT extends View<ModelConstraintT>
	> Result attach(ModelConstraintT model, ViewConstraintT view) {
		if(!model.attachView(view))
			return Result.MODEL_REJECTED_VIEW;
		boolean commit = false;
		try {
			if(!view.attachModel(model))
				return Result.VIEW_REJECTED_MODEL;
			commit = true;
		}
		finally {
			if(!commit)
				model.detachView(view);
		}
		return Result.OK;
	}

}
