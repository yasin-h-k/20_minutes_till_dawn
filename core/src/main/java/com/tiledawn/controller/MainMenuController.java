package com.tiledawn.controller;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiledawn.Main;
import com.tiledawn.models.*;
import com.tiledawn.view.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class MainMenuController {

    private MainMenu view;

    public void setView(MainMenu view) {
        this.view = view;
    }

    public void handle() {
        if(view != null) {

            if(view.getSettingButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingMenu(new SettingMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getProfileButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getPreGameButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getScoreBoardButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreBoardMenu(new ScoreBoardMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getTalentButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new TalentMenu(new TalentMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getPlaySavedButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                resumeGame();

                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getExitButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                App.setCurrentUser(null);
                Main.getBatch().setShader(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new FirstMenu(new FirstMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }



        }
    }

    private void resumeGame(){

        AppGame game;

        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader(App.getCurrentUser().getUsername() + ".json");
        } catch (FileNotFoundException e) {
            view.getErrorLoadGame().setText("You do not have any saved game!");
            return;
        }

        Type type = new TypeToken<AppGame>() {
        }.getType();
        game = gson.fromJson(file, type);

        for(Tree tree : game.getTrees()){
            tree.setSprite(new Sprite());
        }

        for(TentacleMonster tentacleMonster : game.getTentacleMonsters()){
            tentacleMonster.setSprite(new Sprite());
        }

        for(EyeBat bat : game.getEyeBats()){
            bat.setSprite(new Sprite());
        }

        for(BossFight bossFight : game.getBossFights()){
            bossFight.setSprite(new Sprite());
        }

        game.getPlayer().setSprite(new Sprite());
        game.getPlayer().setHealthSprite(new Sprite());

        App.setCurrentGame(game);

        // TODO
    }

}
