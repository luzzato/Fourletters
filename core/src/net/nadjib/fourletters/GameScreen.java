package net.nadjib.fourletters;



import net.nadjib.draw.GameOverDraw;
import net.nadjib.draw.GameOverMenuDraw;
import net.nadjib.draw.MenuDraw;

import net.nadjib.draw.RunningDraw;
import net.nadjib.draw.SplashDraw;


import net.nadjib.storage.SplashStorage;
import net.nadjib.update.GameOverMenuUpdate;
import net.nadjib.update.GameOverUpdate;
import net.nadjib.update.MenuUpdate;

import net.nadjib.update.RunningUpdate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ApplicationAdapter {
	
	public static int score, best;
	public static ActionResolver actionResolver;

    public GameScreen() {

    }

    public enum GameState {
		   SPLASH, RUNNING, GAMEOVER, MENU, GAMEOVER_MENU, PAUSE
		 }
	public static GameState state; 
	public static SpriteBatch batch;
	
	//The action resolver allows to call android methods
	public GameScreen(ActionResolver actionResolver) {
		    this.actionResolver = actionResolver;
		  }
	
	
	@Override
	public void create () {
		
		//The android begins with splash screen
		state = GameState.SPLASH;
		
			
			SplashDraw.time_acu_splash=0;	
			
			//Batch for drawing on the screen
			batch = new SpriteBatch();
			
			SplashStorage.load();


	}

	@Override
	public void render () {

		switch(state){

		case SPLASH:

			SplashDraw.draw();

		break;
			
		case MENU:
			
			MenuUpdate.update();
			
			MenuDraw.draw();
		
		break;
		
		case RUNNING:
			
			RunningUpdate.update();
			
			RunningDraw.draw();
				
			break;
			
		case GAMEOVER:
			
			GameOverUpdate.update();
			
			GameOverDraw.draw();
			
			break;
		
		case GAMEOVER_MENU:
			
			GameOverMenuUpdate.update();
			
			GameOverMenuDraw.draw();
			
		break;

		
		}
		
		

		
	}
}
