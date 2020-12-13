package edu.umb.cs680.hw12.sorting;

import java.util.Comparator;

import edu.umb.cs680.hw12.apfs.ApfsElement;

//Compare by name in reverse alphabetical order
public class ReverseAlphabeticalComparator<T> implements Comparator<ApfsElement> {
	public ReverseAlphabeticalComparator() {}
	@Override
	public int compare(ApfsElement o1, ApfsElement o2) {
		return o2.getName().compareTo(o1.getName());

	}

}
