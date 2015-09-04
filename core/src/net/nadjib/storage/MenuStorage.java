package net.nadjib.storage;





import net.nadjib.fourletters.But;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//This class stores all the variables for Menu Screen

public class MenuStorage {
	
	
	
	public static Sprite best_sprite, title;

	
	public static But play_button, ranking_button, sound_button;


	
	
	public static void load(){
		
		
		int height_buttons = (int) (GeneralStorage.h*0.071);
	
		int separation = (int) (GeneralStorage.w*0.05);


		//PLAY BUTTON
		play_button = new But((int) (GeneralStorage.w-GeneralStorage.h*0.13f)/2,(int) (GeneralStorage.h*0.4f - GeneralStorage.h*0.13f*2),
                (int)  (GeneralStorage.h*0.13f),(int)  (GeneralStorage.h*0.13f),  GeneralStorage.play_texture, GeneralStorage.w, GeneralStorage.h);
		

		//RANKING BUTTON
        ranking_button = new But((int) (GeneralStorage.w-GeneralStorage.h*0.13f)/2 + GeneralStorage.h*0.13f,
                (int) (GeneralStorage.h*0.4f - GeneralStorage.h*0.13f),
                (int)  (GeneralStorage.h*0.13f),(int)  (GeneralStorage.h*0.13f),  GeneralStorage.ranking_texture, GeneralStorage.w, GeneralStorage.h);

        best_sprite = new Sprite(GeneralStorage.circle_white);
        best_sprite.setSize(GeneralStorage.h*0.13f, GeneralStorage.h*0.13f);
        best_sprite.setPosition((int) (GeneralStorage.w-GeneralStorage.h*0.13f)/2, GeneralStorage.h*0.4f);


        //Title
        title = new Sprite(new Texture("menu_title.png"));
        title.setSize(GeneralStorage.h * 0.2f * 2.4137f, GeneralStorage.h*0.2f);
        title.setPosition((GeneralStorage.w - title.getWidth())/2, GeneralStorage.h*0.68f);

		//SOUND BUTTON
		if(GeneralStorage.prefs.getInteger("sound",1)==1)
		sound_button = new But((int) (GeneralStorage.w-GeneralStorage.h*0.13f)/2 - GeneralStorage.h*0.13f,
                (int) (GeneralStorage.h*0.4f - GeneralStorage.h*0.13f),
                (int)  (GeneralStorage.h*0.13f),(int)  (GeneralStorage.h*0.13f), GeneralStorage.sound_button, GeneralStorage.w, GeneralStorage.h);
			else sound_button = new But((int) (GeneralStorage.w-GeneralStorage.h*0.13f)/2 - GeneralStorage.h*0.13f,
                (int) (GeneralStorage.h*0.4f - GeneralStorage.h*0.13f),
                (int)  (GeneralStorage.h*0.13f),(int)  (GeneralStorage.h*0.13f), GeneralStorage.sound_off_button, GeneralStorage.w, GeneralStorage.h);



		
	}

}
