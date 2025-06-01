package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.LoginMenuController;

public class LoginMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private Label usernameLabel;
    private TextField usernameBox;
    private Label ErrorUsername;
    private Label passwordLabel;
    private TextField passwordBox;
    private Label ErrorPass;
    private TextButton loginButton;
    private TextButton forgetPassButton;
    private Table table;
    private final LoginMenuController controller;


    public LoginMenu(LoginMenuController controller,Skin skin) {
        this.menuTitle = new Label("Login Menu", skin,"title");
        this.usernameLabel = new Label("Username : ", skin);
        this.usernameBox = new TextField("", skin);
        this.ErrorUsername = new Label("", skin);
        this.ErrorUsername.setColor(Color.RED);
        this.passwordLabel = new Label("Password : ", skin);
        this.passwordBox = new TextField("", skin);
        this.ErrorPass = new Label("", skin);
        this.ErrorPass.setColor(Color.RED);
        this.loginButton = new TextButton("Login", skin);
        this.forgetPassButton = new TextButton("Forget password", skin);
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
        table.add(menuTitle).colspan(2).pad(100, 0, 100, 0);
        table.row();
        table.add(usernameLabel).pad(10);
        table.add(usernameBox).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(ErrorUsername).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordLabel).pad(10);
        table.add(passwordBox).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(ErrorPass).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(forgetPassButton).pad(10);
        table.add(loginButton).pad(10);



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


    public Stage getStage() {
        return stage;
    }

    public Label getMenuTitle() {
        return menuTitle;
    }

    public TextField getUsernameBox() {
        return usernameBox;
    }

    public Label getErrorUsername() {
        return ErrorUsername;
    }

    public TextField getPasswordBox() {
        return passwordBox;
    }

    public Label getErrorPass() {
        return ErrorPass;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getForgetPassButton() {
        return forgetPassButton;
    }

}
