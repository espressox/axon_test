package com.example.axon_test.helper;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author xin
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkerId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String serviceKey;
}
