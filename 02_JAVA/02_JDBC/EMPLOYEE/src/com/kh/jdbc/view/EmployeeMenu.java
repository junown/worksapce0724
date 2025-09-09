package com.kh.jdbc.view;

import java.util.Scanner;

import com.kh.jdbc.controller.EmployeeController;
import com.kh.jdbc.dao.EmployeeDao;
import com.kh.jdbc.service.EmployeeService;

public class EmployeeMenu {
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	private EmployeeService es = new EmployeeService();
	
	public void mainmenu() {
		while(true) {
			System.out.println("======== 사원 메인 화면 ========");
			System.out.println("1.사원 등록");
			System.out.println("2.사원 삭제");
			System.out.println("3.사원 정보 수정");
			System.out.println("4.사원 정보 검색");
			System.out.println("5.사원 전체 검색");
			System.out.println("6.사원 여러명 추가");
			System.out.println("7.사원 여려명 삭제");
			System.out.println("8.통계");
			System.out.println("9.종료");
			System.out.println("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				insertEmployee(); break;
			case 2:
				deleteEmployee(); break;
			case 3:
				updateEmployee(); break;
			case 4:
				keywordEmployee(); break;
			case 5:
				ec.selectEmployeeAll(); break;
			case 6:
				updateEmployees(); break;
			case 7:
				deleteEmployees(); break;
			case 8:
				statisticsEmployee(); break;
				
		/*	case 99:
				deleteAllEmployee(); break;*/
			case 9:
				System.out.println("종료합니다");
				return;
			default :
				System.out.println("잘못 입력하셨습니다");
			}
			System.out.println();
		}
	}
	public void statisticsEmployee() {
		while(true) {
			System.out.println("======== 사원 통계 화면 ========");
			System.out.println("1.전체 사원 수");
			System.out.println("2.부서별 사원 전체 정보");
			System.out.println("3.월급 순위");
			System.out.println("9.종료");
			System.out.println("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				ec.countEmployee(); ec.countDept(); break;
			case 2:
				deleteEmployee(); break;
			case 3:
				updateEmployee(); break;
			case 9:
				System.out.println("종료합니다");
				return;
			default :
				System.out.println("잘못 입력하셨습니다");
			}
			System.out.println();
		}
	}
	
	public void deleteEmployees() {
		System.out.println("======== 사원 여려명 제거 ========");
		System.out.println("제거할 사원 수 : ");
		int num = sc.nextInt();
		sc.nextLine();
		for(int i=0; i<num; i++) {
			deleteEmployee();
			System.out.println("사원" + (i+1) + "명 제거 완료");
			System.out.println("더 삭제하시려면 Y, 아니면 N을 입력해주세요 : ");
			char stop = sc.next().charAt(0);
			if(stop == 'Y' && stop == 'y') {
			} else {
				System.out.println("메뉴로 돌아갑니다");
				return;
			}
		}
	}
	
	public void updateEmployees() {
		System.out.println("======== 사원 여러명 추가 ========");
		System.out.print("추가할 사원 수 : ");
		int num = sc.nextInt();
		sc.nextLine();
		for(int i=0; i<num; i++) {
			insertEmployee();
			System.out.println("사원" + (i+1) + "명 추가 완료");
			System.out.println("더 추가하시려면 Y, 아니면 N을 입력해주세요 : ");
			char stop = sc.next().charAt(0);
			if(stop == 'Y' && stop == 'y') {
			} else {
				System.out.println("메뉴로 돌아갑니다");
				return;
			}
		}
		
		System.out.println("사원 모두 추가 완료");
	}
	
	public void keywordEmployee() {
		System.out.println("======== 사원 이름 검색 ========");
		System.out.println("사원명 : ");
		String keyword = sc.nextLine();
		
		ec.keywordEmployee(keyword);
	}
	
	public void deleteEmployee() {
		System.out.println("======== 사원 삭제 화면 ========");
		System.out.println("삭제할 사원 NO : ");
		String empno = sc.nextLine();
		
		System.out.println("삭제할 사원 이름 : ");
		String empname = sc.nextLine();
		
		ec.deleteEmployee(empno, empname);
	}
	
	public void insertEmployee() {
		System.out.println("======== 사원 등록 화면 ========");
		System.out.print("사원 번호 : ");
		String empno = sc.nextLine();
		
		System.out.print("부서 코드 : ");
		String deptcode = sc.nextLine();
		
		System.out.print("사원 이름 : ");
		String empname = sc.nextLine();
		
		System.out.print("사원 주소 : ");
		String address = sc.nextLine();
		
		System.out.print("사원 이메일 : ");
		String email = sc.nextLine();

		System.out.print("사원 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("사원 월급 : ");
		String salary = sc.nextLine();
		
		ec.insertEmployee(empno, deptcode, empname, address, email, phone, salary);
		
	}
	
	public void updateEmployee() {
		System.out.println("======== 사원 정보 수정 화면 ========");
		System.out.print("정보를 수정할 사원 NO : ");
		String empno = sc.nextLine();
		
		System.out.print("변경할 부서코드 : ");
		String deptcode = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String address = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 월급 : ");
		int salary = sc.nextInt();
		sc.nextLine();

		ec.updateEmployee(empno, deptcode, address, email, phone, salary);
	}
}
