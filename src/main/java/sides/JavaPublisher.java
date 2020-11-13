package sides;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class JavaPublisher {
    public static void main(String args[]) {
        String content;
        String topic        = "devices/sensor/temperature";
        int qos             = 1;
        String broker       = "tcp://mqtt.eclipse.org:1883";
        String clientId     = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+ broker + "\n");
            while(true) {
                sampleClient.connect(connOpts);
                content = Integer.toString(getRNB(15, 45));
                System.out.println("Publishing message: " + content + "º");
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                System.out.println("Message published\n");
                sampleClient.disconnect();
                Thread.sleep(1000);
            }
        } catch(MqttException me) {
            System.out.println("----------Exception captured----------");
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Get Random Number Between [min, max]
    public static int getRNB(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}