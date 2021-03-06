package GUI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


/*
The tetris class is where most of the game functionality will take place. In terms of
functionality, a lot of the things, such as timer, score, lines cleared, etc, will be kept track
here. More importantly, the board will be set up here, along with each tile being
initialized. The action handler and key adapter are both also implemented here. All the
key events for escape, arrow keys, enter, and ‘p’ are also implemented here.
 */
public class Tetris extends JFrame {

	/*
	 * Game board parameters
	 */
	static int ROWS = 25;
	static int COLS = 15;

	/*
	 * Gui objects
	 */
	private JPanel guiPanel;
	private java.awt.Container container;

	// The grid that holds individual positions
	private JPanel gameGrid;
	private JButton[][] positions;
	private GridLayout board;

	// Timer and associated labels
	private JLabel timeLabel;
	private JLabel helpLabel;
	private JPanel timerPanel;
	private JLabel rowsLabel;
	private Timer timer;
	Integer count;
	
	Integer delay;
	
	TetronimoDrawer drawer;

	public Tetris() {
		super("Tetris");
		container = getContentPane();

		/*
		 * Set up timer
		 */
		delay = 650;
		count = 0;
		timeLabel = new JLabel();
		timeLabel.setFocusable(false);
		helpLabel = new JLabel("    Press 'h' for help  ");
		rowsLabel = new JLabel("    Rows cleared        ");

		timer = new Timer(delay, new timerAction());

		timerPanel = new JPanel();
		timerPanel.setFocusable(false);

		timerPanel.add(timeLabel, BorderLayout.EAST);
		timerPanel.add(helpLabel, BorderLayout.WEST);
		timerPanel.add(rowsLabel, BorderLayout.PAGE_END);
		container.add(timerPanel, BorderLayout.PAGE_START);

		// Overall layout of the GUI
		guiPanel = new JPanel();
		guiPanel.setFocusable(false);

		guiPanel.setLayout((LayoutManager) new BoxLayout(guiPanel, BoxLayout.PAGE_AXIS));
		container.add(guiPanel, BorderLayout.AFTER_LAST_LINE);

		// Add the game grid that will contain the pieces
		gameGrid = new JPanel();
		gameGrid.setFocusable(false);

		board = new GridLayout(ROWS, COLS);
		gameGrid.setLayout(board);
		positions = new JButton[ROWS][COLS];

		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++) {
				positions[i][j] = new JButton();
				positions[i][j].setBackground(Color.black);
				positions[i][j].setVisible(true);
				positions[i][j].setFocusable(false);
				gameGrid.add(positions[i][j]);
			}

		container.add(gameGrid, BorderLayout.CENTER);
		gameGrid.setVisible(true);

		this.addKeyListener(new buttonAction());

		drawer = new TetronimoDrawer(positions, ROWS, COLS, rowsLabel, timeLabel, this);

		setSize(500, 500);
		timer.start();
		setVisible(true);
	}

	public static void main(String[] args) {
		Tetris gui = new Tetris();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/*
	 * Timer and reset methods
	 */
	void pauseTimer() {
		timer.stop();
	}

	void resetGameAndTimer() {
		count = 0;
		timeLabel.setText("   Current time   ");
		rowsLabel.setText("   Rows cleared   ");
	}

	void startTimer() {
		delay = 500;
		timer.setDelay(delay);
		timer.start();
	}

	private class timerAction implements ActionListener {
		timerAction() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			timeLabel.setText("  " + count.toString() + "  ");
			count += 1;
			if( delay > 300){
				delay -= 2;
			}else if (delay > 100){
				delay -= 1;
			}
			
			timer.setDelay(delay);
			
			try {
				drawer.Next();
			} catch (Exception ex) {
				ex.printStackTrace();
				drawer.releaseSemaphore();
			}
		}
	}

	private class buttonAction extends KeyAdapter {
		buttonAction() {
			super();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("Pressed " + e.getKeyChar());

			switch (e.getKeyChar()) {
			case 'h':
			case 'H':
				System.out.println("In if statement " + e.getKeyChar());
				timer.stop();

				JOptionPane.showMessageDialog(null,
						"H for help\n" + "Side arrows to shift left & right\n" +
						"Up arrow to rotate\n"+
						"Down arrow to speed downward\n"+
						"P to pause\n" + "ESC to exit\n",
						"How to play Tetris", JOptionPane.INFORMATION_MESSAGE);
				timer.start();
				break;
			case 'p':
			case 'P':
				timer.stop();
				helpLabel.setText("   Press enter to resume   ");
				break;
			default:
				break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				timer.stop();
				System.exit(0);
			case KeyEvent.VK_DOWN:
				drawer.Down();
				break;
			case KeyEvent.VK_ENTER:
				helpLabel.setText("   Press 'h' for help   ");
				timer.start();
				break;
			case KeyEvent.VK_RIGHT:

				drawer.Right();

				break;
			case KeyEvent.VK_LEFT:

				drawer.Left();

				break;
			case KeyEvent.VK_UP:
				drawer.Rotate();
				break;
			}

		}
	}
}
