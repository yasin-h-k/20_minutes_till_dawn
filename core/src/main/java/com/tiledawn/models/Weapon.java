package com.tiledawn.models;

import com.badlogic.gdx.utils.TimeUtils;

public class Weapon {
    private WeaponType type;
    private int maxAmmo;
    private int Ammo ;
    private int projectile;
    private boolean isReloading;
    private float reloadTimer;
    private long damagerTime;


    public Weapon(WeaponType type) {
        this.type = type;
        Ammo = type.getMaxAmmo();
        maxAmmo = type.getMaxAmmo();
        projectile = type.getProjectile();
    }

    public WeaponType getType() {
        return type;
    }

    public int getAmmo() {
        return Ammo;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public float getReloadTimer() {
        return reloadTimer;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getProjectile() {
        return projectile;
    }

    public long getDamagerTime() {
        return damagerTime;
    }



    public void decreaseAmmo(int ammo) {
        Ammo -= ammo;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }

    public void increaseReloadTimer(float reloadTimer) {
        this.reloadTimer += reloadTimer;
    }

    public void setReloadTimer(float reloadTimer) {
        this.reloadTimer = reloadTimer;
    }

    public void reloadAmmo() {
        Ammo = this.maxAmmo;
    }

    public void increaseMaxAmmo(int maxAmmo) {
        this.maxAmmo += maxAmmo;
    }

    public void increaseProjectile(int projectile) {
        this.projectile += projectile;
    }

    public void setDamagerTime() {
        this.damagerTime = TimeUtils.millis();
    }

    public int getDamage(){
        if(TimeUtils.millis() - damagerTime <  10_000){
            return type.getDamage() * 5/4;
        }
        return type.getDamage();
    }
}
