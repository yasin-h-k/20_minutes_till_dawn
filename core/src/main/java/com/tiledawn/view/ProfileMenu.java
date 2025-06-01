package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.ProfileMenuController;
import com.tiledawn.models.Messages;

public class ProfileMenu implements Screen {


    private Stage stage;
    private final Label menuTitle;

    private final Label usernameTitle;
    private final Label usernameLabel;
    private final TextField usernameBox;
    private final Label ErrorUsername;
    private final TextButton usernameButton;

    private final Label passwordTitle;
    private final Label passwordLabel;
    private final TextField passwordBox;
    private final Label ErrorPass;
    private final TextButton passButton;

    private final TextButton changeUsernameButton;
    private final TextButton changePasswordButton;
    private final TextButton changeAvatarButton;
    private final TextButton deleteAccountButton;
    private final TextButton backButton;
    private final Table table;
    private final ProfileMenuController controller;


    public ProfileMenu(ProfileMenuController controller,Skin skin) {
        this.menuTitle = new Label(Messages.ProfileMenu.getMessage(), skin,"title");
        this.changeUsernameButton = new TextButton(Messages.Change.getMessage() + " " +
            Messages.Username.getMessage(), skin);
        this.changePasswordButton = new TextButton(Messages.Change.getMessage() +" " +
            Messages.Password.getMessage(), skin);
        this.changeAvatarButton = new TextButton(Messages.Change.getMessage() +" " +
            Messages.Avatar.getMessage(), skin);
        this.deleteAccountButton = new TextButton(Messages.DeleteAccount.getMessage(), skin);

        this.usernameTitle = new Label(Messages.Change.getMessage() + " " + Messages.Username.getMessage() + " " +
           Messages.Menu.getMessage(), skin);
        this.usernameTitle.setFontScale(2f);
        this.usernameLabel = new Label(Messages.New.getMessage() + " " + Messages.Username.getMessage()
            +" : ", skin);
        this.usernameBox = new TextField("", skin);
        this.ErrorUsername = new Label("", skin);
        this.ErrorUsername.setColor(Color.RED);
        this.usernameButton = new TextButton(Messages.Change.getMessage() + " " + Messages.Username.getMessage()
            , skin);

        this.passwordTitle = new Label(Messages.Change.getMessage() + " " + Messages.Password.getMessage()
            +" " + Messages.Menu, skin);
        this.passwordTitle.setFontScale(2f);
        this.passwordLabel = new Label(Messages.New.getMessage() + " " + Messages.Password.getMessage()
            +" : ", skin);
        this.passwordBox = new TextField("", skin);
        this.ErrorPass = new Label("", skin);
        this.ErrorPass.setColor(Color.RED);
        this.passButton = new TextButton( Messages.Change.getMessage() + " " +
            Messages.Password.getMessage(), skin);

        this.backButton = new TextButton(Messages.Back.getMessage(), skin);

        this.table = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);

        table.center();
        table.add(menuTitle).colspan(2).pad(100,0,100,0);
        table.row();
        table.add(changeUsernameButton).colspan(2).pad(20,0,20,0);
        table.row();
        table.add(changePasswordButton).colspan(2).pad(20,0,20,0);
        table.row();
        table.add(changeAvatarButton).colspan(2).pad(20,0,20,0);
        table.row();
        table.add(deleteAccountButton).colspan(2).pad(20,0,20,0);

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


    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getChangeAvatarButton() {
        return changeAvatarButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public Label getUsernameTitle() {
        return usernameTitle;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public TextField getUsernameBox() {
        return usernameBox;
    }

    public Label getErrorUsername() {
        return ErrorUsername;
    }

    public TextButton getUsernameButton() {
        return usernameButton;
    }

    public Label getPasswordTitle() {
        return passwordTitle;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public TextField getPasswordBox() {
        return passwordBox;
    }

    public Label getErrorPass() {
        return ErrorPass;
    }

    public TextButton getPassButton() {
        return passButton;
    }

    public Table getTable() {
        return table;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
