package br.com.lucas.Container.Store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "collectionName", nullable = false)
    private String collectionName;
    @Column(name = "floorPrice", nullable = false)
    private Double floorPrice;
    @Column(name = "tradingVolume", nullable = false)
    private String tradingVolume;
    @Column(name = "owners", nullable = false)
    private String owners;
    @Column(name = "link", nullable = false)
    private String link;

    @ManyToMany(mappedBy = "nfts")
    private List<Cliente> cliente = new ArrayList<>();
}
