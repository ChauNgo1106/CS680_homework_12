package edu.umb.cs680.hw12.sorting;

import java.util.Comparator;

import edu.umb.cs680.hw12.apfs.ApfsElement;
//Comparison based on their kinds
public class ElementKindsComparator<T> implements Comparator<ApfsElement> {
	public ElementKindsComparator() {}
	@Override
	public int compare(ApfsElement o1, ApfsElement o2) {
		return o1.getClass().toString().compareTo(o2.getClass().toString());

	}

}
