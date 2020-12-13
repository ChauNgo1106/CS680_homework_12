package edu.umb.cs680.hw12.sorting;

import java.util.Comparator;

import edu.umb.cs680.hw12.apfs.ApfsElement;
//Compare by name in alphabetical order
public class AlphabeticalComparator<T> implements Comparator<ApfsElement> {
	
	public AlphabeticalComparator() {}
	@Override
	public int compare(ApfsElement o1, ApfsElement o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
