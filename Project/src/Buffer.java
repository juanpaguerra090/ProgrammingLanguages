
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    GUIFrame frame;
    private int buffer;
    public String operation;
    int cantidad = 0;
    int size;
    
    
    Buffer() {
        this.buffer = 0;
    }
    
    Buffer(GUIFrame frame, int size){
        this.frame = frame;
        this.buffer = 0;
        this.size = size;
        this.frame.getJLabel9().setText("Lleno: 0%");
    }
    
    synchronized int consume() {
        int product = 0;
        
        if(this.buffer == 0) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = 0;
        cantidad--;
        if(cantidad >= size){
            this.frame.getJLabel9().setText("Lleno: 100%");
        }
        else if(cantidad >= 0){
            this.frame.getJLabel9().setText("Lleno: " + (float)cantidad/size + "%");

        }
        else {
                this.frame.getJLabel9().setText("Lleno: 0%");
        }
        notify();
        return product;
    }
    
    synchronized void produce(int product) {
        if(this.buffer != 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;  
        cantidad++;
        if(cantidad > size){
            this.frame.getJLabel9().setText("Lleno: 100%");
        }
        else if(cantidad >= 0){
            this.frame.getJLabel9().setText("Lleno: " + (float)cantidad/size + "%");

        }
        else {
                this.frame.getJLabel9().setText("Lleno: 0%");
        }
        notify();
    }
    
    synchronized void setOperation(String operation) {
        this.operation = operation;
        notify();
    }
    
    synchronized String getOperation() {
        return this.operation;
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
