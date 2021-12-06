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
public class Game  extends JFrame{
    private static int grid = Puzzle.getGrid();
    private static int subgrid = Puzzle.getSubgrid();
    private static int lebar= grid * 60;
    private static int tinggi = grid * 60;
    private static Color color_blank;
    private static Color color_fill; 
    private static  Color color_true;
    private static  Color color_false;
	private static Color color_number; 
	private static Font font_number;
	private static int NumSisa;
	private JPanel Board;
	private JPanel PanelBoard;
	private JLabel LabelBoard;
	private JMenuBar menu;
    private JMenu MenuBar;
    private JMenu helpMenu;
    private JMenu aboutMenu;
    private JMenu Level;
    private JMenuItem help;
    private JMenuItem about;
    private JMenuItem restartGame;
    private JMenuItem easylevel;
    private JMenuItem mediumlevel;
    private JMenuItem hardlevel;
    private JMenuItem resetGameItem;
    private JMenuItem exitItem;
    private JTextField[][] display;
    private int[][] sudokupuzzle;
    private static boolean[][] restart = {   
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false } 
    };
    private static boolean[][] game = {
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false },
        { false, false, false, false, false, false, false, false, false } 
    };
    Container contentp;

public Game(){
    initComponents();
}
public void initComponents(){
    color_blank = new Color(255, 248, 220); 
    color_true = Color.BLUE; // warna font ketika jawaban user benar
    color_false = Color.RED; // warna font ketika jawaban user salah
    color_fill = new Color(255, 228, 181); 
    color_number = new Color(139, 71, 38); 
    font_number = new Font("Arial", Font.BOLD, 20);
    display = new JTextField[grid][grid];
    sudokupuzzle = Puzzle.getPuzzle();
    contentp = getContentPane();
    contentp.setLayout(new BorderLayout());
    
    //Sudoku Board
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

    //membuat menubar
    menu = new JMenuBar();
    //membuat menu
    MenuBar = new JMenu("Menu");
    menu.add(MenuBar);
    helpMenu = new JMenu("Help");
    menu.add(helpMenu);
    aboutMenu = new JMenu("About");
    Level = new JMenu("Difficulty level");
    menu.add(aboutMenu);
    //menu item
    help = new JMenuItem("Game Instructions");
    helpMenu.add(help);
    about = new JMenuItem("Sudoku Game");
    aboutMenu.add(about);
    restartGame = new JMenuItem("Restart Game");
    MenuBar.add(restartGame);
    MenuBar.add(Level);
    LevelListener difflistener = new LevelListener();
    easylevel = new JMenuItem("Easy");
    easylevel.addActionListener(difflistener);
    Level.add(easylevel);
    mediumlevel = new JMenuItem("Medium");
    Level.add(mediumlevel);
    mediumlevel.addActionListener(difflistener);
    hardlevel = new JMenuItem("Hard");
    hardlevel.addActionListener(difflistener);
    Level.add(hardlevel);
    resetGameItem = new JMenuItem("Reset game");
    MenuBar.add(resetGameItem);
    exitItem = new JMenuItem("Exit");
    MenuBar.add(exitItem);
    setJMenuBar(menu);
    //add actionlistener
    about.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            aboutActionPerformed();
        }
    });
    help.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            helpActionPerformed();
        }
    });
    resetGameItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetActionPerformed();
        }
    });
    restartGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            restartActionPerformed();
        }
    });
    exitItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            exitActionPerformed();
        }
    });
    initGame(Board);
    contentp.setPreferredSize(new Dimension(lebar, tinggi));
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Sudoku Game");
}

private void aboutActionPerformed(){
    JOptionPane.showMessageDialog(null,"Sudoku, juga dikenal Number Place atau Nanpure, adalah sejenis teka-teki logika. \nTujuannya adalah untuk mengisikan angka-angka dari 1 sampai 9, \nke dalam jaring-jaring 9 x 9 yang terdiri dari 9 kotak 3 x 3 tanpa ada angka yang berulang di satu baris, kolom atau kotak");
}

private void helpActionPerformed() {
    JOptionPane.showMessageDialog(null,"1. Pada baris harus di isi oleh angka-angka mulai dari 1-9 dan tidak boleh ada angka yang kembar dalam satu baris.\n2. Pada kolom juga begitu harus di isi dengan angka-angka mulai dari 1-9 dan tidak boleh ada angka yang kembar dalam satu kolom.\n3. Pada setiap bagian kotak area dengan ukuran 3 x 3 yang berisi 9 kotak-kotak kecil. Kotak kecil tersebut harus diisi dengan angka 1-9 dan syaratnya tidak boleh ada angka yang berulang pada setiap kotak area.");
}

private void resetActionPerformed() {
    InputListener listener = new InputListener();
    for (int row = 0; row < grid; ++row) {
        for (int col = 0; col < grid; ++col) {
            if (restart[row][col]) {
                display[row][col].setText(""); // set ke string kosong
                display[row][col].setEditable(true);	// dapat diedit
                display[row][col].setBackground(color_blank);
                display[row][col].addActionListener(listener);
            } else {
                display[row][col].setText(sudokupuzzle[row][col] + ""); // sudah berisi angka
                display[row][col].setEditable(false); // tidak dapat diedit
                display[row][col].setBackground(color_fill);
                display[row][col].setForeground(color_number);
            }
        }
    }
}

private void restartActionPerformed(){
    dispose();
    setLevel("easy");
    new Game().setVisible(true);
}

private void exitActionPerformed() {
    System.exit(0); //exit
}

public static void setLevel (String level) {
    switch (level) {
        case "easy":
            NumSisa = 5 * 9;
            break;
        case "medium":
            NumSisa = 7 * 9;
            break;
        case "hard":
            NumSisa = 8 * 9;
            break;
        default:
            NumSisa = 5 * 9;
            break;
    }
    
    for (int row = 0; row < grid; ++row) {
        for (int col = 0; col < grid; ++col){
            game[row][col] = false;
            restart[row][col] = false;
        }
    }
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
            } else {
                display[row][col].setText(sudokupuzzle[row][col] +"");
                display[row][col].setEditable(false);
                display[row][col].setBackground(color_fill);
                display[row][col].setForeground(color_number);
            }
        }
    }
}

private class LevelListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "Easy":
            JOptionPane.showMessageDialog(null, "Easy");
            setLevel("easy");
            dispose();
            new Game().setVisible(true);
            break;
        case "Medium":
            JOptionPane.showMessageDialog(null, "Medium");
            setLevel("medium");
            dispose();
            new Game().setVisible(true);
            break;
        case "Hard":
            JOptionPane.showMessageDialog(null, "Hard");
            setLevel("hard");
            dispose();
            new Game().setVisible(true);
            break;
        default:
            JOptionPane.showMessageDialog(null, "Easy");
            setLevel ("easy");
            dispose();
            new Game().setVisible(true);
            break;
        }
    }
}

private class InputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int rowBoard = -1;
        int colBoard = -1;
        JTextField source = (JTextField) e.getSource();
        boolean found = false;
        for (int row = 0; row < grid && !found; row++) {
            for (int col = 0; col < grid && !found; col++) {
                if (display[row][col] == source) {
                    rowBoard = row;
                    colBoard = col;
                    found = true;
                }
            }
        }
        int input = -1;
        try {
            input = Integer.parseInt(source.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input angka");
        }   
        int firstRow = rowBoard / subgrid * subgrid;
        int firstCol = colBoard / subgrid * subgrid;
    
        if (input == sudokupuzzle[rowBoard][colBoard]) {
            display[rowBoard][colBoard].setForeground(color_true);
            display[rowBoard][colBoard].setEditable(false);
            game[rowBoard][colBoard] = false;
            for (int row = firstRow; row < firstRow + subgrid; row++) {
                for (int col = firstCol; col < firstCol + subgrid; col++) {
                    if (!game[row][col]) {
                        display[row][col] .setBackground(color_fill);
                    }
                }
            }
            
            //mengecek cell yang sudah terisi dan yang belum
            LabelBoard.setText("Cells remaining: " + --NumSisa);
        } else {
            display[rowBoard][colBoard]
                    .setForeground(color_false);
            // mengubah background angka pada subgrid yang sama dengan inputan
            for (int row = firstRow; row < firstRow
                    + subgrid; row++) {
                for (int col = firstCol; col < firstCol
                        + subgrid; col++) {
                    if (sudokupuzzle[row][col] == input && !game[row][col]) {
                        display[row][col]
                        .setBackground(color_false);
                    }
                }
            }
        }
        // cek apakah permainan sudah berhasil diselesaikan
        boolean winner = true;
        for (int row = 0; row < grid && winner; row++) {
            for (int col = 0; col < grid && winner; col++) {
                winner = !game[row][col];
            }
        }

        if (winner) {
            JOptionPane.showMessageDialog(null, "Menang!!!!");
        }
    }
}
}
