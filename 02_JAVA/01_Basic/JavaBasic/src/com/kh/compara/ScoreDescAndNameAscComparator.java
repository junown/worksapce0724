package com.kh.compara;

import java.util.Comparator;

public class ScoreDescAndNameAscComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		int sort = o2.score - o1.score;
		if(sort == 0) {
			sort = o1.name.compareTo(o2.name);
		}
		return sort;
	}
	
	

}
