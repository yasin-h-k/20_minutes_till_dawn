package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.SettingMenuController;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.Messages;

public class SettingMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final Label musicVolume;
    private final Slider musicVolumeSlider;
    private final Label musicType;
    private final SelectBox<String> musicSelectBox;
    private final CheckBox sfxCheckBox;
    private final CheckBox keyboardCheckBox;
    private final CheckBox autoReloadCheckBox;
    private final CheckBox blackWhiteCheckBox;
    private final CheckBox languageCheckBox;
    private final TextButton backButton;

    private final Table table;
    private final SettingMenuController controller;


    public SettingMenu(SettingMenuController controller,Skin skin) {
        this.menuTitle = new Label(Messages.SettingMenu.getMessage(), skin,"title");
        this.musicVolume = new Label(Messages.Music.getMessage() +" " + Messages.Volume.getMessage() + " : ",skin);
        this.musicVolumeSlider = new Slider(0, 1, 0.01f, false, skin);
        this.musicType = new Label(Messages.Music.getMessage() + " : ",skin);
        this.musicSelectBox = new SelectBox<>(skin);
        this.sfxCheckBox = new CheckBox("SFX " + Messages.Sound.getMessage(),skin);
        this.keyboardCheckBox = new CheckBox("Walk With WASD", skin);
        this.autoReloadCheckBox = new CheckBox( Messages.Auto.getMessage() + " " + Messages.Reload.getMessage()
            , skin);
        this.blackWhiteCheckBox = new CheckBox(Messages.BlackWhite.getMessage(), skin);
        this.languageCheckBox = new CheckBox(Messages.Language.getMessage(), skin);
        this.backButton = new TextButton(Messages.Back.getMessage(), skin);

        this.table = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        musicVolumeSlider.setValue(App.getMusic().getVolume());
        sfxCheckBox.setChecked(App.getCurrentUser().isSfx());
        keyboardCheckBox.setChecked(App.getCurrentUser().isWalkWithWSAD());
        autoReloadCheckBox.setChecked(App.getCurrentUser().isAutoReload());
        blackWhiteCheckBox.setChecked(App.getCurrentUser().isBlackWhite());

        musicSelectBox.setItems(GameAssetManager.getGameAssetManager().getMusics());

        table.setFillParent(true);

        table.center();
        table.add(menuTitle).center().pad(200,0,100,0);
        table.row();
        table.add(musicVolume).pad(10,0,10,0);
        table.add(musicVolumeSlider).size(300,5).pad(10,0,10,0);
        table.row();
        table.add(musicType).pad(10,0,10,0);
        table.add(musicSelectBox).pad(10,0,10,0);
        table.row();
        table.add(sfxCheckBox).pad(10,100,10,100);
        table.row();
        table.add(keyboardCheckBox).pad(10,100,10,100);
        table.row();
        table.add(autoReloadCheckBox).pad(10,100,10,100);
        table.row();
        table.add(blackWhiteCheckBox).pad(10,100,10,100);
        table.row();
        table.add(languageCheckBox).pad(10,100,10,100);

        backButton.left().bottom();
        stage.addActor(backButton);


        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        controller.handle();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    public Slider getMusicVolumeSlider() {
        return musicVolumeSlider;
    }

    public SelectBox<String> getMusicSelectBox() {
        return musicSelectBox;
    }

    public CheckBox getSfxCheckBox() {
        return sfxCheckBox;
    }

    public CheckBox getKeyboardCheckBox() {
        return keyboardCheckBox;
    }

    public CheckBox getAutoReloadCheckBox() {
        return autoReloadCheckBox;
    }

    public CheckBox getBlackWhiteCheckBox() {
        return blackWhiteCheckBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public CheckBox getLanguageCheckBox() {
        return languageCheckBox;
    }
}
