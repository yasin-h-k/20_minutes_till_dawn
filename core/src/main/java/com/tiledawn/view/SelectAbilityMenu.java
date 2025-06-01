package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.SelectAbilityMenuController;
import com.tiledawn.models.Messages;

public class SelectAbilityMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final TextButton vitalityButton;
    private final TextButton damagerButton;
    private final TextButton procreaseButton;
    private final TextButton amocreaseButton;
    private final TextButton speedyButton;
    private final Table table;

    private final SelectAbilityMenuController controller;

    public SelectAbilityMenu(SelectAbilityMenuController controller, Skin skin) {
        this.menuTitle = new Label(Messages.SelectAbility.getMessage(), skin,"title");
        this.vitalityButton = new TextButton("Vitality", skin);
        this.damagerButton = new TextButton("Damager", skin);
        this.procreaseButton = new TextButton("Procrease", skin);
        this.amocreaseButton = new TextButton("Amocrease", skin);
        this.speedyButton = new TextButton("Speedy", skin);
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
        table.add(menuTitle).pad(100,10,100,10);
        table.row();
        table.add(vitalityButton).pad(25,10,25,10);
        table.row();
        table.add(damagerButton).pad(25,10,25,10);
        table.row();
        table.add(procreaseButton).pad(25,10,25,10);
        table.row();
        table.add(amocreaseButton).pad(25,10,25,10);
        table.row();
        table.add(speedyButton).pad(25,10,25,10);


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

    public TextButton getVitalityButton() {
        return vitalityButton;
    }

    public TextButton getDamagerButton() {
        return damagerButton;
    }

    public TextButton getProcreaseButton() {
        return procreaseButton;
    }

    public TextButton getAmocreaseButton() {
        return amocreaseButton;
    }

    public TextButton getSpeedyButton() {
        return speedyButton;
    }
}
