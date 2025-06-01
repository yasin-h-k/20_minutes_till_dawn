package com.tiledawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.tiledawn.controller.*;
import com.tiledawn.models.*;
import com.tiledawn.view.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
//    private Texture image;
//    private int x = 0;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        startGame();

//        getMain().setScreen(new SignupMenu(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new FirstMenu(new FirstMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new LoginMenu(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new PreGameMenu(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new SettingMenu(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new GameView(new GameController(App.getCurrentGame().getPlayer()), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new EndGameMenu(new EndGameController(),App.getCurrentGame(),false,GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new PauseMenu(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new SelectAbilityMenu(new SelectAbilityMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//        getMain().setScreen(new ScoreBoardMenu(new ScoreBoardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(image, x, 210);
//        batch.end();
//
//        if(Gdx.input.isKeyPressed(Input.Keys.W)){
//            x += 1;
//        }

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        image.dispose();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static Main getMain() {
        return main;
    }

    private void startGame(){
        App.getMusic().play();
        Pixmap pixmap = new Pixmap(Gdx.files.internal("T_Cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap,pixmap.getWidth()/2,pixmap.getHeight()/2));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ChevyRayLantern.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.borderColor = Color.RED;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        GameAssetManager.getGameAssetManager().getSkin().add("default-font",font,BitmapFont.class);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        GameAssetManager.getGameAssetManager().getSkin().add("default",labelStyle);
    }
}
