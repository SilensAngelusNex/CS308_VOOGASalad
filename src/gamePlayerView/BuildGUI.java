package gamePlayerView;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Guhan Muruganandam
 * 
 */

public class BuildGUI {
	
	private TowerColumn myTowerColumn;
	private StatisticsRow myStatisticsRow;
	private GridView myGridView;
	private MapDisplay myMap;
	private Scene myScene;
	
	public BuildGUI(){
	///empty as of now
	}

	public Scene build(Stage stage) {
		Group gameplayer =new Group();
		myScene = new Scene(gameplayer, 900, 700);
		gameplayer.getChildren().add(setScreen());
		return myScene;
	}
	
	public BorderPane setScreen(){
	    myMap = new MapDisplay();
		myTowerColumn   = new TowerColumn();
		myStatisticsRow = new StatisticsRow();
		BorderPane borderpane=new BorderPane();
		borderpane.setRight(myTowerColumn.getView());
		borderpane.setBottom(myStatisticsRow.getView());
	        borderpane.setCenter(myMap.getView());
		myMap.setupDragging(myScene);
		return borderpane;
	}
}
