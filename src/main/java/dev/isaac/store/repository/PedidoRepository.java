package dev.isaac.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.isaac.store.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}