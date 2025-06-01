package com.tiledawn.controller;

import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.Player;
import com.tiledawn.view.EndGameMenu;
import com.tiledawn.view.MainMenu;

public class EndGameController {
    private EndGameMenu view;

    public void setView(EndGameMenu view) {
        this.view = view;
    }

    public void handle(){

        if(view.getExitButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            Player player = App.getCurrentGame().getPlayer();
            App.getCurrentUser().increaseScore(player.getKills() * App.getCurrentGame().getElapsedTime()/1000);
            App.getCurrentUser().increaseNumberOfKills(player.getKills());
            App.getCurrentUser().setMaxSurvivalTime(App.getCurrentGame().getElapsedTime()/1000);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

    }
}
