package com.tiledawn.models;

public enum WeaponType {
    Revolver(20,1,1,6),
    Shotgun(10,4,1,2),
    SMGsDual(8,1,2,24);

    private final int damage;
    private final int projectile;
    private final long reloadTime;
    private final int maxAmmo;

    WeaponType(int damage, int projectile, long reloadTime, int maxAmmo) {
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.maxAmmo = maxAmmo;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public long getReloadTime() {
        return reloadTime;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }
}
