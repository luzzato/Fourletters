package net.nadjib.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import net.nadjib.storage.GeneralStorage;
import net.nadjib.update.GameOverUpdate;
import net.nadjib.update.RunningUpdate;

public class GameOverDraw {

    public static String text_score;
	
	public static void draw(){

        GeneralStorage.batch.begin();

        Gdx.gl.glClearColor(231/255f, 227/255f, 215/255f, (float) 0.3);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);


        text_score = "SCORE";

        //Drawing score
        GeneralStorage.posibilitiesFont.draw(GeneralStorage.batch, text_score,
                (GeneralStorage.w - GeneralStorage.posibilitiesFont.getBounds(text_score).width)/2,
                GeneralStorage.h*0.94f);

        //Draw score
        GeneralStorage.scoreFont.draw(GeneralStorage.batch, String.valueOf(RunningUpdate.score),
                (GeneralStorage.w - GeneralStorage.scoreFont.getBounds(String.valueOf(RunningUpdate.score)).width)/2,
                GeneralStorage.h*0.94f - GeneralStorage.posibilitiesFont.getBounds(text_score).height - GeneralStorage.h * 0.02f);

        //Drawing white circles
        if(RunningDraw.show_circles) {
            if (RunningUpdate.letters_played < 1)
                GeneralStorage.up_letter1.draw(GeneralStorage.batch);
            if (RunningUpdate.letters_played < 2)
                GeneralStorage.up_letter2.draw(GeneralStorage.batch);
            if (RunningUpdate.letters_played < 3)
                GeneralStorage.up_letter3.draw(GeneralStorage.batch);
            if (RunningUpdate.letters_played < 4)
                GeneralStorage.up_letter4.draw(GeneralStorage.batch);
        }
        else{
            GeneralStorage.generalFontReduce.setScale(GeneralStorage.generalFontReduce.getScaleX()*0.975f, GeneralStorage.generalFontReduce.getScaleY()*0.975f);
        }




        //Drawing letter buttons
        if(GameOverUpdate.time >2.3f)
        GeneralStorage.letter1.drawGameOver(true);
        else GeneralStorage.letter1.drawGameOver(false);

        if(GameOverUpdate.time >2.2f)
            GeneralStorage.letter2.drawGameOver(true);
        else GeneralStorage.letter2.drawGameOver(false);

        if(GameOverUpdate.time >2.1f)
            GeneralStorage.letter3.drawGameOver(true);
        else GeneralStorage.letter3.drawGameOver(false);

        if(GameOverUpdate.time >2.0f)
            GeneralStorage.letter4.drawGameOver(true);
        else GeneralStorage.letter4.drawGameOver(false);

        if(GameOverUpdate.time>1.8)
            GeneralStorage.timeUpFont.setScale(GeneralStorage.timeUpFont.getScaleX()*1.01f, GeneralStorage.timeUpFont.getScaleY()*1.01f);

        GeneralStorage.timeUpFont.draw(GeneralStorage.batch, "TEMPS ÉCOULÉ!",
                (GeneralStorage.w - GeneralStorage.timeUpFont.getBounds("TEMPS ÉCOULÉ!").width)/2,
                GeneralStorage.h*0.19f + (GeneralStorage.h*0.39f - GeneralStorage.timeUpFont.getBounds("TEMPS ÉCOULÉ!").height)/2 +
                        GeneralStorage.timeUpFont.getBounds("TEMPS ÉCOULÉ!").height);

        GeneralStorage.batch.end();
		
		
	}

}
