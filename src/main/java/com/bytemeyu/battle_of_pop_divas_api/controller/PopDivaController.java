package com.bytemeyu.battle_of_pop_divas_api.controller;

import com.bytemeyu.battle_of_pop_divas_api.service.PopDivaService;
//Importa a classe PopDivaService, que será usada para chamar a lógica de negócio relacionada às Pop Divas.
import com.bytemeyu.battleofpopdivas.popdiva.PopDiva;
//Importa a classe PopDiva, que representa uma cantora pop.
import org.springframework.http.HttpStatus;
//Importa uma classe, que faz parte do Spring Framework, que contém uma enumeração de todos os códigos de status HTTP (ex.: HttpStatus.OK, HttpStatus.BAD_REQUEST, HttpStatus.CONFLICT) usados para indicar o resultado de uma requisição. Ela é amplamente utilizada em combinação com ResponseEntity para retornar respostas HTTP claras e consistentes
import org.springframework.http.ResponseEntity;
//Importa uma classe, que faz parte do Spring Framework, que permite controle total sobre a resposta HTTP enviada ao cliente. Permite customizar Status Code (200, 404, 500, etc.); Headers (como Content-Type); Body (o conteúdo da resposta, como JSON, texto ou binário).
import org.springframework.web.bind.annotation.*;
//Importa uma classe, que faz parte do Spring Web, que lida com requisições HTTP (como GET, POST, etc.).

import java.util.List;

@RestController
//Indica que esta classe é um controlador Spring que expõe endpoints REST.
@RequestMapping("/api/popdiva")
//Define o caminho base para todos os endpoints desta classe.
public class PopDivaController {

    private final PopDivaService popDivaService;
    //popDivaService é um atributo da classe PopDivaController!
    //PopDivaService especifica o tipo do atributo popDivaService
    //O atributo popDivaService é inicializado via injeção de dependência no construtor da classe PopDivaController.
    //private: Torna o campo acessível apenas dentro desta classe.
    //final: Garante que o campo será inicializado uma única vez, normalmente dentro do construtor da classe, e o valor desse atributo não pode ser alterado. Essa imutabilidade ajuda a garantir que o controlador sempre use o mesmo serviço durante o ciclo de vida da aplicação.

    public PopDivaController(PopDivaService popDivaService) {
        //construtor da classe PopDivaController
        this.popDivaService = popDivaService;
        //Aqui, o Spring automaticamente injeta uma instância do serviço PopDivaService na classe PopDivaController quando o controlador é criado.
        //Com isso, o controlador pode usar o popDivaService para chamar métodos que existem no PopDivaService (como postPopDiva e getPopDivas).
    }



    @PostMapping("/post-popdiva")
    //adiciona a continuação do endpoint que direciona a esse metodo
    public ResponseEntity<PopDiva> postPopDiva(@RequestBody PopDivaRequest request) {
        //o retorno desse metodo será uma ResponseEntity com o corpo do tipo PopDiva
        //@RequestBody: informa ao Spring que o corpo da requisição HTTP (normalmente um JSON) será transformado em um objeto Java do PopDivaRequest e será mapeado diretamente para o parâmetro request.
        PopDiva popDiva = popDivaService.postPopDiva(request.getName(), request.getMusicalGenre(), request.getNationality());
        //é criado um objeto popDiva, do tipo PopDiva, por meio da chamada do metodo postPopDiva (com seus parâmetros) do serviço [!] popDivaService [ele não está chamando ele mesmo - o metodo do próprio controller, rs]. lembrando que o metodo postPopDiva do popDivaService [do tipo PopDivaService] não só cria a popDiva, como a adiciona ao array popDivas

        if (popDiva == null) {
            // Caso a PopDiva não seja criada, nesse caso por ser repetida, retorna 409 Conflict
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // Corpo da resposta é nulo [ou pode incluir uma mensagem personalizada]
        }

        return ResponseEntity.ok(popDiva);
        //retorna uma resposta HTTP, utilizando a classe ResponseEntity, que tem como corpo popDiva (representado por "(popDiva)") e código de status HTTP 200 OK (representado pelo ".ok"), caso a PopDiva seja criada com sucesso
    }

    @GetMapping("/get-popdivas")
    public ResponseEntity<List<PopDiva>> getPopDivas() {
        //o retorno desse metodo será uma ResponseEntity que será uma lista com objetos do tipo PopDiva
        return ResponseEntity.ok(popDivaService.getPopDivas());
        //retorna de maneira direta uma resposta HTTP, que tem como corpo o que é retornado do chamado do metodo getPopDivas() do serviço popDivaService e código de status HTTP 200 OK
    }

    @PostMapping ("/popdiva-current-status")
    public ResponseEntity<String> popDivaCurrentStatus(@RequestBody PopDivaRequest request) {
        String popDivaName = request.getName();

        if(popDivaName == null || popDivaService.findPopDiva(popDivaName) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(popDivaService.popDivaCurrentStatus(popDivaName));
    }

    @PostMapping("/popdiva-presentation")
    public ResponseEntity<String> popDivaPresentation(@RequestBody PopDivaRequest request) {
        String popDivaName = request.getName();

        if(popDivaName == null || popDivaService.findPopDiva(popDivaName) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(popDivaService.popDivaPresentation(popDivaName));
    }

    @PostMapping("post-grammy-nominations")
    public ResponseEntity<String> postGrammyNominations(@RequestBody PopDivaRequest request) {
        String popDivaName = request.getName();
        int popDivaGrammyNominations = request.getGrammyNominations();
        //aqui, ao invés de fazer com que o metodo receba o objeto DTO PopDivaRequest (que é populado com os dados do corpo da requisição), eu poderia ter usado parâmetros direto na URL para capturar essas informações:
        //(@PathVariable String name, @RequestParam int grammyNominations)
        //onde: @PathVariable captura parte da rota da URL ({name}). e @RequestParam lê valores enviados como parâmetros de consulta (ex.: ?nominations=5).
        //mas optei por continuar usando DTO primeiro para manter a consistência (com os outros métodos do controller) e segundo porque o DTO mantém os dados organizados e facilita a validação, o que permite com que a aplicação seja mais escalável.

        if(popDivaName == null || popDivaGrammyNominations < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input data");
        }

        if(!popDivaService.postGrammyNominations(popDivaName, popDivaGrammyNominations)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pop diva not found");
        }

        return ResponseEntity.ok("Grammy nomination added successfully");

    }
}



class PopDivaRequest {
    //PopDivaRequest é uma classe auxiliar que serve como DTO (Data Transfer Object), ou seja, é usada para transferência de dados (tanto entrada, quanto saída) entre cliente e servidor. nesse caso, ela garante a captura de dados do corpo da requisição HTTP (como JSON), para que eles possam ser usados pela aplicação. essa classe ajuda a manter a separação entre os dados recebidos da requisição e a entidade PopDiva [?].
    private String name;
    private String musicalGenre;
    private String nationality;
    private int grammyNominations;

    //esses getters e setters a seguir permitem que o Spring Boot mapeie automaticamente os valores do JSON recebido para os campos da classe [?].
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
        this.grammyNominations = grammyNominations;
    }
}
