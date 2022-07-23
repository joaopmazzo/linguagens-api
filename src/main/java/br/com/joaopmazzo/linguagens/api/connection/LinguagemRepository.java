package br.com.joaopmazzo.linguagens.api.connection;

import br.com.joaopmazzo.linguagens.api.linguagemDAO.Linguagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {

}
