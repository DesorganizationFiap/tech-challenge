package br.com.fiap.techchallenge.adapters.pedido;

import br.com.fiap.techchallenge.domain.entities.Pedido;
import br.com.fiap.techchallenge.domain.model.enums.ErrosEnum;
import br.com.fiap.techchallenge.domain.model.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.domain.model.mapper.pedido.PedidoMapper;
import br.com.fiap.techchallenge.domain.valueobjects.PedidoDTO;
import br.com.fiap.techchallenge.infra.exception.PedidoException;
import br.com.fiap.techchallenge.infra.repositories.PedidoRepository;
import br.com.fiap.techchallenge.ports.pedido.GetPedidoOutboundPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class GetPedidoAdapter implements GetPedidoOutboundPort {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public GetPedidoAdapter(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoDTO buscarPedidoPorId(Long id) {
        log.info("buscarPedidoPorId");
        try {
            Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO));
            return this.pedidoMapper.toDTO(pedido);
        } catch (Exception e) {
            log.error("O identificador informado não existe no banco de dados.");
            return null;
        }
    }

    @Override
    public List<PedidoDTO> listarPedidos(Integer page, Integer size) {
        log.info("listarPedidos");
        List<Pedido> listaPedido = new ArrayList<>();

        PageRequest pageable = PageRequest.of(page, size);
        Page<Pedido> pagePedido = pedidoRepository.findAll(pageable);

        if (pagePedido != null) {
            listaPedido.addAll(pagePedido.toList());
        }

        return pedidoMapper.fromListEntityToListDTO(listaPedido);
    }

    @Override
    public List<PedidoDTO> listarPedidosPorStatus(String status, Integer page, Integer size) {
        StatusPedidoEnum statusPedidoEnum = StatusPedidoEnum.byStatus(status);

        List<PedidoDTO> pedidos = this.listarPedidos(page, size);
        if (pedidos == null || pedidos.isEmpty()) {
            return new ArrayList<>();
        }

        Predicate<PedidoDTO> byStatus = sp -> sp.getStatus().getId().equals(statusPedidoEnum.getId());
        return pedidos.stream().filter(byStatus).toList();
    }
}
