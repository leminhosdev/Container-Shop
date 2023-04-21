package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Scrap;
import br.com.lucas.Container.Store.repository.Scrap_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {
    private final Scrap_repository scrapRepository;

    @Override
    public Scrap saving(Scrap nft) {
        boolean verification = scrapRepository.existsByLink(nft.getLink());
        if (verification == true){
            throw new IllegalStateException("ja existe essa coleção");
        }
        return scrapRepository.save(nft);
    }

    @Override
    public Optional<Scrap> searchById(Long id) {
        return scrapRepository.findById(id);
    }

    @Override
    public void deletedById(Long id) {
        scrapRepository.deleteById(id);
    }
}
