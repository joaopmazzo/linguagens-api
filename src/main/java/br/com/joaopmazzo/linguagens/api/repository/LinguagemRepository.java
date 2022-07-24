package br.com.joaopmazzo.linguagens.api.repository;

import br.com.joaopmazzo.linguagens.api.entity.Linguagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {

}
