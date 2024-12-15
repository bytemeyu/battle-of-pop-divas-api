package com.bytemeyu.battleofpopdivas.battle;

import com.bytemeyu.battleofpopdivas.popdiva.PopDiva;

import java.util.Random;

public class Battle implements BattleInterface{

    private PopDiva challenger;
    private PopDiva challenged;
    private boolean approved;

    public Battle(PopDiva challenger, PopDiva challenged) {
        this.setChallenger(challenger);
        this.setChallenged(challenged);
    }

    public PopDiva getChallenger() {
        return challenger;
    }

    public void setChallenger(PopDiva challenger) {
        this.challenger = challenger;
    }

    public PopDiva getChallenged() {
        return challenged;
    }

    public void setChallenged(PopDiva challenged) {
        this.challenged = challenged;

        if(this.getChallenger() != this.getChallenged()){
            this.approved = true;
        } else {
            this.approved = false;
        }
    }

    public boolean isApproved() {
        return this.approved;
    }

//    public void setApproved() {
//        this.approved = this.isApproved();
//    }

    public void currentStatus() {
        System.out.println("_________");
        System.out.println("Challenger: " + this.getChallenger().getName());
        System.out.println("Challenged: " + this.getChallenged().getName());
        System.out.println("Approved? " + this.isApproved());
    }



    private PopDiva randomMethod(String showdownType) {
        Random random = new Random();

        int challengerScore = random.nextInt(11);
        int challengedScore = random.nextInt(11);

        if(challengerScore > challengedScore) {
            System.out.println(showdownType + " winner is " + this.getChallenger().getName() + "!");
            return this.getChallenger();
        } else {
            System.out.println(showdownType + " winner is " + this.getChallenged().getName() + "!");
            return this.getChallenged();
        }
    }

    @Override
    public PopDiva vocalShowdown() {
        return randomMethod("VocaShowdown");
    }

    @Override
    public PopDiva danceOff() {
        return randomMethod("Dance off");
    }

    @Override
    public PopDiva grammyRelevance() {
        int challengerNomination = this.getChallenger().getGrammyNominations();
        int challengerWins = this.getChallenger().getWins();
        int challengerGrammyRelevance = (challengerNomination/2) + challengerWins;

        int challengedNomination = this.getChallenged().getGrammyNominations();
        int challengedWins = this.getChallenged().getWins();
        int challengedGrammyRelevance = (challengedNomination/2) + challengedWins;

        if(challengerGrammyRelevance > challengedGrammyRelevance) {
            System.out.println("The singer who has the most relevance at the Grammys is " + this.getChallenger().getName() + "!");
            return this.getChallenger();
        } else {
            System.out.println("The singer who has the most relevance at the Grammy is " + this.getChallenged().getName() + "!");
            return this.getChallenged();
        }
    }

    @Override
    public PopDiva lossDueToScandals() {
        int challengerScandalsScore = this.getChallenger().getScandalsScore();
        int challengedScandalsScore = this.getChallenged().getScandalsScore();

        if(challengerScandalsScore < challengedScandalsScore) {
            System.out.println("The singer who was least involved in scandals was " + this.getChallenger().getName() + "!");
            return this.getChallenger();
        } else {
            System.out.println("The singer who was least involved in scandals was " + this.getChallenged().getName() + "!");
            return this.getChallenged();
        }
    }

    @Override
    public void battleAndDetermineWinner() {
        if(this.isApproved()) {
            int challengerOverallScore = 0;
            int challengedOverallScore = 0;

            if(vocalShowdown() == this.getChallenger()) {
                challengerOverallScore++;
            } else {
                challengedOverallScore++;
            }

            if(danceOff() == this.getChallenger()) {
                challengerOverallScore++;
            } else {
                challengedOverallScore++;
            }

            if(grammyRelevance() == this.getChallenger()) {
                challengerOverallScore++;
            } else {
                challengedOverallScore++;
            }

            if(lossDueToScandals() == this.getChallenger()) {
                challengerOverallScore++;
            } else {
                challengedOverallScore++;
            }

            if(challengerOverallScore > challengedOverallScore) {
                System.out.println("The biggest, wonderful, big winner is " + this.getChallenger().getName() + "!!!");
                recordBattleResult(this.getChallenger());
            } else if(challengedOverallScore > challengerOverallScore) {
                System.out.println("The biggest, wonderful, big winner is " + this.getChallenged().getName() + "!!!");
                recordBattleResult(this.getChallenged());
            } else {
                System.out.println("It's a draw!");
                PopDiva tiebreakerWinner = resolveDraw();
                System.out.println("The biggest, wonderful, big winner is " + tiebreakerWinner.getName() + "!!!");
                recordBattleResult(tiebreakerWinner);
            }
        } else {
            System.out.println("This battle was not approved. Try another one.");
        }
    }

    @Override
    public void recordBattleResult(PopDiva winner) {

        if(winner != null) {
            if(winner == this.getChallenger()) {
                this.getChallenger().winBattle();
                this.getChallenged().loseBattle();
            } else {
                this.getChallenged().winBattle();
                this.getChallenger().loseBattle();
            }
        }
    }

    @Override
    public PopDiva resolveDraw() {
        PopDiva tiebreaker = this.grammyRelevance();

        if(tiebreaker == this.getChallenger()) {
            return this.getChallenger();
        } else {
            return this.getChallenged();
        }
    }
}
