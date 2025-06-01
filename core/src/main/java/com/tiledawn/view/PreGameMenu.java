package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.PreGameMenuController;
import com.tiledawn.models.GameAssetManager;
import com.tiledawn.models.Messages;

public class PreGameMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final Label heroLabel;
    private final SelectBox<String> heroSelectBox;
    private final Label weaponLabel;
    private final SelectBox<String> weaponSelectBox;
    private final Label timeLabel;
    private final SelectBox<Integer> timeSelectBox;
    private final TextButton startGameButton;
    private final TextButton backButton;
    private final Table table;
    private final PreGameMenuController controller;


    public PreGameMenu(PreGameMenuController controller,Skin skin) {
        this.menuTitle = new Label(Messages.PreGAmeMenu.getMessage(), skin,"title");
        this.heroLabel = new Label(Messages.Hero.getMessage(), skin);
        this.heroSelectBox = new SelectBox<>(skin);
        this.weaponLabel = new Label(Messages.Weapon.getMessage(), skin);
        this.weaponSelectBox = new SelectBox<>(skin);
        this.timeLabel = new Label(Messages.Time.getMessage(), skin);
        this.timeSelectBox = new SelectBox<>(skin);
        this.startGameButton = new TextButton(Messages.StartGame.getMessage(), skin);
        this.backButton = new TextButton(Messages.Back.getMessage(), skin);
        this.table = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        GameAssetManager gameAssetManager = GameAssetManager.getGameAssetManager();

        heroSelectBox.setItems(gameAssetManager.getHeroes());
        weaponSelectBox.setItems(gameAssetManager.getWeapons());
        timeSelectBox.setItems(gameAssetManager.getGameTimes());

        table.setFillParent(true);

        table.center();
        table.add(menuTitle).center().pad(200,100,200,10);
        table.row();
        table.add(heroLabel).left().pad(10,100,10,100);
        table.add(weaponLabel).center().pad(10,100,10,100);
        table.add(timeLabel).right().pad(10,100,10,100);
        table.row();
        table.add(heroSelectBox).left().pad(10,100,10,100);
        table.add(weaponSelectBox).center().pad(10,100,10,100);
        table.add(timeSelectBox).right().pad(10,100,10,100);
        table.row();
        table.add(startGameButton).center().pad(200,100,200,100);

        table.row();

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


    public SelectBox<String> getHeroSelectBox() {
        return heroSelectBox;
    }

    public SelectBox<String> getWeaponSelectBox() {
        return weaponSelectBox;
    }

    public SelectBox<Integer> getTimeSelectBox() {
        return timeSelectBox;
    }

    public TextButton getStartGameButton() {
        return startGameButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
