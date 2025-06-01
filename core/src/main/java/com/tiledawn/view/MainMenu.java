package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.MainMenuController;
import com.tiledawn.models.App;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.Messages;
import com.tiledawn.models.User;

public class MainMenu implements Screen {
    private Stage stage;
    private final Label menuTitle;
    private final Image avatar;
    private final Label username;
    private final Label score;
    private final TextButton settingButton;
    private final TextButton profileButton;
    private final TextButton preGameButton;
    private final TextButton scoreBoardButton;
    private final TextButton TalentButton;
    private final TextButton playSavedButton;
    private final TextButton exitButton;
    private final Label errorLoadGame;
    private final Table table;
    private final Table profileTable;
    private final MainMenuController controller;



    public MainMenu(MainMenuController controller,Skin skin) {
        this.menuTitle = new Label(Messages.MainMenu.getMessage(), skin,"title");
        if(App.getCurrentUser() != null) {
            User currentUser = App.getCurrentUser();
            this.avatar = new Image(new Texture(Gdx.files.internal(currentUser.getAvatarAddress())));
            this.username = new Label(Messages.Username.getMessage() + " : " + App.getCurrentUser()
                .getUsername(), skin);
            this.score = new Label( Messages.Score.getMessage() + " : " + App.getCurrentUser().getScore(), skin);
        }
        else{
            this.avatar = new Image(new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().
                getAvatarPaths().get(2))));
            this.username = new Label(Messages.Username.getMessage() +" : " + Messages.Guest.getMessage(), skin);
            this.score = new Label(Messages.Score + " : 0", skin);
        }
        this.settingButton = new TextButton(Messages.SettingMenu.getMessage(), skin);
        this.profileButton = new TextButton(Messages.ProfileMenu.getMessage(), skin);
        this.preGameButton = new TextButton(Messages.PreGAmeMenu.getMessage(), skin);
        this.scoreBoardButton = new TextButton(Messages.ScoreBoardMenu.getMessage(), skin);
        this.TalentButton = new TextButton(Messages.TalentMenu.getMessage(), skin);
        this.playSavedButton = new TextButton(Messages.ResumeGame.getMessage(), skin);
        this.exitButton = new TextButton(Messages.Exit.getMessage(), skin);
        this.errorLoadGame = new Label("", skin);
        this.errorLoadGame.setColor(Color.RED);
        this.table = new Table();
        this.profileTable = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        profileTable.setFillParent(true);

        profileTable.left();
        profileTable.top();
        profileTable.add(avatar).pad(30,30,30,30).size(200,250);
        profileTable.row();
        profileTable.add(username);
        profileTable.row().pad(10,0,10,0);
        profileTable.add(score).colspan(2);
        profileTable.row();
        profileTable.add(exitButton).pad(400,0,0,0);

        table.center();
        table.add(menuTitle);
        table.row().pad(20, 0, 20, 0);
        table.add(settingButton);
        table.row().pad(20, 0, 20, 0);
        table.add(profileButton);
        table.row().pad(20, 0, 20, 0);
        table.add(preGameButton);
        table.row().pad(20, 0, 20, 0);
        table.add(scoreBoardButton);
        table.row().pad(20, 0, 20, 0);
        table.add(TalentButton);
        table.row().pad(20, 0, 20, 0);
        table.add(playSavedButton);
        table.row().pad(20, 0, 20, 0);
        table.add(errorLoadGame);



        stage.addActor(table);
        stage.addActor(profileTable);
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


    public TextButton getSettingButton() {
        return settingButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public TextButton getScoreBoardButton() {
        return scoreBoardButton;
    }

    public TextButton getTalentButton() {
        return TalentButton;
    }

    public TextButton getPlaySavedButton() {
        return playSavedButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public Label getErrorLoadGame() {
        return errorLoadGame;
    }
}
