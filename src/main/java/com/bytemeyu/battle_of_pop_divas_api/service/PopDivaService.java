package com.bytemeyu.battle_of_pop_divas_api.service;

import com.bytemeyu.battleofpopdivas.popdiva.PopDiva;
//importando a classe PopDiva, que será usada para criar objetos que representam cantoras pop
import org.springframework.stereotype.Service;
//importando a anotação Service do Spring Boot

import java.util.ArrayList;

@Service
public class PopDivaService {
//criada a classe PopDivaService (com a anotação indicando que faz parte da camada Service e deve ser gerenciada como um componente do Spring)

    private final ArrayList<PopDiva> popDivas = new ArrayList<>();
    //criando uma lista array para armazenar objetos do tipo PopDiva
    //A lista é declarada como "final" para garantir que sua referência não será alterada, embora os dados dentro dela possam ser modificados.

    public PopDiva findPopDiva(String name) {
        for (PopDiva diva : popDivas) {
            //esse for percorre o array popDivas e nomeia cada elemento do array, em seu momento, como 'diva'
            if(diva.getName().equalsIgnoreCase(name)) {
                //diva.getName() retorna o nome de cada diva. se ele for igual (sem considerar a diferenciação entre maiúsculas e minúsculas) ao nome passado como parâmetro, entra nessa condição e...
                System.out.println("Pop diva with name " + name + " exists.");
                //imprime a frase acima na tela e...
                return diva;
                //retorna a PopDiva
            }
        }
        return null;
        //se a PopDiva não existir, retorna null
    }

    public PopDiva postPopDiva(String name, String musicalGenre, String nationality) {
        //o metodo postPopDiva que cria e adiciona uma PopDiva ao array PopDivas e tem como retorno a PopDiva criada
        //tem como parâmetros name, musicalGenre e nationality (que são todos do tipo String)

        if(findPopDiva(name) != null) {
            System.out.println("Pop diva with name " + name + " already exists.");
            return null;
            //retorna null (o que encerra o metodo aqui e impede que ele prossiga para o código abaixo, ou seja, crie a PopDiva)
        }

        PopDiva popDiva = new PopDiva(name, musicalGenre, nationality);
        //se uma PopDiva com esse nome ainda não existe, cria um novo objeto do tipo PopDiva. isso é feito através da chamada new PopDiva usando os valores fornecidos nos parâmetros
        popDivas.add(popDiva);
        //o objeto popDiva criado é adicionado ao array popDivas
        return popDiva;
        //o metodo retorna o objeto popDiva criado
    }

    public ArrayList<PopDiva> getPopDivas() {
        //o metodo retorna um array composto de objetos do tipo PopDiva...
        return popDivas;
        //que é o popDivas criado no início da classe PopDivaService e contém todas as Pop Divas adicionadas até o momento
    }

    public String popDivaCurrentStatus(String name) {
        PopDiva diva = findPopDiva(name);
        if(diva != null) {
            return diva.currentStatus();
        }
        return "Pop diva not found";
    }

    public String popDivaPresentation(String name) {
        PopDiva diva = findPopDiva(name);
        if(diva != null) {
            return diva.presentation();
        }
        return "Pop diva not found";
    }

    public boolean postGrammyNominations(String name, int grammyNomination) {
        PopDiva diva = findPopDiva(name);
        if(diva != null) {
            return diva.addGrammyNominations(grammyNomination);
        }
        return false;
    }

    public boolean postGrammyWins(String name, int grammyWins) {
        PopDiva diva = findPopDiva(name);
        System.out.println(grammyWins + " (grammy wins)");
        if(diva != null) {
            return diva.addGrammyWins(grammyWins);
        }
        return false;
    }
}
