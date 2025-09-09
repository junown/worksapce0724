package com.kh.example.collection1;

import java.util.Objects;

public class Music {
	private String title;
	private String singer;
	
	public Music() {
	}

	public Music(String title, String singer) {
		super();
		this.title = title;
		this.singer = singer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return  singer + " - " + title;
	}

	@Override
	public int hashCode() { // 빠르게 비교
		return Objects.hash(title, singer);
	}

	@Override
	public boolean equals(Object obj) { // 비교하기 쉽게
		if(obj instanceof Music) {
			Music m = (Music)obj;
			return this.title.equals(m.getTitle()) && this.singer.equals(m.getSinger());
		}
		return false;
	}
	
	
	
	
}
