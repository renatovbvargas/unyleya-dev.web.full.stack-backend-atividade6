
package br.edu.unyleya.cidadao.repository;

import br.edu.unyleya.cidadao.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadaoRepository  extends JpaRepository<Cidadao,Long>{
    
}
