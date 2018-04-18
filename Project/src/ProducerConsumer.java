

public class ProducerConsumer {

    public ProducerConsumer(GUIFrame frame, int buffSize, int secProducer, int numProducer, int secConsumer, int numConsumer, int m, int n){
        
        Buffer buffer = new Buffer(frame, buffSize);
        
        Producer producer = new Producer(frame, buffer, secProducer, numProducer, m, n);
        producer.start();
        
        Consumer consumer = new Consumer(frame, buffer, secConsumer, numConsumer);
        consumer.start();
    }
    
    
}
