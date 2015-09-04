package net.nadjib.storage;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import net.nadjib.fourletters.But;
import net.nadjib.fourletters.GameScreen;
import net.nadjib.fourletters.Letter;

import java.util.ArrayList;

import C.C;


public class GeneralStorage {
	
	public static Sprite up_letter1, up_letter2, up_letter3, up_letter4, timer, line_timer;
	public static BitmapFont generalFont, generalFontReduce, generalFontIncrease, posibilitiesFont, scoreFont, bestMenuFont, scoreMenuFont, timeUpFont,
            scoreGameOverMenu, scoreTextGameOverMenu;
	public static FreeTypeFontGenerator generator;
	public static int w, h, best, height_banner;
	public static float ratio, origin_scaleX, origin_scaleY, origin_scaleX2, origin_scaleY2;
	public static SpriteBatch batch;
	public static Preferences prefs;
	public static Music background_music;
	public static Sound click_button, correct_sound, error_sound, timeup;
	public static Texture background_letter, pixel, circle_white, play_texture, ranking_texture, sound_button, sound_off_button,
            back_button, red_texture, share_button, pause_texture;
    public static Letter letter1, letter2, letter3, letter4;
    public static ArrayList<Texture> backgrounds_letters;
    public static FreeTypeFontParameter parameter;
    public static But back_but, pause_but;
    public static Color green;

	public static void load(){

        height_banner = GameScreen.actionResolver.getBannerHeight();

		//Screen Sizes. DON'T CHANGE THEM!
		 w = Gdx.graphics.getWidth();
		 h = Gdx.graphics.getHeight();
            height_banner = GameScreen.actionResolver.getBannerHeight();
		 
		 //Ratio is used for text size
			float ratio1 = (float) (w / 1080.0);
			float ratio2 = (float) (h / 1920.0);

			//The ratio is used for text size
			if (ratio1 > ratio2)
				ratio = ratio2;
			else
				ratio = ratio1;
		/////////////END SIZE VARIABLES DECLARATION////////////////////////
		
			
			//Batch for drawing on the screen
			batch = new SpriteBatch();
			
			//Preferences for store and get the best score
			prefs = Gdx.app.getPreferences("Prefs");
		
			if(C.BACKGROUND_MUSIC){
            //Background music
            background_music = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
            background_music.setLooping(true);

        }

			//Sound
			click_button = Gdx.audio.newSound(Gdx.files.internal("click_button.mp3"));
            correct_sound = Gdx.audio.newSound(Gdx.files.internal("correct_sound.wav"));
            error_sound = Gdx.audio.newSound(Gdx.files.internal("error_sound.mp3"));
            timeup = Gdx.audio.newSound(Gdx.files.internal("timeup.wav"));


		 
		 
		 
		 //FONTS
			 parameter = new FreeTypeFontParameter();
             generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));

      //  parameter.characters = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWYZabcçdefgğhıijklmnoöprsştuüvwyz0123456789";

            parameter.size = (int) (150*ratio);
            generalFont = generator.generateFont(parameter);





        parameter.size = (int) (80*ratio);
        posibilitiesFont = generator.generateFont(parameter);
        posibilitiesFont.setColor(Color.GRAY);

        bestMenuFont = generator.generateFont(parameter);

        parameter.size = (int) (160*ratio);
        scoreFont = generator.generateFont(parameter);
        scoreFont.setColor(Color.GRAY);

        parameter.size = (int) (115*ratio);
        scoreMenuFont = generator.generateFont(parameter);

        parameter.size = (int) (150*ratio);
        generalFontReduce = generator.generateFont(parameter);

        parameter.size = (int) (60*ratio);
        generalFontIncrease = generator.generateFont(parameter);
        origin_scaleX = generalFontIncrease.getScaleX();
        origin_scaleY = generalFontIncrease.getScaleY();

        parameter.size = (int) (230*ratio);
        scoreGameOverMenu = generator.generateFont(parameter);
        scoreGameOverMenu.setColor(Color.GRAY);

        parameter.size = (int) (110*ratio);
        scoreTextGameOverMenu = generator.generateFont(parameter);
        scoreTextGameOverMenu.setColor(Color.GRAY);

        parameter.size = (int) (100*ratio);
        timeUpFont = generator.generateFont(parameter);
        timeUpFont.setColor(Color.RED);
        origin_scaleX2 = timeUpFont.getScaleX();
        origin_scaleY2 = timeUpFont.getScaleY();

        //End fonts



        //Loading texture from memory
        pixel = new Texture("pixel.png");

        circle_white = new Texture("circle_white.png");

        backgrounds_letters = new ArrayList<Texture>();

        background_letter = new Texture("circle1.png");
        background_letter.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgrounds_letters.add(background_letter);

        background_letter = new Texture("circle2.png");
        background_letter.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgrounds_letters.add(background_letter);

        background_letter = new Texture("circle3.png");
        background_letter.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgrounds_letters.add(background_letter);

        play_texture = new Texture("play_button.png");
        pause_texture = new Texture("pause.png");
        ranking_texture = new Texture("ranking_button.png");
        sound_button = new Texture("sound_button.png");
        sound_off_button = new Texture("sound_off_button.png");
        back_button = new Texture("back_button.png");
        red_texture = new Texture("red_background.png");
        share_button = new Texture("share_button.png");

        //End textures


        back_but = new But(GeneralStorage.w*0.03f, GeneralStorage.h*0.88f, GeneralStorage.h*0.08f*0.68f, GeneralStorage.h*0.08f, GeneralStorage.back_button,
                GeneralStorage.w, GeneralStorage.h);

        pause_but = new But(GeneralStorage.w*0.97f - GeneralStorage.h*0.08f, GeneralStorage.h*0.88f, GeneralStorage.h*0.08f, GeneralStorage.h*0.08f,
                pause_texture, GeneralStorage.w, GeneralStorage.h);



        //Creating letters
        letter1 = new Letter(1, false);
        letter2 = new Letter(2, false);
        letter3 = new Letter(3, false);
        letter4 = new Letter(4, false);

        float left_margin = (w - h*0.13f*4)/7*2;
        float separation = (w - h*0.13f*4)/7;

        up_letter1 = new Sprite(circle_white);
        up_letter1.setSize(h*0.13f, h*0.13f);
        up_letter1.setPosition(left_margin,h*0.6f);

        up_letter2 = new Sprite(circle_white);
        up_letter2.setSize(h*0.13f, h*0.13f);
        up_letter2.setPosition(left_margin + h*0.13f + separation,h*0.6f);

        up_letter3 = new Sprite(circle_white);
        up_letter3.setSize(h*0.13f, h*0.13f);
        up_letter3.setPosition(left_margin + h*0.13f*2 + separation*2,h*0.6f);

        up_letter4 = new Sprite(circle_white);
        up_letter4.setSize(h*0.13f, h*0.13f);
        up_letter4.setPosition(left_margin + h*0.13f*3 + separation*3,h*0.6f);


        //Preferences to get get and store best score
		best = prefs.getInteger("best",0);

        //Color green for timer
        green = new Color(64/255f, 126/255f, 70/255f, 1.0f);


        //Timer and background timer
        timer = new Sprite(pixel);
        timer.setColor(green);

        line_timer = new Sprite(new Texture("line_timer.png"));
        line_timer.setSize(w*0.88f, h*0.07f);
        line_timer.setPosition(GeneralStorage.w*0.06f, GeneralStorage.h*0.76f);


	}
	
}
