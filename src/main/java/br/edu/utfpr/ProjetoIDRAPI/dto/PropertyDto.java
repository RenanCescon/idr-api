package br.edu.utfpr.ProjetoIDRAPI.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.edu.utfpr.ProjetoIDRAPI.model.Property;
import lombok.Data;

@Data
public class PropertyDto {
	
	private long id;
	
    private String ocupationArea;
    
    private BigDecimal totalArea;
    
    private Byte[] soilMap;
    
    private BigInteger latitude;
    
    private BigInteger longitude;
    
    private Boolean leased;
    
    private UserDto user;
    
    public Property toProperty() {
    	Property prop = new Property();
    	prop.setId(id);
    	prop.setOcupationArea(ocupationArea);
    	prop.setTotalArea(totalArea);
    	prop.setSoilMap(soilMap);
    	prop.setLatitude(latitude);
    	prop.setLongitude(longitude);
    	prop.setLeased(leased);
    	
    	return prop;
    }
}
