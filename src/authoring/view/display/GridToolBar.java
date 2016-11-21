package authoring.view.display;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author Christopher Lu
 * Creates the toolbar for the grid, allowing the user to select the draw tool and terrain type and fill in terrain types upon clicking individual cells.
 */

public class GridToolBar {

	private HBox toolBar;
	private ResourceBundle myResources;
	private String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private boolean toggleStatus;
	private Color selectedColor;
	private String selectedTerrain;
	private ObservableList<String> terrainOptions = 
			FXCollections.observableArrayList (
					"Ground",
					"Water",
					"Ice",
					"Acid"
					);
	
	public GridToolBar(VBox box) {
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.toolBar = new HBox();
		createToolBar();
		box.getChildren().add(toolBar);
		toolBar.setAlignment(Pos.BOTTOM_CENTER);
	}
	
	private void createToolBar() {
		ToggleGroup toggles = new ToggleGroup();
		ToggleButton drawMode = new ToggleButton(myResources.getString("DrawMode"));
		drawMode.setToggleGroup(toggles);
		toggleHandler(toggles);
		ColorPicker colorChooser = new ColorPicker();
		colorHandler(colorChooser);
		ComboBox<String> terrainChooser = new ComboBox<String>(terrainOptions);
		terrainHandler(terrainChooser);
		toolBar.getChildren().addAll(drawMode, colorChooser, terrainChooser);
	}
	
	/**
	 * Sets toggleStatus to true if the draw mode toggle button is selected, or false if not.
	 * @param drawMode
	 */
	private void toggleHandler(ToggleGroup drawMode)  {
		drawMode.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle toggle, Toggle new_toggle) {
		            if (new_toggle == null) {
		                toggleStatus = false;
		            }
		            else {
		               toggleStatus = true;
		            }
		    }
		});
	}
	
	/**
	 * Sets selectedColor to the color chosen by the user when using the Color Picker from the toolbar.
	 * @param colors
	 */
	private void colorHandler(ColorPicker colors) {
		colors.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent e) {
				selectedColor = colors.getValue();
			}
		});
	}
	
	/**
	 * Sets selectedTerrain to the terrain chosen by the user when using the terrain combo box from the toolbar.
	 * @param terrains
	 */
	private void terrainHandler(ComboBox<String> terrains) {
		terrains.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				selectedTerrain = terrains.getSelectionModel().getSelectedItem();

			}
		});
	}
	
	public boolean getToggleStatus() {
		return toggleStatus;
	}
	
	public Color getSelectedColor() {
		return selectedColor;
	}
	
	public String getSelectedTerrain() {
		return selectedTerrain;
	}
	
}