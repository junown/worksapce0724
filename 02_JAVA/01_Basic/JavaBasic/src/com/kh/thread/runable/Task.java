package com.kh.thread.runable;

//스레드를 만드는 방법2
//Thread 클래스를 상속받은 클래스를 작성
public class Task extends Thread{

	
	//메인문처럼 해당 스레드 실행시 독립적으로 실행되는 메서드
	@Override
	public void run() {
		int n1 = 20;
		int n2 = 30;
		
		//Thread.currentThread() ->
		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + (n1 + n2));
	}
	
}
