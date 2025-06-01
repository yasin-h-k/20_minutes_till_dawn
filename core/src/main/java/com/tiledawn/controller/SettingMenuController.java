package com.tiledawn.controller;

import com.badlogic.gdx.Gdx;
import com.tiledawn.Main;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.view.MainMenu;
import com.tiledawn.view.SettingMenu;

public class SettingMenuController {

    private SettingMenu view;


    public void setView(SettingMenu view) {
        this.view = view;
    }

    public void handle(){

        if(! view.getMusicSelectBox().getSelected().equals(App.getPlayingMusic())){
            App.getMusic().stop();
            App.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Musics/" + view.getMusicSelectBox().getSelected() + ".mp3")));
            App.setPlayingMusic(view.getMusicSelectBox().getSelected());
            App.getMusic().play();
        }

        App.getCurrentUser().setAutoReload(view.getAutoReloadCheckBox().isChecked());
        App.getCurrentUser().setSfx(view.getSfxCheckBox().isChecked());
        App.getCurrentUser().setFrench(view.getLanguageCheckBox().isChecked());
        App.getCurrentUser().setWalkWithWSAD(view.getKeyboardCheckBox().isChecked());

        if(view.getBlackWhiteCheckBox().isChecked()){
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getShader());
        }
        else{
            Main.getBatch().setShader(null);
        }

        if(view.getBackButton().isChecked()){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getButtonSFX().play();
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }


        //Todo music sound
        App.getMusic().setVolume(view.getMusicVolumeSlider().getValue());

    }


}
