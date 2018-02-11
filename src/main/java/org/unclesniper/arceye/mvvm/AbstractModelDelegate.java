package org.unclesniper.arceye.mvvm;

import java.util.IdentityHashMap;
import org.unclesniper.arceye.utils.ListenerSet;

public abstract class AbstractModelDelegate<
	ViewConstraintT extends View<? extends Model<ViewConstraintT>>,
	ViewAdapterT
> implements Model<ViewConstraintT> {

	private class RemoveViewUndoAction implements Runnable {

		private final ViewConstraintT view;

		public RemoveViewUndoAction(ViewConstraintT view) {
			this.view = view;
		}

		public void run() {
			views.remove(view);
		}

	}

	private class PutViewUndoAction implements Runnable {

		private final ViewConstraintT view;

		private final ViewAdapterT adapter;

		public PutViewUndoAction(ViewConstraintT view, ViewAdapterT adapter) {
			this.view = view;
			this.adapter = adapter;
		}

		public void run() {
			views.put(view, adapter);
		}

	}

	private final Class<ViewAdapterT> adapterClass;

	private final IdentityHashMap<ViewConstraintT, ViewAdapterT> views = new IdentityHashMap<>();

	private final ListenerSet<ViewAttachListener<ViewConstraintT>> viewAttachListeners = new ListenerSet<>();

	private ViewBridge<ViewConstraintT, ViewAdapterT> viewBridge;

	public AbstractModelDelegate(Class<ViewAdapterT> adapterClass) {
		this(adapterClass, null);
	}

	public AbstractModelDelegate(
		Class<ViewAdapterT> adapterClass,
		ViewBridge<ViewConstraintT, ViewAdapterT> viewBridge
	) {
		this.adapterClass = adapterClass;
		this.viewBridge = viewBridge;
	}

	public Class<ViewAdapterT> getAdapterClass() {
		return adapterClass;
	}

	public ViewBridge<ViewConstraintT, ViewAdapterT> getViewBridge() {
		return viewBridge;
	}

	public void setViewBridge(ViewBridge<ViewConstraintT, ViewAdapterT> viewBridge) {
		this.viewBridge = viewBridge;
	}

	public boolean attachView(ViewConstraintT view) {
		if(view == null)
			return false;
		if(adapterClass.isInstance(view))
			views.put(view, adapterClass.cast(view));
		else {
			ViewAdapterT adapter = viewBridge == null ? null : viewBridge.bridgeView(view);
			if(adapter == null)
				return false;
			views.put(view, adapter);
		}
		viewAttachListeners.fireRevocableEvent(
			new ViewAttachListener.ViewAttachedEventFireClosure<ViewConstraintT>(true),
			new ViewAttachedEvent<ViewConstraintT>(view),
			new RemoveViewUndoAction(view),
			new ViewAttachListener.ViewDetachedEventFireClosure<ViewConstraintT>(false),
			new ViewDetachedEvent<ViewConstraintT>(view)
		);
		return true;
	}

	public boolean detachView(ViewConstraintT view) {
		if(view == null || !views.containsKey(view))
			return false;
		ViewAdapterT adapter = views.remove(view);
		viewAttachListeners.fireRevocableEvent(
			new ViewAttachListener.ViewDetachedEventFireClosure<ViewConstraintT>(true),
			new ViewDetachedEvent<ViewConstraintT>(view),
			new PutViewUndoAction(view, adapter),
			new ViewAttachListener.ViewAttachedEventFireClosure<ViewConstraintT>(false),
			new ViewAttachedEvent<ViewConstraintT>(view)
		);
		return true;
	}

	public Iterable<ViewConstraintT> getViews() {
		return views.keySet();
	}

	public boolean addViewAttachListener(ViewAttachListener<ViewConstraintT> listener) {
		return viewAttachListeners.addListener(listener);
	}

	public boolean removeViewAttachListener(ViewAttachListener<ViewConstraintT> listener) {
		return viewAttachListeners.removeListener(listener);
	}

}
