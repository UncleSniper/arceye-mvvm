package org.unclesniper.arceye.mvvm;

public interface ModelAdapter {

	<T extends Model<?>> T adaptModel(Class<T> desiredModel);

}
