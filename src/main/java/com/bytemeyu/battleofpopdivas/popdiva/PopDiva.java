package com.bytemeyu.battleofpopdivas.popdiva;

public class PopDiva implements PopDivaInterface{
    private String name;
    private String musicalGenre;
    private String nationality;
    private int grammyNominations;
    private int grammyWins;
    private int scandalsScore;
    private int wins;
    private int losses;

    public PopDiva(String name, String musicalGenre, String nationality) {
        this.setName(name);
        this.setMusicalGenre(musicalGenre);
        this.setNationality(nationality);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicalGenre() {
        return musicalGenre;
    }

    public void setMusicalGenre(String musicalGenre) {
        this.musicalGenre = musicalGenre;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGrammyNominations() {
        return grammyNominations;
    }

    public void setGrammyNominations(int grammyNominations) {
        if(grammyNominations > 0) {
            this.grammyNominations = grammyNominations;
        } else {
            System.out.println("Grammy nominations cannot be negative.");
        }
    }

    public int getGrammyWins() {
        return grammyWins;
    }

    public void setGrammyWins(int grammyWins) {
        if(grammyWins > 0) {
            this.grammyWins = grammyWins;
        } else {
            System.out.println("Grammy wins cannot be negative.");
        }

    }

    public int getScandalsScore() {
        return scandalsScore;
    }

    public void setScandalsScore(int scandalsScore) {
        this.scandalsScore = scandalsScore;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        if(wins > 0) {
            this.wins = wins;
        } else {
            System.out.println("Wins cannot be negative.");
        }
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        if(losses > 0) {
            this.losses = losses;
        } else {
            System.out.println("Losses cannot be negative.");
        }
    }

    public void currentStatus() {
        System.out.println("_________");
        System.out.println(this.getName());
        System.out.println(this.getMusicalGenre());
        System.out.println(this.getNationality());
        System.out.println("Grammy Nominations: " + this.getGrammyNominations());
        System.out.println("Grammy Wins: " + this.getGrammyWins());
        System.out.println("Scandals Score: " + this.getScandalsScore());
        System.out.println("Wins: " + this.getWins());
        System.out.println("Losses: " + this.getLosses());
        System.out.println("_________");
    }



    @Override
    public void presentation() {
        System.out.println("We present " + this.getName() + ", " + this.getNationality() + " " + this.getMusicalGenre() + " singer!");
    }

    @Override
    public void addGrammyNominations(int grammyNomination) {
        int totalGrammyNominations = this.getGrammyNominations() + grammyNomination;
        this.setGrammyNominations(totalGrammyNominations);
    }

    @Override
    public void addGrammyWins(int grammyWin) {
        int totalGrammyWins = this.getGrammyWins() + grammyWin;
        this.setGrammyWins(totalGrammyWins);
    }

    @Override
    public void addScandalsScore(String severityOfTheScandal) {
        int scoreIncrement = 0;

        switch (severityOfTheScandal) {
            case "minor":
                scoreIncrement = 1;
                break;
            case "noteworthy":
                scoreIncrement = 2;
                break;
            case "significant":
                scoreIncrement = 3;
                break;
            case "public outrage":
                scoreIncrement = 6;
                break;
            default:
                System.out.println("Invalid scandal severity: " + severityOfTheScandal);
        }

        if(scoreIncrement > 0){
            int totalScandalsScore = this.getScandalsScore() + scoreIncrement;
            this.setScandalsScore(totalScandalsScore);
        }
    }

    @Override
    public void winBattle() {
        this.setWins(this.getWins() + 1);
    }

    @Override
    public void loseBattle() {
        this.setLosses(this.getLosses() + 1);
    }
}
