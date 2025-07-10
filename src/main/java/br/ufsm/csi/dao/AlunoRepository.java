package br.ufsm.csi.dao;

import br.ufsm.csi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByMatricula(String matricula);

}