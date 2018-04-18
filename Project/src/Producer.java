

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Producer extends Thread {
    GUIFrame frame;
    Buffer buffer;
    int seconds;
    int number;
    int m;
    int n;
    int result;
    int rand1 = 0;
    int rand2 = 0;
    
    Producer(GUIFrame frame, Buffer buffer, int seconds, int number, int m, int n) {
        this.frame = frame;
        this.buffer = buffer;
        this.seconds = seconds;
        this.number = number;
        this.m = m;
        this.n = n;
    }
    
    String lisp(int index){
        String fin = "";
        if(index == 1){
            fin += "+ " + rand1 + " " + rand2;
            this.result = rand1 + rand2;
        }
        else if(index == 2){
            fin += "- " + rand1 + " " + rand2;
            this.result = rand1 - rand2;
        }
        else if(index == 3){
            fin += "* " + rand1 + " " + rand2;
            this.result = rand1 * rand2;
        }
        else{
            fin += "/ " + rand1 + " " + rand2;
            this.result = rand1 / rand2;
        }
        return fin;
    }

    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        while(true) {
            int index = r.nextInt(4);
            rand1 = r.nextInt(n - m + 1) + m;
            rand2 = r.nextInt(n - m + 1) + m;
            
            product = lisp(index);
            this.buffer.produce(result);
            this.buffer.setOperation(product);
            DefaultTableModel model = (DefaultTableModel) frame.getTable1().getModel();
            Object [] row = {product};
            
            model.addRow(row);
            
            Buffer.print(product);
            
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
