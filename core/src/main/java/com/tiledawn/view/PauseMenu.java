package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.PauseMenuController;
import com.tiledawn.models.App;
import com.tiledawn.models.Messages;


public class PauseMenu implements Screen {

    private Stage stage;
    private final Label menuTitle;
    private final TextButton continueButton;
    private final TextButton exitButton;
    private final TextButton saveButton;
    private final CheckBox blackWhiteCheckBox;

    private final Label addHpLabel;
    private final Label levelUpLabel;
    private final Label decreaseTimeLabel;
    private final Label goToBossFightLabel;
    private final Label speedUpLabel;

    private final Label vitalityLabel;
    private final Label damagerLabel;
    private final Label procreaseLabel;
    private final Label amocreaseLabel;
    private final Label speedyLabel;

    private final Table centerTable;
    private final Table rightTable;
    private final Table leftTable;

    private final PauseMenuController controller;

    public PauseMenu(PauseMenuController controller,Skin skin) {
        this.menuTitle = new Label(Messages.PauseMenu.getMessage(),skin,"title");

        this.continueButton = new TextButton(Messages.Continue.getMessage(), skin);
        this.exitButton = new TextButton(Messages.Exit.getMessage(), skin);
        this.saveButton = new TextButton(Messages.Save.getMessage(), skin);
        this.blackWhiteCheckBox = new CheckBox(Messages.BlackWhite.getMessage(), skin);

        this.addHpLabel = new Label("Q : " + Messages.AddHP.getMessage(),skin);
        this.levelUpLabel = new Label("Z : " + Messages.LevelUp.getMessage(),skin);
        this.decreaseTimeLabel = new Label("X : " + Messages.DecreaseTime.getMessage(),skin);
        this.goToBossFightLabel = new Label("G : " + Messages.GoToBossFight.getMessage(),skin);
        this.speedUpLabel = new Label("C : " + Messages.SpeedUp.getMessage(),skin);

        this.vitalityLabel = new Label("Vitality : " + App.getCurrentGame().getVitality(),skin);
        this.damagerLabel = new Label("Damager : " + App.getCurrentGame().getDamager(),skin);
        this.procreaseLabel = new Label("Procrease : " + App.getCurrentGame().getProcrease(),skin);
        this.amocreaseLabel = new Label("Amocreate : " + App.getCurrentGame().getAmocrease(),skin);
        this.speedyLabel = new Label("Speedy : " + App.getCurrentGame().getSpeedy(),skin);

        this.centerTable = new Table();
        this.rightTable = new Table();
        this.leftTable = new Table();

        this.controller = controller;
        controller.setView(this);

    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        centerTable.setFillParent(true);
        rightTable.setFillParent(true);
        leftTable.setFillParent(true);

        centerTable.center();
        rightTable.right();
        leftTable.left();

        centerTable.add(menuTitle).pad(100,10,100,10);
        centerTable.row();
        centerTable.add(continueButton).pad(50,10,50,10);
        centerTable.row();
        centerTable.add(exitButton).pad(50,10,50,10);
        centerTable.row();
        centerTable.add(saveButton).pad(50,10,50,10);
        centerTable.row();
        centerTable.add(blackWhiteCheckBox).pad(50,10,50,10);
        centerTable.row();

        rightTable.add(addHpLabel).pad(50,10,50,10);
        rightTable.row();
        rightTable.add(levelUpLabel).pad(50,10,50,10);
        rightTable.row();
        rightTable.add(decreaseTimeLabel).pad(50,10,50,10);
        rightTable.row();
        rightTable.add(goToBossFightLabel).pad(50,10,50,10);
        rightTable.row();
        rightTable.add(speedUpLabel).pad(50,10,50,10);
        rightTable.row();

        leftTable.add(vitalityLabel).pad(50,10,50,10);
        leftTable.row();
        leftTable.add(damagerLabel).pad(50,10,50,10);
        leftTable.row();
        leftTable.add(procreaseLabel).pad(50,10,50,10);
        leftTable.row();
        leftTable.add(amocreaseLabel).pad(50,10,50,10);
        leftTable.row();
        leftTable.add(speedyLabel).pad(50,10,50,10);
        leftTable.row();

        stage.addActor(centerTable);
        stage.addActor(rightTable);
        stage.addActor(leftTable);
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

    public TextButton getContinueButton() {
        return continueButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getSaveButton() {
        return saveButton;
    }

    public CheckBox getBlackWhiteCheckBox() {
        return blackWhiteCheckBox;
    }
}
