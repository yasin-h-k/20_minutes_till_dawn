package com.tiledawn.models;

public enum Messages {
    MainMenu("Main Menu",""),
    PauseMenu("Pause Menu",""),
    PreGAmeMenu("Pre Game Menu",""),
    ProfileMenu("Profile Menu",""),
    ScoreBoardMenu("Score Board Menu",""),
    SettingMenu("Setting Menu",""),
    TalentMenu("Talent Menu",""),
    Signup("Signup",""),
    Login("Login",""),
    Username("Username",""),
    Password("Password",""),
    SecurityQuestion("Security Question",""),
    SecurityAnswer("Security Answer",""),
    Score("Score",""),
    Guest("Guest",""),
    Time("Time",""),
    Kills("Kills",""),
    Level("Level",""),
    ResumeGame("Resume Game",""),
    Exit("Exit",""),
    Win("Win",""),
    Dead("Dead",""),
    Continue("Continue",""),
    Save("Save",""),
    BlackWhite("Black White",""),
    AddHP("Add HP",""),
    LevelUp("Level Up",""),
    DecreaseTime("Decrease Time",""),
    GoToBossFight("Go to Boss Fight",""),
    SpeedUp("Speed Up",""),
    Hero("Hero",""),
    Weapon("Weapon",""),
    StartGame("Start Game",""),
    Change("Change",""),
    Avatar("Avatar",""),
    DeleteAccount("Delete Account",""),
    Menu("Menu",""),
    New("New",""),
    Rank("Rank",""),
    Survival("Survival",""),
    Back("Back",""),
    SelectAbility("SelectAbility",""),
    Music("Music",""),
    Volume("Volume",""),
    Sound("Sound",""),
    Reload("Reload",""),
    Auto("Auto",""),
    Language("French","French"),
    BulletsDamageMoreFor("Bullets damage 25% more for 20s",""),
    MakeWeaponProjectile("Make weapon projectile 1 more",""),
    AddToAmmo("Add 5 Bullet to Ammo",""),
    MakeSpeed("Make speed 2x",""),

//    PlaySaved("Play Saved",""),

    ;

    private final String English;
    private final String French;

    Messages(String english, String french) {
        English = english;
        French = french;
    }

    public String getMessage() {
        if(App.getCurrentUser().isFrench()){
            return French;
        }
        return English;
    }
}
