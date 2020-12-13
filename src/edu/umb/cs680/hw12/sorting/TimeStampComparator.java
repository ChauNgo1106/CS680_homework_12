package edu.umb.cs680.hw12.sorting;

import java.util.Comparator;

import edu.umb.cs680.hw12.apfs.ApfsElement;
//Compare by time based reverse alphabetical order
public class TimeStampComparator<T> implements Comparator<ApfsElement> {
	public TimeStampComparator() {}
	@Override
	public int compare(ApfsElement o1, ApfsElement o2) {
		return o2.getLastModified().toString().compareTo(o1.getLastModified().toString());

	}

}
