package com.kh.jdbc.count;

public class DeptCount {
	private String deptTitle;
	private int count;
	
	public DeptCount() {
		super();
	}

	public DeptCount(String deptTitle, int count) {
		super();
		this.deptTitle = deptTitle;
		this.count = count;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	 @Override
	    public String toString() {
	        return deptTitle + " / 인원 : " + count;
	    }
}
