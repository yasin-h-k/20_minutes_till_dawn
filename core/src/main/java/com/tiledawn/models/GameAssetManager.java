package com.tiledawn.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GameAssetManager {

    private static GameAssetManager gameAssetManager;

    private GameAssetManager() {
    }

    public static GameAssetManager getGameAssetManager() {
        if(gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }




    private final Skin skin = new Skin(Gdx.files.internal("Skin3/pixthulhu-ui.json"));

    public Skin getSkin() {
        return skin;
    }

    private final ArrayList<String> avatarPaths = new ArrayList<String>(){{
        add("Avatars/T_Abby_Portrait.png");
        add("Avatars/T_Dasher_Portrait.png");
        add("Avatars/T_Diamond_Portrait.png");
        add("Avatars/T_Hastur_Portrait.png");
        add("Avatars/T_Hina_Portrait.png");
        add("Avatars/T_Lilith_Portrait.png");
        add("Avatars/T_Luna_Portrait.png");
        add("Avatars/T_Raven_Portrait.png");
        add("Avatars/T_Scarlett_Portrait.png");
        add("Avatars/T_Shana_Portrait.png");
        add("Avatars/T_Spark_Portrait.png");
        add("Avatars/T_Yuki_Portrait.png");
    }};

    public ArrayList<String> getAvatarPaths() {
        return avatarPaths;
    }


    private final Array<String> heroes = new Array<>();
    {
        for(Hero hero : Hero.values()) {
            heroes.add(hero.toString());
        }
    }

    public Array<String> getHeroes() {
        return heroes;
    }


    private final Array<String> weapons = new Array<>();
    {
        for(WeaponType weaponType : WeaponType.values()){
            weapons.add(weaponType.toString());
        }
    }

    public Array<String> getWeapons() {
        return weapons;
    }

    private final Array<Integer> gameTimes = new Array<>();
    {
        gameTimes.add(2);
        gameTimes.add(5);
        gameTimes.add(10);
        gameTimes.add(20);
    }

    public Array<Integer> getGameTimes() {
        return gameTimes;
    }

    private final Array<String> musics = new Array<>();
    {
        musics.add("Ready to Fight");
        musics.add("otagh kaboos");
        musics.add("02 Shadow Warriors");
    }

    public Array<String> getMusics() {
        return musics;
    }

    private final Sound buttonSFX;
    {
        buttonSFX = Gdx.audio.newSound(Gdx.files.internal("ButtonSFX/UI Click 36.wav"));
    }

    public Sound getButtonSFX() {
        return buttonSFX;
    }

    private final Texture gameFloor;
    {
        gameFloor = new Texture(Gdx.files.internal("floor.png"));
    }

    public Texture getGameFloor() {
        return gameFloor;
    }


    private final TextureRegion[] shanaFrames = new TextureRegion[6];

    {
        shanaFrames[0] = new TextureRegion(new Texture("HeroesAnimation/SHANA/Idle_0.png"));
        shanaFrames[1] = new TextureRegion(new Texture("HeroesAnimation/SHANA/Idle_1.png"));
        shanaFrames[2] = new TextureRegion(new Texture("HeroesAnimation/SHANA/Idle_2.png"));
        shanaFrames[3] = new TextureRegion(new Texture("HeroesAnimation/SHANA/Idle_3.png"));
        shanaFrames[4] = new TextureRegion(new Texture("HeroesAnimation/SHANA/Idle_4.png"));
        shanaFrames[5] = new TextureRegion(new Texture("HeroesAnimation/SHANA/Idle_5.png"));
        Hero.SHANA.setAnimation(new Animation<>(0.1f, shanaFrames));
    }

    public TextureRegion[] getShanaFrames() {
        return shanaFrames;
    }

    private final TextureRegion[] diamondFrames = new TextureRegion[6];
    {
        diamondFrames[0] = new TextureRegion(new Texture("HeroesAnimation/DIAMOND/Idle_0.png"));
        diamondFrames[1] = new TextureRegion(new Texture("HeroesAnimation/DIAMOND/Idle_1.png"));
        diamondFrames[2] = new TextureRegion(new Texture("HeroesAnimation/DIAMOND/Idle_2.png"));
        diamondFrames[3] = new TextureRegion(new Texture("HeroesAnimation/DIAMOND/Idle_3.png"));
        diamondFrames[4] = new TextureRegion(new Texture("HeroesAnimation/DIAMOND/Idle_4.png"));
        diamondFrames[5] = new TextureRegion(new Texture("HeroesAnimation/DIAMOND/Idle_5.png"));
        Hero.DIAMOND.setAnimation(new Animation<>(0.1f, diamondFrames));
    }

    public TextureRegion[] getDiamondFrames() {
        return diamondFrames;
    }

    private final TextureRegion[] scarletFrames = new TextureRegion[6];
    {
        scarletFrames[0] = new TextureRegion(new Texture("HeroesAnimation/SCARLET/Idle_0.png"));
        scarletFrames[1] = new TextureRegion(new Texture("HeroesAnimation/SCARLET/Idle_1.png"));
        scarletFrames[2] = new TextureRegion(new Texture("HeroesAnimation/SCARLET/Idle_2.png"));
        scarletFrames[3] = new TextureRegion(new Texture("HeroesAnimation/SCARLET/Idle_3.png"));
        scarletFrames[4] = new TextureRegion(new Texture("HeroesAnimation/SCARLET/Idle_4.png"));
        scarletFrames[5] = new TextureRegion(new Texture("HeroesAnimation/SCARLET/Idle_5.png"));
        Hero.SCARLET.setAnimation(new Animation<>(0.1f,scarletFrames));
    }

    public TextureRegion[] getScarletFrames() {
        return scarletFrames;
    }

    private final TextureRegion[] lilithFrames = new TextureRegion[6];
    {
        lilithFrames[0] = new TextureRegion(new Texture("HeroesAnimation/LILITH/Idle_0.png"));
        lilithFrames[1] = new TextureRegion(new Texture("HeroesAnimation/LILITH/Idle_1.png"));
        lilithFrames[2] = new TextureRegion(new Texture("HeroesAnimation/LILITH/Idle_2.png"));
        lilithFrames[3] = new TextureRegion(new Texture("HeroesAnimation/LILITH/Idle_3.png"));
        lilithFrames[4] = new TextureRegion(new Texture("HeroesAnimation/LILITH/Idle_4.png"));
        lilithFrames[5] = new TextureRegion(new Texture("HeroesAnimation/LILITH/Idle_5.png"));
        Hero.LILITH.setAnimation(new Animation<>(0.1f,lilithFrames));
    }

    public TextureRegion[] getLilithFrames() {
        return lilithFrames;
    }

    private final TextureRegion[] dasherFrames = new TextureRegion[6];
    {
        dasherFrames[0] = new TextureRegion(new Texture("HeroesAnimation/DASHER/Idle_0.png"));
        dasherFrames[1] = new TextureRegion(new Texture("HeroesAnimation/DASHER/Idle_1.png"));
        dasherFrames[2] = new TextureRegion(new Texture("HeroesAnimation/DASHER/Idle_2.png"));
        dasherFrames[3] = new TextureRegion(new Texture("HeroesAnimation/DASHER/Idle_3.png"));
        dasherFrames[4] = new TextureRegion(new Texture("HeroesAnimation/DASHER/Idle_4.png"));
        dasherFrames[5] = new TextureRegion(new Texture("HeroesAnimation/DASHER/Idle_5.png"));
        Hero.DASHER.setAnimation(new Animation<>(0.1f,dasherFrames));
    }

    public TextureRegion[] getDasherFrames() {
        return dasherFrames;
    }

    private final Texture bulletTexture = new Texture("Bullet/RevolverReload_0.png");
    public Texture getBulletTexture() {
        return bulletTexture;
    }

    private final Animation<TextureRegion> heartAnimation;
    {
        TextureRegion[] heartFrames = new TextureRegion[3];
        heartFrames[0] = new TextureRegion(new Texture("Heart/HeartAnimation_0.png"));
        heartFrames[1] = new TextureRegion(new Texture("Heart/HeartAnimation_1.png"));
        heartFrames[2] = new TextureRegion(new Texture("Heart/HeartAnimation_2.png"));
//        heartFrames[3] = new TextureRegion(new Texture("Heart/HeartAnimation_3.png"));
        heartAnimation = new Animation<>(0.2f,heartFrames);
    }

    public Animation<TextureRegion> getHeartAnimation() {
        return heartAnimation;
    }

    private final Texture deadHeart = new Texture("Heart/HeartAnimation_3.png");

    public Texture getDeadHeart() {
        return deadHeart;
    }

    private final Animation<TextureRegion> treeAnimation;
    {
        TextureRegion[] treeFrames = new TextureRegion[4];
        treeFrames[0] = new TextureRegion(new Texture("Tree/T_TreeMonster_0.png"));
        treeFrames[1] = new TextureRegion(new Texture("Tree/T_TreeMonster_1.png"));
        treeFrames[2] = new TextureRegion(new Texture("Tree/T_TreeMonster_2.png"));
        treeFrames[3] = new TextureRegion(new Texture("Tree/T_TreeMonster_3.png"));
        treeAnimation = new Animation<>(1f,treeFrames);
    }

    public Animation<TextureRegion> getTreeAnimation() {
        return treeAnimation;
    }

    private final Animation<TextureRegion> tentacleMonsterAnimation;
    {
        TextureRegion[] tentacleFrames = new TextureRegion[4];
        tentacleFrames[0] = new TextureRegion(new Texture("TentacleMonster/T_TentacleEnemy_0.png"));
        tentacleFrames[1] = new TextureRegion(new Texture("TentacleMonster/T_TentacleEnemy_1.png"));
        tentacleFrames[2] = new TextureRegion(new Texture("TentacleMonster/T_TentacleEnemy_2.png"));
        tentacleFrames[3] = new TextureRegion(new Texture("TentacleMonster/T_TentacleEnemy_3.png"));
        tentacleMonsterAnimation = new Animation<>(1f,tentacleFrames);
    }

    public Animation<TextureRegion> getTentacleMonsterAnimation() {
        return tentacleMonsterAnimation;
    }

    private final Animation<TextureRegion> eyeBatAnimation;
    {
        TextureRegion[] eyeBatFrames = new TextureRegion[4];
        eyeBatFrames[0] = new TextureRegion(new Texture("Eyebat/T_EyeBat_0.png"));
        eyeBatFrames[1] = new TextureRegion(new Texture("Eyebat/T_EyeBat_1.png"));
        eyeBatFrames[2] = new TextureRegion(new Texture("Eyebat/T_EyeBat_2.png"));
        eyeBatFrames[3] = new TextureRegion(new Texture("Eyebat/T_EyeBat_3.png"));
        eyeBatAnimation = new Animation<>(0.2f,eyeBatFrames);
    }

    public Animation<TextureRegion> getEyeBatAnimation() {
        return eyeBatAnimation;
    }

    private final Animation<TextureRegion> bossFightAnimation;
    {
        TextureRegion[] bossFightFrames = new TextureRegion[5];
        bossFightFrames[0] = new TextureRegion(new Texture("BossFight/WingedMonster_0.png"));
        bossFightFrames[1] = new TextureRegion(new Texture("BossFight/WingedMonster_1.png"));
        bossFightFrames[2] = new TextureRegion(new Texture("BossFight/WingedMonster_2.png"));
        bossFightFrames[3] = new TextureRegion(new Texture("BossFight/WingedMonster_3.png"));
        bossFightFrames[4] = new TextureRegion(new Texture("BossFight/WingedMonster_4.png"));
        bossFightAnimation = new Animation<>(0.25f,bossFightFrames);
    }

    public Animation<TextureRegion> getBossFightAnimation() {
        return bossFightAnimation;
    }

    private final Texture pointTexture = new Texture("Point/HitImpactFX_0.png");

    public Texture getPointTexture() {
        return pointTexture;
    }

    private final Texture enemyBullet = new Texture("EyeMonsterProjecitle.png");

    public Texture getEnemyBullet() {
        return enemyBullet;
    }

    private final Sound shotSfx = Gdx.audio.newSound(Gdx.files.internal("ButtonSFX/single_shot.wav"));

    public Sound getShotSfx() {
        return shotSfx;
    }

    private final Sound coinSfx = Gdx.audio.newSound(Gdx.files.internal("ButtonSFX/Coins (10).wav"));

    public Sound getCoinSfx() {
        return coinSfx;
    }

    private final Sound reloadSfx = Gdx.audio.newSound(Gdx.files.internal("ButtonSFX/Weapon_Shotgun_Reload.wav"));

    public Sound getReloadSfx() {
        return reloadSfx;
    }

    private final Animation<TextureRegion> damageAnimation;
    {
        TextureRegion[] damageFrames = new TextureRegion[6];
        damageFrames[0] = new TextureRegion(new Texture("DamageAnimation/FireballExplosion_0.png"));
        damageFrames[1] = new TextureRegion(new Texture("DamageAnimation/FireballExplosion_1.png"));
        damageFrames[2] = new TextureRegion(new Texture("DamageAnimation/FireballExplosion_2.png"));
        damageFrames[3] = new TextureRegion(new Texture("DamageAnimation/FireballExplosion_3.png"));
        damageFrames[4] = new TextureRegion(new Texture("DamageAnimation/FireballExplosion_4.png"));
        damageFrames[5] = new TextureRegion(new Texture("DamageAnimation/FireballExplosion_5.png"));
        damageAnimation = new Animation<>(0.5f,damageFrames);
    }

    public Animation<TextureRegion> getDamageAnimation() {
        return damageAnimation;
    }

    private final Animation<TextureRegion> enemyDieAnimation;
    {
        TextureRegion[] enemyDieFrames = new TextureRegion[6];
        enemyDieFrames[0] = new TextureRegion(new Texture("DeadAnimation/ExplosionFX_0.png"));
        enemyDieFrames[1] = new TextureRegion(new Texture("DeadAnimation/ExplosionFX_1.png"));
        enemyDieFrames[2] = new TextureRegion(new Texture("DeadAnimation/ExplosionFX_2.png"));
        enemyDieFrames[3] = new TextureRegion(new Texture("DeadAnimation/ExplosionFX_3.png"));
        enemyDieFrames[4] = new TextureRegion(new Texture("DeadAnimation/ExplosionFX_4.png"));
        enemyDieFrames[5] = new TextureRegion(new Texture("DeadAnimation/ExplosionFX_5.png"));
        enemyDieAnimation = new Animation<>(0.5f,enemyDieFrames);
    }

    public Animation<TextureRegion> getEnemyDieAnimation() {
        return enemyDieAnimation;
    }

    private final ShaderProgram shader;
    {
        shader = new ShaderProgram(Gdx.files.internal("Shader/grayscale.vert"),
            Gdx.files.internal("Shader/grayscale.frag"));
        if (! shader.isCompiled()) {
            Gdx.app.error("Shader",shader.getLog());
        }
    }

    public ShaderProgram getShader() {
        return shader;
    }

    private final Texture FovTexture = new Texture("5897982505892564149.jpg");

    public Texture getFovTexture() {
        return FovTexture;
    }

    private final Texture ringTexture = new Texture("T_TentacleAttackIndicator_6.png");

    public Texture getRingTexture() {
        return ringTexture;
    }
}
