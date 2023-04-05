package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import br.com.lucas.Container.Store.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ClienteControllerTest {
    @InjectMocks
    private ClienteController clienteControllerMock;
    @Mock
    private ClienteServiceImpl clienteServiceMock;

    @BeforeEach
    void setup(){
        PageImpl<Cliente> clientePage = new PageImpl<>(List.of(ClienteCreator.createValidCliente()));
        BDDMockito.when(clienteServiceMock.listingClientes(ArgumentMatchers.any(), ArgumentMatchers.any())).
                thenReturn(clientePage);
    }
    @Test
    void list_ReturnsListofClientes_WhenSuccessful(){
        String expectedEmail = ClienteCreator.createValidCliente().getEmail();

        Page<Cliente> clientePage = clienteControllerMock.listCliente(null, null);

        Assertions.assertThat(clientePage).isNotNull();

        Assertions.assertThat(clientePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clientePage.toList().get(0).getEmail()).isEqualTo(expectedEmail);

    }


}