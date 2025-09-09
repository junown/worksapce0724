package com.kh.example.collection2;

import java.util.Scanner;

public class LotteryMenu {
	Scanner sc = new Scanner(System.in);
	LotteryController lc = new LotteryController();
	
	public void mainMenu() {
		while(true) {
			  System.out.println("========== KH 추첨 프로그램 ==========");
			  System.out.println("1. 추첨 대상 추가");
			  System.out.println("2. 추첨 대상 삭제");
			  System.out.println("3. 당첨 대상 확인  ");
			  System.out.println("4. 정렬된 당첨 대상 확인");
			  System.out.println("5. 당첨 대상 검색");
			  System.out.println("9. 종료");
			  System.out.print("메뉴 번호 입력 : ");
			  
			  int menu = sc.nextInt();
			  sc.nextLine();
			  switch(menu) {
			  case 1: 
				  insertObject();
				  break;
			  case 2:
				  delectObject();
				  break;
			  case 3:
				  winObject();
				  break;
			  case 5:
				  break;
			  case 9:
				  System.out.println("프로그램종료"); return;
		      default :
		    	  System.out.println("잘못입력하셨습니다");
			  }
			  System.out.println();
	}
	}
		public void insertObject() {
			System.out.println("추가할 추첨 대상 수 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			for(int i=0; i<num; i++) {
				System.out.println("이름 : ");
				String name = sc.nextLine();
				
				System.out.println("핸드폰 번호('-'빼고) : ");
				String phone = sc.nextLine();
				
				boolean isInsert = lc.insertObject(new Lottery(name, phone));
				if(!isInsert) {
					System.out.println("중복된 인원입니다. 다시 입력해주세요");
				}
			}
			System.out.println(num + "명 추가 완료되었습니다");
		}
		
		public void  delectObject() {
			System.out.println("삭제할 대상의 이름과 핸드폰 번호를 입력하세요. ");
			System.out.println("이름 : ");
			String name = sc.nextLine();
			
			System.out.println("핸드폰 번호('-'빼고) : ");
			String num = sc.nextLine();

			boolean isRemove = lc.deleteObject(new Lottery(name, num));
			if(isRemove) {
				System.out.println("삭제 완료되었습니다");
			}else {
				System.out.println("존재하지 않는 대상입니다");
			}
			
		}
		
		public void winObject() {
			System.out.println("검색할 대상의 이름과 핸드폰 번호를 입력하세요. ");
			System.out.println("이름 : ");
			String name = sc.nextLine();
			
			System.out.println("핸드폰 번호('-'빼고) : ");
			String phone = sc.nextLine();
			
			boolean isWin = lc.searchWinner(new Lottery(name, phone));
			
			if(isWin) {
				System.out.println("축하합니다");
			}else {
				System.out.println("실패");
			}
		}
}
