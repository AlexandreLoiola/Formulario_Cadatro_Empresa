package com.AlexandreLoiola.FormularioEmpresa.repository;

import com.AlexandreLoiola.FormularioEmpresa.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
    Optional<CompanyModel> findByCnpj(String cnpj);

    void deleteByCnpj(String cnpj);
}
