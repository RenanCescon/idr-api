package br.edu.utfpr.ProjetoIDRAPI.entity.perennialanualforage;

import br.edu.utfpr.ProjetoIDRAPI.entity.crud.CrudController;
import br.edu.utfpr.ProjetoIDRAPI.entity.crud.CrudService;
import br.edu.utfpr.ProjetoIDRAPI.entity.perennialanualforage.dto.PerennialAnualForageDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("fodders")
public class PerennialAnualForageController extends CrudController<PerennialAnualForage, PerennialAnualForageDto, Long> {

    private final PerennialAnualForageService perennialAnualFoddersService;
    private ModelMapper modelMapper;

    public PerennialAnualForageController(PerennialAnualForageService perennialAnualFoddersService, ModelMapper modelMapper) {
        super(PerennialAnualForage.class, PerennialAnualForageDto.class);
        this.perennialAnualFoddersService = perennialAnualFoddersService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected CrudService<PerennialAnualForage, Long> getService() {
        return this.perennialAnualFoddersService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    @GetMapping("/fodderProperty/{id}")
    public ResponseEntity<List<PerennialAnualForageDto>> findAllByPropertyId(@PathVariable Long id) {
        return ResponseEntity.ok(
                perennialAnualFoddersService.findByPropertyId(id)
                        .stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList())
        );
    }

    private PerennialAnualForageDto convertEntityToDto(PerennialAnualForage perennialAnualFodders) {
        return modelMapper.map(perennialAnualFodders, PerennialAnualForageDto.class);
    }
}
