package br.com.fiap.techchallenge.ports.cliente;

import br.com.fiap.techchallenge.domain.valueobjects.ClienteDTO;

public interface PostClienteOutboundPort {
    ClienteDTO salvarCliente(ClienteDTO clienteDTO);

    void validarCpfCadastrado(String cpf);
}