package br.com.joaopmazzo.linguagens.api.controller;

import br.com.joaopmazzo.linguagens.api.repository.LinguagemRepository;
import br.com.joaopmazzo.linguagens.api.entity.Linguagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repository;

    @GetMapping(value = "/linguagens")
    public ResponseEntity<List<Linguagem>> retornaLinguagens() {
        try {
            List<Linguagem> linguagens = repository.findAll();
            return new ResponseEntity<>(linguagens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/linguagens/{id}")
    public ResponseEntity<Linguagem> retornaLinguagemId(@PathVariable(value = "id") String id) {
        Optional<Linguagem> linguagem = repository.findById(id);
        if (linguagem.isPresent()) {
            return new ResponseEntity<>(linguagem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
        try {
            Linguagem linguagemSalva = repository.save(linguagem);
            return new ResponseEntity<>(linguagemSalva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/linguagens/{id}")
    public ResponseEntity<Linguagem> atualizarLinguagem(@PathVariable(value = "id") String id, @RequestBody Linguagem linguagem) {
        Optional<Linguagem> linguagemParaAtualizada = repository.findById(id);

        if (linguagemParaAtualizada.isPresent()) {
            Linguagem _linguagem = linguagemParaAtualizada.get();
            _linguagem.setTitle(linguagem.getTitle());
            _linguagem.setImage(linguagem.getImage());
            _linguagem.setRanking(linguagem.getRanking());

            Linguagem linguagemAtualizada = repository.save(_linguagem);
            return new ResponseEntity<>(linguagemAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/linguagens/{id}")
    public ResponseEntity<HttpStatus> excluiLinguagemPorId(@PathVariable(value = "id") String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
