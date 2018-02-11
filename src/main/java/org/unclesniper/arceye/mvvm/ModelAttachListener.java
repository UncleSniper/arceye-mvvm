package org.unclesniper.arceye.mvvm;

import org.unclesniper.arceye.utils.EventFireClosure;

public interface ModelAttachListener<ModelConstraintT> {

	public static class ModelAttachedEventFireClosure<ModelConstraintT>
			implements EventFireClosure<
				ModelAttachListener<ModelConstraintT>,
				ModelAttachedEvent<ModelConstraintT>
			> {

		private final boolean heedConsume;

		public ModelAttachedEventFireClosure(boolean heedConsume) {
			this.heedConsume = heedConsume;
		}

		public void fireEventForListener(
			ModelAttachListener<ModelConstraintT> listener,
			ModelAttachedEvent<ModelConstraintT> event
		) {
			if(!heedConsume && !event.isConsumed())
				listener.modelAttached(event);
		}

	}

	public static class ModelDetachedEventFireClosure<ModelConstraintT>
			implements EventFireClosure<
				ModelAttachListener<ModelConstraintT>,
				ModelDetachedEvent<ModelConstraintT>
			> {

		private final boolean heedConsume;

		public ModelDetachedEventFireClosure(boolean heedConsume) {
			this.heedConsume = heedConsume;
		}

		public void fireEventForListener(
			ModelAttachListener<ModelConstraintT> listener,
			ModelDetachedEvent<ModelConstraintT> event
		) {
			if(!heedConsume && !event.isConsumed())
				listener.modelDetached(event);
		}

	}

	void modelAttached(ModelAttachedEvent<ModelConstraintT> event);

	void modelDetached(ModelDetachedEvent<ModelConstraintT> event);

}
