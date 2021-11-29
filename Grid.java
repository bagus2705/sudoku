import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Grid extends JPanel {
    public final int jumlahCell = 9;
    public Cell []  grid = new Cell[jumlahCell];
    public Grid(){ 
        this.setLayout(new GridLayout(3,3));
        this.setBorder(new LineBorder(Color.BLUE,2));
        for(int i =0; i<jumlahCell; i++){
            grid[i] = new Cell();
            this.add(grid[i]);
        }
    }
    public class Cell extends JTextField{
        public Cell(){
        }
    }
}
