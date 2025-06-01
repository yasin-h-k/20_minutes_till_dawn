package com.tiledawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.User;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.ScoreBoardMenu;

import java.util.ArrayList;

public class ScoreBoardMenuController {
    private ScoreBoardMenu view;

    public void setView(ScoreBoardMenu view) {
        this.view = view;
    }

    public void handle(){

        if(! view.getSelectBoxOrder().getSelected().equals(view.getCurrentOrderType())){
            if(view.getSelectBoxOrder().getSelected().equals("Score")){
                orderByScore();
            }
            else if(view.getSelectBoxOrder().getSelected().equals("Kills")){
                orderByKills();
            }
            else if(view.getSelectBoxOrder().getSelected().equals("Time")){
                orderByTime();
            }
            else if(view.getSelectBoxOrder().getSelected().equals("Username")){
                orderByUsername();
            }
        }

        if(view.getBackButton().isChecked()){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    private void orderByScore(){
        ArrayList<User> users = new ArrayList<>(App.getUsers().values());
        int numberOfUsers = users.size();
        Label[][] labels = view.getUsersInfo();
        User user;

        for(int i = 0 ; i < Math.min(numberOfUsers,ScoreBoardMenu.usersNumber) ; i++){
            user = users.get(0);
            for(User user1 : users){
                if(user1.getScore() > user.getScore()){
                    user = user1;
                }
            }
            putUserInTable(user,i,labels);
            users.remove(user);
        }

    }

    private void orderByKills(){
        ArrayList<User> users = new ArrayList<>(App.getUsers().values());
        int numberOfUsers = users.size();
        Label[][] labels = view.getUsersInfo();
        User user;

        for(int i = 0 ; i < Math.min(numberOfUsers,ScoreBoardMenu.usersNumber) ; i++){
            user = users.get(0);
            for(User user1 : users){
                if(user1.getNumberOfKills() > user.getNumberOfKills()){
                    user = user1;
                }
            }
            putUserInTable(user,i,labels);
            users.remove(user);
        }
    }

    private void orderByTime(){
        ArrayList<User> users = new ArrayList<>(App.getUsers().values());
        int numberOfUsers = users.size();
        Label[][] labels = view.getUsersInfo();
        User user;

        for(int i = 0 ; i < Math.min(numberOfUsers,ScoreBoardMenu.usersNumber) ; i++){
            user = users.get(0);
            for(User user1 : users){
                if(user1.getMaxSurvivalTime() > user.getMaxSurvivalTime()){
                    user = user1;
                }
            }
            putUserInTable(user,i,labels);
            users.remove(user);
        }
    }

    private void orderByUsername(){
        ArrayList<User> users = new ArrayList<>(App.getUsers().values());
        int numberOfUsers = users.size();
        Label[][] labels = view.getUsersInfo();
        User user;

        for(int i = 0 ; i < Math.min(numberOfUsers,ScoreBoardMenu.usersNumber) ; i++){
            user = users.get(0);
            for(User user1 : users){
                if(user1.getUsername().compareTo(user.getUsername()) > 0){
                    user = user1;
                }
            }
            putUserInTable(user,i,labels);
            users.remove(user);
        }
    }

    private void putUserInTable(User user,int i,Label[][] labels){
        i++;

        if(i == 1){
            for(Label label : labels[i]){
                label.clear();
                label.setColor(Color.GOLD);
            }
        }
        else if(i == 2){
            for(Label label : labels[i]){
                label.clear();
                label.setColor(Color.RED);
            }
        }
        else if(i == 3){
            for(Label label : labels[i]){
                label.clear();
                label.setColor(Color.GREEN);
            }
        }
        else{
            for(Label label : labels[i]){
                label.clear();
                label.setColor(Color.GRAY);
            }
        }

        if(user.getUsername().equals(App.getCurrentUser().getUsername())){
            for(Label label : labels[i]){
                label.clear();
                label.setColor(Color.PURPLE);
            }
        }

        labels[i][0].setText(i);
        labels[i][1].setText(user.getUsername());
        labels[i][2].setText(String.valueOf(user.getScore()));
        labels[i][3].setText(String.valueOf(user.getNumberOfKills()));
        labels[i][4].setText(String.valueOf(user.getMaxSurvivalTime()/1000));
    }

}
