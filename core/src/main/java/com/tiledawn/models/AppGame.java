package com.tiledawn.models;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;


import java.util.ArrayList;


public class AppGame {

    public static float worldSize = Gdx.graphics.getWidth() * 2;

    private final  ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Bullet> enemyBullets = new ArrayList<>();
    private final ArrayList<Tree> trees = new ArrayList<>();
    private final ArrayList<TentacleMonster> tentacleMonsters = new ArrayList<>();
    private final ArrayList<EyeBat> eyeBats = new ArrayList<>();
    private final ArrayList<BossFight> bossFights = new ArrayList<>();
    private final ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Explosion> playerDamaged = new ArrayList<>();
    private ArrayList<Explosion> enemyDead = new ArrayList<>();
    private long lastTentacleSpawnTime;
    private long lastEyeBatSpawnTime;

    private final Player player;
    private long endTime;
    private long startTime;
    private long pauseTime;

    private int vitality = 0;
    private int damager = 0;
    private int procrease = 0;
    private int amocrease = 0;
    private int speedy = 0;
    private boolean bossFightReleased = false;
    private Rectangle ring;


    public AppGame(Player player, long endTime) {
        this.player = player;
        this.endTime = endTime * 60 * 1000 + TimeUtils.millis();
        this.startTime = TimeUtils.millis();
        this.pauseTime = TimeUtils.millis();
        this.ring = new Rectangle(0, 0, worldSize, worldSize);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public Player getPlayer() {
        return player;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getTimeString() {
        return "Time : " + ((endTime - TimeUtils.millis())/1000);
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<TentacleMonster> getTentacleMonsters() {
        return tentacleMonsters;
    }

    public ArrayList<EyeBat> getEyeBats() {
        return eyeBats;
    }

    public ArrayList<BossFight> getBossFights() {
        return bossFights;
    }

    public long getLastTentacleSpawnTime() {
        return lastTentacleSpawnTime;
    }

    public long getLastEyeBatSpawnTime() {
        return lastEyeBatSpawnTime;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }


    public void setLastTentacleSpawnTime(long lastTentacleSpawnTime) {
        this.lastTentacleSpawnTime = lastTentacleSpawnTime;
    }

    public void setLastEyeBatSpawnTime(long lastEyeBatSpawnTime) {
        this.lastEyeBatSpawnTime = lastEyeBatSpawnTime;
    }

    public long getGameTime() {
        return endTime - startTime;
    }

    public void pause(){
        this.pauseTime = TimeUtils.millis();
    }

    public void resume(){
        this.endTime = TimeUtils.millis() + (endTime - pauseTime);
        this.startTime = TimeUtils.millis() - (pauseTime - startTime);
        this.lastTentacleSpawnTime = TimeUtils.millis() - (pauseTime - lastTentacleSpawnTime);
        this.lastEyeBatSpawnTime = TimeUtils.millis() - (pauseTime - lastEyeBatSpawnTime);
    }

    public long getElapsedTime(){
        return TimeUtils.millis() - startTime;
    }

    public int getVitality() {
        return vitality;
    }

    public int getDamager() {
        return damager;
    }

    public int getProcrease() {
        return procrease;
    }

    public int getAmocrease() {
        return amocrease;
    }

    public int getSpeedy() {
        return speedy;
    }

    public ArrayList<Explosion> getPlayerDamaged() {
        return playerDamaged;
    }

    public ArrayList<Explosion> getEnemyDead() {
        return enemyDead;
    }

    public boolean isBossFightReleased() {
        return bossFightReleased;
    }

    public Rectangle getRing() {
        return ring;
    }

    public void increaseVitality() {
        this.vitality++;
    }

    public void increaseDamager() {
        this.damager++;
    }

    public void increaseAmocrease() {
        this.amocrease++;
    }

    public void increaseProcrease() {
        this.procrease++;
    }

    public void increaseSpeedy() {
        this.speedy++;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setPlayerDamaged(ArrayList<Explosion> playerDamaged) {
        this.playerDamaged = playerDamaged;
    }

    public void setEnemyDead(ArrayList<Explosion> enemyDead) {
        this.enemyDead = enemyDead;
    }

    public void setBossFightReleased(boolean bossFightReleased) {
        this.bossFightReleased = bossFightReleased;
    }
}
