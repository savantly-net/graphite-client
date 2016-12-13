package net.savantly.graphite.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.savantly.graphite.CarbonMetric;
import net.savantly.graphite.CarbonSender;

public class DefaultCarbonSender implements CarbonSender {
	Logger log = LoggerFactory.getLogger(DefaultCarbonSender.class);

	private final int carbonPort;
	private InetAddress localInetAddress= null;
	private InetAddress remoteInetAddress= null;
	DatagramSocket socket = null;

	/**
	 * @param carbonPort
	 * @param graphiteHost
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public DefaultCarbonSender(String graphiteHost, int carbonPort) throws UnknownHostException, SocketException {
		this(graphiteHost, carbonPort, null);
	}
	
	public DefaultCarbonSender(String graphiteHost, int carbonPort, String sourceIp) throws UnknownHostException, SocketException {
		this.carbonPort = carbonPort;
		if(graphiteHost == null){
			remoteInetAddress = InetAddress.getLocalHost();
		} else{
			remoteInetAddress = Inet4Address.getByName(graphiteHost);
		}
		if(sourceIp == null){
			localInetAddress = InetAddress.getLocalHost();
		} else{
			localInetAddress = Inet4Address.getByName(sourceIp);
		}
		socket = new DatagramSocket(new InetSocketAddress(localInetAddress, 0));
	}

	@Override
	public void sendToCarbon(CarbonMetric metric) {
		sendToCarbon(getStringFromMetric(metric));
	}

	@Override
	public void sendToCarbon(Collection<CarbonMetric> metrics) {
		sendToCarbon(getLinesFromMetrics(metrics));
	}

	private StringBuffer getLinesFromMetrics(
			Collection<CarbonMetric> carbonMetrics) {
		StringBuffer lines = new StringBuffer();
		for (CarbonMetric carbonMetric : carbonMetrics) {
			lines.append(getStringFromMetric(carbonMetric)).append("\n");
		}
		return lines;
	}

	private String getStringFromMetric(CarbonMetric carbonMetric) {
		return String.format("%s %s %s", carbonMetric.getMetricName(),
				carbonMetric.getValue(), carbonMetric.getEpoch());
	}

	private void sendToCarbon(StringBuffer lines) {
		String msg = lines.toString();
		sendToCarbon(msg);
	}

	private void sendToCarbon(String msg) {
		if(log.isDebugEnabled()){
			log.debug(msg);
		}
		
		try {
			DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), remoteInetAddress, carbonPort);
			socket.send(packet);
		} catch (IOException e) {
			log.error("", e);
		}
	}
	
	public void dispose(){
		if(socket != null){
			socket.close();
			socket = null;
		}
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

}
