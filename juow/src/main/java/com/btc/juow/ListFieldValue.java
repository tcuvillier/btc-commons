package com.btc.juow;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

@Descriptor(ListFieldDescriptor.class)
public class ListFieldValue<E extends WorkingBean> extends CollectionFieldValue<List<E>, E, CollectionFieldDescriptor<Collection<?>>> {

	public class ListProxy<E> extends CollectionProxy<E> implements List<E> {
		private List<E> delegate;

		public ListProxy(List<E> delegate) {
			super(delegate);
			
			this.delegate = delegate;
		}

		public boolean addAll(int index, Collection<? extends E> c) {
			if( !isLoaded() ) load();
			return delegate.addAll(index, c);
		}

		public E get(int index) {
			mustBeLoaded();
			return delegate.get(index);
		}

		public E set(int index, E element) {
			if( !isLoaded() ) load();
			E old = delegate.set(index, element);

			if( old != element ) {
				if( old != null ) state.removed(old);
				state.added(element);
			}

			return old;
		}

		public void add(int index, E element) {
			if( !isLoaded() ) load();
			delegate.add(index, element);
			if( delegate.contains(element) ) state.added(element);
		}

		public E remove(int index) {
			if( !isLoaded() ) load();
			E element = delegate.remove(index);
			state.removed(element);
			return element;
		}

		public int indexOf(Object o) {
			mustBeLoaded();
			return delegate.indexOf(o);
		}

		public int lastIndexOf(Object o) {
			mustBeLoaded();
			return delegate.lastIndexOf(o);
		}

		public ListIterator<E> listIterator() {
			mustBeLoaded();
			return delegate.listIterator();
		}

		public ListIterator<E> listIterator(int index) {
			mustBeLoaded();
			return delegate.listIterator(index);
		}

		public List<E> subList(int fromIndex, int toIndex) {
			mustBeLoaded();
			return delegate.subList(fromIndex, toIndex);
		}
	}
	public ListFieldValue() {
		super();
	}

	public void setValue(List<E> collection) {
		super.setValue( new ListProxy<E>(createCollection(collection)));
	}
}
