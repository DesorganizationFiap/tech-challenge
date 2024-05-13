package br.com.fiap.techchallenge.usecases;

import br.com.fiap.techchallenge.domain.usecases.PatchClienteUseCase;
import br.com.fiap.techchallenge.domain.valueobjects.ClienteDTO;
import br.com.fiap.techchallenge.infra.exception.ClienteException;
import br.com.fiap.techchallenge.ports.PatchUsuarioOutboundPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PatchClienteUseCaseTest {

    @Mock
    private PatchUsuarioOutboundPort port;

    private PatchClienteUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new PatchClienteUseCase(port);
    }

    @Test
    void shouldUpdateClienteSuccessfully() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678901");
        clienteDTO.setNome("João");
        clienteDTO.setEmail("email@email.com");

        when(port.atualizarClientes(clienteDTO)).thenReturn(clienteDTO);

        useCase.atualizarClientes(clienteDTO);

        verify(port).atualizarClientes(clienteDTO);
    }

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf(null);

        assertThrows(ClienteException.class, () -> useCase.atualizarClientes(clienteDTO));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsEmpty() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("");

        assertThrows(ClienteException.class, () -> useCase.atualizarClientes(clienteDTO));
    }
}