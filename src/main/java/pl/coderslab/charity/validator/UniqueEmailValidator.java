package pl.coderslab.charity.validator;

import lombok.AllArgsConstructor;
import pl.coderslab.charity.repository.AppUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final AppUserRepository appUserRepository;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return appUserRepository.findByEmail(email) == null;
    }
}
