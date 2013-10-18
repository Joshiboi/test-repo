package com.Echoer.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread {
	private DatagramSocket socket;
	
	public Server(int port) {
		try {
			this.socket = new DatagramSocket(port);
			System.out.println("Now echoing on port " + port + "!");
		} catch (SocketException e) {
			System.out.println("Error setting up socket on port " + port + "!");
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String msg = new String(packet.getData()).trim();
			
			System.out.println("Echoing '" + msg + "' (trimmed for console readout) (Raw: '" + packet.getData().toString() + "') from " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
			
			sendData(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	public void sendData(byte[] data, InetAddress IP, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, IP, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			String dataString = new String(data);
			System.out.println("Error sending data '" + dataString + "' to " + IP + ":" + port);
			e.printStackTrace();
		}
	}
}
