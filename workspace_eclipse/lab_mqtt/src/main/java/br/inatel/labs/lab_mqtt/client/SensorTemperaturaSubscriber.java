package br.inatel.labs.lab_mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

public class SensorTemperaturaSubscriber {

	public static void main(String[] args){

        //cria o subscriber
        String subscriberId = UUID.randomUUID().toString();
        IMqttClient subscriber = null;
        try {
            subscriber = new MqttClient(MyConstants.URI_BROKER, subscriberId);

            //conecta
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            subscriber.connect(options);
            System.out.println("Subscriber connectado e aguardando msg...");

            //recebendo msgs...
            subscriber.subscribe(MyConstants.TOPIC_SENSOR, (topic, msg) -> {
                byte[] payload = msg.getPayload();
                System.out.println("Payload recebido: " + msg);
                System.out.println("Topico recebido: " + topic);
            });

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                subscriber.close();

            } catch (MqttException e) {

            }
        }
    }	
}
