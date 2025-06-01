package com.tiledawn.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiledawn.Main;
import com.tiledawn.models.*;
import com.tiledawn.view.EndGameMenu;
import com.tiledawn.view.GameView;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.PauseMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PauseMenuController {
    private PauseMenu view;

    public void setView(PauseMenu view) {
        this.view = view;
    }

    public void handle(){

        if(view.getContinueButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getExitButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new EndGameMenu(new EndGameController(), App.getCurrentGame(),false,
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getSaveButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            saveGame();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getBlackWhiteCheckBox().isChecked()){
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getShader());
        }
        else{
            Main.getBatch().setShader(null);
        }
    }

    private void saveGame(){
        AppGame game = App.getCurrentGame();

        for(Tree tree : game.getTrees()){
            tree.setSprite(null);
        }

        for(TentacleMonster tentacleMonster : game.getTentacleMonsters()){
            tentacleMonster.setSprite(null);
        }

        for(EyeBat bat : game.getEyeBats()){
            bat.setSprite(null);
        }

        for(BossFight bossFight : game.getBossFights()){
            bossFight.setSprite(null);
        }

        game.setPlayerDamaged(new ArrayList<>());
        game.setEnemyDead(new ArrayList<>());
        game.getPlayer().setSprite(null);
        game.getPlayer().setHealthSprite(null);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter( App.getCurrentUser().getUsername() + ".json")) {
            gson.toJson(game, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
