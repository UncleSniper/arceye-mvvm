package org.unclesniper.arceye.mvvm;

public interface ViewAdapter {

	<T extends View<?>> T adaptView(Class<T> desiredView);

}
