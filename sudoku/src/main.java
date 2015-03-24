
public class main {
	static int [][] test = {
			{1,2,3,4,5,6},
			{4,5,6,3,4,5},
			{3,6,1,2,3,4},
			{4,5,6,1,2,3},
			{3,1,5,6,1,2},
			{2,6,4,5,6,1}
	};
	static int[] level = {0,0,1,1,1,1,1,1,1};
	public static void main(String[] args){
		
		produce sudoku= new produce();
		
		int[][] sudoku_matrix = new int[9][9];
		int[][] random_matrix = new int[9][9];
		int[]   random1 = new int[9];
		sudoku_matrix = sudoku.generatePuzzleMatrix();	
		random_matrix = sudoku.generatePuzzleQuestion(2);
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(" " + sudoku_matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(" " + random_matrix[i][j]);
			}
			System.out.println();
		}
		
	}
}
