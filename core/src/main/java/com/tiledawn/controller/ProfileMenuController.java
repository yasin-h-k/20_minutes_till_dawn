package com.tiledawn.controller;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.view.FirstMenu;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.ProfileMenu;

public class ProfileMenuController {

    private ProfileMenu view;

    public void setView(ProfileMenu view) {
        this.view = view;
    }

    public void handle(){
        if(view != null){

            if(view.getChangeUsernameButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Table table = view.getTable();
                table.clear();
                table.setFillParent(true);
                table.add(view.getUsernameTitle()).colspan(2).pad(100,0,100,0);
                table.row();
                table.add(view.getUsernameLabel()).pad(10);
                table.add(view.getUsernameBox()).width(400);
                table.row();
                table.add(view.getErrorUsername()).colspan(2);
                table.row();
                table.add(view.getUsernameButton()).pad(20,0,20,0).colspan(2);

                view.getChangeUsernameButton().setChecked(false);
            }

            if(view.getChangePasswordButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                Table table = view.getTable();
                table.clear();
                table.setFillParent(true);
                table.add(view.getPasswordTitle()).colspan(2).pad(100,0,100,0);
                table.row();
                table.add(view.getPasswordLabel()).pad(10);
                table.add(view.getPasswordBox()).width(400);
                table.row();
                table.add(view.getErrorPass()).colspan(2);
                table.row();
                table.add(view.getPassButton()).pad(20,0,20,0).colspan(2);

                view.getChangePasswordButton().setChecked(false);
            }

            if(view.getChangeAvatarButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                //TODO
            }

            if(view.getDeleteAccountButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }
                App.getUsers().remove(App.getCurrentUser().getUsername());
                App.setCurrentUser(null);
                Main.getBatch().setShader(null);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new FirstMenu(new FirstMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(view.getUsernameButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }

                view.getErrorUsername().setText("");

                String username = view.getUsernameBox().getText();
                if(App.getUsers().get(username) != null){
                    view.getErrorUsername().setText("Username is already taken");
                }
                else{
                    App.getCurrentUser().setUsername(username);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(),
                        GameAssetManager.getGameAssetManager().getSkin()));
                }

                view.getUsernameButton().setChecked(false);
            }

            if(view.getPassButton().isChecked()){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getButtonSFX().play();
                }

                view.getErrorPass().setText("");

                String password = view.getPasswordBox().getText();
                if(! SignupMenuController.checkPassword(password)){
                    view.getErrorPass().setText("Password is weak !!");
                }
                else{
                    App.getCurrentUser().setPassword(password);
                    Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(),
                        GameAssetManager.getGameAssetManager().getSkin()));
                }

                view.getPassButton().setChecked(false);
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
}
