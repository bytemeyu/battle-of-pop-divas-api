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
