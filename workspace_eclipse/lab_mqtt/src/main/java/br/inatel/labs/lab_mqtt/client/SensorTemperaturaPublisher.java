package br.inatel.labs.lab_mqtt.client;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;
import java.util.UUID;

public class SensorTemperaturaPublisher {

	private static MqttMessage getTemperatureMessage() {
        Random r = new Random();
        double temperatura = 80 + r.nextDouble() * 20;
        byte[] payload = String.format("T:%04.2f", temperatura).getBytes();
        return new MqttMessage(payload);
    }

    public static void main(String args[]) {

        IMqttClient publisher = null;
        try {
            //criar o publisher
            String publisherId = UUID.randomUUID().toString();
            publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);

            //conecta
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);

            for (int i = 0; i < 10; i++) {

                //publicar a msg
                MqttMessage msg = getTemperatureMessage();
                msg.setQos(0);
                msg.setRetained(true);

                //publicar a msg
                publisher.publish(MyConstants.TOPIC_SENSOR, msg);

                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                //desconecta
                publisher.disconnect();
                publisher.close();
            } catch (MqttException e) {          
            }
        }
    }
}
    
	
	
	
	
	
	

