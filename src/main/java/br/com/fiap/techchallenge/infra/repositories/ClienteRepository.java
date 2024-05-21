package br.com.fiap.techchallenge.infra.repositories;

import br.com.fiap.techchallenge.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    Optional<List<Cliente>> findByEmailOrCpf(String email, String cpf);
}
