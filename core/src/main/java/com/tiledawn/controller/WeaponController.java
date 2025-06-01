package com.tiledawn.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tiledawn.Main;
import com.tiledawn.models.*;

import java.util.ArrayList;



public class WeaponController {
    private final Player player;
    private final float worldSize;

    public WeaponController(Player player, float worldSize) {
        this.player = player;
        this.worldSize = worldSize;
    }

    public void handle(float delta,OrthographicCamera camera){
        ArrayList<Bullet> bullets = App.getCurrentGame().getBullets();
        for(int i = bullets.size()-1; i >= 0; i--){
            bullets.get(i).update(delta);

            if(bullets.get(i).isOffScreen(worldSize)){
                App.getCurrentGame().getBullets().remove(bullets.get(i));
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.R) || player.getWeapon().isReloading()){
            reloadAmmo(delta);
        }

        if(App.getCurrentUser().isAutoReload() && player.getWeapon().getAmmo() == 0){
            reloadAmmo(delta);
        }

        if(App.getCurrentUser().isAutoAim()){
            setCursor(camera);
        }

    }

    public void render(){
        for(Bullet bullet : App.getCurrentGame().getBullets()){
            Main.getBatch().draw(GameAssetManager.getGameAssetManager().getBulletTexture(),bullet.getBounds().x,
                bullet.getBounds().y,bullet.getBounds().width,bullet.getBounds().height);
        }
        for(int i = 0 ; i < player.getWeapon().getAmmo() ; i++){
            Main.getBatch().draw(GameAssetManager.getGameAssetManager().getBulletTexture(),
                player.getBounds().x - (float) Gdx.graphics.getWidth() /2 + i * 50 ,
                (player.getBounds().y + (float) Gdx.graphics.getWidth() / 2) - 200,60,60)  ;
        }
    }

    public void handleShoot(int screenX, int screenY, OrthographicCamera camera){
        if(player.getWeapon().getAmmo() > 0){
            if(App.getCurrentUser().isSfx()){
                GameAssetManager.getGameAssetManager().getShotSfx().play();
            }
            for(int i = 0 ; i < player.getWeapon().getProjectile();i++) {
                Vector3 direction = new Vector3(screenX, screenY, 0);
                camera.unproject(direction);
                direction.x -= player.getBounds().x;
                direction.y -= player.getBounds().y;
                direction = direction.rotate(Vector3.Z,i * 4);
                direction.nor();
                App.getCurrentGame().getBullets().add(new Bullet(player.getBounds().x, player.getBounds().y,
                    player.getWeapon().getDamage(), direction));
            }
            player.getWeapon().decreaseAmmo(1);
        }
    }

    public void setCursor(OrthographicCamera camera){
        TentacleMonster tentacleMonster = nearestTentacle();
        EyeBat eyeBat = nearestEyeBat();
        BossFight bossFight = nearestBossFight();
        Vector3 position;

        if(tentacleMonster != null){
            if(eyeBat != null && calculateDistance(player.getBounds(),tentacleMonster.getBounds()) >
                calculateDistance(player.getBounds(), eyeBat.getBounds())){
                if(bossFight != null && calculateDistance(player.getBounds(),eyeBat.getBounds()) >
                    calculateDistance(player.getBounds(), bossFight.getBounds())){
                    position = new Vector3(bossFight.getBounds().x + bossFight.getBounds().width/2,
                        bossFight.getBounds().y + bossFight.getBounds().height/2, 0);
                    camera.project(position);
                    Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
                }
                else{
                    position = new Vector3(eyeBat.getBounds().x + eyeBat.getBounds().width/2,
                        eyeBat.getBounds().y + eyeBat.getBounds().height/2, 0);
                    camera.project(position);
                    Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
                }
            }
            else if(bossFight != null && calculateDistance(player.getBounds(),tentacleMonster.getBounds()) >
                calculateDistance(player.getBounds(), bossFight.getBounds())){
                position = new Vector3(bossFight.getBounds().x + bossFight.getBounds().width/2,
                    bossFight.getBounds().y + bossFight.getBounds().height/2, 0);
                camera.project(position);
                Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
            }
            else{
                position = new Vector3(tentacleMonster.getBounds().x + tentacleMonster.getBounds().width/2,
                    tentacleMonster.getBounds().y + tentacleMonster.getBounds().height/2, 0);
                camera.project(position);
                Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
            }
        }
        else if(eyeBat != null){
            if(bossFight != null && calculateDistance(player.getBounds(),bossFight.getBounds()) <
                calculateDistance(player.getBounds(), eyeBat.getBounds())){
                position = new Vector3(bossFight.getBounds().x + bossFight.getBounds().width/2,
                    bossFight.getBounds().y + bossFight.getBounds().height/2, 0);
                camera.project(position);
                Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
            }
            else{
                position = new Vector3(eyeBat.getBounds().x + eyeBat.getBounds().width/2,
                    eyeBat.getBounds().y + eyeBat.getBounds().height/2, 0);
                camera.project(position);
                Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
            }

        }
        else if(bossFight != null){
            position = new Vector3(bossFight.getBounds().x + bossFight.getBounds().width/2,
                bossFight.getBounds().y + bossFight.getBounds().height/2, 0);
            camera.project(position);
            Gdx.input.setCursorPosition((int)position.x,(int)(Gdx.graphics.getHeight() - position.y));
        }

    }

    private TentacleMonster nearestTentacle(){
        TentacleMonster nearest = null;
        for(TentacleMonster tentacleMonster : App.getCurrentGame().getTentacleMonsters()){
            if(nearest == null){
                nearest = tentacleMonster;
            }
            else if(calculateDistance(player.getBounds(),nearest.getBounds()) >
                calculateDistance(player.getBounds(),tentacleMonster.getBounds())){
                nearest = tentacleMonster;
            }
        }
        return nearest;
    }

    private EyeBat nearestEyeBat(){
        EyeBat nearest = null;
        for(EyeBat eyeBat : App.getCurrentGame().getEyeBats()){
            if(nearest == null){
                nearest = eyeBat;
            }
            else if(calculateDistance(player.getBounds(),nearest.getBounds()) >
                calculateDistance(player.getBounds(),eyeBat.getBounds())){
                nearest = eyeBat;
            }
        }
        return nearest;
    }

    private BossFight nearestBossFight(){
        BossFight nearest = null;
        for(BossFight bossFight : App.getCurrentGame().getBossFights()){
            if(nearest == null){
                nearest = bossFight;
            }
            else if(calculateDistance(player.getBounds(),nearest.getBounds()) >
                calculateDistance(player.getBounds(),bossFight.getBounds())){
                nearest = bossFight;
            }
        }
        return nearest;
    }

    private float calculateDistance(Rectangle rectangle1, Rectangle rectangle2){
        return ((rectangle1.x - rectangle2.x) * (rectangle1.x - rectangle2.x)) + ((rectangle1.y - rectangle2.y) *
            (rectangle1.y - rectangle2.y));
    }

    public void reloadAmmo(float delta) {

        if(App.getCurrentUser().isSfx()){
            GameAssetManager.getGameAssetManager().getReloadSfx().play();
        }

        Weapon weapon = player.getWeapon();

        weapon.setReloading(true);
        weapon.increaseReloadTimer(delta);
        if(weapon.getReloadTimer() > weapon.getType().getReloadTime()){
            weapon.setReloadTimer(0f);
            weapon.setReloading(false);
            weapon.reloadAmmo();
        }
    }


}
