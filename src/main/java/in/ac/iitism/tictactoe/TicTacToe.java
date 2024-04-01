package in.ac.iitism.tictactoe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TicTacToe extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JButton[][] buttons = new JButton[3][3];
	private boolean currentPlayer = true; // true for X, false for O
	private final JLabel statusLabel = new JLabel("X's turn", SwingConstants.CENTER);

	public TicTacToe() {
		setTitle("Tic Tac Toe");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null); // Center the window on the screen

		// Create a panel for the grid
		JPanel gridPanel = new JPanel(new GridLayout(3, 3));
		add(gridPanel, BorderLayout.CENTER);
		add(statusLabel, BorderLayout.SOUTH);
		statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
		statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		// Initialize buttons
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton button = new JButton();
				buttons[i][j] = button;
				button.setFont(new Font("Arial", Font.PLAIN, 40));
				button.setFocusPainted(false);
				button.addActionListener(new ButtonListener());
				gridPanel.add(button);
			}
		}
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getText().equals("")) {
				if (currentPlayer) {
					button.setText("X");
					statusLabel.setText("O's turn");
				} else {
					button.setText("O");
					statusLabel.setText("X's turn");
				}
				currentPlayer = !currentPlayer;
				checkForWin();
			}
		}
	}

	public void checkForWin() {
		for (int i = 0; i < 3; i++) {
			// Check rows and columns
			if (!buttons[i][0].getText().equals("") && 
					buttons[i][0].getText().equals(buttons[i][1].getText()) && 
					buttons[i][1].getText().equals(buttons[i][2].getText()) ||
					!buttons[0][i].getText().equals("") && 
					buttons[0][i].getText().equals(buttons[1][i].getText()) && 
					buttons[1][i].getText().equals(buttons[2][i].getText())) {
				JOptionPane.showMessageDialog(this, buttons[i][0].getText() + " wins!");
				resetBoard();
				return;
			}
		}

		// Check diagonals
		if (!buttons[0][0].getText().equals("") &&
				buttons[0][0].getText().equals(buttons[1][1].getText()) && 
				buttons[1][1].getText().equals(buttons[2][2].getText()) ||
				!buttons[0][2].getText().equals("") &&
				buttons[0][2].getText().equals(buttons[1][1].getText()) && 
				buttons[1][1].getText().equals(buttons[2][0].getText())) {
			JOptionPane.showMessageDialog(this, buttons[1][1].getText() + " wins!");
			resetBoard();
			return;
		}

		// Check for draw
		boolean draw = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (buttons[i][j].getText().equals("")) {
					draw = false;
					break;
				}
			}
			if (!draw) break;
		}
		if (draw) {
			JOptionPane.showMessageDialog(this, "Draw!");
			resetBoard();
		}
	}

	public void resetBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setText("");
			}
		}
		currentPlayer = true; // Reset to player X
		statusLabel.setText("X's turn"); // Reset the status label
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TicTacToe game = new TicTacToe();
			game.setVisible(true);
		});
	}
}
