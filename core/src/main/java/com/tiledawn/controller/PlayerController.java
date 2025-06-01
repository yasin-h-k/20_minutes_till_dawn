package com.tiledawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.tiledawn.Main;
import com.tiledawn.models.*;
import com.tiledawn.view.EndGameMenu;
import com.tiledawn.view.GameView;
import com.tiledawn.view.SelectAbilityMenu;

public class PlayerController {
    private final Player player;
    private final float worldSize;
    private GameView view;

    public PlayerController(Player player, float worldSize) {
        this.player = player;
        this.worldSize = worldSize;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void update(float delta, OrthographicCamera camera) {

        animation();
        heartAnimation();
        if(App.getCurrentUser().isWalkWithWSAD()) {
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                player.getSprite().flip(true, false);
                player.getBounds().x -= delta * player.getSpeed();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                player.getBounds().x += delta * player.getSpeed();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                player.getBounds().y += delta * player.getSpeed();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                player.getBounds().y -= delta * player.getSpeed();
            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                player.getSprite().flip(true, false);
                player.getBounds().x -= delta * player.getSpeed();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                player.getBounds().x += delta * player.getSpeed();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                player.getBounds().y += delta * player.getSpeed();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                player.getBounds().y -= delta * player.getSpeed();
            }
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            player.increaseHealth(1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
            player.setSpeed(player.getSpeed() * 2);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            player.increaseLevel();
            App.getCurrentGame().pause();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SelectAbilityMenu(new SelectAbilityMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        player.setX(MathUtils.clamp(player.getBounds().x, 0, worldSize));
        player.setY(MathUtils.clamp(player.getBounds().y, 0, worldSize));
        camera.position.set( MathUtils.clamp(player.getBounds().x,0, worldSize),
            MathUtils.clamp(player.getBounds().y,0, worldSize),0);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);

        updatePoints();

        if(player.getXp() > player.getLevel() * 20){
            player.decreaseXp(player.getLevel() * 20);
            player.increaseLevel();
            App.getCurrentGame().pause();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SelectAbilityMenu(new SelectAbilityMenuController(),
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        if(player.getHealth() <= 0){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new EndGameMenu(new EndGameController(), App.getCurrentGame(),false,
                GameAssetManager.getGameAssetManager().getSkin()));
        }

        view.getXpBar().setValue(Math.min((float) player.getXp()/(player.getLevel()*20) * 100,100));
    }

    public void updatePoints(){

        for(int i = App.getCurrentGame().getPoints().size() - 1; i >= 0; i--){
            if(player.getBounds().overlaps(App.getCurrentGame().getPoints().get(i).getBounds())){
                if(App.getCurrentUser().isSfx()){
                    GameAssetManager.getGameAssetManager().getCoinSfx().play();
                }
                player.increaseXp(Point.Xp);
                App.getCurrentGame().getPoints().remove(i);
            }
        }
    }

    public void render() {

        fovRender();

        player.getSprite().setPosition(player.getBounds().x,player.getBounds().y);
        player.getSprite().setSize(player.getBounds().width,player.getBounds().height);
        player.getSprite().draw(Main.getBatch());
        for(int i = 0 ; i < player.getMaxHealth() ; i++){
            if(i < player.getHealth()){
                player.getHealthSprite().setBounds(player.getBounds().x - (float) Gdx.graphics.getWidth() /2 + 50 * i,
                    player.getBounds().y + (float) Gdx.graphics.getWidth() /2 - 100,50,70);
                player.getHealthSprite().draw(Main.getBatch());
            }
            else{
                Main.getBatch().draw(GameAssetManager.getGameAssetManager().getDeadHeart(),
                    player.getBounds().x - (float) Gdx.graphics.getWidth() /2 + 50 * i,
                    player.getBounds().y + (float) Gdx.graphics.getWidth() /2 - 100,50,70);
            }

        }

        view.getKillFont().draw(Main.getBatch(),"Kills : " + player.getKills(),
            player.getBounds().x - (float)Gdx.graphics.getWidth()/2,
            player.getBounds().y + (float) Gdx.graphics.getHeight() /2 + player.getBounds().getHeight());
        view.getLevelFont().draw(Main.getBatch(),"Level : " + player.getLevel(),player.getBounds().x - 50,
            player.getBounds().y + (float) Gdx.graphics.getWidth() /2 - 100);

        for(Point point : App.getCurrentGame().getPoints()){
            Main.getBatch().draw(GameAssetManager.getGameAssetManager().getPointTexture(),point.getBounds().x,
                point.getBounds().y,Point.width,Point.height);
        }

        for(Explosion explosion : App.getCurrentGame().getPlayerDamaged()){
            explosion.getSprite().setPosition(explosion.getBounds().x,explosion.getBounds().y);
            explosion.getSprite().draw(Main.getBatch());
        }


    }

    private void fovRender(){
        Main.getBatch().setBlendFunction(GL20.GL_DST_COLOR,GL20.GL_ZERO);
        Main.getBatch().draw(GameAssetManager.getGameAssetManager().getFovTexture(),player.getBounds().x -
                (float) Gdx.graphics.getWidth() /2,
            player.getBounds().y - (float) Gdx.graphics.getWidth() /2,Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        Main.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void animation(){

        player.getSprite().setRegion(player.getHero().getAnimation().getKeyFrame(player.getTime()));

        if(! player.getHero().getAnimation().isAnimationFinished(player.getTime())){
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else{
            player.setTime(0);
        }

        player.getHero().getAnimation().setPlayMode(Animation.PlayMode.LOOP);
    }

    public void heartAnimation(){
        player.getHealthSprite().setRegion(GameAssetManager.getGameAssetManager().getHeartAnimation().
            getKeyFrame(player.getHeartTime()));

        if(! GameAssetManager.getGameAssetManager().getHeartAnimation().isAnimationFinished(player.getHeartTime())){
            player.setHeartTime(player.getHeartTime() + Gdx.graphics.getDeltaTime());
        }
        else{
            player.setHeartTime(0);
        }

        GameAssetManager.getGameAssetManager().getHeartAnimation().setPlayMode(Animation.PlayMode.LOOP);
    }

}
