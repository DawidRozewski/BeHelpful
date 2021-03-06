package pl.coderslab.charity.converter;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

@Component
@AllArgsConstructor
public class InstitutionConverter implements Converter<String, Institution> {

    private final InstitutionRepository institutionRepository;

    @Override
    public Institution convert(String id) {
        return institutionRepository.getOne(Long.parseLong(id));
    }
}
