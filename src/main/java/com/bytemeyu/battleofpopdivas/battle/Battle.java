package com.bytemeyu.battleofpopdivas.battle;

import com.bytemeyu.battleofpopdivas.popdiva.PopDiva;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Battle implements BattleInterface{

    private PopDiva challenger;
    private PopDiva challenged;
    private boolean approved;
    private String battleCode;

    private static final Set<String> generatedCodes = new HashSet<>();
    // Armazena os códigos já gerados para garantir unicidade. Um Set não permite elementos duplicados. Se você tentar adicionar um elemento que já existe, ele não será incluído.

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
            this.battleCode = generateBattleCode();
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

    public String generateBattleCode() {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String code;

        do {
            StringBuilder codeBuilder = new StringBuilder();
            //Diferentemente da classe String, que é imutável (não pode ser alterada após sua criação), o StringBuilder permite modificar seu conteúdo diretamente, economizando recursos computacionais ao evitar a criação de novos objetos. A classe StringBuilder está no pacote padrão java.lang. Isso significa que ela é automaticamente importada para qualquer programa Java, sem necessidade de usar import.

            // Gerar código de tamanho fixo (por exemplo, 8 caracteres):
            for (int i = 0; i < 8; i++) {
                int index = random.nextInt(characters.length());
                //random define um número aleatório entre 0 e 35 (que é o comprimento da string characters) e armazena na variável index
                codeBuilder.append(characters.charAt(index));
                //characters.charAt(index) retorna o caractere na posição index da string characters e o adiciona a StringBuilder codeBuilder com .append (isso só é possível pois trata-se de uma StringBuilder e não de uma String
                //isso é repetido 8 vezes, até criar a String code. lembrando que o incremento do i é gerenciado automaticamente pelo for e ocorre no final de cada ciclo (não é preciso fazer isso manualmente)
            }

            code = codeBuilder.toString() + "-" + challenger.getName().charAt(0) + challenged.getName().charAt(0);
            //aqui transformamos codeBuilder em String e concatenamos o seu resultado com a primeira letra do nome do challenger e a primeira letra do nome do challenged. seu resultado foi armazenado na String, já inicializada, code
        } while (generatedCodes.contains(code));
        //generatedCodes.contains(code) verifica se o conjunto já contém o código gerado. Enquanto essa condição for verdadeira (o código existe), o loop continuará. Quando o código gerado não estiver no conjunto (contains retornar false), o do-while será encerrado.

        System.out.println(code);
        generatedCodes.add(code);
        return code;
    }

    public String getBattleCode() {
        return battleCode;
    }

    public void setBattleCode(String battleCode) {
        this.battleCode = battleCode;
    }



    public String currentStatus() {
        String status = "_________\n" +
                this.getChallenger().getName() + "\n" +
                this.getChallenged().getName() + "\n" +
                this.isApproved() + "\n" +
                "Battle Code: " + (this.battleCode != null ? this.battleCode : "N/A") + "\n" +
                "_________\n";

        return status;
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
        return randomMethod("Vocal Showdown");
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
