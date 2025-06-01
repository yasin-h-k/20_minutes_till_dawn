package com.tiledawn.controller;

import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.view.GameView;
import com.tiledawn.view.SelectAbilityMenu;

public class SelectAbilityMenuController {
    SelectAbilityMenu view;

    public void setView(SelectAbilityMenu view) {
        this.view = view;
    }

    public void handle(){

        if(view.getVitalityButton().isChecked()){
            App.getCurrentGame().increaseVitality();
            App.getCurrentGame().getPlayer().increaseHealth(1);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getDamagerButton().isChecked()){
            App.getCurrentGame().increaseDamager();
            App.getCurrentGame().getPlayer().getWeapon().setDamagerTime();

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getProcreaseButton().isChecked()){
            App.getCurrentGame().increaseProcrease();
            App.getCurrentGame().getPlayer().getWeapon().increaseProjectile(1);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getAmocreaseButton().isChecked()){
            App.getCurrentGame().increaseAmocrease();
            App.getCurrentGame().getPlayer().getWeapon().increaseMaxAmmo(5);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(view.getSpeedyButton().isChecked()){
            App.getCurrentGame().increaseSpeedy();
            App.getCurrentGame().getPlayer().setSpeedAbilityTime();

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
