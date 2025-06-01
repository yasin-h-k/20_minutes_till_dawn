package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.EndGameController;
import com.tiledawn.models.*;

public class EndGameMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final Label userName;
    private final Label survivalTime;
    private final Label kills;
    private final Label score;
    private final TextButton exitButton;
    private final Table table;

    private final EndGameController controller;

    public EndGameMenu(EndGameController controller,AppGame game, boolean win, Skin skin) {
        if(win){
            this.menuTitle = new Label(Messages.Win.getMessage(), skin,"title");
            this.menuTitle.setColor(Color.GREEN);
        }
        else{
            this.menuTitle = new Label(Messages.Dead.getMessage(), skin,"title");
            this.menuTitle.setColor(Color.RED);
        }

        this.userName = new Label(Messages.Username.getMessage() + " : " + App.getCurrentUser().getUsername(), skin);
        this.score = new Label(Messages.Score.getMessage() + " : " + App.getCurrentGame().getPlayer().getKills() * App.getCurrentGame().getElapsedTime()/1000 , skin);
        this.survivalTime = new Label(Messages.Time.getMessage() + " : " + game.getElapsedTime()/1000, skin);
        this.kills = new Label(Messages.Kills.getMessage() + " : " + game.getPlayer().getKills(),skin);
        this.exitButton = new TextButton(Messages.Exit.getMessage(), skin);
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
        table.add(menuTitle).pad(100,100,100,100);
        table.row();
        table.add(userName).pad(50,100,50,100);
        table.row();
        table.add(score).pad(50,100,50,100);
        table.row();
        table.add(survivalTime).pad(50,100,50,100);
        table.row();
        table.add(kills).pad(50,100,50,100);
        table.row();
        table.add(exitButton).pad(100,100,100,100);

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

    public TextButton getExitButton() {
        return exitButton;
    }
}
