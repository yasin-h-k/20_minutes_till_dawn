package com.tiledawn.controller;

import com.tiledawn.Main;
import com.tiledawn.models.*;
import com.tiledawn.view.GameView;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.PreGameMenu;

public class PreGameMenuController {

    private PreGameMenu view;

    public void setView(PreGameMenu view) {
        this.view = view;
    }

    public void handle(){
        if(view.getStartGameButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            Player player = new Player(Hero.valueOf(view.getHeroSelectBox().getSelected()),
                WeaponType.valueOf(view.getWeaponSelectBox().getSelected()));
            App.setCurrentGame(new AppGame(player,view.getTimeSelectBox().getSelected()));

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getBackButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
