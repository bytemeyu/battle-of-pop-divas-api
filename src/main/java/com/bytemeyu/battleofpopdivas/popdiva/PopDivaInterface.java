package com.bytemeyu.battleofpopdivas.popdiva;

public interface PopDivaInterface {
    public abstract String currentStatus();
    public abstract String presentation();
    public abstract void addGrammyNominations(int grammyNomination);
    public abstract void addGrammyWins(int grammyWin);
    public abstract void addScandalsScore(String severityOfTheScandal);
    public abstract void winBattle ();
    public abstract void loseBattle();
}
