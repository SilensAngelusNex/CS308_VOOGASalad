package authoring.view.display;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * @author Christopher Lu
 * Creates the game display in the authoring environment, where the map is divided into clickable cells that allows the user
 * to change the property of the terrain upon click, including color and terrain type.
 */

public class GameDisplay {

	private VBox terrainContainer;
	private ScrollPane terrainArea;
	private TilePane terrainGrid;
	private GridToolBar toolBar;
	public int DEFAULT_TILE_SIZE = 50;
	public int GAP = DEFAULT_TILE_SIZE/20;
	private int columns; // Number of blocks in the horizontal direction of the gridPane. Can be set by user.
	private int rows; // Number of blocks in the vertical direction of the gridPane. Can be set by user.
	private int screenWidth;
	private int screenHeight;
	
	public GameDisplay(BorderPane root) {
		setUpScreenResolution();
		this.terrainContainer = new VBox();
		this.terrainArea = new ScrollPane();
		this.terrainGrid = new TilePane();
		this.toolBar = new GridToolBar(terrainContainer);
		terrainGrid.setPrefWidth(screenWidth*0.8);
		terrainGrid.setMaxHeight(screenHeight*0.9);
		terrainArea.setContent(terrainGrid);
		terrainContainer.getChildren().add(terrainArea);
		root.setCenter(terrainContainer);
		columns = (int) (screenWidth*0.8/(DEFAULT_TILE_SIZE + GAP));
		rows = (int) (screenHeight*0.88/(DEFAULT_TILE_SIZE + GAP));
		populateGrid();
		clickEvent();
	}
	
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	private void populateGrid() {
		terrainGrid.getChildren().clear();
		terrainGrid.setHgap(GAP);
		terrainGrid.setVgap(GAP);
		for (int col = 0; col < columns; col++) {
			for (int r = 0; r < rows; r++) {
				TerrainCell cell = new TerrainCell(toolBar);
				cell.setWidth(DEFAULT_TILE_SIZE);
				cell.setHeight(DEFAULT_TILE_SIZE);
				terrainGrid.getChildren().add(cell);
			}
		}
	}
	
	private void clickEvent() {
		TerrainCell cell = new TerrainCell(toolBar);
		terrainArea.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getTarget().getClass().equals(cell.getClass())) {
					if (toolBar.getToggleStatus()) {
//						terrainArea.setContent(null);
						TerrainCell c = (TerrainCell) mouseEvent.getTarget();
						c.setColor(toolBar.getSelectedColor());
						c.setType(toolBar.getSelectedTerrain());
						c.setWidth(DEFAULT_TILE_SIZE);
						c.setHeight(DEFAULT_TILE_SIZE);
						terrainGrid.getChildren().remove(mouseEvent.getTarget());
						System.out.println("Removed");
						System.out.println("Width: " + c.getWidth());
						System.out.println("Height: " + c.getHeight());
						System.out.println("Color: " + c.getColor());
						System.out.println("Type: " + c.getType());
						terrainGrid.getChildren().add(c);
						terrainArea.setContent(terrainGrid);
					}
				}
			}
		});
	}

	public void setCols(int numCols) {
		columns = numCols;
		terrainGrid.setPrefColumns(columns);
		populateGrid();
	}
	
	public void setRows(int numRows) {
		rows = numRows;
		terrainGrid.setPrefRows(rows);
		populateGrid();
	}
	
}