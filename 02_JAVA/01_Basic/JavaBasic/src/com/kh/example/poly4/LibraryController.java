package com.kh.example.poly4;

public class LibraryController {
	private Member mem = null;
	private Book[] bList;
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[0] = new AniBook("아기공룡 뿌꾸", "미티", "원모어", 19);
		bList[0] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[0] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[0] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	public void insertMember(Member mem) {
		this.mem = mem;
	}
	
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	public Book[] searchBook(String keyword) {
		Book[] searchbook = new Book[bList.length];
		int count = 0;
		for(Book b : bList) {
			if(b != null && b.getTitle() != null && b.getTitle().contains(keyword)) {
				searchbook[count++] = b;
			}
		}
		return searchbook;
	}
	
	public int rentBook(int index) {
		Book book = bList[index];
		
		if(book instanceof AniBook) {
			AniBook ab = ((AniBook)book);
			if(mem != null && mem.getAge() < ab.getAccessAge()) {
				return 1;
			}
		}
		if(book instanceof CookBook) {
			CookBook ck = ((CookBook)book);
			if(ck.isCoupon()) {
				if(mem != null) {
					mem.setCouponCount(mem.getCouponCount() + 1);
					return 2;
				}
			}
		}
		return 0;
	}
}
