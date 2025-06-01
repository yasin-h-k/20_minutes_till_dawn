package com.tiledawn.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class App {

    private final static HashMap<String,User> users;

    static{
        Gson gson = new Gson();
        FileReader file = null;
        try {
            file = new FileReader("users.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Type type = new TypeToken<HashMap<String, User>>() {
        }.getType();
        users = gson.fromJson(file, type);
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    private static User currentUser;

    static {
        currentUser = new User("yasin","123456","111","111",
            GameAssetManager.getGameAssetManager().getAvatarPaths().get(2));
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    private static Music music;
    static {
        setMusic(Gdx.audio.newMusic(Gdx.files.internal("Musics/Ready to Fight.mp3")));
    }
    private static String playingMusic = "Ready to Fight";

    public static Music getMusic() {
        return music;
    }

    public static void setMusic(Music music) {
        App.music = music;
        App.music.setLooping(true);
    }

    public static String getPlayingMusic() {
        return playingMusic;
    }

    public static void setPlayingMusic(String playingMusic) {
        App.playingMusic = playingMusic;
    }

    private static AppGame currentGame = new AppGame(new Player(Hero.DIAMOND,WeaponType.Revolver),2);

    public static AppGame getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(AppGame currentGame) {
        App.currentGame = currentGame;
    }


}
