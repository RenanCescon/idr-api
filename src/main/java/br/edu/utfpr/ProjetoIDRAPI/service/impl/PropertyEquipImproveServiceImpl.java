package br.edu.utfpr.ProjetoIDRAPI.service.impl;

import br.edu.utfpr.ProjetoIDRAPI.model.PropertyEquipImprove;
import br.edu.utfpr.ProjetoIDRAPI.repository.PropertyEquipImproveRepository;
import br.edu.utfpr.ProjetoIDRAPI.service.PropertyEquipImproveService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyEquipImproveServiceImpl extends CrudServiceImpl<PropertyEquipImprove, Long>
        implements PropertyEquipImproveService {

    private final PropertyEquipImproveRepository equipImproveRepository;

    public PropertyEquipImproveServiceImpl(PropertyEquipImproveRepository repository) {
        this.equipImproveRepository = repository;
    }

    @Override
    protected JpaRepository<PropertyEquipImprove, Long> getRepository() {
        return this.equipImproveRepository;
    }

    @Override
    public List<PropertyEquipImprove> findByPropertyId(Long id) {
        return equipImproveRepository.findAllByPropertyId(id);
    }

}
