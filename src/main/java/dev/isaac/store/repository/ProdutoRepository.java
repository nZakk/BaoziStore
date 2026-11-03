package dev.isaac.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.isaac.store.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}