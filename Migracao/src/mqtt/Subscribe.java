package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscribe {
	
	private MqttClient client;
	private final String serverURI = "tcp://localhost:1883";
	private final String clientID = "client_subscribe";
	private final String topic = "sensor/#";
	
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
	
	public void receiveMessage() {
		try {
			client.subscribe(topic);
			client.setCallback(new MqttCallback() {
				
				@Override
				public void messageArrived(String arg0, MqttMessage msg) throws Exception {
					String message = msg.toString();
					System.out.println("Message: " + message);
				}
				
				@Override
				public void deliveryComplete(IMqttDeliveryToken arg0) {
					System.out.println("Message arrived sucessfull");
				}
				
				@Override
				public void connectionLost(Throwable arg0) {
					System.out.println("Connection lost");
				}
			});
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Subscribe subscribe = new Subscribe();
		subscribe.connect();
		subscribe.receiveMessage();
	}

}
