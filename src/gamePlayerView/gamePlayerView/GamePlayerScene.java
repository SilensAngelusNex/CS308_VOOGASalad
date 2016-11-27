package gamePlayerView.gamePlayerView;

import java.util.ArrayList;
import java.util.List;

import gamePlayerView.GUIPieces.CashBox;
import gamePlayerView.GUIPieces.LivesBox;
import gamePlayerView.GUIPieces.MapDisplay;
import gamePlayerView.GUIPieces.StatisticsRow;
import gamePlayerView.GUIPieces.TowerColumn;
import gamePlayerView.GUIPieces.WavesBox;
import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.ILivesAcceptor;
import gamePlayerView.interfaces.IWavesAcceptor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * @author Guhan Muruganandam
 * 
 */

public class GamePlayerScene {
	
	private Stage myStage;
	private TowerColumn myTowerColumn;
	private StatisticsRow myStatisticsRow;
	private MapDisplay myMap;
	private Scene myScene;
	private List<ICashAcceptor> myCash;
	private List<ILivesAcceptor> myLives; 
	private List<IWavesAcceptor> myWaves;
	
	public GamePlayerScene(){
		//myStage=stage;
		//init();
	}

	public void init(Stage s) {
		Scene myScene=build(s);
		setScene(s,myScene);
	}

	private void setScene(Stage s, Scene scene) { ///public or private
		s.setScene(scene);
		s.show();
	}
	/*
	public List<ICashAcceptor> getCashAcceptors()
	{
		List<ICashAcceptor> acceptors = new ArrayList<ICashAcceptor>();
		acceptors.add(gui.getCash());
		return acceptors;
	}
	public List<IResourceStoreAcceptor> getResourceStoreAcceptors()
	{
		//TODO:
		// get all frontend components that need info from the resource store (available towers, ect)
	}
	*/

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
	
	public List<ICashAcceptor> getCash() {
		CashBox cashbox=myStatisticsRow.getCash();
		myCash.add(cashbox);
		return myCash;
	}

	public List<ILivesAcceptor> getLives() {
		LivesBox livesbox=myStatisticsRow.getLives();
		myLives.add(livesbox);
		return myLives;
	}

	public List<IWavesAcceptor> getWaves() {
		WavesBox wavesbox=myStatisticsRow.getWaves();
		myWaves.add(wavesbox);
		return myWaves;
	}
}

