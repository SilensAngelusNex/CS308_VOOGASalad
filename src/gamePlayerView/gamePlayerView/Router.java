package gamePlayerView.gamePlayerView;

import java.util.List;

import authoring.model.map.MapData;
import engine.IObservable;
import gamePlayerView.interfaces.ICashAcceptor;
import gamePlayerView.interfaces.ILivesAcceptor;
import gamePlayerView.interfaces.IWavesAcceptor;

public class Router {
	private GamePlayerScene  myGamePlayerScene;
	//TODO: Change these box objects to instead be acceptor interfaces
	private List<ICashAcceptor> myCash;
	private List<ILivesAcceptor> myLives; 
	private List<IWavesAcceptor> myWaves;
	//List<IEnemySources> myFrontendEnemySources;
	
	
	public Router(GamePlayerScene aGamePlayerScene)
	{
		myGamePlayerScene  = aGamePlayerScene;
		//myCash=myGamePlayerScene.getCash();
		//myLives=myGamePlayerScene.getLives();
		//myWaves=myGamePlayerScene.getWaves();
		//myFrontendEnemySources = myGamePlayerScene.getEnemySources();
	}
	
	public void distributeMapData(MapData aMapData)
	{
		//TODO: distribute to all interested frontend objects
	}
	
	public void distributeCash(IObservable aCash){
		//myCash.acceptCash(aCash);
	}
	public void distributeLives(IObservable aLives){
		//myLives.acceptLives(aLives); 
	}
	public void distributeWaves(IObservable aWaves){
		//myWaves.acceptWaves(aWaves); 
	}
	
	/**
	 * An example to follow for setting up the router
	 * @param aResourceStore
	 */
	/*
	public void distributeResourceStore(IViewableResourceStore aResourceStore)
	{
		myGamePlayerScene.getResourceStoreAcceptors().forEach(acceptor -> acceptor.acceptResourceStore(aResourceStore));
	}
	*/
	
	
	/*
	 * void distributeEnemy(IViewableEnemy aEnemy)
	 * {
	 * 		for (IEnemySource enemySource: myFrontendEnemySources) {
	 * 			enemySource.giveEnemy(aEnemy)
	 * 		]
	 * }
	 * 
	 * 
	 * 
	 * over  in some IEnemySource object:
	 * 
	 * @Override
	 * void giveEnemy(IViewableEnemy aEnemy)
	 * {
	 * 		aEnemy.attach(this);
	 * }
	 * 
	 * @Override
	 * void update()
	 * {
	 * 		//redraw the enemy
	 * }
	 */
	
}
