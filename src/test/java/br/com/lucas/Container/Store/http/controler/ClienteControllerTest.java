package br.com.lucas.Container.Store.http.controler;

import br.com.lucas.Container.Store.service.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ClienteControllerTest {
    @InjectMocks
    private ClienteController clienteController;
    @Mock
    private ClienteServiceImpl clienteService;

    @Test
    void test(){

    }

}