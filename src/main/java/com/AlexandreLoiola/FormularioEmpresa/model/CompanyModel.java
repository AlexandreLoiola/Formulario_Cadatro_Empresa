package com.AlexandreLoiola.FormularioEmpresa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_COMPANY_MODEL")
public class CompanyModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corporateName", length = 256, nullable = false)
    private String corporateName;

    @Column(name= "cnpj", length = 14, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "dateFoundation", nullable = false)
    private LocalDate dateFoundation;

    @Column(name = "legalRepresentative", length = 128, nullable = false)
    private String legalRepresentative;

    @Column(name = "site")
    private String site;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "typeCompany", nullable = false)
    private String typeCompany;

}
