package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.GameController;
import com.tiledawn.models.GameAssetManager;


public class GameView implements Screen , InputProcessor {
    private Stage stage;
    private final OrthographicCamera camera;
    private final BitmapFont timeFont = new BitmapFont();
    private final BitmapFont killFont = new BitmapFont();
    private final ProgressBar XpBar;
    private final BitmapFont levelFont = new BitmapFont();
    private final GameController controller;


    public GameView(GameController controller, Skin skin) {

        this.camera = new OrthographicCamera();

        this.XpBar = new ProgressBar(0, 100, 1, false, skin);

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);

        timeFont.getData().setScale(2f);
        killFont.getData().setScale(2f);
        levelFont.getData().setScale(2f);
        stage.addActor(XpBar);
        XpBar.setBounds((float) Gdx.graphics.getWidth() /2 - 150, (float) Gdx.graphics.getHeight() - 30, 300,5);

        controller.start();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        controller.handle(v, camera);
        Main.getBatch().begin();
        controller.render(v);
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
//        controller.dispose();
    }


    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int Button) {
        controller.handleShoot(screenX, screenY,camera);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public BitmapFont getKillFont() {
        return killFont;
    }

    public BitmapFont getTimeFont() {
        return timeFont;
    }

    public ProgressBar getXpBar() {
        return XpBar;
    }

    public BitmapFont getLevelFont() {
        return levelFont;
    }
}
