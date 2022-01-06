package pl.coderslab.charity.converter;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

@Component
@AllArgsConstructor
public class CategoryConverter implements Converter<String, Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category convert(String source) {
        return categoryRepository.getOne(Long.parseLong(source));
    }
}
