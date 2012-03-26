package org.jgel.graphics.gui;

public interface ItemControl<T>
{
	public boolean addItem(T item);
	public boolean addItem(T item, int index);
	public boolean removeItem(T item);
	public boolean removeFirstItem(T item);
	public boolean setItems(T[] item);
	public T[] getItems();
}
