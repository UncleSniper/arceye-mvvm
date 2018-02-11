package org.unclesniper.arceye.mvvm;

import org.unclesniper.arceye.utils.EventFireClosure;

public interface ViewAttachListener<ViewConstraintT> {

	public static class ViewAttachedEventFireClosure<ViewConstraintT>
			implements EventFireClosure<ViewAttachListener<ViewConstraintT>, ViewAttachedEvent<ViewConstraintT>> {

		private final boolean heedConsume;

		public ViewAttachedEventFireClosure(boolean heedConsume) {
			this.heedConsume = heedConsume;
		}

		public void fireEventForListener(
			ViewAttachListener<ViewConstraintT> listener,
			ViewAttachedEvent<ViewConstraintT> event
		) {
			if(!heedConsume && !event.isConsumed())
				listener.viewAttached(event);
		}

	}

	public static class ViewDetachedEventFireClosure<ViewConstraintT>
			implements EventFireClosure<ViewAttachListener<ViewConstraintT>, ViewDetachedEvent<ViewConstraintT>> {

		private final boolean heedConsume;

		public ViewDetachedEventFireClosure(boolean heedConsume) {
			this.heedConsume = heedConsume;
		}

		public void fireEventForListener(
			ViewAttachListener<ViewConstraintT> listener,
			ViewDetachedEvent<ViewConstraintT> event
		) {
			if(!heedConsume && !event.isConsumed())
				listener.viewDetached(event);
		}

	}

	void viewAttached(ViewAttachedEvent<ViewConstraintT> event);

	void viewDetached(ViewDetachedEvent<ViewConstraintT> event);

}
