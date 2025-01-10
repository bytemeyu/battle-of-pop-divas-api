package com.bytemeyu.battle_of_pop_divas_api.service;

import com.bytemeyu.battleofpopdivas.battle.Battle;
import com.bytemeyu.battleofpopdivas.popdiva.PopDiva;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BattleService {

    private final PopDivaService popDivaService;
    //O atributo popDivaService (da classe BattleService) é inicializado via injeção de dependência no construtor da classe BattleService:
    public BattleService(PopDivaService popDivaService) {
        //esse é o construtor citado acima
        this.popDivaService = popDivaService;
        //Aqui, o Spring automaticamente injeta uma instância do serviço PopDivaService na classe BattleService quando esse service é criado.
        //Com isso, o service em questão pode usar o popDivaService para chamar métodos que existem no PopDivaService (como findPopDiva).
    }

    private final ArrayList<Battle> battles = new ArrayList<>();

    public Battle createBattle(String challengerName, String challengedName) {
        PopDiva challenger = popDivaService.findPopDiva(challengerName);
        PopDiva challenged = popDivaService.findPopDiva(challengedName);

        if (challenger == null || challenged == null) {
            System.out.println("One or both divas were not found.");
        }

        Battle battle = new Battle(challenger, challenged);

        battles.add(battle);

        return battle;
    }

    public Battle findBattle(String battleCode) {
        for(Battle battle: battles) {
            if(battle.getBattleCode().equalsIgnoreCase(battleCode)) {
                return battle;
            }
        }

        return null;
    }

    public String battleCurrentStatus(String battleCode) {
        Battle battle = findBattle(battleCode);
        return battle.currentStatus();

    }
}
