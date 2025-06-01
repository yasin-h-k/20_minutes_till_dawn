package com.tiledawn.controller;

import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.User;
import com.tiledawn.view.FirstMenu;
import com.tiledawn.view.ForgetPasswordMenu;
import com.tiledawn.view.LoginMenu;
import com.tiledawn.view.MainMenu;

public class LoginMenuController {

    private LoginMenu view;

    public void setView(LoginMenu view) {
        this.view = view;
    }

    public void handle(){
        if(view != null) {
            if(view.getLoginButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                view.getErrorUsername().setText("");
                view.getErrorPass().setText("");
                User user = App.getUsers().get(view.getUsernameBox().getText());
                if(user == null) {
                    view.getErrorUsername().setText("Wrong username");
                }
                else if(! user.getPassword().equals(view.getPasswordBox().getText())) {
                    view.getErrorPass().setText("Wrong password");
                }
                else{
                    App.setCurrentUser(user);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                        GameAssetManager.getGameAssetManager().getSkin()));
                }
                view.getLoginButton().setChecked(false);
            }

            if(view.getForgetPassButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                view.getErrorUsername().setText("");
                view.getErrorPass().setText("");
                User user = App.getUsers().get(view.getUsernameBox().getText());
                if(user == null) {
                    view.getErrorUsername().setText("Wrong username");
                }
                else {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new ForgetPasswordMenu(new ForgetPasswordMenuController(user),
                        GameAssetManager.getGameAssetManager().getSkin()));
                }
                view.getForgetPassButton().setChecked(false);
            }
        }
    }

}
