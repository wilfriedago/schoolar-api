package dev.thewlabs.schoolar.domain.groups.validators;

import dev.thewlabs.schoolar.domain.groups.GroupRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class GroupValidator implements ConstraintValidator<GroupExist, String> {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupValidator(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void initialize(GroupExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (isNull(id)) return true;// Let other validators handle null value

        try {
            UUID groupId = UUID.fromString(id);
            return this.groupRepository.existsById(groupId);
        } catch (IllegalArgumentException exception) {
            return true; // Let other validators handle uuid validation
        }
    }
}
