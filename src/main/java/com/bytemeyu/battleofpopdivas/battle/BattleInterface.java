package com.bytemeyu.battleofpopdivas.battle;

import com.bytemeyu.battleofpopdivas.popdiva.PopDiva;

public interface BattleInterface {
    public abstract void currentStatus();
    public abstract PopDiva vocalShowdown();
    public abstract PopDiva danceOff();
    public abstract PopDiva grammyRelevance();
    public abstract PopDiva lossDueToScandals();
    public abstract void battleAndDetermineWinner();
    public abstract void recordBattleResult(PopDiva winner);
    public abstract PopDiva resolveDraw();
}