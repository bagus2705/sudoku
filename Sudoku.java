import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Sudoku  extends JFrame {
    public static int grid = Puzzle.grid; // ukuran grid
	public static  int subgrid = Puzzle.subgrid; // ukuran subgrid
	public static  int besar = 60;
	public static  int lebar = besar * grid;
	public static  int tinggi = besar * grid;
    public  static Color color_blank = Color.WHITE; 
    public static  Color color_fill = new Color(240, 240, 240); 
	public static  Color color_number = Color.BLACK; 
	public  static Font font_number = new Font("Arial", Font.BOLD, 20);
    public  static int level; 
	public static int NumSisa;
	public static JPanel Board;
	public  static JPanel PanelBoard;
	public static  JLabel LabelBoard;
	public static JMenuBar menu;
    JTextField[][] display = new JTextField[grid][grid];
    private int[][] sudokupuzzle = Puzzle.getPuzzle();
    private static boolean[][] restart = {   
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false } };
private static boolean[][] game = {
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false } };

public Sudoku() {

    Container contentp = getContentPane();
    contentp.setLayout(new BorderLayout());

    //SudokuBoard
    Board = new JPanel();
    Board.setLayout(new GridLayout(grid, grid));
    Board.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
    contentp.add(Board, BorderLayout.CENTER);

    //membuatPanel
    PanelBoard = new JPanel();
    PanelBoard.setBorder(new BevelBorder(BevelBorder.RAISED));
    PanelBoard.setPreferredSize(new Dimension(contentp.getWidth(), 25));
    PanelBoard.setLayout(new BoxLayout(PanelBoard, BoxLayout.X_AXIS));
    LabelBoard = new JLabel("Kolom Sisa: " + NumSisa);
    LabelBoard.setHorizontalAlignment(SwingConstants.LEFT);
    PanelBoard.add(LabelBoard);
    contentp.add(PanelBoard, BorderLayout.SOUTH);
    
    menu = new JMenuBar();
    
    //membuat  menu
    JMenu MenuBar = new JMenu("Menu");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem help = new JMenuItem("Game Instructions");
    helpMenu.add(help);
    menu.add(helpMenu);	

    JMenu aboutMenu = new JMenu("About");
    JMenuItem about = new JMenuItem("Sudoku Game");
    aboutMenu.add(about);
    menu.add(aboutMenu);

    JMenuItem restartGame = new JMenuItem("Restart Game");
    MenuBar.add(restartGame);
    JMenu Level = new JMenu("Difficulty level");
    MenuBar.add(Level);
    LevelListener difflistener = new LevelListener();
    JMenuItem easylevel = new JMenuItem("Easy");
    easylevel.addActionListener(difflistener);
    Level.add(easylevel);
    JMenuItem mediumlevel = new JMenuItem("Medium");
    mediumlevel.addActionListener(difflistener);
    Level.add(mediumlevel);
    JMenuItem hardlevel = new JMenuItem("Hard");
    hardlevel.addActionListener(difflistener);
    Level.add(hardlevel);
    
    menu.add(MenuBar);
    setJMenuBar(menu);
        
    JMenuItem resetGameItem = new JMenuItem("Reset game");
    MenuBar.add(resetGameItem);
    JMenuItem exitItem = new JMenuItem("Exit");
    MenuBar.add(exitItem);
    about.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Sudoku, juga dikenal Number Place atau Nanpure, adalah sejenis teka-teki logika. \nTujuannya adalah untuk mengisikan angka-angka dari 1 sampai 9, \nke dalam jaring-jaring 9 x 9 yang terdiri dari 9 kotak 3 x 3 tanpa ada angka yang berulang di satu baris, kolom atau kotak");
        }
    });
    help.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"1. Pada baris harus di isi oleh angka-angka mulai dari 1-9 dan tidak boleh ada angka yang kembar dalam satu baris.\n2. Pada kolom juga begitu harus di isi dengan angka-angka mulai dari 1-9 dan tidak boleh ada angka yang kembar dalam satu kolom.\n3. Pada setiap bagian kotak area dengan ukuran 3 x 3 yang berisi 9 kotak-kotak kecil. Kotak kecil tersebut harus diisi dengan angka 1-9 dan syaratnya tidak boleh ada angka yang berulang pada setiap kotak area.");
        }
    });
    resetGameItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            InputListener listener = new InputListener();
            for (int row = 0; row < grid; ++row) {
                for (int col = 0; col < grid; ++col) {
                    if (restart[row][col]) {
                        display[row][col].setText(""); // set ke string kosong
                        display[row][col].setEditable(true);	// dapat diedit
                        display[row][col].setBackground(color_blank);
                        display[row][col].addActionListener(listener);
                    }
                    else {
                        display[row][col].setText(sudokupuzzle[row][col] + ""); // sudah berisi angka
                        display[row][col].setEditable(false); // tidak dapat diedit
                        display[row][col].setBackground(color_fill);
                        display[row][col].setForeground(color_number);
                    }
                 }
            }

        }
    });

    restartGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            setLevel (1);
            new Sudoku();
            }
        });
    exitItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0); //exit
        }
    });
    menu.add(MenuBar);

    // inisialisasi game
    initGame(Board);
    contentp.setPreferredSize(new Dimension(lebar, tinggi));
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Sudoku Game");
    setVisible(true);
}

private class LevelListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "Easy":
            JOptionPane.showMessageDialog(null, "Easy");
            setLevel (1);
            dispose();
            new Sudoku();
            break;
        case "Medium":
            JOptionPane.showMessageDialog(null, "Medium");
            setLevel (2);
            dispose();
            new Sudoku();
            break;
        case "Hard":
            JOptionPane.showMessageDialog(null, "Hard");
            setLevel (3);
            dispose();
            new Sudoku();
            break;
        default:
            JOptionPane.showMessageDialog(null, "Easy");
            setLevel (1);
            dispose();
            new Sudoku();
            break;
        }
    }
}
private void initGame(JPanel Board) {
    InputListener listener = new InputListener();
    for (int row = 0; row < grid; ++row) {
        for (int col = 0; col < grid; ++col) {
            display[row][col] = new JTextField(); 
            if (col == 3 || col == 6 || row == 3 || row == 6) {
                if ((col == 3 || col == 6) && (row == 3 || row == 6)) {
                    display[row][col].setBorder(BorderFactory.createMatteBorder(5, 5, 1, 1, Color.black));
                } else if (col != 3 && col != 6) {
                    display[row][col].setBorder(BorderFactory.createMatteBorder(5, 1, 1, 1, Color.black));
                } else if (row != 3 && row != 6) {
                    display[row][col].setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.black));
                }
            } else {
                display[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
            }
            display[row][col].setHorizontalAlignment(JTextField.CENTER);
            display[row][col].setFont(font_number);
            Board.add(display[row][col]);  
            if (game[row][col]) {
    display[row][col].setText("");
    display[row][col].setEditable(true);
    display[row][col].setBackground(color_blank);
    display[row][col].addActionListener(listener);
        } 
    else {
    display[row][col].setText(sudokupuzzle[row][col] +"");
    display[row][col].setEditable(false);
    display[row][col].setBackground(color_fill);
    display[row][col].setForeground(color_number);
}
}
}
}


public static void setLevel (int level) {
    for (int row = 0; row < grid; ++row) {
        for (int col = 0; col < grid; ++col){
            game[row][col] = false;
            restart[row][col] = false;
        }
    }
    NumSisa = level * 9;
    Random random = new Random();
    int randomRow = -1;
    int randomCol = -1;

    for (int i = 0; i < NumSisa; i++) {
        randomRow = random.nextInt(grid);
        randomCol = random.nextInt(grid);
        if (!game[randomRow][randomCol]) {
            game[randomRow][randomCol] = true;
            restart[randomRow][randomCol] = true;
        } else {
            i--;
        }
    }
    
}

private class InputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField source = (JTextField) e.getSource();
        int input = -1;
        try {
            input = Integer.parseInt(source.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input angka");
        }   
            //mengecek cell yang sudah terisi dan yang belum
            LabelBoard.setText("Cells remaining: " + --NumSisa);
        }
        }


public static void main(String[] args) {
    JOptionPane.showMessageDialog(null, "Sudoku");
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            if(args.length < 1 || args[0].contains("easy")) {
                JOptionPane.showMessageDialog(null, "Easy");
                setLevel (1);
            }

            else if(args[0].contains("medium")) {
                JOptionPane.showMessageDialog(null, "Medium");
                setLevel (2);
            }

            else if(args[0].contains("hard")) {
                JOptionPane.showMessageDialog(null, "Hard");
                setLevel (3);
            }
            new Sudoku();
        }
    });
}
}
