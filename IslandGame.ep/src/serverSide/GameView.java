package serverSide;

import javax.swing.JFrame;

public class GameView {
	private JFrame frame = new JFrame("IslandGame");
	private ViewingGrid gridView;
	
	
	public GameView(IslandGrid islandGrid) {
		gridView = new ViewingGrid(islandGrid);
		frame.setContentPane(gridView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(900, 750);
		frame.setLocation(500, 50);
		frame.setVisible(true);
		
	}
	
	public ViewingGrid getViewingGrid() {
		return gridView;
	}
	
}
