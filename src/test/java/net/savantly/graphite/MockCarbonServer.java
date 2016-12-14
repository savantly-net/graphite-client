package net.savantly.graphite;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockCarbonServer {
	private static final Logger log = LoggerFactory.getLogger(MockCarbonServer.class);

	DatagramSocket serverSocket;
	private final int port;
	private List<String> history = new ArrayList<String>();
	private int numberOfRequests;
	private final Thread serverThread;

	private void addToHistory(String sentence) {
		this.history.add(sentence);
	}
	
	public MockCarbonServer() {
		this(1);
	}

	/**
	 * 
	 * @param numberOfRequests
	 * The number of expected requests. The server will automatically shutdown after it reaches this number
	 */
	public MockCarbonServer(int numberOfRequests) {
		this(numberOfRequests, 2003);
	}

	public MockCarbonServer(int numberOfRequests, int port) {
		this.numberOfRequests = numberOfRequests;
		this.port = port;
		
		serverThread = new Thread(new RunnableServer());
	}

	public void start() throws IOException {
		this.serverSocket = new DatagramSocket(this.port);
		serverThread.start();
	}
	
	/**
	 * 
	 * @param wait 
	 * How long to wait in milliseconds for the server to shutdown before interupting it
	 * @throws InterruptedException
	 */
	public void stop(int wait) throws InterruptedException{
		Thread.sleep(1000);
		this.serverSocket.disconnect();
		this.serverSocket.close();
		serverThread.join(wait);
	}
	
	public void clearHistory() {
		this.history.clear();
	}
	
	public List<String> getHistory(){
		return Collections.unmodifiableList(this.history);
	}
	
	class RunnableServer implements Runnable{
		byte[] receiveData = new byte[1024*3];
		
		public void run() {
			while (numberOfRequests > 0 && !serverSocket.isClosed()) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				try {
					serverSocket.receive(receivePacket);
					String sentence = new String(receivePacket.getData());
					log.info("RECEIVED: " + sentence);
					addToHistory(sentence);
					--numberOfRequests;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
