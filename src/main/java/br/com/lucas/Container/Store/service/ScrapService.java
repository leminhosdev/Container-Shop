package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.entity.Scrap;
import br.com.lucas.Container.Store.http.controler.dto.filter.ClientFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScrapService {

    Scrap saving(Scrap nft);
    //Page<Scrap> listingClientes(ClientFilter clientFilter, Pageable pageable);

    Optional<Scrap> searchById(Long id);
    void deletedById(Long id);
}
