import java.util.Random;
public class Puzzle {
    public static final int grid = 9;   
    public static final int subgrid = 3; 
    public static Random random = new Random();  

    private static int[][] puzzle = {
        { 5, 9, 8, 2, 7, 4, 3, 6, 1 },
        { 4, 7, 2, 1, 6, 3, 9, 5, 8 },
        { 1, 3, 6, 8, 5, 9, 2, 4, 7 },
        
        { 7, 2, 3, 5, 1, 8, 6, 9, 4 },
        { 8, 1, 9, 3, 4, 6, 5, 7, 2 },
        { 6, 5, 4, 7, 9, 2, 8, 1, 3 },
        
        
        { 3, 6, 1, 9, 2, 7, 4, 8, 5 },
        { 2, 4, 7, 6, 8, 5, 1, 3, 9 },
        { 9, 8, 5, 4, 3, 1, 7, 2, 6 }
    };
    
    private static int[][] generateSudoku() {
        int[] rowTemplate;
        for(int i=0; i<10; i++) {
            // acak nomor baris 1-9
            // utk i=0. misal dirandom dan didapat rowNumber = 1
            // utk i=1. misal dirandom dan didapat rowNumber = 2
            int rowNumber = (random.nextInt(subgrid));
            int newRow = (rowNumber*subgrid) / grid;
            // tukar baris 
            rowTemplate = puzzle[rowNumber];
            puzzle[rowNumber] = puzzle[newRow];
            puzzle[newRow] = rowTemplate;
        }
        return puzzle;
    }

    public static int[][] getPuzzle() { //mereturn puzzle sudoku 
        return generateSudoku(); 
    }

}
