package dev.isaac.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.isaac.store.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
