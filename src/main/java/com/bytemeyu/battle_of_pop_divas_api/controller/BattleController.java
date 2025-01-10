package com.bytemeyu.battle_of_pop_divas_api.controller;

import com.bytemeyu.battle_of_pop_divas_api.service.BattleService;
import com.bytemeyu.battleofpopdivas.battle.Battle;
import org.springframework.boot.autoconfigure.batch.BatchTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battle")
public class BattleController {

    private final BattleService battleService;
    //um atributo da classe BattleController...

    public BattleController(BattleService battleService) {
        //...injetado (inicializado) via construtor
        this.battleService = battleService;
    }

    @PostMapping("/create-battle")
    public ResponseEntity<Battle> createBattle(@RequestBody BattleRequest request) {
        String challengerName = request.getChallengerName();
        String challengedName = request.getChallengedName();

        Battle battle = battleService.createBattle(challengerName, challengedName);

        if(battle == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null);
        }

        return ResponseEntity.ok(battle);

    }

    @PostMapping("/battle-current-status")
    public ResponseEntity<String> battleCurrentStatus(@RequestBody BattleRequest request) {
        String battleCode = request.getBattleCode();

        Battle battle = battleService.findBattle(battleCode);

        if(battle == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The battle was not found");
        }

        String battleStatus = battle.currentStatus();

        return ResponseEntity.ok(battleStatus);
    }

    @PostMapping("/start-battle")
    public ResponseEntity<String> startBattle (@RequestBody BattleRequest request) {
        String battleCode = request.getBattleCode();

        Battle battle = battleService.findBattle(battleCode);

        if(battle == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The battle was not found, therefore it cannot happen");
        }

        battleService.startBattle(battleCode);

        return ResponseEntity.ok("Battle started and completed");

    }
}



class BattleRequest {
    private String challengerName;
    private String challengedName;
    private String battleCode;

    public String getChallengerName() {
        return challengerName;
    }

    public void setChallengerName(String challengerName) {
        this.challengerName = challengerName;
    }

    public String getChallengedName() {
        return challengedName;
    }

    public void setChallengedName(String challengedName) {
        this.challengedName = challengedName;
    }

    public String getBattleCode() {
        return battleCode;
    }

    public void setBattleCode(String battleCode) {
        this.battleCode = battleCode;
    }
}
