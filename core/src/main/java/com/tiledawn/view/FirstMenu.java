package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.FirstMenuController;

public class FirstMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final TextButton signupButton;
    private final  TextButton loginButton;
    private final TextButton guestButton;
    private final Table table;
    private final FirstMenuController controller;


    public FirstMenu(FirstMenuController controller,Skin skin) {
        this.menuTitle = new Label("First Menu", skin,"title");
        this.signupButton = new TextButton("Signup", skin);
        this.loginButton = new TextButton("Login", skin);
        this.guestButton = new TextButton("Guest", skin);

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
        table.add(menuTitle).pad(50,0,100,0);
        table.row().pad(100,0,50,0);
        table.add(signupButton);
        table.row().pad(50,0,50,0);
        table.add(loginButton);
        table.row().pad(50,0,50,0);
        table.add(guestButton);


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

    public TextButton getSignupButton() {
        return signupButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getGuestButton() {
        return guestButton;
    }

    public Table getTable() {
        return table;
    }

    public FirstMenuController getController() {
        return controller;
    }
}
