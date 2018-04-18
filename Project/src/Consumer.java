

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Consumer extends Thread {
    GUIFrame frame;
    Buffer buffer;
    int seconds;
    int number;
    int finished = 0;
    
    Consumer(GUIFrame frame, Buffer buffer, int seconds, int number) {
        this.frame = frame;
        this.buffer = buffer;
        this.seconds = seconds;
        this.number = number;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        int product;
        
        while(true) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print(this.buffer.getOperation() + " = " + product);
            DefaultTableModel model = (DefaultTableModel) frame.getTable2().getModel();
            DefaultTableModel model2 = (DefaultTableModel) frame.getTable1().getModel();
            Object [] row = {this.buffer.getOperation() + " = " + product};
            model.addRow(row);
            model2.removeRow(0);
            finished++;
            frame.getJSpinner4().setValue(finished);
            
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
