package de.keo9ren;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// Strange not working??
@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, UUID> {

	@Override
	public UUID convertToDatabaseColumn(UUID arg0) {
		return arg0;
	}

	@Override
	public UUID convertToEntityAttribute(UUID arg0) {
		return arg0;
	}

}
