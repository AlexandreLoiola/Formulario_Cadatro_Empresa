package com.AlexandreLoiola.FormularioEmpresa.service;

import com.AlexandreLoiola.FormularioEmpresa.model.CompanyModel;
import com.AlexandreLoiola.FormularioEmpresa.repository.CompanyRepository;
import com.AlexandreLoiola.FormularioEmpresa.rest.Dto.CompanyDto;
import com.AlexandreLoiola.FormularioEmpresa.rest.Form.CompanyForm;
import com.AlexandreLoiola.FormularioEmpresa.rest.Form.CompanyUpdateForm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyDto> getAllCompanys() {
        List<CompanyModel> companyModelList = companyRepository.findAll();
        return convertListModelToListDto(companyModelList);
    }

    public CompanyDto getCompanyByCnpj(String cnpj) {
        try {
            CompanyModel companyModel = companyRepository.findByCnpj(cnpj).get();
            return convertModelToDto(companyModel);
        } catch (NoSuchElementException error) {
            throw new NoSuchElementException("Empresa não encontrada. CNPJ: " + cnpj);
        }
    }

    @Transactional
    public CompanyDto insertCompany(CompanyForm companyForm) {
        try {
            CompanyModel newCompanyModel = convertFormToModel(companyForm);

            Optional<CompanyModel> companyModel = companyRepository.findByCnpj(companyForm.getCnpj());
            if (companyModel.isPresent()) {
                throw new DataIntegrityViolationException("O CNPJ: "+companyForm.getCnpj()+" já está registrado! Use outro CNPJ");
            }

            companyRepository.save(newCompanyModel);
            return convertModelToDto(newCompanyModel);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) da empresa não foi(foram) preenchido(s).");
        }
    }

    @Transactional
    public CompanyDto updateCompany(String cnpj, CompanyUpdateForm companyUpdateForm) {
        try {
            Optional<CompanyModel> companyModel = companyRepository.findByCnpj(cnpj);
            if (companyModel.isPresent()) {
                CompanyModel companyUpdate = companyModel.get();

                companyUpdate.setCorporateName(companyUpdateForm.getCorporateName());
                companyUpdate.setCnpj(companyUpdateForm.getCnpj());
                companyUpdate.setDateFoundation(companyUpdateForm.getDateFoundation());
                companyUpdate.setLegalRepresentative(companyUpdateForm.getLegalRepresentative());
                companyUpdate.setSite(companyUpdateForm.getSite());
                companyUpdate.setEmail(companyUpdateForm.getEmail());
                companyUpdate.setTelephone(companyUpdateForm.getTelephone());
                companyUpdate.setTypeCompany(companyUpdateForm.getTypeCompany());

                companyRepository.save(companyUpdate);
                return convertModelToDto(companyUpdate);
            } else {
                throw new DataIntegrityViolationException("Essa empresa não está cadastrada");
            }
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Laboaratório não foi(foram) preenchido(s).");
        }
    }

    @Transactional
    public void deleteCompany(String cnpj) {
        try {
            if (companyRepository.findByCnpj(cnpj).isPresent()) {
                companyRepository.deleteByCnpj(cnpj);
            }
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityViolationException("Não é possível excluir está empresa");
        }
    }

    private CompanyModel convertFormToModel(CompanyForm companyForm) {
        CompanyModel companyModel = new CompanyModel();

        companyModel.setCorporateName(companyForm.getCorporateName());
        companyModel.setCnpj(companyForm.getCnpj());
        companyModel.setDateFoundation(companyForm.getDateFoundation());
        companyModel.setLegalRepresentative(companyForm.getLegalRepresentative());
        companyModel.setSite(companyForm.getSite());
        companyModel.setEmail(companyForm.getEmail());
        companyModel.setTelephone(companyForm.getTelephone());
        companyModel.setTypeCompany(companyForm.getTypeCompany());

        return companyModel;
    }

    private CompanyDto convertModelToDto(CompanyModel companyModel) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setCorporateName(companyModel.getCorporateName());
        companyDto.setCnpj(companyModel.getCnpj());
        companyDto.setDateFoundation(companyModel.getDateFoundation());
        companyDto.setLegalRepresentative(companyModel.getLegalRepresentative());
        companyDto.setSite(companyModel.getSite());
        companyDto.setEmail(companyModel.getEmail());
        companyDto.setTelephone(companyModel.getTelephone());
        companyDto.setTypeCompany(companyModel.getTypeCompany());

        return companyDto;
    }

    private List<CompanyDto> convertListModelToListDto(List<CompanyModel> list) {
        List<CompanyDto> companyDtoList = new ArrayList<>();
        for (CompanyModel companyModel : list) {
            CompanyDto companyDto = this.convertModelToDto(companyModel);
            companyDtoList.add(companyDto);
        }
        return companyDtoList;
    }
}
