package br.com.lucas.Container.Store.util;

import br.com.lucas.Container.Store.entity.Cliente;

public class ClienteCreator {

    public static Cliente createClienteTobeSaved(){
        return Cliente.builder().
                name("stallonseis").cpf("48579352010").
                password("12345678").email("stallone@gmail.com").build();
    }
    public static Cliente createValidCliente(){
        return Cliente.builder().
                name("stallonseis").cpf("48579352010").
                password("12345678").email("stallone@gmail.com").id(1L).build();
    }

    public static Cliente createValidUpdatedCliente(){
        return Cliente.builder().
                name("stallone").cpf("48579352010").
                password("12345678910").email("stallone@gmail.com").id(1L).build();
    }
}
