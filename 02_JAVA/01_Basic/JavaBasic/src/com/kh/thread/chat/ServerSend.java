package com.kh.thread.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend extends Thread{
	private Socket socket;

	public ServerSend(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try (Scanner sc = new Scanner(System.in)){
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
		
		while(true) {
			System.out.println("클라이언트로 보낼 내용 : ");
			String sendMessage = sc.nextLine();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
