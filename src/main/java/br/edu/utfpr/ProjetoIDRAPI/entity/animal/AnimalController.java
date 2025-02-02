package br.edu.utfpr.ProjetoIDRAPI.entity.animal;

import br.edu.utfpr.ProjetoIDRAPI.entity.animal.dto.AnimalDto;
import br.edu.utfpr.ProjetoIDRAPI.entity.crud.CrudController;
import br.edu.utfpr.ProjetoIDRAPI.entity.crud.CrudService;
import br.edu.utfpr.ProjetoIDRAPI.utils.GenericResponse;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animals")
public class AnimalController extends CrudController<Animal, AnimalDto, Long> {

    private final AnimalService animalService;
    private ModelMapper modelMapper;

    public AnimalController(AnimalService animalService, ModelMapper modelMapper) {
        super(Animal.class, AnimalDto.class);
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected CrudService<Animal, Long> getService() {
        return this.animalService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    @GetMapping("/findAnimal/{identifier}")
    public ResponseEntity<AnimalDto> findAnimalByIdentifier(@PathVariable String identifier) {
        Animal entity = animalService.findByIdentifier(identifier);
        
        if(entity != null) {
        	return ResponseEntity.ok(convertEntityToDto(animalService.findByIdentifier(identifier)));
        } else {
    		return ResponseEntity.noContent().build();
    	}
    }

    @PostMapping("/sendAnimals")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse createRegister(@RequestBody @Valid List<Animal> animals) {
        animalService.saveListAnimals(animals);
        return new GenericResponse("Registros inseridos com sucesso");
    }

    private AnimalDto convertEntityToDto(Animal animal) {
        return modelMapper.map(animal, AnimalDto.class);
    }
}
