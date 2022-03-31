package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisch {
	
	private MqttClient client;
	private final String serverURI = "tcp://localhost:1883";
	private final String clientID = "client_publisch";
	private final String topic = "sensor/sensort1";
	private final String msg = "30";
	private MqttMessage message;
	
	public void connect() {
		try {
			client = new MqttClient(serverURI, clientID);
			client.connect();
			System.out.println("Client "+ clientID +" connected sucessfull");
		} catch (MqttException e) {
			System.out.println("Erro connect to server");
		}		
	}
	
	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage() {
		try {
			message = new MqttMessage(msg.getBytes());
			client.publish(topic, message);
			System.out.println("Message send sucessfull");
		} catch (MqttException e) {
			System.out.println("Error sending message");
		}		
	}

	public static void main(String[] args) {
		Publisch publisch = new Publisch();
		publisch.connect();
		publisch.sendMessage();
		publisch.disconnect();
	}

}
