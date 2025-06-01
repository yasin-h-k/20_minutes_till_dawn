package com.tiledawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.User;
import com.tiledawn.view.FirstMenu;
import com.tiledawn.view.SignupMenu;

import java.io.FileWriter;
import java.io.IOException;

public class SignupMenuController {

    private SignupMenu view;

    public void setView(SignupMenu view) {
        this.view = view;
    }



    public void handle() {
        if(view != null) {
            if(view.getTextButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                view.getErrorUsername().setText("");
                view.getErrorPass().setText("");

                String username = view.getUsernameBox().getText();
                String password = view.getPasswordBox().getText();
                if(App.getUsers().containsKey(username) || username.equals("Guest")) {
                    view.getErrorUsername().setText("Username is already taken !!");
                }
                else if(! checkPassword(password)) {
                    view.getErrorPass().setText("Password is weak !!");
                }
                else{
                    view.getErrorUsername().setText("Sign up successful !!");
                    view.getErrorUsername().setColor(Color.GREEN);
                    GameAssetManager gameAssetManager = GameAssetManager.getGameAssetManager();
                    App.getUsers().put(username, new User(username,password,
                        (String) view.getSecurityQuestionBox().getSelected(),view.getSecurityAnswerBox().getText(),
                        gameAssetManager.getAvatarPaths().get((int) Math.floor(Math.random() *
                            gameAssetManager.getAvatarPaths().size()))));

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    try (FileWriter file = new FileWriter("users.json")) {
                        gson.toJson(App.getUsers(), file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new FirstMenu(new FirstMenuController(),
                        GameAssetManager.getGameAssetManager().getSkin()));
                }
                view.getTextButton().setChecked(false);
            }
        }
    }



    public static boolean checkPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@%$#&*()_])[A-Za-z\\d@%$#&*()_]{8,}$");
    }

}
