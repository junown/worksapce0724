package com.kh.example.collection1;

import java.util.List;
import java.util.Scanner;

public class MusicView {
	private Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("=====***** 메인 메뉴 *****===== ");
			System.out.println("1. 마지막 위치에 곡 추가 ");
			System.out.println("2. 첫 위치에 곡 추가 ");
			System.out.println("3. 전체 곡 목록 출력 ");
			System.out.println("4. 특정 곡 검색  ");
			System.out.println("5. 특정 곡 삭제 ");
			System.out.println("6. 특정 곡 수정 ");
			System.out.println("7. 곡 명 오름차순 정렬");
			System.out.println("8. 가수 명 내림차순 정렬 ");
			System.out.println("9. 종료 ");
			System.out.print("메뉴 번호 입력 : ");
			
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
			case 1: 
				addList();
				break;
			case 2:
				addAtZero();
				break;
			case 3:
				printAll();
				break;
			case 4:
				searchMusic();
				break;
			case 5:
				removeMusic();
				break;
			case 6:
				setMusic();
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				System.out.println("프로그램 종료 ");
				return;
			}
		}
	}
	
	public void addList() {
		System.out.println("****** 마지막 위치에 곡 추가 ****** ");
		System.out.println("곡 명 : ");
		String title = sc.next();
		System.out.println("가수명 : ");
		String singer = sc.next();
		int result = mc.addList(new Music(title, singer));
		System.out.println(result == 1 ? "추가 성공" : "추가 실패");
	}
	
	public void addAtZero() {
		System.out.println("****** 첫 위치에 곡 추가 ******");
		System.out.println("곡 명 : ");
		String title = sc.next();
		System.out.println("가수명 : ");
		String singer = sc.next();
		int result = mc.addAtZero(new Music(title, singer));
		System.out.println(result == 1 ? "추가 성공" : "추가 실패");
	}
	
	public void printAll() {
		System.out.println("****** 전체 곡 목록 출력 ****** ");
		List<Music> list = mc.printAll();
		System.out.println(list);
	}
	
	public void searchMusic() {
		System.out.println("****** 특정 곡 검색 ******");
		System.out.println("검색할 곡 명 : ");
		String title = sc.next();
		Music m =mc.searchMusic(title);
		if(m == null) {
			System.out.println("검색한 곡이 없습니다");
		} else {
			System.out.println("검색한 곡은 " + m.toString() + "입니다");
		}
	}
	
	public void removeMusic() {
		System.out.println("****** 특정 곡 검색 ******");
		System.out.println("검색할 곡 명 : ");
		String title = sc.next();
		Music m = mc.removeMusic(title);
		if(m == null) {
			System.out.println("삭제할 곡이 없습니다");
		}else {
			System.out.println(m.toString() + "을 삭제했습니다");
		}
	}

	public void setMusic() {
		System.out.println("****** 특정 곡 수정 ******");
		System.out.println("검색할 곡 명 : ");
		String title1 = sc.next();
		System.out.println("수정할 곡 명 : ");
		String title2 = sc.next();
		System.out.println("수정할 가수 명 : ");
		String singer = sc.next();
		
		mc.setMusic(title1, new Music(title2, singer));
	}
}
