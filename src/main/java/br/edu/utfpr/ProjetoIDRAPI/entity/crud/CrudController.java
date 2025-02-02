package br.edu.utfpr.ProjetoIDRAPI.entity.crud;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.utfpr.ProjetoIDRAPI.search.request.SearchRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.utfpr.ProjetoIDRAPI.utils.GenericResponse;
import jakarta.validation.Valid;

public abstract class CrudController<T, D, ID extends Serializable> {
	protected abstract CrudService<T, ID> getService();
	protected abstract ModelMapper getModelMapper();
	private final Class<T> typeClass;
	private final Class<D> typeDtoClass;
	
	public CrudController(Class<T> typeClass, Class<D> typeDtoClass) {
		this.typeClass = typeClass;
		this.typeDtoClass = typeDtoClass;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse createRegister(@RequestBody @Valid T entity) {
		getService().save(entity);
	    return new GenericResponse("Registro inserido com sucesso");
	}
	
	@PutMapping("{id}")
    public GenericResponse updateRegister(@RequestBody @Valid T entity, @PathVariable ID id) {
		try {
			T ent = getService().findOne(id);
	    	
	    	if(ent != null) {
	    		getService().save(entity);
	    		return new GenericResponse("Registro atualizado com sucesso");
	    	}else {
	    		return new GenericResponse("Registro inexistente");
	    	}
		} catch (Exception e) {
			return new GenericResponse("Erro ao atualizar o registro!");
		}
    }
	
	@GetMapping
    public ResponseEntity<List<D>> listAll(){
    	return ResponseEntity.ok(getService().findAll().stream()
    			.map(this::convertToDto)
    			.collect(Collectors.toList()));
    }
	
	@GetMapping("{id}")
    public ResponseEntity<D> findOne(@PathVariable ID id){
    	T entity = getService().findOne(id);
    	
    	if(entity != null) {
    		return ResponseEntity.ok(convertToDto(getService().findOne(id)));
    	} else {
    		return ResponseEntity.noContent().build();
    	}
    }
	
	@DeleteMapping("{id}")
	public ResponseEntity deleteRegister(@PathVariable ID id){
		try {
			getService().delete(id);
	    	return ResponseEntity.ok(new GenericResponse("Registro excluido com sucesso"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new GenericResponse("Não foi possível excluir o registro!"));
		}
	}
	
	public D convertToDto(T entity) {
    	return getModelMapper().map(entity, this.typeDtoClass);
    }

	@PostMapping("/search")
	public Page<T> search(@RequestBody @Valid SearchRequest entity){
		return getService().search(entity);
	}
}
