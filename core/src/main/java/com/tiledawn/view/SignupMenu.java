package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.SignupMenuController;

public class SignupMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final Label usernameLabel;
    private final TextField usernameBox;
    private final Label ErrorUsername;
    private final Label passwordLabel;
    private final TextField passwordBox;
    private final Label ErrorPass;
    private final Label securityQuestionLabel;
    private final SelectBox<String> securityQuestionBox;
    private final Label securityAnswerLabel;
    private final TextField securityAnswerBox;
    private final TextButton textButton;
    private final Table table;
    private final SignupMenuController controller;


    public SignupMenu(SignupMenuController controller,Skin skin) {
        this.menuTitle = new Label("Signup Menu", skin,"title");
        this.usernameLabel = new Label("Username : ", skin);
        this.usernameBox = new TextField("", skin);
        this.ErrorUsername = new Label("", skin);
        this.ErrorUsername.setColor(Color.RED);
        this.passwordLabel = new Label("Password : ", skin);
        this.passwordBox = new TextField("", skin);
        this.ErrorPass = new Label("", skin);
        this.ErrorPass.setColor(Color.RED);
        this.securityQuestionLabel = new Label("Security Question : ", skin);
        this.securityQuestionBox = new SelectBox<>(skin);
        this.securityAnswerLabel = new Label("Answer : ", skin);
        this.securityAnswerBox = new TextField("", skin);
        this.textButton = new TextButton("Signup", skin);
        this.table = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        securityQuestionBox.setItems("What is your favorite color?", "What is your favorite food?");

        table.setFillParent(true);

        table.center();
        table.add(menuTitle).colspan(2).pad(100,0,100,0);
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
        table.add(securityQuestionLabel).pad(10);
        table.add(securityQuestionBox).colspan(2).width(800);
        table.row().pad(10, 0, 10, 0);
        table.add(securityAnswerLabel).pad(10);
        table.add(securityAnswerBox).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(textButton).colspan(2);


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

    public TextButton getTextButton() {
        return textButton;
    }

    public SelectBox getSecurityQuestionBox() {
        return securityQuestionBox;
    }

    public TextField getSecurityAnswerBox() {
        return securityAnswerBox;
    }
}
