package com.bytemeyu.battleofpopdivas.popdiva;

public interface PopDivaInterface {
    public abstract String currentStatus();
    public abstract String presentation();
    public abstract boolean addGrammyNominations(int grammyNomination);
    public abstract boolean addGrammyWins(int grammyWin);
    public abstract boolean addScandalsScore(String severityOfTheScandal);
    public abstract void winBattle ();
    public abstract void loseBattle();
}
