package com.kh.example.gearrent;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
	private String itemId; //장비 Id
	private String memberId; //회원 id
	private LocalDate loanDate; //대여일
	private LocalDate dueDate; //반납 예정일
	private LocalDate returnedDate; //실제 반납일
	
	public Loan(String itemId, String memberId, LocalDate loanDate, LocalDate dueDate) {
		super();
		this.itemId = itemId;
		this.memberId = memberId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
	}
	
	public boolean isOverdue(LocalDate today) {
		
		//계산기준일(반납날짜 또는 오늘)
		LocalDate day = (returnedDate != null) ? returnedDate : today;
		
		//d기준일이 마감일(duDate)보다 늦으면 연체 발생
		return day.isAfter(dueDate);
	}
	
	public int overdueDays(LocalDate today) {
		//계산기준일(반납날짜 또는 오늘)
		LocalDate day = (returnedDate != null) ? returnedDate : today;
		
		if(day.isAfter(dueDate)) {
			long days = ChronoUnit.DAYS.between(dueDate, day);
			
			return (int)Math.abs(days);
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Loan [itemId=" + itemId + ", memberId=" + memberId + ", loanDate=" + loanDate + ", dueDate=" + dueDate
				+ ", returnedDate=" + returnedDate + ", toString()=" + super.toString() + "]";
	}

	public void setReturendDate(LocalDate today) {
		// TODO Auto-generated method stub
		
	}

	public String getDueDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
