package br.com.lucas.Container.Store.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "collectionName", nullable = false,  unique = true)
    private String collectionName;
    @Column(name = "floorPrice", nullable = false)
    private String floorPrice;
    @Column(name = "tradingVolume", nullable = false)
    private String tradingVolume;
    @Column(name = "owners", nullable = false)
    private String owners;
    @Column(name = "link", nullable = false)
    private String link;
}
