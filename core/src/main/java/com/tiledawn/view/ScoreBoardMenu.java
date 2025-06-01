package com.tiledawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiledawn.Main;
import com.tiledawn.controller.ScoreBoardMenuController;
import com.tiledawn.models.Messages;

public class ScoreBoardMenu implements Screen {

    public static final int numberOfUserInfo = 5;
    public static final int usersNumber = 10;

    private Stage stage;
    private final Label menuTitle;
    private final SelectBox<String> selectBoxOrder;
    private final Label[][] usersInfo;
    private String currentOrderType;
    private final TextButton backButton;
    private final Table table;
    private final ScoreBoardMenuController controller;


    public ScoreBoardMenu(ScoreBoardMenuController controller,Skin skin) {
        this.menuTitle = new Label(Messages.ScoreBoardMenu.getMessage(), skin,"title");

        this.selectBoxOrder = new SelectBox<>(skin);
        this.usersInfo = new Label[usersNumber][numberOfUserInfo];
        for(int i = 0 ; i < numberOfUserInfo ; i++){
            for(int j = 0 ; j < numberOfUserInfo ; j++){
                usersInfo[i][j] = new Label("", skin);
            }
        }
        usersInfo[0][0].setText(Messages.Rank.getMessage());
        usersInfo[0][1].setText(Messages.Username.getMessage());
        usersInfo[0][2].setText(Messages.Score.getMessage());
        usersInfo[0][3].setText(Messages.Kills.getMessage());
        usersInfo[0][4].setText(Messages.Survival.getMessage() + " " + Messages.Time);

        this.backButton = new TextButton(Messages.Back.getMessage(), skin);

        this.table = new Table();

        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        selectBoxOrder.setItems(Messages.Score.getMessage(),Messages.Kills.getMessage(),Messages.Time.getMessage(),
            Messages.Username.getMessage());


        table.setFillParent(true);

        table.center();
        table.add(menuTitle).colspan(3).pad(50,0,50,0);
        table.row();

        for(Label[] label : usersInfo){
            for(Label label1 : label){
                table.add(label1).pad(10,50,10,50);
            }
            table.row();
        }
        table.add(selectBoxOrder).colspan(3).pad(10);

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

    public SelectBox<String> getSelectBoxOrder() {
        return selectBoxOrder;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Label[][] getUsersInfo() {
        return usersInfo;
    }

    public String getCurrentOrderType() {
        return currentOrderType;
    }

    public void setCurrentOrderType(String currentOrderType) {
        this.currentOrderType = currentOrderType;
    }


}
