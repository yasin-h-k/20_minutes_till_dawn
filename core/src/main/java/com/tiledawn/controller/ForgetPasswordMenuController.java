package com.tiledawn.controller;

import com.tiledawn.Main;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.User;
import com.tiledawn.view.FirstMenu;
import com.tiledawn.view.ForgetPasswordMenu;

public class ForgetPasswordMenuController {
    private ForgetPasswordMenu view;
    private final User user;

    public ForgetPasswordMenuController(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setView(ForgetPasswordMenu view) {
        this.view = view;
    }

    public void handle(){
        if(view != null){
            if(view.getTextButton().isChecked()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
                view.getErrorAnswer().setText("");
                view.getErrorPass().setText("");

                String password = view.getPasswordBox().getText();
                if(! view.getAnswerBox().getText().equals(user.getSecurityAnswer())){
                    view.getErrorAnswer().setText("Wrong answer");
                }
                else if(! SignupMenuController.checkPassword(password)){
                    view.getErrorPass().setText("Password is weak !!");
                }
                else{
                    user.setPassword(password);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new FirstMenu(new FirstMenuController(),
                        GameAssetManager.getGameAssetManager().getSkin()));
                }
                view.getTextButton().setChecked(false);
            }

        }
    }
}
