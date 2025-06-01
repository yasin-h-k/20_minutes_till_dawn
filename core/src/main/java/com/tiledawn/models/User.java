package com.tiledawn.models;

import com.badlogic.gdx.graphics.Texture;

public class User {


    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private String avatarAddress;
    private long score;
    private long maxSurvivalTime;
    private int numberOfKills;
    private boolean sfx = true;
    private boolean blackWhite = false;
    private boolean autoReload = false;
    private String playingMusic;
    boolean french = false;
    boolean walkWithWSAD = true;
    boolean autoAim = true;


    public User(String username, String password, String securityQuestion, String securityAnswer, String avatar) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatarAddress = avatar;
        this.score = 0;
        this.playingMusic = App.getPlayingMusic();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public String getAvatarAddress() {
        return avatarAddress;
    }

    public long getScore() {
        return score;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public boolean isSfx() {
        return sfx;
    }

    public boolean isBlackWhite() {
        return blackWhite;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public String getPlayingMusic() {
        return playingMusic;
    }

    public long getMaxSurvivalTime() {
        return maxSurvivalTime;
    }

    public boolean isFrench() {
        return french;
    }

    public boolean isWalkWithWSAD() {
        return walkWithWSAD;
    }

    public boolean isAutoAim() {
        return autoAim;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setAvatarAddress(String avatarAddress) {
        this.avatarAddress = avatarAddress;
    }

    public void increaseScore(long score) {
        this.score += score;
    }

    public void increaseNumberOfKills(int numberOfKills) {
        this.numberOfKills += numberOfKills;
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public void setPlayingMusic(String playingMusic) {
        this.playingMusic = playingMusic;
    }

    public void setMaxSurvivalTime(long maxSurvivalTime) {
        if(maxSurvivalTime > this.maxSurvivalTime){
            this.maxSurvivalTime = maxSurvivalTime;
        }
    }

    public void setFrench(boolean french) {
        this.french = french;
    }

    public void setWalkWithWSAD(boolean walkWithWSAD) {
        this.walkWithWSAD = walkWithWSAD;
    }

    public void setBlackWhite(boolean blackWhite) {
        this.blackWhite = blackWhite;
    }

    public void setAutoAim(boolean autoAim) {
        this.autoAim = autoAim;
    }
}
