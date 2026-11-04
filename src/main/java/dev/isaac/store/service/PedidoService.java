package dev.isaac.store.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import dev.isaac.store.model.Cliente;
import dev.isaac.store.model.Pedido;
import dev.isaac.store.model.Produto;
import dev.isaac.store.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService,
            ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public Pedido criar(Pedido pedido) {

        pedido.setId(null);
        Cliente cliente = clienteService.buscarPorId(pedido.getCliente().getId());
        pedido.setCliente(cliente);
        Produto produto = produtoService.buscarPorId(pedido.getProduto().getId());
        if (!Boolean.TRUE.equals(produto.getEstoque())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Produto não tem em estoque");
        }
        pedido.setProduto(produto);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
