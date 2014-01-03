package org.smartcity.entity.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.math.BigInteger;

@Converter
public class LongToBigIntegerConverter
		implements AttributeConverter<BigInteger,Long> {

	@Override
	public Long convertToDatabaseColumn( BigInteger attribute ) {
		return attribute.longValue();
	}

	@Override
	public BigInteger convertToEntityAttribute( Long dbData ) {
		return BigInteger.valueOf( dbData );
	}

}