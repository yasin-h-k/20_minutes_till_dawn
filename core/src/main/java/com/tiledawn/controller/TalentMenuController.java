package com.tiledawn.controller;

import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.TalentMenu;

public class TalentMenuController {
    private TalentMenu view;

    public void setView(TalentMenu view) {
        this.view = view;
    }

    public void handle(){
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
