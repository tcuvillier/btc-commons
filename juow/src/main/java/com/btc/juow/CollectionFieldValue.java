package com.btc.juow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Descriptor(CollectionFieldDescriptor.class)
public abstract class CollectionFieldValue<C extends Collection<E>, E extends WorkingBean, D extends CollectionFieldDescriptor<Collection<?>>> extends ToManyFieldValue<C, E, D> {

	public abstract class CollectionProxy<E> implements Collection<E> {
		private Collection<E> delegate;
		protected CollectionTxState<E> state = new CollectionTxState<>();

		public CollectionProxy() {
		}

		public void setRecording(boolean onOff) {
			state.setRecording(onOff);
		}

		public boolean isRecording() {
			return state.isRecording();
		}
		public CollectionProxy(Collection<E> delegate) {
			this.delegate = delegate;
		}

		public boolean isModified() {
			return state.isModified();
		}

		public List<E> getAddedElements() {
			return state.getAddedElements();
		}

		public List<E> getRemovedElements() {
			return state.getRemovedElements();
		}

		public int size() {
			mustBeLoaded();
			return delegate.size();
		}

		public boolean isEmpty() {
			mustBeLoaded();
			return delegate.isEmpty();
		}

		public boolean contains(Object o) {
			mustBeLoaded();
			return delegate.contains(o);
		}

		public Iterator<E> iterator() {
			mustBeLoaded();
			return delegate.iterator();
		}

		public Object[] toArray() {
			mustBeLoaded();
			return delegate.toArray();
		}

		public <T> T[] toArray(T[] a) {
			mustBeLoaded();
			return delegate.toArray(a);
		}

		public boolean add(E e) {
			if( !isLoaded() ) load();
			if( delegate.add(e) ) {
				state.added(e);
				return true;
			} else
				return false;
		}

		public boolean remove(Object element) {
			if( !isLoaded() ) load();
			if( delegate.remove(element) ) {
				state.removed((E)element);
				return true;
			} else
				return false;
		}

		public boolean containsAll(Collection<?> c) {
			mustBeLoaded();
			return delegate.containsAll(c);
		}

		public boolean addAll(Collection<? extends E> elements) {
			if( !isLoaded() ) load();
			if( delegate.addAll(elements) ) {
				state.added(elements);
				return true;
			} else
				return false;
		}

		public boolean removeAll(Collection<?> elements) {
			if( !isLoaded() ) load();
			if( delegate.removeAll(elements) ) {
				state.removed((Collection<E>)elements);
				return true;
			} else
				return false;
		}

		public boolean retainAll(Collection<?> c) {
			if( !isLoaded() ) load();
			for(E element: delegate) {
				if( ! c.contains(element) ) 
					state.removed(element);
			}

			return delegate.retainAll(c);
		}

		public void clear() {
			if( !isLoaded() ) load();
			state.removed(delegate);
			delegate.clear();
		}

		public boolean equals(Object o) {
			return delegate.equals(o);
		}

		public int hashCode() {
			return delegate.hashCode();
		}

	}

	public CollectionFieldValue() {
		super();
	}

	public boolean remove(E element) {
		return getValue().remove(element);
	}

	public boolean add(E element) {
		return getValue().add(element);
	}

	public void load() {
		setValue(createCollection(null));
	}

	protected C createCollection(C content) {

		@SuppressWarnings("unchecked")
		C newCollection = (C)getDescriptor().createCollection(content);
		return newCollection;
	}

	public CollectionProxy<E> getValueProxy() {
		return (CollectionProxy<E>)getValue();
	}

	public void reset() {
		CollectionProxy<E> proxy = getValueProxy();
		List<E> added = new ArrayList<>(proxy.getAddedElements());
		List<E> removed = new ArrayList<>(proxy.getRemovedElements());
		for(E element: added) proxy.remove(element);
		for(E element: removed) proxy.add(element);
	}

	public int size() {
		return getValue().size();
	}
}
