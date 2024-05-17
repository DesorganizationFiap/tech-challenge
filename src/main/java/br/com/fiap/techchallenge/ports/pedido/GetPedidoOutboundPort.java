package br.com.fiap.techchallenge.ports.pedido;

import br.com.fiap.techchallenge.domain.valueobjects.PedidoDTO;
import java.util.List;

public interface GetPedidoOutboundPort {

    PedidoDTO buscarPedidoPorId(Long id);

    List<PedidoDTO> listarPedidos(Integer page, Integer size);

}
