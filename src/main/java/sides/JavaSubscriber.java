package sides;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class JavaSubscriber{
    public static void main(String[] args) throws MqttException {
        String topic = "devices/sensor/temperature";
        String broker = "tcp://mqtt.eclipse.org:1883";
        String clientId = "JavaSample2";
        MqttClient sampleClient2 = new MqttClient(broker, clientId);

        sampleClient2.setCallback(new SimpleMqttCallback());
        sampleClient2.connect();
        sampleClient2.subscribe(topic);
    }
}
