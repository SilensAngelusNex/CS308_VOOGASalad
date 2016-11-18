package authoring.view.side_panel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Christopher Lu
 * This class initiates the pane that contains the tab that allows user to edit towers, enemies, terrain, and game mechanics.
 */

public class InfoTabs {

	private TabPane infoTab;
	private int screenWidth;
	private int screenHeight;
	
	public InfoTabs(BorderPane root) {
		screenInfo();
		infoTab = new TabPane();
		Tab towerTab = new TowerTab(infoTab);
		Tab enemyTab = new EnemyTab(infoTab);
		Tab waveTab = new WaveLevelTab(infoTab);
		Tab gameTab = new GameTab(infoTab);
		infoTab.getTabs().addAll(towerTab, enemyTab, waveTab, gameTab);
		infoTab.setPrefSize(screenWidth/5, screenHeight);
		root.setLeft(infoTab);
	}
	
	private void screenInfo() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
}
