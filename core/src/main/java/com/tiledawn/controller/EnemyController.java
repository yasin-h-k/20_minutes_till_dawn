package com.tiledawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.tiledawn.Main;
import com.tiledawn.models.*;

import java.util.Random;

public class EnemyController {

    private final float worldSize;
    private final Random rand = new Random();
    private final AppGame game = App.getCurrentGame();

    public EnemyController(float worldSize) {
        this.worldSize = worldSize;
    }

    public void spawnTrees(){
        if(App.getCurrentGame().getTrees().isEmpty()){
            for(int i = 0 ; i < 20;i++){
                game.getTrees().add(new Tree(new Rectangle(rand.nextInt(0,(int)worldSize),
                    rand.nextInt(0,(int)worldSize),Tree.width,Tree.height)));
            }
        }
    }

    public void update(float delta) {

        long now = TimeUtils.millis();

        if(now - game.getLastTentacleSpawnTime() > 3000){
            game.setLastTentacleSpawnTime(now);
            long numberOfMonsters = (now - game.getStartTime())/30_000;
            for(int i = 0 ; i < numberOfMonsters;i++){
                game.getTentacleMonsters().add(new TentacleMonster(new Rectangle(rand.nextInt(0,(int)worldSize),
                    rand.nextInt(0,(int)worldSize),TentacleMonster.width, TentacleMonster.height)));
            }
        }

        if(now - game.getLastEyeBatSpawnTime() > 10_000){
            game.setLastEyeBatSpawnTime(now);
            long numberOfMonsters = ((4 * (now - game.getStartTime()) - game.getGameTime())/1000)/30 + 1;
            for(int i = 0 ; i < numberOfMonsters;i++){
                game.getEyeBats().add(new EyeBat(new Rectangle(rand.nextInt(0,(int)worldSize),
                    rand.nextInt(0,(int)worldSize),EyeBat.width,EyeBat.height)));
            }
        }

        if(now - game.getStartTime() > (game.getEndTime() - game.getStartTime())/2 && (! game.isBossFightReleased())){
            game.setBossFightReleased(true);
            game.getBossFights().add(new BossFight(new Rectangle(rand.nextInt(0,(int)worldSize),
                rand.nextInt(0,(int)worldSize),BossFight.width,BossFight.height)));
        }

        for(Tree tree : game.getTrees()){
            if(tree.getBounds().overlaps(game.getPlayer().getBounds()) && now - game.getPlayer().getLastDamageTime() > 1000){
                game.getPlayer().setLastDamageTime(now);
                game.getPlayer().decreaseHealth(1);
            }
        }


        updateTentacles(now);
        updateBats(now);
        updateEnemyBullets(now,delta);
        updateBossFight(now);

        animation(delta);
    }

    private void updateTentacles(long now){
        for(int i = game.getTentacleMonsters().size() - 1; i >= 0 ; i--){
            TentacleMonster tentacle = game.getTentacleMonsters().get(i);
            if(tentacle.getBounds().overlaps(game.getPlayer().getBounds()) && TimeUtils.millis() - game.getPlayer().getLastDamageTime() > 1000) {
                game.getPlayer().setLastDamageTime(now);
                game.getPlayer().decreaseHealth(1);
            }
            for(int j = game.getBullets().size() - 1 ; j >= 0 ; j--){
                if(game.getBullets().get(j).getBounds().overlaps(tentacle.getBounds())){
                    tentacle.getBounds().x += game.getBullets().get(j).getDirection().x * 100;
                    tentacle.getBounds().y += game.getBullets().get(j).getDirection().y * 100;
                    tentacle.decreaseHealth(game.getBullets().get(j).getDamage());
                    game.getBullets().remove(j);
                    if(tentacle.getHealth() <= 0){
                        break;
                    }
                }
            }
            if(tentacle.getHealth() <= 0){
                game.getEnemyDead().add(new Explosion(tentacle.getBounds()));
                game.getTentacleMonsters().remove(tentacle);
                game.getPoints().add(new Point(tentacle.getBounds()));
                game.getPlayer().increaseKills(1);
            }

            Vector2 direction = new Vector2(game.getPlayer().getBounds().x - tentacle.getBounds().x,
                game.getPlayer().getBounds().y - tentacle.getBounds().y);
            direction.nor();
            tentacle.getBounds().x += direction.x * tentacle.getSpeed();
            tentacle.getBounds().y += direction.y * tentacle.getSpeed();
        }
    }

    private void updateBats(long now){
        for(int i = game.getEyeBats().size() - 1 ; i >= 0 ; i--){
            EyeBat bat = game.getEyeBats().get(i);
            if(bat.getBounds().overlaps(game.getPlayer().getBounds()) && TimeUtils.millis() - game.getPlayer().getLastDamageTime() > 1000){
                game.getPlayer().setLastDamageTime(now);
                game.getPlayer().decreaseHealth(1);
            }
            for(int j = game.getBullets().size() - 1 ; j >= 0 ; j--){
                if(game.getBullets().get(j).getBounds().overlaps(bat.getBounds())){
                    bat.getBounds().x += game.getBullets().get(j).getDirection().x * 100;
                    bat.getBounds().y += game.getBullets().get(j).getDirection().y * 100;
                    bat.decreaseHealth(game.getBullets().get(j).getDamage());
                    game.getBullets().remove(j);
                    if(bat.getHealth() <= 0){
                        break;
                    }
                }
            }
            if(bat.getHealth() <= 0){
                game.getEnemyDead().add(new Explosion(bat.getBounds()));
                game.getEyeBats().remove(bat);
                game.getPoints().add(new Point(bat.getBounds()));
                game.getPlayer().increaseKills(1);
            }

            Vector3 direction = new Vector3(game.getPlayer().getBounds().x - bat.getBounds().x,
                game.getPlayer().getBounds().y - bat.getBounds().y,0);
            direction.nor();
            bat.getBounds().x += direction.x * bat.getSpeed();
            bat.getBounds().y += direction.y * bat.getSpeed();

            if(now - bat.getLastShotTime() > 3000){
                bat.setLastShotTime(now);
                App.getCurrentGame().getEnemyBullets().add(new Bullet(bat.getBounds().x,bat.getBounds().y,
                    1,direction));
            }
        }
    }

    private void updateBossFight(long now){
        for(int i = game.getBossFights().size() - 1 ; i >= 0 ; i--){
            BossFight bossFight = game.getBossFights().get(i);
            if(bossFight.getBounds().overlaps(game.getPlayer().getBounds()) &&
                TimeUtils.millis() - game.getPlayer().getLastDamageTime() > 1000){
                game.getPlayer().setLastDamageTime(now);
                game.getPlayer().decreaseHealth(1);
            }
            for(int j = game.getBullets().size() - 1 ; j >= 0 ; j--){
                if(game.getBullets().get(j).getBounds().overlaps(bossFight.getBounds())){
                    bossFight.getBounds().x += game.getBullets().get(j).getDirection().x * 100;
                    bossFight.getBounds().y += game.getBullets().get(j).getDirection().y * 100;
                    bossFight.decreaseHealth(game.getBullets().get(j).getDamage());
                    game.getBullets().remove(j);
                    if(bossFight.getHealth() <= 0){
                        break;
                    }
                }
            }
            if(bossFight.getHealth() <= 0){
                game.getEnemyDead().add(new Explosion(bossFight.getBounds()));
                game.getBossFights().remove(bossFight);
                game.getPoints().add(new Point(bossFight.getBounds()));
                game.getPlayer().increaseKills(1);
            }

            Vector3 direction = new Vector3(game.getPlayer().getBounds().x - bossFight.getBounds().x,
                game.getPlayer().getBounds().y - bossFight.getBounds().y,0);
            direction.nor();
            if(now - bossFight.getLastDash() < 1000){
                direction.x *= 3;
                direction.y *= 3;
            }
            bossFight.getBounds().x += direction.x * bossFight.getSpeed();
            bossFight.getBounds().y += direction.y * bossFight.getSpeed();

            if(now - bossFight.getLastDash() > 5000) {
                bossFight.setLastDash(now);
            }
        }

        if(! game.getBossFights().isEmpty()){
            if(! game.getPlayer().getBounds().overlaps(game.getRing())){
                game.getPlayer().setLastDamageTime(now);
                game.getPlayer().decreaseHealth(1);
            }
            game.getRing().height -= 0.1f;
            game.getRing().width -= 0.1f;
            game.getRing().x += 0.05f;
            game.getRing().y += 0.05f;
        }
    }

    private void updateEnemyBullets(long now,float delta){
        for(int i =  game.getEnemyBullets().size() - 1 ; i >= 0 ; i--){
            game.getEnemyBullets().get(i).update(delta);
            if(game.getPlayer().getBounds().overlaps(game.getEnemyBullets().get(i).getBounds())){
                game.getPlayerDamaged().add(new Explosion(new Rectangle(game.getPlayer().getBounds())));
                game.getPlayer().setLastDamageTime(now);
                game.getPlayer().decreaseHealth(1);
                game.getEnemyBullets().remove(i);
                continue;
            }
            if(game.getEnemyBullets().get(i).isOffScreen(worldSize)){
                game.getEnemyBullets().remove(i);
            }
        }
    }

    public void render(float delta) {

        if(! game.getBossFights().isEmpty()){
            Main.getBatch().draw(GameAssetManager.getGameAssetManager().getRingTexture(),game.getRing().x,
                game.getRing().y,game.getRing().width,game.getRing().height);
        }

        for(Tree tree : game.getTrees()){
            tree.getSprite().draw(Main.getBatch());
        }

        for(TentacleMonster tentacle : game.getTentacleMonsters()){
            tentacle.getSprite().setPosition(tentacle.getBounds().x, tentacle.getBounds().y);
            tentacle.getSprite().draw(Main.getBatch());
        }

        for(EyeBat bat : game.getEyeBats()){
            bat.getSprite().setPosition(bat.getBounds().x, bat.getBounds().y);
            bat.getSprite().draw(Main.getBatch());
        }

        for(Bullet bullet : game.getEnemyBullets()){
            Main.getBatch().draw(GameAssetManager.getGameAssetManager().getEnemyBullet(), bullet.getBounds().x,
                bullet.getBounds().y,bullet.getBounds().width,bullet.getBounds().height);
        }

        for(BossFight bossFight : game.getBossFights()){
            bossFight.getSprite().setPosition(bossFight.getBounds().x, bossFight.getBounds().y);
            bossFight.getSprite().draw(Main.getBatch());
        }



        for (Explosion explosion : game.getEnemyDead()){
            explosion.getSprite().setPosition(explosion.getBounds().x,explosion.getBounds().y);
            explosion.getSprite().draw(Main.getBatch());
        }
    }

    private void animation(float delta) {
        for (Tree tree : game.getTrees()) {
            tree.getSprite().setRegion(GameAssetManager.getGameAssetManager().getTreeAnimation().
                getKeyFrame(tree.getAnimationTime()));
            if(! GameAssetManager.getGameAssetManager().getTreeAnimation().isAnimationFinished(tree.getAnimationTime())){
                tree.setAnimationTime(tree.getAnimationTime() + delta);
            }
            else{
                tree.setAnimationTime(0);
            }

            GameAssetManager.getGameAssetManager().getTreeAnimation().setPlayMode(Animation.PlayMode.LOOP);

        }

        for(TentacleMonster tentacle : App.getCurrentGame().getTentacleMonsters()){
            tentacle.getSprite().setRegion(GameAssetManager.getGameAssetManager().getTentacleMonsterAnimation().
                getKeyFrame(tentacle.getAnimationTime()));

            if(! GameAssetManager.getGameAssetManager().getTentacleMonsterAnimation().
                isAnimationFinished(tentacle.getAnimationTime())){
                tentacle.setAnimationTime(tentacle.getAnimationTime() + delta);
            }
            else{
                tentacle.setAnimationTime(0);
            }

            GameAssetManager.getGameAssetManager().getTentacleMonsterAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }

        for(EyeBat bat : game.getEyeBats()){
            bat.getSprite().setRegion(GameAssetManager.getGameAssetManager().getEyeBatAnimation().
                getKeyFrame(bat.getAnimationTime()));

            if(! GameAssetManager.getGameAssetManager().getEyeBatAnimation().
                isAnimationFinished(bat.getAnimationTime())){
                bat.setAnimationTime(bat.getAnimationTime() + delta);
            }
            else {
                bat.setAnimationTime(0);
            }

            GameAssetManager.getGameAssetManager().getEyeBatAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }

        for(BossFight bossFight : game.getBossFights()){
            bossFight.getSprite().setRegion(GameAssetManager.getGameAssetManager().getBossFightAnimation()
                .getKeyFrame(bossFight.getAnimationTime()));

            if(! GameAssetManager.getGameAssetManager().getBossFightAnimation().
                isAnimationFinished(bossFight.getAnimationTime())){
                bossFight.setAnimationTime(bossFight.getAnimationTime() + delta);
            }
            else{
                bossFight.setAnimationTime(0);
            }

            GameAssetManager.getGameAssetManager().getBossFightAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }

        enemyDieAnimation(delta);
        damageAnimation();

    }

    private void enemyDieAnimation(float delta){
        for(int i = game.getEnemyDead().size() - 1;i >=0;i--){
            Explosion explosion = game.getEnemyDead().get(i);
            explosion.getSprite().setRegion(GameAssetManager.getGameAssetManager().
                getEnemyDieAnimation().getKeyFrame(explosion.getAnimationTime()));

            if(! GameAssetManager.getGameAssetManager().getDamageAnimation().
                isAnimationFinished(explosion.getAnimationTime())){
                explosion.setAnimationTime(explosion.getAnimationTime() + delta);
            }
            else{
                game.getEnemyDead().remove(i);
            }


        }
    }

    public void damageAnimation(){
        for(int i = App.getCurrentGame().getPlayerDamaged().size() - 1; i >= 0; i--){
            Explosion explosion = App.getCurrentGame().getPlayerDamaged().get(i);
            explosion.getSprite().setRegion(GameAssetManager.getGameAssetManager().getDamageAnimation().
                getKeyFrame(explosion.getAnimationTime()));

            if(! GameAssetManager.getGameAssetManager().getDamageAnimation().
                isAnimationFinished(explosion.getAnimationTime())){
                explosion.setAnimationTime(explosion.getAnimationTime() + Gdx.graphics.getDeltaTime());
            }
            else{
                App.getCurrentGame().getPlayerDamaged().remove(explosion);
            }
        }
    }

}
