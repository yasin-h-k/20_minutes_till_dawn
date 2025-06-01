package com.tiledawn.models;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Player {

    private Weapon weapon;
    private final Hero hero;
    private int health;
    private int maxHealth;
    private int xp;
    private int level = 1;
    private int kills;

    private long speedAbilityTime;
    private float speed;

    private Rectangle bounds;
    private Sprite sprite;
    private float time = 0f;
    private float heartTime = 0f;
    private Sprite healthSprite;
    private long lastDamageTime;


    public Player(Hero hero, WeaponType weaponType) {
        this.hero = hero;
        this.health = hero.getHealth();
        this.maxHealth = hero.getHealth();
        this.speed = hero.getSpeed() * Gdx.graphics.getWidth() * 0.25f;
        this.weapon = new Weapon(weaponType);
        this.sprite = new Sprite();
        this.sprite.setRegion(GameAssetManager.getGameAssetManager().getDiamondFrames()[0]);
        this.healthSprite = new Sprite(GameAssetManager.getGameAssetManager().getHeartAnimation().getKeyFrame(heartTime));

        float width = Gdx.graphics.getWidth() * 0.02f;
        float height = Gdx.graphics.getWidth() * 0.02f * 3;
        float x = Gdx.graphics.getWidth() - width / 2f;
        float y = Gdx.graphics.getWidth() - height / 2f;

        this.bounds = new Rectangle(x, y, width, height);

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public float getSpeed() {
        if(TimeUtils.millis() - speedAbilityTime < 10_000) {
            return speed * 2;
        }
        return speed;
    }

    public Hero getHero() {
        return hero;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getTime() {
        return time;
    }

    public float getHeartTime() {
        return heartTime;
    }

    public Sprite getHealthSprite() {
        return healthSprite;
    }

    public int getKills() {
        return kills;
    }

    public long getLastDamageTime() {
        return lastDamageTime;
    }



    public void increaseHealth(int health) {
        this.health += health;
        this.maxHealth += health;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseX(int x) {
        bounds.x += x;
    }

    public void increaseY(int y) {
        bounds.y += y;
    }

    public void setX(float x){
        bounds.x = x;
    }

    public void setY(float y){
        bounds.y = y;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setHeartTime(float heartTime) {
        this.heartTime = heartTime;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void increaseKills(int kills) {
        this.kills += kills;
    }

    public void setLastDamageTime(long lastDamageTime) {
        this.lastDamageTime = lastDamageTime;
    }

    public void increaseXp(int xp) {
        this.xp += xp;
    }

    public void decreaseXp(int xp) {
        this.xp -= xp;
    }

    public void increaseLevel() {
        this.level++;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setSpeedAbilityTime() {
        this.speedAbilityTime = TimeUtils.millis();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setHealthSprite(Sprite healthSprite) {
        this.healthSprite = healthSprite;
    }

}
