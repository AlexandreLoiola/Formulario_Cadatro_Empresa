package com.AlexandreLoiola.FormularioEmpresa.rest.Controller;

import com.AlexandreLoiola.FormularioEmpresa.rest.Dto.CompanyDto;
import com.AlexandreLoiola.FormularioEmpresa.rest.Form.CompanyForm;
import com.AlexandreLoiola.FormularioEmpresa.rest.Form.CompanyUpdateForm;
import com.AlexandreLoiola.FormularioEmpresa.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyform")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanys() {
        List<CompanyDto> companyDtoList = companyService.getAllCompanys();
        return ResponseEntity.ok().body(companyDtoList);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<CompanyDto> getCompanyByCnpj(
            @PathVariable("cnpj") String cnpj
    ) {
        CompanyDto companyDto = companyService.getCompanyByCnpj(cnpj);
        return ResponseEntity.ok().body(companyDto);
    }

    @PostMapping
    public ResponseEntity<CompanyDto> insertCompany(
            @Valid @RequestBody CompanyForm companyForm
    ) {
        CompanyDto companyDto = companyService.insertCompany(companyForm);
        return ResponseEntity.ok().body(companyDto);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<CompanyDto> updateCompany(
            @PathVariable("cnpj") String cnpj,
            @Valid @RequestBody CompanyUpdateForm companyUpdateForm
    ) {
        CompanyDto companyDto = companyService.updateCompany(cnpj, companyUpdateForm);
        return ResponseEntity.ok().body(companyDto);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<CompanyDto> deleteCompany(
            @PathVariable("cnpj") String cnpj
    ) {
        companyService.deleteCompany(cnpj);
        return ResponseEntity.noContent().build();
    }
}
