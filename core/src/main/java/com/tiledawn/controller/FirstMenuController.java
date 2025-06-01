package com.tiledawn.controller;

import com.badlogic.gdx.graphics.Texture;
import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.User;
import com.tiledawn.view.FirstMenu;
import com.tiledawn.view.LoginMenu;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.SignupMenu;

public class FirstMenuController {

    private FirstMenu view;

    public void setView(FirstMenu view) {
        this.view = view;
    }

    public void handle(){
        if(view != null){
            if(view.getSignupButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SignupMenu(new SignupMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getLoginButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenu(new LoginMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getGuestButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                App.setCurrentUser(new User("Guest","","","",
                    GameAssetManager.getGameAssetManager().getAvatarPaths().get(2)));
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
