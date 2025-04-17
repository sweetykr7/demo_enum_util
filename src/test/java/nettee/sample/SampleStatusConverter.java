package nettee.sample;

import jakarta.persistence.AttributeConverter;

public class SampleStatusConverter implements AttributeConverter<SampleStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SampleStatus sampleStatus) {
        return sampleStatus != null ? sampleStatus.getCode() : null;
    }

    @Override
    public SampleStatus convertToEntityAttribute(Integer code) {
        return SampleStatus.valueOf(code);
    }
}
