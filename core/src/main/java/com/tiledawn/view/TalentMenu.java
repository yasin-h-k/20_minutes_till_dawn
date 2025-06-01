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
import com.tiledawn.controller.TalentMenuController;
import com.tiledawn.models.App;
import com.tiledawn.models.Hero;
import com.tiledawn.models.Messages;

public class TalentMenu implements Screen {

    private Stage stage;
    private final Table table;
    private final Table leftTable;
    private final Table rightTable;
    private final Label titleLabel;
    private final Label Hero1Label;
    private final Label Hero2Label;
    private final Label Hero3Label;
    private final Label upKeyLabel;
    private final Label downKeyLabel;
    private final Label leftKeyLabel;
    private final Label rightKeyLabel;
    private final Label addHpKeyLabel;
    private final Label addLevelKeyLabel;
    private final Label decreaseTimeKeyLabel;
    private final Label bossFightKeyLabel;
    private final Label speedUpKeyLabel;
    private final Label vitilityKeyLabel;
    private final Label damagerKeyLabel;
    private final Label procreaseLabel;
    private final Label amocreaseLabel;
    private final Label speedyLabel;
    private final TextButton backButton;
    private final TalentMenuController controller;

    public TalentMenu(TalentMenuController controller, Skin skin) {
        stage = new Stage();
        this.titleLabel = new Label(Messages.TalentMenu.getMessage(), skin,"title");

        this.Hero1Label = new Label(Hero.SHANA + " : Hp = " + Hero.SHANA.getHealth() + " , Speed = "
            + Hero.SHANA.getSpeed(), skin);
        this.Hero2Label = new Label( Hero.DIAMOND + " : Hp = " + Hero.DIAMOND.getHealth() + " , Speed = " +
            Hero.DIAMOND.getSpeed(), skin);
        this.Hero3Label = new Label( Hero.DASHER + " : Hp = " + Hero.DASHER.getHealth() + " , Speed = " +
            Hero.DASHER.getSpeed(), skin);
        if(App.getCurrentUser().isWalkWithWSAD()){
            this.upKeyLabel = new Label("Up : W", skin);
            this.downKeyLabel = new Label("Down : S", skin);
            this.leftKeyLabel = new Label("Left : A", skin);
            this.rightKeyLabel = new Label("Right : D", skin);
        }
        else{
             this.upKeyLabel = new Label("Up : Up Key", skin);
             this.downKeyLabel = new Label("Down : Down Key", skin);
             this.leftKeyLabel = new Label("Left : Left Key", skin);
             this.rightKeyLabel = new Label("Right : Right key", skin);
        }

        this.addHpKeyLabel = new Label("Q : " + Messages.AddHP.getMessage(),skin);
        this.addLevelKeyLabel = new Label("Z : " + Messages.LevelUp.getMessage(),skin);
        this.decreaseTimeKeyLabel = new Label("X : " + Messages.DecreaseTime.getMessage(),skin);
        this.bossFightKeyLabel = new Label("G : " + Messages.GoToBossFight.getMessage(),skin);
        this.speedUpKeyLabel = new Label("C : " + Messages.SpeedUp.getMessage(),skin);

        this.vitilityKeyLabel = new Label("Vitility : " + Messages.AddHP.getMessage(), skin);
        this.damagerKeyLabel = new Label("Damager : " + Messages.BulletsDamageMoreFor.getMessage() , skin);
        this.procreaseLabel = new Label("Procrease : " + Messages.MakeWeaponProjectile.getMessage() , skin);
        this.amocreaseLabel = new Label("Amocrease : " + Messages.AddToAmmo.getMessage(), skin);
        this.speedyLabel = new Label("Speedy : " + Messages.SpeedUp.getMessage(), skin);

        this.backButton = new TextButton(Messages.Back.getMessage(), skin);

        this.table = new Table();
        this.leftTable = new Table();
        this.rightTable = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        leftTable.setFillParent(true);
        rightTable.setFillParent(true);

        table.center();
        table.add(titleLabel).pad(200);
        table.row();
        table.add(upKeyLabel).pad(20);
        table.row();
        table.add(downKeyLabel).pad(20);
        table.row();
        table.add(leftKeyLabel).pad(20);
        table.row();
        table.add(rightKeyLabel).pad(20);
        table.row();

        leftTable.left();
        leftTable.add(Hero1Label).pad(20);
        leftTable.row();
        leftTable.add(Hero2Label).pad(20);
        leftTable.row();
        leftTable.add(Hero3Label).pad(20);
        leftTable.row();

        rightTable.right();
        rightTable.add(addHpKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(addLevelKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(decreaseTimeKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(bossFightKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(speedUpKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(vitilityKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(damagerKeyLabel).pad(20);
        rightTable.row();
        rightTable.add(procreaseLabel).pad(20);
        rightTable.row();
        rightTable.add(amocreaseLabel).pad(20);
        rightTable.row();
        rightTable.add(speedyLabel).pad(20);

        backButton.left().bottom();
        stage.addActor(backButton);

        stage.addActor(table);
        stage.addActor(leftTable);
        stage.addActor(rightTable);
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

    public TextButton getBackButton() {
        return backButton;
    }
}
