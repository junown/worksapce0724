package com.kh.example.gearrent;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GearRentMenu {
	public GearRentController gc = new GearRentController();
	public Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		while(true) {
			System.out.println("=== KH 장비 대여 관리 ===");
			System.out.println("1) 장비등록");
			System.out.println("2) 회원등록");
			System.out.println("3) 대여");
			System.out.println("4) 반납");
			System.out.println("5) 태그검색");
			System.out.println("6) 키워드검색");
			System.out.println("7) 전체장비");
			System.out.println("8) 대여중목록");
			System.out.println("9) 종료");
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				addDevice();
				break;
			case 2:
				addMember();
				break;
			case 3:
				borrow();
				break;
			case 4:
				returnItem();
				break;
			case 5:
				findByTag();
				break;
			case 6:
				findByKeyword();
				break;
			case 7:
				printAllDevices();
				break;
			case 8:
				printActiveLoans();
				break;
			case 9:
				System.out.println("종료");
				return;
			default :
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
					
			}
			System.out.println();
		}
	}
	
	public void addDevice() {
		System.out.println("유형(1: Camera, 2: Laptop) : ");
		int type = sc.nextInt();
		System.out.println("id : ");
		String id = sc.next();
		System.out.println("name : ");
		String name = sc.next();
		System.out.println("category : ");
		String category = sc.next();
		System.out.println("tags(쉼표로 구분) : ");
		String tag = sc.next();
		
		Set<String> tags = new HashSet<>();
		for(String s : tag.split(",")) {
			tags.add(s.trim());
		}
		
		Device device;
		switch(type) {
		case 1:
			device = new Camera(id, name, category, tags);
			break;
		case 2:
			device = new Laptop(id, name, category, tags);
			break;
			default :
				System.out.println("유형을 잘못 입력하셨습니다");
				return;
		}
		
		boolean isOk = gc.addDevice(device);
		System.out.println(isOk ? "등록완료" : "중복된 ID입니다 다시입력해주세요");
	}
	
	public void addMember() {
		System.out.println("회원 id : ");
		String id = sc.next();
		System.out.println("이름 : ");
		String name = sc.next();
		
		boolean isOk = gc.addMember(new Member(id, name));
		System.out.println(isOk ? "등록완료" : "중복된 ID입니다 다시입력해주세요");
	}
	
	public void borrow() {
		System.out.println("memberId : ");
		String memberId = sc.next();
		
		System.out.println("itemId : ");
		String itemId = sc.next();
		
		System.out.println("대여일(YYYY-MM-DD) : ");
		String day = sc.next();
		sc.nextLine();
		
		LocalDate today = LocalDate.parse(day);
		
		Loan loan = gc.borrow(memberId, itemId, today);
		if(loan == null) {
			System.out.println("대여 불가");
			return;
		}
		System.out.println("대여 생성 완료 : " + loan);
		System.out.println("반납 예정일 : " + loan.getDueDate());
	}
	
	public void returnItem() {
		System.out.println("memberId : ");
		String itemId = sc.next();
		
		System.out.println("반납일(YYYY-MM-DD) : ");
		String day =sc.next();
		sc.nextLine();
		
		LocalDate today = LocalDate.parse(day);
		
		int fee = gc.returnItem(itemId, today);
		
		if(fee == -1) {
			System.out.println("대여이력이 없습니다");
		}else {
			System.out.println("반납 완료, 연체료 : " + fee + "원");
		}
	}
	
	public void findByTag() {
		System.out.println("검색 태그 : ");
		String tag = sc.next();

		List<Device> list = gc.findByTag(tag);
		if(list.isEmpty()) {
			System.out.println("결과 없음");
		} else {
			for(Device d : list) {
				System.out.println(d);
			}
		}
	}
	
	public void findByKeyword() {
		System.out.println("검색 키워드 : ");
		String keyword = sc.next();

		List<Device> list = gc.findByKeyword(keyword);
		if(list.isEmpty()) {
			System.out.println("결과 없음");
		} else {
			for(Device d : list) {
				System.out.println(d);
			}
		}
	}
	
	public void printAllDevices() {
		Collection<Device> all = gc.getAllDevices();
		
		if(all.isEmpty()) {
			System.out.println("결과 없음");
		} else {
			for(Device d : all) {
				System.out.println(d);
			}
		}
	}
	
	public void printActiveLoans() {
		Collection<Loan> all = gc.getActiveLoans();
		
		if(all.isEmpty()) {
			System.out.println("결과 없음");
		} else {
			for(Loan d : all) {
				System.out.println(d);
			}
		}
	}
}
