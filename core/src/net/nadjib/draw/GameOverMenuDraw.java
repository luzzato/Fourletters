package net.nadjib.draw;



import net.nadjib.storage.GameOverMenuStorage;
import net.nadjib.storage.GeneralStorage;
import net.nadjib.update.RunningUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;



//This class draws the Game Over Menu on the screen

public class GameOverMenuDraw {

    public static String up_string = "Vous pourriez avoir formé", more_words_string;
    public static boolean new_record;
	
	public static void draw(){

        Gdx.gl.glClearColor(231/255f, 227/255f, 215/255f, (float) 0.3);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 Gdx.gl.glEnable(GL20.GL_BLEND);
		
		GeneralStorage.batch.begin();


        //Drawing text
        GeneralStorage.posibilitiesFont.draw(GeneralStorage.batch, up_string,
                (GeneralStorage.w - GeneralStorage.posibilitiesFont.getBounds(up_string).width)/2,
                GeneralStorage.h*0.84f + GeneralStorage.posibilitiesFont.getBounds(up_string).height);

        //Drawing possible word
        for(int i=0; i<4;i++)
            GameOverMenuStorage.letters_gameover.get(i).drawGameOverMenu();


        more_words_string = "et " + String.valueOf(RunningUpdate.posibilities - 1) + " mots de plus";

        //Drawing text
        GeneralStorage.posibilitiesFont.draw(GeneralStorage.batch, more_words_string,
                (GeneralStorage.w - GeneralStorage.posibilitiesFont.getBounds(more_words_string).width)/2,
                GeneralStorage.h*0.65f);


        //Drawing score text
        GeneralStorage.scoreTextGameOverMenu.draw(GeneralStorage.batch, "SCORE",
                (GeneralStorage.w - GeneralStorage.scoreTextGameOverMenu.getBounds("SCORE").width)/2,
                GeneralStorage.h*0.49f);

        //Drawing score
        GeneralStorage.scoreGameOverMenu.draw(GeneralStorage.batch, String.valueOf(RunningUpdate.score),
                (GeneralStorage.w - GeneralStorage.scoreGameOverMenu.getBounds(String.valueOf(RunningUpdate.score)).width)/2,
                GeneralStorage.h*0.49f - GeneralStorage.scoreTextGameOverMenu.getBounds("SCORE").height -GeneralStorage.h * 0.02f);

        //Drawing new record
        if(new_record)
            GeneralStorage.posibilitiesFont.draw(GeneralStorage.batch, "NOUVEAU RECORD!",
                    (GeneralStorage.w - GeneralStorage.posibilitiesFont.getBounds("NOUVEAU RECORD!").width)/2,
                    GeneralStorage.h*0.49f - GeneralStorage.scoreTextGameOverMenu.getBounds("SCORE").height -GeneralStorage.h * 0.04f -
                    GeneralStorage.scoreGameOverMenu.getBounds(String.valueOf(RunningUpdate.score)).height);


        //Drawing game over menu buttons
		GameOverMenuStorage.replay_button.sp.draw(GeneralStorage.batch);
        GameOverMenuStorage.share_button.sp.draw(GeneralStorage.batch);

		GeneralStorage.batch.end();
		
	}

}
