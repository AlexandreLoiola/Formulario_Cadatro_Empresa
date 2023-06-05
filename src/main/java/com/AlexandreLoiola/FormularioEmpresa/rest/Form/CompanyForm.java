package com.AlexandreLoiola.FormularioEmpresa.rest.Form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@Data
public class CompanyForm {

    @NotEmpty
    @NotBlank(message = "O campo nome da empresa não pode ficar em branco!")
    private String corporateName;

    @NotEmpty
    @NotBlank(message = "O campo nome da empresa não pode ficar em branco!")
    @CNPJ(message = "Este CNPJ é inválido")
    @Size(min = 14, max = 14, message = "O campo CNPJ deve ter 14 dígitos")
    private String cnpj;

    @NotNull(message = "A data de fundação não pode ficar em branco")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFoundation;

    @NotEmpty
    @NotBlank(message = "O campo nome do responsável legal não pode ficar em branco!")
    private String legalRepresentative;

    @NotEmpty
    @NotBlank(message = "O campo site não pode ficar em branco!")
    private String site;

    @NotEmpty
    @NotBlank(message = "O campo e-mail não pode ficar em branco!")
    private String email;

    @NotEmpty
    @NotBlank(message = "O campo telphone não pode ficar em branco!")
    private String telephone;

    @NotEmpty
    @NotBlank(message = "O campo tipo da empresa não pode ficar em branco!")
    private String typeCompany;

}
