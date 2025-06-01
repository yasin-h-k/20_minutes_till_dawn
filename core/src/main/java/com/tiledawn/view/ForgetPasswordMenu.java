package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.ForgetPasswordMenuController;

public class ForgetPasswordMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final Label securityQuestionLabel;
    private final TextField answerBox;
    private final Label ErrorAnswer;
    private final Label passwordLabel;
    private final TextField passwordBox;
    private final Label ErrorPass;
    private final TextButton textButton;
    private final Table table;
    private final ForgetPasswordMenuController controller;


    public ForgetPasswordMenu(ForgetPasswordMenuController controller,Skin skin) {
        this.menuTitle = new Label("Forget Password Menu", skin,"title");
        this.securityQuestionLabel = new Label(controller.getUser().getSecurityQuestion(), skin);
        this.answerBox = new TextField("", skin);
        this.ErrorAnswer = new Label("", skin);
        this.ErrorAnswer.setColor(Color.RED);
        this.passwordLabel = new Label("New Password : ", skin);
        this.passwordBox = new TextField("", skin);
        this.ErrorPass = new Label("", skin);
        this.ErrorPass.setColor(Color.RED);
        this.textButton = new TextButton("Change password", skin);
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
        table.add(securityQuestionLabel).pad(10);
        table.add(answerBox).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(ErrorAnswer).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordLabel).pad(10);
        table.add(passwordBox).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(ErrorPass).colspan(2);
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



    public TextField getAnswerBox() {
        return answerBox;
    }

    public Label getErrorAnswer() {
        return ErrorAnswer;
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

}
