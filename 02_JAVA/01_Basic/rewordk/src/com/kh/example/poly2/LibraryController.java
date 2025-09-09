package com.kh.example.poly2;

public class LibraryController {
	private Member mem = null;
	private Book[] bList = new Book[5];
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("한번 더 해요", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
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
		int result = 0;
		Book book = bList[index];
		if(book != null) {
		if(book instanceof AniBook) {
			AniBook ani = (AniBook)book;
			if(ani != null && ani.getAccessAge() > mem.getAge()) {
				result = 1;
			}else {
				result = 0;
			}
		}
		else if (book instanceof CookBook){
			CookBook ck = (CookBook)book;
			if(ck != null && ck.isCoupon() == true) {
				mem.setCouponCount(mem.getCouponCount() + 1);
				result = 2;
			}
		}else {
			result = 0;
		}
		}
		return result;
	}
	
	//도서목록이 가득찼을경우 길이가+1 배열을 새로 생성하여 추가
	public void insertBook(Book b) {
		for(int i=0; i<bList.length; i++) {
			if(bList[i] == null) {
				bList[i] = b;
				return;
			}
		}
		//종료되지않음 -> 모든 bList의 index가 null이 아님
		Book[] newList = new Book[bList.length + 1];
		for(int i=0; i<bList.length; i++) { //bList를 전부 반복하며 newList에 넣기
			newList[i] = bList[i];
		}
		
		newList[newList.length - 1] = b;
		bList = newList;
		}
	
	public Book[] deleteBook(int index) {
		bList[index] = null;
		
		for(int i=index; i<bList.length; i++) {
			if(i == bList.length -1) {
				bList[i] = null;
			} else {
				bList[i] = bList[i+1];
			}
			
		}
		
		Book[] newList = new Book[bList.length - 1];
		for(int i=0; i<newList.length; i++) {
			newList[i] = bList[i];
		}
		bList = newList;
		return newList;
		/*Book delete = bList[index];
		if(delete != null) {
			delete = null;
			for(int i=0; i<bList.length; i++) {
				if(bList[i] == null) {
					Book[] newBook = new Book[bList.length - 1];
					newBook[i] = bList[i+1]; 
				}
			}
		}
		return newBook;
	*/}
	
}
