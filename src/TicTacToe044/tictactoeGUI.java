package TicTacToe044;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;


// X = player O = opponent ? = unused square
public class tictactoeGUI {
	

	// gui stuff
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		tictactoeGUI window = new tictactoeGUI();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public tictactoeGUI() {
		initialize();
	}
	static class gameBoard{
		public static char game[][] = new char[3][3];
	}

	// game logic stuff
	static class Vars {
		int bestMoveRowNum;
		int bestMoveColNum;
	}

	static Boolean drawTest(char game[][]) {
		Boolean isDraw = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (game[i][j] == '?') {
					isDraw = false;
				}
			}
		}
		return isDraw;
	}

	static int isWin(char game[][]) {
	    for (int i = 0; i < 3; i++){
	        if (game[i][0] == game[i][1] && game[i][1] == game[i][2]){
	            if (game[i][0] == 'X') {
	                return +1;
	            }
	            else if (game[i][0] == 'O') {
	                return -1;
	            }
	        }
	    }
	    for (int j = 0; j < 3; j++){
	        if (game[0][j] == game[1][j] && game[1][j] == game[2][j]){
	            if (game[0][j] == 'X') {
	                return +1;
	            }
	            else if (game[0][j] == 'O') {
	                return -1;
	            }
	        }
	    }
	    if (game[0][0] == game[1][1] && game[1][1] == game[2][2]){
	        if (game[0][0] == 'X') {
	            return +1;
	        }
	        else if (game[0][0] == 'O') {
	            return -1;
	        }
	    }
	    if (game[0][2] == game[1][1] && game[1][1] == game[2][0]){
	        if (game[0][2] == 'X') {
	            return +1;
	        }
	        else if (game[0][2] == 'O') {
	        	return -1;
	        }
	            
	    }
	    return 0;
	}
	// algorithm (recursive brute force)
	static int algorithm(char game[][], int depth, Boolean maximizer) {
		int score = isWin(game);
		if (score == 1) {
			return score;
		}
		if (score == -1) {
			return score;
		}
		if (drawTest(game) == true) {
			return 0;
		}
		if (maximizer) {
			int best = -1000;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (game[i][j] == '?') {
						game[i][j] = 'X';
						best = Math.max(best, algorithm(game, depth + 1, !maximizer));
						game[i][j] = '?';
					}
				}
			}
			return best;
		} else {
			int best = 1000;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (game[i][j] == '?') {
						game[i][j] = 'O';
						best = Math.min(best, algorithm(game, depth + 1, !maximizer));
						game[i][j] = '?';
					}
				}
			}
			return best;
		}
	}
	static Vars findBestMove(char board[][]) {
		int bestFoundMove = 1000;
		Vars bestMove = new Vars();
		bestMove.bestMoveRowNum = -1;
		bestMove.bestMoveColNum = -1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '?') {
					board[i][j] = 'O';
					int moveVal = algorithm(board, 0, true);
					board[i][j] = '?';
					if (moveVal < bestFoundMove) {
						bestMove.bestMoveRowNum = i;
						bestMove.bestMoveColNum = j;
						bestFoundMove = moveVal;
					}
				}
			}
		}
		gameBoard.game[bestMove.bestMoveRowNum][bestMove.bestMoveColNum] = 'O';
		return bestMove;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.POPUP);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard.game[i][j] = '?';
			}
		}
		// Reset Button
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBounds(0, 1, 151, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); // you can't see me!
				frame.dispose(); // Destroy the JFrame object
				tictactoeGUI window = new tictactoeGUI();
				window.frame.setVisible(true);

			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		// tic tac toe grid
		JButton btnNewButton_3= new JButton("        "),btnNewButton_4= new JButton("        "),btnNewButton_5= new JButton("        "),btnNewButton_6= new JButton("        "),btnNewButton_7= new JButton("        "),btnNewButton_8= new JButton("        "),btnNewButton_9= new JButton("        "),btnNewButton_10= new JButton("        "),btnNewButton_11= new JButton("        ");
		JLabel lblNewLabel = new JLabel("  ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(258, 5, 100, 14); 
		frame.getContentPane().add(lblNewLabel);
		btnNewButton_3.setBounds(0, 29, 151, 77);
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[0][0] = 'X';
				btnNewButton_3.setEnabled(false);
				int test =isWin(gameBoard.game);
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 0) {
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_4.setBounds(156, 29, 139, 77);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[0][1] = 'X';
				btnNewButton_4.setEnabled(false);
				int test =isWin(gameBoard.game);
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test2 =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_4);
		btnNewButton_5.setBounds(300, 29, 144, 77);
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[0][2] = 'X';
				btnNewButton_5.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_5);
		btnNewButton_6.setBounds(0, 111, 151, 77);
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[1][0] = 'X';
				btnNewButton_6.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_6);
		btnNewButton_7.setBounds(156, 111, 139, 77);
		btnNewButton_7.setForeground(Color.WHITE);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[1][1] = 'X';
				btnNewButton_7.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_7);
		btnNewButton_8.setBounds(300, 111, 144, 77);
		btnNewButton_8.setForeground(Color.WHITE);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[1][2] = 'X';
				btnNewButton_8.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_8);
		btnNewButton_9.setBounds(0, 193, 151, 77);
		btnNewButton_9.setForeground(Color.WHITE);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[2][0] = 'X';
				btnNewButton_9.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_9);
		btnNewButton_10.setBounds(156, 193, 139, 77);
		btnNewButton_10.setForeground(Color.WHITE);
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[2][1] = 'X';
				btnNewButton_10.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_10);
		btnNewButton_11.setBounds(300, 193, 144, 77);
		btnNewButton_11.setForeground(Color.WHITE);
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
						.getResource("/TicTacToe044/99-992857_inner-page-closing-comments-tic-tac-toe-x_300x300.png")));
				gameBoard.game[2][2] = 'X';
				btnNewButton_11.setEnabled(false);
				int test2 =isWin(gameBoard.game); ;
				if (test2 == 1) {
					lblNewLabel.setText("Player Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test2 == -1) {
					lblNewLabel.setText("Computer Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				Vars compMove = findBestMove(gameBoard.game);
				gameBoard.game[compMove.bestMoveRowNum][compMove.bestMoveColNum] = 'O';
				System.out.println(Arrays.deepToString(gameBoard.game));
				if (gameBoard.game[0][0] == 'O') {
					btnNewButton_3.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_3.setEnabled(false);
				}
				if (gameBoard.game[0][1] == 'O') {
					btnNewButton_4.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_4.setEnabled(false);
				}
				if (gameBoard.game[0][2] == 'O') {
					btnNewButton_5.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_5.setEnabled(false);
				}
				if (gameBoard.game[1][0] == 'O') {
					btnNewButton_6.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_6.setEnabled(false);
				}
				if (gameBoard.game[1][1] == 'O') {
					btnNewButton_7.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_7.setEnabled(false);
				}
				if (gameBoard.game[1][2] == 'O') {
					btnNewButton_8.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_8.setEnabled(false);
				}
				if (gameBoard.game[2][0] == 'O') {
					btnNewButton_9.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_9.setEnabled(false);
				}
				if (gameBoard.game[2][1] == 'O') {
					btnNewButton_10.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_10.setEnabled(false);
				}
				if (gameBoard.game[2][2] == 'O') {
					btnNewButton_11.setIcon(new ImageIcon(tictactoeGUI.class
							.getResource("/TicTacToe044/o-300x300.png")));
					btnNewButton_11.setEnabled(false);
				}
				int test =isWin(gameBoard.game);
				if (test2 == 0) {
				if (test == 1) {
					lblNewLabel.setText("Play Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
				}
				if (test == -1) {
					lblNewLabel.setText("Comp Win");
					btnNewButton_11.setEnabled(false);
					btnNewButton_10.setEnabled(false);
					btnNewButton_9.setEnabled(false);
					btnNewButton_8.setEnabled(false);
					btnNewButton_7.setEnabled(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_5.setEnabled(false);
					btnNewButton_4.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				}
			}
		});
		frame.getContentPane().add(btnNewButton_11);
		System.out.println(Arrays.deepToString(gameBoard.game));	
	}
}