package sides;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class JavaSubscriber{
    public static void main(String[] args) throws MqttException {
        String topic = "devices/sensor/temperature";
        String broker = "tcp://192.168.100.35:1883";
        String clientId = "JavaSample2";
        MqttClient sampleClient2 = new MqttClient(broker, clientId);

        try {
            sampleClient2.setCallback(new SimpleMqttCallback());
            sampleClient2.connect();
            sampleClient2.subscribe(topic);
        } catch(MqttException me) {
            System.out.println("----------Exception captured----------");
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
