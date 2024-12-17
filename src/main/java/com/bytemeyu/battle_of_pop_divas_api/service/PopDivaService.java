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

    public PopDiva postPopDiva(String name, String musicalGenre, String nationality) {
        //o metodo postPopDiva que cria e adiciona uma PopDiva ao array PopDivas e tem como retorno a PopDiva criada
        //tem como parâmetros name, musicalGenre e nationality (que são todos do tipo String)

        for (PopDiva diva : popDivas) {
            //esse for percorre o array popDivas e nomeia cada elemento do array como 'diva'
            if(diva.getName().equalsIgnoreCase(name)) {
                //diva.getName() retorna o nome de cada diva. se ele for igual (sem considerar a diferenciação entre maiúsculas e minúsculas) ao nome passado como parâmetro, entra nessa condição e...
                System.out.println("Pop diva with name " + name + "already exists.");
                //imprime a frase acima na tela e...
                return null;
                //retorna null (o que encerra o metodo aqui e impede que ele prossiga para o código abaixo, ou seja, crie a PopDiva)
            }
        }

        PopDiva popDiva = new PopDiva(name, musicalGenre, nationality);
        //cria um novo objeto do tipo PopDiva. isso é feito através da chamada new PopDiva usando os valores fornecidos nos parâmetros
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
}
