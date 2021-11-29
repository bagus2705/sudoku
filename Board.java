import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
public class Board extends JFrame {

public  int jumlahKotak = 9;
public Grid [] kotak = new Grid[jumlahKotak];

public  Board(){

    super("Sudoku");
    setSize(700,700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel panel = new JPanel(new GridLayout(3,3));
    for(int i=0; i<jumlahKotak; i++){
        kotak[i] = new Grid();
        panel.add(kotak[i]);
    }

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);

    JMenuItem newLevel = new JMenuItem("Select New Level");
    menu.add(newLevel);

    JMenuItem reset = new JMenuItem("Reset");
    menu.add(reset);

    JMenuItem exit = new JMenuItem("Exit");
    menu.add(exit);

    add(panel, BorderLayout.CENTER);
    setVisible(true);
    setLocationRelativeTo(null);

}

public class exitaction implements ActionListener{
    public void actionPerformed (ActionEvent e){
        System.exit(0);
    }
}

public static void main(String[] args) { 
    Board board = new Board();
}    
}