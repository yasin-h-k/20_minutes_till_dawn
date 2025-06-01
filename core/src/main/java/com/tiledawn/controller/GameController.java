package com.tiledawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.TimeUtils;
import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.AppGame;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.Player;
import com.tiledawn.view.EndGameMenu;
import com.tiledawn.view.GameView;
import com.tiledawn.view.PauseMenu;

public class GameController {

    private GameView view;
    private final float worldSize = AppGame.worldSize;
    private final PlayerController playerController;
    private final WeaponController weaponController;
    private final EnemyController enemyController;
    private final Player player;

    public GameController() {

        Player player = App.getCurrentGame().getPlayer();

        this.playerController = new PlayerController(player, worldSize);
        this.weaponController = new WeaponController(player,worldSize);
        this.enemyController = new EnemyController(worldSize);
        this.player = player;
        App.getCurrentGame().resume();
    }

    public void setView(GameView view) {
        this.view = view;
        this.playerController.setView(view);
    }

    public void start(){
        enemyController.spawnTrees();
    }

    public void handle(float delta , OrthographicCamera camera) {

        if(App.getCurrentGame().getEndTime() < TimeUtils.millis()){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new EndGameMenu(new EndGameController(), App.getCurrentGame(),true,
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            App.getCurrentGame().pause();

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenu(new PauseMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            App.getCurrentGame().setEndTime(App.getCurrentGame().getEndTime() - 1000 * 60);
            App.getCurrentGame().setStartTime(App.getCurrentGame().getStartTime() - 1000 * 60);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            App.getCurrentGame().setStartTime(App.getCurrentGame().getStartTime() -
                (App.getCurrentGame().getEndTime() - App.getCurrentGame().getStartTime()));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            App.getCurrentUser().setAutoAim(!App.getCurrentUser().isAutoAim());
        }

        playerController.update(delta , camera);
        weaponController.handle(delta,camera);
        enemyController.update(delta);

    }

    public void render(float delta) {
        Main.getBatch().draw(GameAssetManager.getGameAssetManager().getGameFloor(),0,0, worldSize, worldSize);

        enemyController.render(delta);
        playerController.render();
        weaponController.render();

        view.getTimeFont().draw(Main.getBatch(),App.getCurrentGame().getTimeString(),
            player.getBounds().x - (float) Gdx.graphics.getWidth() /2,
            player.getBounds().y + (float) Gdx.graphics.getWidth() /2 - 250);
    }

    public void dispose(){
        view.getTimeFont().dispose();
    }

    public void handleShoot(int screenX, int screenY,OrthographicCamera camera){
        weaponController.handleShoot(screenX , screenY, camera);
    }
}
