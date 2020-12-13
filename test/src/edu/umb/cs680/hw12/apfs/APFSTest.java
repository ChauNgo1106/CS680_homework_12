package edu.umb.cs680.hw12.apfs;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw12.sorting.AlphabeticalComparator;
import edu.umb.cs680.hw12.sorting.ElementKindsComparator;
import edu.umb.cs680.hw12.sorting.ReverseAlphabeticalComparator;
import edu.umb.cs680.hw12.sorting.TimeStampComparator;

class APFSTest {

	// setting up object
	private static ApfsDirectory root;
	private static ApfsDirectory home;
	private static ApfsDirectory applications;
	private static ApfsDirectory code;
	private static ApfsFile a;
	private static ApfsFile b;
	private static ApfsFile c;
	private static ApfsFile d;
	private static ApfsFile e;
	private static ApfsFile f;
	private static ApfsLink x;
	private static ApfsLink y;

	@BeforeAll
	public static void setUp() {
		// initialize APFS file system
		APFS.getFileSystem().initFileSystemAPFS("APFS", 10240);

		// assign root in APFS system into root variable
		root = APFS.getFileSystem().getRootDir();

		applications = new ApfsDirectory(root, "applications", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 00, 40)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 00, 40)));

		home = new ApfsDirectory(root, "home", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(20, 30, 00)),
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(20, 30, 00)));
		code = new ApfsDirectory(home, "code", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 31, 00)),
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 31, 00)));

		a = new ApfsFile(applications, "a", 2048, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 01, 00)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 01, 00)));
		b = new ApfsFile(applications, "b", 1024, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 10, 00)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 10, 00)));

		c = new ApfsFile(home, "c", 2048, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(21, 32, 45)),
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(21, 32, 45)));
		d = new ApfsFile(home, "d", 4096, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(21, 45, 18)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(21, 45, 18)));

		e = new ApfsFile(code, "e", 1024, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 40, 59)),
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 40, 59)));
		f = new ApfsFile(code, "f", 2048, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 45, 51)),
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 45, 51)));
		// symbolic Link
		x = new ApfsLink(home, "x", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 17), LocalTime.of(20, 46, 00)),
				LocalDateTime.of(LocalDate.of(2019, 11, 17), LocalTime.of(20, 46, 00)), applications);
		y = new ApfsLink(code, "y", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 16), LocalTime.of(20, 39, 00)),
				LocalDateTime.of(LocalDate.of(2019, 9, 16), LocalTime.of(20, 39, 00)), b);

		applications.appendChild(a);
		applications.appendChild(b);

		root.appendChild(applications);
		root.appendChild(home);

		home.appendChild(code);
		home.appendChild(c);
		home.appendChild(d);
		home.appendChild(x);

		code.appendChild(e);
		code.appendChild(f);
		code.appendChild(y);

	}

	// function in purpose of converting the list of ApfsElements into a String by
	// name.
	public String convert(LinkedList<ApfsElement> children) {
		ArrayList<String> result = new ArrayList<String>();
		for (ApfsElement child : children) {
			result.add(child.getName());
		}
		return result.toString();
	}
	
	// 16 test cases in total

	////////////////////////// Alphabetical
	@Test
	public void arrange_the_children_of_home_by_alphabetical() {
		String expected = "[c, code, d, x]";
		assertEquals(expected, convert(home.getChildren(new AlphabeticalComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_files_of_home_by_alphabetical() {
		String expected = "[c, d, x]";
		assertEquals(expected, convert(home.getFiles(new AlphabeticalComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_subDirectories_of_root_by_alphabetical() {
		String expected = "[applications, home]";
		assertEquals(expected, convert(root.getSubDirectories(new AlphabeticalComparator<ApfsElement>())));
	}

	////////////////////////// Reverse alphabetical order
	@Test
	public void arrange_the_children_of_applications_by_reverse_alphabetical() {
		String expected = "[b, a]";
		assertEquals(expected, convert(applications.getChildren(new ReverseAlphabeticalComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_files_of_code_by_reverse_alphabetical() {
		String expected = "[y, f, e]";
		assertEquals(expected, convert(code.getFiles(new ReverseAlphabeticalComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_subDirectories_of_root_by_reverse_alphabetical() {
		String expected = "[home, applications]";
		assertEquals(expected, convert(root.getSubDirectories(new ReverseAlphabeticalComparator<ApfsElement>())));
	}

	////////////////////////// Time Stamp based on lastModified ==> recently
	////////////////////////// modified will come first.
	@Test
	public void arrange_the_children_of_home_by_timestamp() {
		String expected = "[x, code, c, d]";
		assertEquals(expected, convert(home.getChildren(new TimeStampComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_files_of_applications_by_timestamp() {
		String expected = "[b, a]";
		assertEquals(expected, convert(applications.getFiles(new TimeStampComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_subDirectories_of_root_by_timestamp() {
		String expected = "[home, applications]";
		assertEquals(expected, convert(root.getSubDirectories(new TimeStampComparator<ApfsElement>())));
	}

	////////////////////////// Kinds of elements by following scheme: directories ->
	////////////////////////// files -> links
	@Test
	public void arrange_the_children_of_home_by_kinds() {
		String expected = "[code, c, d, x]";
		assertEquals(expected, convert(home.getChildren(new ElementKindsComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_files_of_code_by_kinds() {
		String expected = "[e, f, y]";
		assertEquals(expected, convert(code.getFiles(new ElementKindsComparator<ApfsElement>())));
	}

	@Test
	public void arrange_the_subDirectories_of_root_by_kinds() {
		String expected = "[applications, home]";
		assertEquals(expected, convert(root.getSubDirectories(new ElementKindsComparator<ApfsElement>())));
	}

}
