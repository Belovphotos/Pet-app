package ru.belov.filter;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.entity.PetEntity;
import ru.common.dto.specificationdtos.search.SearchDateObject;
import ru.common.dto.specificationdtos.search.SearchStringObject;
import ru.common.filter.Filter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.belov.specification.PetSpecification.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetEntityFilter implements Filter<PetEntity> {
    private Set<UUID> id;
    private SearchStringObject name;
    private SearchStringObject animalKind;
    private SearchStringObject breed;
    private SearchStringObject gender;
    private SearchDateObject birthDate;
    private List<Integer> weight;
    private SearchStringObject color;
    private SearchDateObject lastVisitDate;
    private SearchDateObject registrationDate;
    private Set<UUID> cheapId;
    private Set<UUID> collarId;
    private Set<UUID> ownerId;

    @Override
    public Specification<PetEntity> toSpecification() {
        return where(hasId(id)
                .and(hasName(name))
                .and(hasAnimalKind(animalKind))
                .and(hasBreed(breed))
                .and(hasGender(gender))
                .and(hasBirthDate(birthDate))
                .and(hasWeight(weight))
                .and(hasColor(color))
                .and(hasLastVisitDate(lastVisitDate))
                .and(hasRegistrationDate(registrationDate))
                .and(hasCheapId(cheapId))
                .and(hasCollarId(collarId))
                .and(hasOwnerId(ownerId)));
    }
}
