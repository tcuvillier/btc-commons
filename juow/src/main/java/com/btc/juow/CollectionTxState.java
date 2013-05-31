package com.btc.juow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionTxState<E> {
	public enum Status {
		ADDED, REMOVED;
	}
	private Map<E, Status> statusMap = new HashMap<>();
	private boolean recording = true;

	public void setRecording(boolean onOff) {
		recording = onOff;
	}

	public boolean isRecording() {
		return recording;
	}

	
	public boolean isModified() {
		return statusMap == null || statusMap.size() != 0;
	}

	public void added(E element) {
		Status s = statusMap.get(element);
		if( s == null ) {
			statusMap.put(element, Status.ADDED);
		} else {
			switch(s) {
			case REMOVED:
				statusMap.remove(element);	// Added back
				break;
			case ADDED:
				break;	// Added twice ? do nothing
			default:
				throw new RuntimeException("Impossible case");
			}
		}
	}

	public void added(Collection<?  extends E> elements) {
		for(E element: elements) {
			added(element);
		}
	}

	public void removed(Collection<E> elements) {
		for(E element: elements) {
			removed(element);
		}
	}

	public void removed(E element) {
		Status s = statusMap.get(element);
		if( s == null ) {
			statusMap.put(element, Status.REMOVED);
		} else {
			switch(s) {
			case REMOVED:
				break;	// Already removed....
			case ADDED:
				statusMap.remove(element);
				break;	// Added and then removed
			default:
				throw new RuntimeException("Impossible case");
			}
		}
		
	}

	public List<E> getAddedElements() {
		return getElementsByStatus(Status.ADDED);
	}

	public List<E> getRemovedElements() {
		return getElementsByStatus(Status.REMOVED);
	}

	protected List<E> getElementsByStatus(Status status) {
		List<E> result = new ArrayList<>();
		for(Map.Entry<E, Status> entry: statusMap.entrySet()) {
			if( entry.getValue() == status ) result.add(entry.getKey());
		}

		return result;
	}

	public void clear() {
		statusMap.clear();
	}
}
