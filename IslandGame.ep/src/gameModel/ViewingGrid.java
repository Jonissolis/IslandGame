package gameModel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * This class requires a lot of work. 
 *
 */
public class ViewingGrid extends JPanel implements Observer {

	private JFrame frame = new JFrame();
	private IslandGrid islandGrid;
	private int gridWidth;
	private int gridHeight;
	public ViewingGrid(IslandGrid islandGrid) {
		this.islandGrid = islandGrid;
		this.gridWidth = islandGrid.getWidth();
		this.gridHeight = islandGrid.getHeight();
		for(int i = 0; i < gridWidth; i++) {
			for(int j = 0; j < gridHeight; j++) {
				islandGrid.getTile(i, j).addObserver(this);
			}
		}
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int x = 0; x < gridWidth; x++) {
			for(int y = 0; y < gridHeight; y++) {
				if(islandGrid.getTile(x, y).isOccupied()) {
					g.setColor(Color.BLUE);
					g.fillRect(x*getWidth()/gridWidth, y*getHeight()/gridHeight, this.getWidth()/gridWidth, this.getHeight()/gridHeight);
				}
				else if(islandGrid.getTile(x, y).isBlocked()) {
					g.setColor(Color.RED);
					g.fillRect(x*getWidth()/gridWidth, y*getHeight()/gridHeight, this.getWidth()/gridWidth, this.getHeight()/gridHeight);
				}
				else {
					g.setColor(Color.GREEN);
					g.fillRect(x*getWidth()/gridWidth, y*getHeight()/gridHeight, this.getWidth()/gridWidth, this.getHeight()/gridHeight);
				}
				
			}
		}
	}

	public static void main(String[] args) {
		IslandGrid islandGrid = new IslandGrid();
		new Thread(new SimpleAI(islandGrid)).start();
		new Thread(new SimpleAI(islandGrid)).start();
		new Thread(new SimpleAI(islandGrid)).start();
//		new Thread(new SimpleAI(islandGrid)).start();
		new ViewingGrid(islandGrid);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
		
	}
}