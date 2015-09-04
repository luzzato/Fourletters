package net.nadjib.draw;






import net.nadjib.fourletters.GameScreen;
import net.nadjib.fourletters.GameScreen.GameState;
import net.nadjib.storage.GameOverMenuStorage;
import net.nadjib.storage.GeneralStorage;
import net.nadjib.storage.MenuStorage;

import net.nadjib.storage.SplashStorage;
import net.nadjib.storage.WordsStorage;
import net.nadjib.update.RunningUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import C.C;

//This class draws the splash on the screen

public class SplashDraw {
	
	public static float time_acu_splash = 0;
	
	public static void draw(){
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 Gdx.gl.glEnable(GL20.GL_BLEND);
		GameScreen.batch.begin();
		
		//Drawing splash background
		SplashStorage.splash_sprite.draw(GameScreen.batch);
		
		//Storing time of splash
		time_acu_splash += Gdx.graphics.getDeltaTime();
		
		//After 0.8 seconds the game change to the menu
		if(time_acu_splash>=0.8){

			//Loading Game Variables
            GeneralStorage.load();
            GameScreen.actionResolver.initializeLeaderboard();
            MenuStorage.load();
            RunningUpdate.letters_played = 0;
            WordsStorage.load();
            GameOverMenuStorage.load();
            RunningUpdate.initialize_game();
			GameScreen.state = GameState.MENU;


            if(C.BACKGROUND_MUSIC) {
                if (GeneralStorage.prefs.getInteger("sound", 1) == 1)
                    GeneralStorage.background_music.play();
            }



			
		}
		
		GameScreen.batch.end();
	}

}
