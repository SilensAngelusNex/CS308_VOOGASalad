package authoring.view.tabs;

import java.util.ArrayList;

import authoring.controller.Container;
import authoring.controller.WaveDataContainer;
import authoring.model.WaveData;
import authoring.controller.MapDataContainer;
import authoring.controller.EntityDataContainer;
import engine.IObserver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WaveTab extends ListTab<String> implements IObserver<Container>, ISubmittable{

    private static final int COLS = 2;
    
	private WaveDataContainer myContainer;
	private boolean isDefault;
	private TextField myNameField;
	private TextField myTimeBetweenField;
	private TextField myTimeForField;
	private TextField myNumEnemiesField;
	private ComboBox<String> myEnemyBox;
	private ComboBox<String> mySpawnBox;
	private VBox myV;
	private String myName;
	
	
	private ObservableList<String> myEntities = FXCollections.observableList(new ArrayList<String>());
	private ObservableList<String> mySpawnPoints = FXCollections.observableList(new ArrayList<String>());
	
	public WaveTab(String text, WaveDataContainer container){
		super(text, COLS);
		myContainer = container;
	}
	

	@Override
	protected EventHandler handleAddNewObject() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				isDefault = true;
				VBox menu = setUpMenu(getResources().getString("DefaultWaveName"), 
						getResources().getString("DefaultTimeBetween"), 
						getResources().getString("DefaultTimeFor"), 
						getResources().getString("DefaultNumberEnemies"), null, null);
				getTilePane().getChildren().add(menu);
			}
		};
	}
	
	private VBox setUpMenu(String name, String timeBetween, String timeFor, String numEnemies, 
			String enemy, String spawn){
		myV = new VBox();
		myV.setId("blue-background");
		myNameField = setUpTextInputWithLabel(getResources().getString("EnterName"), name, myV);
		myTimeBetweenField = setUpTextInputWithLabel(getResources().getString("EnterTimeBetween"), 
				timeBetween, myV);
		myTimeForField = setUpTextInputWithLabel(getResources().getString("EnterTimeFor"), timeFor,
				myV);
		myNumEnemiesField = setUpTextInputWithLabel(getResources().getString("EnterNumberEnemies"),
				numEnemies, myV);
		myEnemyBox = setUpStringComboBoxWithLabel(getResources().getString("EnterEnemyName"),
				enemy, myEntities, myV);
		mySpawnBox = setUpStringComboBoxWithLabel(getResources().getString("EnterSpawnPoint"),
				spawn, mySpawnPoints, myV);
		myName = name;
		Button finish = setUpFinishButton();
		Button cancel = setUpCancelButton(myV);
		HBox h = new HBox();
		h.getChildren().addAll(finish, cancel);
		myV.getChildren().add(h);
		return myV;
	}


	/**
	 * IObserver interface methods
	 */
	public void update(Container c){
		if (c instanceof MapDataContainer){
			mySpawnPoints.clear();
			for (String spawnPoint: ((MapDataContainer) c).getSpawnPointMap().keySet()){
				mySpawnPoints.add(spawnPoint);
			}
		}else if(c instanceof EntityDataContainer){
			myEntities.clear();
			for (String enemyName: ((EntityDataContainer) c).getEntityDataMap().keySet()){
				myEntities.add(enemyName);
			}
		}
	}


	@Override
	protected void edit(String name) {
		isDefault = false;
		WaveData wave = myContainer.getWaveData(name);
		VBox menu = setUpMenu(wave.getName(), String.valueOf(wave.getTimeBetweenEnemy()), 
				String.valueOf(wave.getTimeUntilNextWave()), String.valueOf(wave.getNumEnemies()), 
				wave.getWaveEnemy(), wave.getSpawnPointName());
		getTilePane().getChildren().add(menu);
	}

	public Button setUpFinishButton() {
		Button finish = new Button(getResources().getString("Finish"));
		finish.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				WaveData wave = new WaveData();
				try {
					wave.setName(myNameField.getText());
					wave.setTimeBetweenEnemy(myTimeBetweenField.getText());
					wave.setTimeForWave(myTimeForField.getText());
					wave.setNumEnemies(myNumEnemiesField.getText());
					wave.setWaveEnemy(myEnemyBox.getValue());
					wave.setSpawnPointName(mySpawnBox.getValue());
				} catch(Exception e){
					showError(e.getMessage());
					return;
				}
				if (isDefault)
					myContainer.createNewWave(myNameField.getText(), wave);
				else{
					getObservableList().remove(myName);
					myContainer.updateWave(wave, myName);
				}
				getTilePane().getChildren().remove(myV);
				getObservableList().add(myNameField.getText());
			}
		});
		return finish;
	}
}
