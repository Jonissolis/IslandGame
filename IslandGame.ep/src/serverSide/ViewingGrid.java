package serverSide;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cookieGame.EatCookie;
import interactions.Woodcutting;


/**
 * This class is used to display the grid. 
 * 
 * This class requires a lot of work. 
 *
 */
public class ViewingGrid extends JPanel implements Observer {
	private static final long serialVersionUID = 7830774810728311332L; // Eclipse varnade mig innan jag lagt till detta. 
	
	
	private JFrame frame = new JFrame("Green, blue and red dots");
	private IslandGrid islandGrid;
	private final int gridWidth; // The width of the grid, defined in the constructor. 
	private final int gridHeight; // The height of the grid, defined in the constructor. 
	
	
	public ViewingGrid(IslandGrid islandGrid) {
		this.islandGrid = islandGrid;
		this.gridWidth = islandGrid.getWidth();
		this.gridHeight = islandGrid.getHeight();
		for(int i = 0; i < gridWidth; i++) {
			for(int j = 0; j < gridHeight; j++) {
				islandGrid.getTile(i, j).addObserver(this);
			}
		}
		frame.setContentPane(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(900, 750);
		frame.setLocation(500, 50);
		frame.setVisible(true);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Tile tile;
		for(int x = 0; x < gridWidth; x++) {
			for(int y = 0; y < gridHeight; y++) {
				tile = islandGrid.getTile(x, y);
				if(tile.isOccupied()) {
					drawRectangle(g, Color.BLUE, x, y);
				}
				else if(tile.hasInteraction(EatCookie.INTERACTION_ID)) {
					drawRectangle(g, Color.CYAN, x, y);
				}
				else if(islandGrid.getTile(x, y).isBlocked()) {
					drawRectangle(g, Color.RED, x, y);
				}
				else if(islandGrid.getTile(x, y).hasInteraction(Woodcutting.INTERACTION_ID)) {
					drawRectangle(g, Color.YELLOW, x, y);
				}
				else {
					drawRectangle(g, Color.GREEN, x, y);
				}
			}
		}
	}

	/**
	 * This method draws a filled rectangle in the grid. 
	 * @param g The graphics from paintComponent
	 * @param c The color of the rectangle
	 * @param x The x coordinate of the rectangle
	 * @param y The y coordinate of the rectangle
	 */
	private void drawRectangle(Graphics g, Color c, int x, int y) {
		g.setColor(c);
		int adjustedY = gridWidth-y-1; // This adjusts the y coordinate to make (0,0) be the lower left corner instead of the upper left. 
		g.fillRect(x*getWidth()/gridWidth, adjustedY*getHeight()/gridHeight, this.getWidth()/gridWidth, this.getHeight()/gridHeight);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}