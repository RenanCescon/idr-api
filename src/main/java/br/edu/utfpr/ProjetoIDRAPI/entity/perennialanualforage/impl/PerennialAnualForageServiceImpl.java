package br.edu.utfpr.ProjetoIDRAPI.entity.perennialanualforage.impl;

import br.edu.utfpr.ProjetoIDRAPI.entity.crud.impl.CrudServiceImpl;
import br.edu.utfpr.ProjetoIDRAPI.entity.perennialanualforage.PerennialAnualForage;
import br.edu.utfpr.ProjetoIDRAPI.entity.perennialanualforage.PerennialAnualForageRepository;
import br.edu.utfpr.ProjetoIDRAPI.entity.perennialanualforage.PerennialAnualForageService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerennialAnualForageServiceImpl extends CrudServiceImpl<PerennialAnualForage, Long>
        implements PerennialAnualForageService {

    private final PerennialAnualForageRepository perennialAnualForageRepository;

    public PerennialAnualForageServiceImpl(PerennialAnualForageRepository repository) {
        this.perennialAnualForageRepository = repository;
    }

    @Override
    public List<PerennialAnualForage> findByPropertyId(Long id) {
        return perennialAnualForageRepository.findAllByPropertyId(id);
    }

    @Override
    protected JpaRepository<PerennialAnualForage, Long> getRepository() {
        return this.perennialAnualForageRepository;
    }

}
