package br.edu.utfpr.ProjetoIDRAPI.entity.insemination.impl;

import br.edu.utfpr.ProjetoIDRAPI.entity.animal.Animal;
import br.edu.utfpr.ProjetoIDRAPI.entity.crud.impl.CrudServiceImpl;
import br.edu.utfpr.ProjetoIDRAPI.entity.animal.AnimalService;
import br.edu.utfpr.ProjetoIDRAPI.entity.insemination.Insemination;
import br.edu.utfpr.ProjetoIDRAPI.entity.insemination.InseminationRepository;
import br.edu.utfpr.ProjetoIDRAPI.entity.insemination.InseminationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InseminationServiceImpl extends CrudServiceImpl<Insemination, Long> implements InseminationService {

    private final InseminationRepository inseminationRepository;
    private final AnimalService animalService;

    public InseminationServiceImpl(InseminationRepository inseminationRepository, AnimalService animalService) {
        this.inseminationRepository = inseminationRepository;
        this.animalService = animalService;
    }

    @Override
    protected JpaRepository<Insemination, Long> getRepository() {
        return this.inseminationRepository;
    }

    @Override
    public boolean saveListInseminations(List<Insemination> inseminations) {
        boolean status = true;
        try {
            for (Insemination insemination : inseminations) {

                //Identifica o animal de acordo com o identifier
                String animalIdentifier = insemination.getAnimal().getIdentifier();
                Animal animal = animalService.findByIdentifier(animalIdentifier);

                insemination.setAnimal(animal);

                inseminationRepository.save(insemination);
            }
        } catch (Exception e){
            status = false;
            log.error(e.getMessage());
        }

        return status;
    }
}