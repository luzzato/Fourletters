package net.nadjib.update;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import net.nadjib.draw.GameOverMenuDraw;
import net.nadjib.draw.RunningDraw;
import net.nadjib.fourletters.GameScreen;
import net.nadjib.fourletters.Letter;
import net.nadjib.storage.GameOverMenuStorage;
import net.nadjib.storage.GeneralStorage;
import net.nadjib.storage.WordsStorage;

import java.util.ArrayList;
import java.util.Random;

import C.C;

public class RunningUpdate {
	
	public static boolean pressed, first=true, pause;
	public static int first_x, first_y, letters_played, combination, posibilities, score;
    public static float time, time_total = 15.0f, pixels_timer, time_side;
    public static String word_played;
    public static ArrayList<Letter> letters_played_array;
    public static ArrayList<Integer> combinations_played, words_position;
	
	
	public static void update(){

        if(!pause) {

            move_timer();

            time -= Gdx.graphics.getDeltaTime();
        }

        //Upsprites should move from one side to other
        if(time_side>0){

            time_side-= Gdx.graphics.getDeltaTime();

            GeneralStorage.letter1.move_side();
            GeneralStorage.letter2.move_side();
            GeneralStorage.letter3.move_side();
            GeneralStorage.letter4.move_side();

            if(time_side<=0) {

                GeneralStorage.letter1.restorePosition();
                GeneralStorage.letter2.restorePosition();
                GeneralStorage.letter3.restorePosition();
                GeneralStorage.letter4.restorePosition();
            }

        }


        //Game should finish
        if(time<0) {

            if(GeneralStorage.prefs.getInteger("sound",1)==1)
                GeneralStorage.timeup.play();

            GameScreen.actionResolver.submitScore(score);

            GameScreen.state = GameScreen.GameState.GAMEOVER;
            GameOverUpdate.time = 2.4f;
            GeneralStorage.timeUpFont.setScale(GeneralStorage.origin_scaleX2, GeneralStorage.origin_scaleY2);
            for(int i = 0; i<4; i++)
                GameOverMenuStorage.letters_gameover.get(i).letter = WordsStorage.words.get(combination).get(0).substring(i,i+1);

            if(score > GeneralStorage.prefs.getInteger("best",0)) {
                GeneralStorage.prefs.putInteger("best", score);
                GeneralStorage.prefs.flush();
                GeneralStorage.best = score;

                GameOverMenuDraw.new_record = true;
            }

            else GameOverMenuDraw.new_record = false;

        }

        //Game doesn't have to finish yet
        else {
            if (RunningUpdate.time_side <= 0){
            //Storing if the screen is touched
            pressed = Gdx.input.isTouched();

            if (pressed && first == true) {
                //Getting where has been touched the screen
                first_x = Gdx.input.getX();
                first_y = Gdx.input.getY();
                first = false;

            }


                if (!pressed) {

                    if (first == false) {



                            //Letters onClick

                            if (!pause && GeneralStorage.letter1.onClickListener()) {

                            } else if (!pause && GeneralStorage.letter2.onClickListener()) {

                            } else if (!pause && GeneralStorage.letter3.onClickListener()) {

                            } else if (!pause && GeneralStorage.letter4.onClickListener()) {

                            }


                        //Back button onClick
                        else if (GeneralStorage.back_but.isPressed()) {
                            if (GeneralStorage.prefs.getInteger("sound", 1) == 1)
                                GeneralStorage.click_button.play();
                            GameScreen.state = GameScreen.GameState.MENU;
                        }

                        //Pause button onClick
                        else if (C.PAUSE && GeneralStorage.pause_but.isPressed()) {

                            if (pause) {
                                GeneralStorage.pause_but.sp.setTexture(GeneralStorage.pause_texture);
                                pause = false;
                            } else {
                                GeneralStorage.pause_but.sp.setTexture(GeneralStorage.play_texture);
                                pause = true;
                            }


                        }

                    }
                    first = true;

                }
        }

            for (int i = 0; i < letters_played_array.size(); i++)
                letters_played_array.get(i).move();

        }
	}


    //Initializing all the stuff for the game. This method is called before each game
    public static void initialize_game(){


        //Initializing pause to false
        pause = false;
        GeneralStorage.pause_but.sp.setTexture(GeneralStorage.pause_texture);



        combinations_played = new ArrayList<Integer>();
        words_position = new ArrayList<Integer>();

        GeneralStorage.generalFontIncrease.setScale(GeneralStorage.origin_scaleX, GeneralStorage.origin_scaleY);

        letters_played = 0;

        time = time_total;

        GeneralStorage.timer.setSize(GeneralStorage.w*0.87f, GeneralStorage.h*0.068f);
        GeneralStorage.timer.setPosition(GeneralStorage.w*0.065f, GeneralStorage.h*0.761f);

        pixels_timer = GeneralStorage.w / time_total;

        score = 0;

        letters_played_array = new ArrayList<Letter>();

        word_played = "";

        combination = randomNum(0, WordsStorage.words.size()-1);

        combinations_played.add(combination);

        posibilities = WordsStorage.words.get(combination).size();

        int color = randomNum(0, GeneralStorage.backgrounds_letters.size()-1);

        GeneralStorage.letter1.restorePosition();
        GeneralStorage.letter2.restorePosition();
        GeneralStorage.letter3.restorePosition();
        GeneralStorage.letter4.restorePosition();

        int word_random = randomNum(0, WordsStorage.words.get(combination).size()-1);

        GeneralStorage.letter1.setLetter(WordsStorage.words.get(combination).get(word_random).substring(0, 1), color);
        GeneralStorage.letter2.setLetter(WordsStorage.words.get(combination).get(word_random).substring(1, 2),color);
        GeneralStorage.letter3.setLetter(WordsStorage.words.get(combination).get(word_random).substring(2, 3),color);
        GeneralStorage.letter4.setLetter(WordsStorage.words.get(combination).get(word_random).substring(3, 4),color);

    }



    //This method is called when the user match a word, the letters should change
    public static void update_letters(){

        GeneralStorage.generalFontIncrease.setScale(GeneralStorage.origin_scaleX, GeneralStorage.origin_scaleY);

        GeneralStorage.generalFontReduce.setScale(GeneralStorage.generalFont.getScaleX(), GeneralStorage.generalFont.getScaleY());

        letters_played_array = new ArrayList<Letter>();

        letters_played = 0;

        if(combinations_played.size() == WordsStorage.words.size())
            combinations_played.clear();

        do {
            combination = randomNum(0, WordsStorage.words.size() - 1);

        }while(combinations_played.contains(combination));

        combinations_played.add(combination);


        posibilities = WordsStorage.words.get(combination).size();

        int color = randomNum(0, GeneralStorage.backgrounds_letters.size()-1);

        int word_random = randomNum(0, WordsStorage.words.get(combination).size()-1);

        words_position.clear();

        int word_position;

        do {
            word_position = randomNum(0, 3);

        }while(words_position.contains(word_position));
        words_position.add(word_position);

        GeneralStorage.letter1.setLetter(WordsStorage.words.get(combination).get(word_random).substring(word_position,word_position+1),color);


        do {
            word_position = randomNum(0, 3);

        }while(words_position.contains(word_position));
        words_position.add(word_position);

        GeneralStorage.letter2.setLetter(WordsStorage.words.get(combination).get(word_random).substring(word_position, word_position+1),color);


        do {
            word_position = randomNum(0, 3);

        }while(words_position.contains(word_position));
        words_position.add(word_position);

        GeneralStorage.letter3.setLetter(WordsStorage.words.get(combination).get(word_random).substring(word_position, word_position+1),color);


        do {
            word_position = randomNum(0, 3);

        }while(words_position.contains(word_position));

        GeneralStorage.letter4.setLetter(WordsStorage.words.get(combination).get(word_random).substring(word_position, word_position+1),color);


    }


    //Checking if the word is correct. This method is called when the user plays 4 letters
    public static void check_word(){

        String aux = "";

        for(int i=0; i<4;i++){
            aux += letters_played_array.get(i).letter;
        }


        for(int i=0; i<WordsStorage.words.get(combination).size();i++) {
            if (WordsStorage.words.get(combination).get(i).equals(aux)) {
                if(GeneralStorage.prefs.getInteger("sound",1)==1)
                    GeneralStorage.correct_sound.play();
                update_letters();
                RunningUpdate.score++;
                RunningDraw.show_circles = false;
                if(time + 2.0f > time_total)
                    time = time_total;
                else time += 2.0f;
                return;
            }
        }

        if(GeneralStorage.prefs.getInteger("sound",1)==1)
            GeneralStorage.error_sound.play();

        time_side = 0.32f;


        letters_played = 0;

        RunningUpdate.letters_played_array.clear();





    }

    //Moving the timer line
    public static void move_timer(){


        float pixels = time/time_total;

        if(pixels <0.3)
            GeneralStorage.timer.setColor(Color.RED);
        else GeneralStorage.timer.setColor(GeneralStorage.green);

        GeneralStorage.timer.setSize(GeneralStorage.w*0.87f * pixels, GeneralStorage.timer.getHeight());
        GeneralStorage.timer.setPosition(GeneralStorage.w*0.935f - GeneralStorage.timer.getWidth(), GeneralStorage.timer.getY());

    }


    //Generating a random number
    public static int randomNum(int min, int max) {
        Random rand = new Random();
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random;
    }

}
