package com.Echoer.Server;

import java.util.Scanner;

public class Interface extends Thread {
	static Scanner scanner = new Scanner(System.in);
	
	public Interface() {
		//nuthin...
	}
	
	public static void main(String args[]) {
		Interface startup1 = new Interface();
		
		System.out.println("Port to listen on?");
		Server server1 = new Server(Integer.parseInt(scanner.nextLine()));
		
		server1.start();
	}
}
