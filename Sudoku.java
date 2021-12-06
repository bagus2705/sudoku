import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
public class Sudoku extends JFrame {
public static void main(String[] args) {
    Game Game=new Game();
    JOptionPane.showMessageDialog(null, "Sudoku");
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            if(args.length < 1 || args[0].contains("easy")) {
                JOptionPane.showMessageDialog(null, "Easy");
                Game.setLevel("easy");               
            }

            else if(args[0].contains("medium")) {
                JOptionPane.showMessageDialog(null, "Medium");
                Game.setLevel("medium");
            }

            else if(args[0].contains("hard")) {
                JOptionPane.showMessageDialog(null, "Hard");
                Game.setLevel("hard");
            }
            new Game().setVisible(true);
        }
    });
}
}
