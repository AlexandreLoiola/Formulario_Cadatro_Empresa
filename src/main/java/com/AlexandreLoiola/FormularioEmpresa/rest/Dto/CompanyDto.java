package com.AlexandreLoiola.FormularioEmpresa.rest.Dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyDto {

    private String corporateName;
    private String cnpj;

    private LocalDate dateFoundation;

    private String legalRepresentative;

    private String site;

    private String email;

    private String telephone;

    private String typeCompany;

}
