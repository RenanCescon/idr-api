package br.edu.utfpr.ProjetoIDRAPI.entity.region;

import br.edu.utfpr.ProjetoIDRAPI.entity.crud.CrudController;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.ProjetoIDRAPI.entity.crud.CrudService;

@RestController
@RequestMapping("regions")
public class RegionController extends CrudController<Region, Region, Long> {

	private final RegionService regionService;
	private ModelMapper modelMapper;
	
	public RegionController(RegionService regionService, ModelMapper modelMapper) {
		super(Region.class, Region.class);
		this.regionService = regionService;
		this.modelMapper = modelMapper;
	}

	@Override
	protected CrudService<Region, Long> getService() {
		return this.regionService;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return this.modelMapper;
	}
	
	@GetMapping("/findName/{name}")
	public ResponseEntity<Region> findByName(@PathVariable String name){
		Region entity = regionService.findByName(name);
		
		if(entity != null) {
			return ResponseEntity.ok(regionService.findByName(name));
		} else {
    		return ResponseEntity.noContent().build();
    	}
	}
}
