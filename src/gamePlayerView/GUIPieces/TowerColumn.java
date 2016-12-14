
package gamePlayerView.GUIPieces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.model.EntityData;
import engine.IObservable;
import engine.IObserver;
import engine.model.playerinfo.IViewablePlayer;
import gamePlayerView.interfaces.IGUIPiece;
import gamePlayerView.interfaces.IResourceAcceptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/**
 * @author Guhan Muruganandam & Grayson Wise
 * @author owenchung(refactored)
 */
public class TowerColumn extends VBox implements IResourceAcceptor, IObserver<IViewablePlayer>, IGUIPiece {
	
	//private ResourceBundle mytext=ResourceBundle.getBundle("Resources/textfiles");
	private ImageView towerToBeDragged;
	private Map<ImageView,EntityData> towerSettings= new HashMap<ImageView,EntityData>();
	private TextArea towerDataDisplay;
	private ListView<ImageView> myTowerInfo;
	//private VBox myColumn;

	
	public TowerColumn() {
		buildTowerColumn();
	}
	
	public EntityData getTowerData(String aTowerName)
	{
		for (EntityData data: towerSettings.values()) {
			if (data.getName().equals(aTowerName)) {
				return data;
			}
		}
		return null;
	}
	
	/**
	 * Builds object that will actually be returned
	 */
	private void buildTowerColumn() {
	    towerDataDisplay= new TextArea();
	    myTowerInfo=new ListView<ImageView>(); 
	    //populatetowerInfo(availableTowers,towerDataDisplay);
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(myTowerInfo, "Towers"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	    
	    getChildren().addAll(resourceTabs,towerDataDisplay);
	}
	/*
	 * Creates ListView for the selected towerData
	 */
	private void populateTowerInfo(IViewablePlayer aPlayer) {
		List<EntityData> availableTowers = aPlayer.getAvailableTowers();
		List<EntityData> affordableTowers = aPlayer.getAffordableTowers();
		
		ObservableList<ImageView> items =FXCollections.observableArrayList();
		towerSettings.clear();
		for(EntityData t : availableTowers){
			ImageView towerPicture = new ImageView();
			//TODO: This is super hack-y.
			String imagePath = t.getComponents().get("PhysicalComponent").getFields().get("myImagePath");
			//TODO: make this cleaner--hard coded now
			if (imagePath.substring(0, 4).equals("src/")) {
				imagePath = imagePath.substring(4);
			}
			Image towerImage = new Image(this.getClass().getClassLoader().getResourceAsStream(imagePath)); //THIS IS IFFY. COME BACK TO THIS
			towerPicture.setImage(towerImage);
			if(!affordableTowers.contains(t)){
				towerPicture.setOpacity(0.3);
			}
			// TODO: Hardcoded, change asap
			towerPicture.setPreserveRatio(true);
			towerPicture.setFitHeight(50);
			towerPicture.setFitWidth(50);
			towerSettings.put(towerPicture,t);
			items.add(towerPicture);
		}
		
		myTowerInfo.setFixedCellSize(50);
        myTowerInfo.setItems(items);
        setDragFunctionality(myTowerInfo);
        setPopulateFunctionality(myTowerInfo,towerDataDisplay);
        getChildren().clear();
        
	    TabPane resourceTabs= new TabPane();
	    resourceTabs.getTabs().add(buildTab(myTowerInfo, "Towers"));
	    resourceTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
	    getChildren().addAll(resourceTabs,towerDataDisplay);
	}
	/*
	 * Sets Mouse Click event for List View
	 */
	private void setPopulateFunctionality(ListView<ImageView> towerSet, TextArea towerDataDisplay) {
		towerSet.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                ImageView towerChosen = towerSet.getSelectionModel().getSelectedItem();
                EntityData tower=towerSettings.get(towerChosen);
                PopulateTowerDataDisplay(tower,towerDataDisplay);
            }  
        });
	}
	/*
	 * Allows Text Area to display attributes based on the Tower selected in the ListView
	 */
	private void PopulateTowerDataDisplay(EntityData tower,TextArea towerDataDisplay) {
		towerDataDisplay.clear();
		towerDataDisplay.setEditable(false);
		String name= new String(tower.getName());
		String cost=new String(Integer.toString(tower.getBuyPrice()));
		
		towerDataDisplay.setText(String.format("Tower Name: %s\nCost: %s\n", name, cost));
	}
	
	private void setDragFunctionality(ListView<ImageView> towerSet) {
		towerSet.setOnDragDetected(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Dragboard db = towerSet.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                towerToBeDragged = towerSet.getSelectionModel().getSelectedItem();
                if(towerToBeDragged.getOpacity()>0.5){
                	content.putImage(towerToBeDragged.getImage());
                	content.putString(towerSettings.get(towerToBeDragged).getName());
                	db.setContent(content);
                	event.consume();
                }
            }  
        });
	}

	private Tab buildTab(Node list, String title) {
		Tab tab= new Tab();
		tab.setText(title);
		tab.setContent(list);
		return tab;
	}

	public Node getView() {
		return this;
	}
	
	public void acceptResources(IObservable<IViewablePlayer> aPlayer) {
		aPlayer.attach(this);
//		update(aPlayer);
	}

	@Override
	public void update(IViewablePlayer aChangedObject) {
		populateTowerInfo(aChangedObject);
	}

	@Override
	public void remove(IViewablePlayer aRemovedObject) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 
	 * @Override // for the IResourceStoreAcceptor interface 
	 * void acceptResourceStore(IViewableResourceStore aResourceStore)
	 * {
	 * 		aResourceStore.attach(this);
	 * }
	 * 
	 * @Override // for the IObserver interface
	 * void update(...)
	 * {
	 * 		refresh the list view
	 * }
	 * 
	 * 
	 * 
	 */
	
	//MIGHT NEED LATER
	/*public IResourceAcceptor getResources() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	/*private Button makeTowerImage(String string, ImageView imageView) {
	Button b =new Button(string,imageView);
	b.setOnMouseEntered(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
        	Tooltip t= new Tooltip("Towers");
	        Tooltip.install(b, t);
        }    
    });
	
	b.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );");
	b.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
        	//TODO:
        }    
    });
	
	return b;
}*/
}