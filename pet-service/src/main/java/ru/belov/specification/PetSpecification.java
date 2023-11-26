package ru.belov.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.entity.PetEntity;
import ru.common.dto.specificationdtos.search.SearchDateObject;
import ru.common.dto.specificationdtos.search.SearchStringObject;

import java.util.Collection;
import java.util.UUID;

import static ru.common.dto.specificationdtos.CommonSpecifications.*;

@UtilityClass
public class PetSpecification {
    public static Specification<PetEntity> hasId(Collection<UUID> id){
        return in("id", id);
    }

    public static Specification<PetEntity> hasName(SearchStringObject name){
        return (((root, query, criteriaBuilder) -> getStringCriteria("name", name, criteriaBuilder, root)));
    }

    public static Specification<PetEntity> hasAnimalKind(SearchStringObject animalKind){
        return (((root, query, criteriaBuilder) -> getStringCriteria("animalKind", animalKind, criteriaBuilder, root)));
    }

    public static Specification<PetEntity> hasBreed(SearchStringObject breed){
        return (((root, query, criteriaBuilder) -> getStringCriteria("breed", breed, criteriaBuilder, root)));
    }

    public static Specification<PetEntity> hasGender(SearchStringObject gender){
        return (((root, query, criteriaBuilder) -> getStringCriteria("gender", gender, criteriaBuilder, root)));
    }

    public static Specification<PetEntity> hasBirthDate(SearchDateObject birthDate) {
        return ((root, query, cb) -> getDateTimeCriteria("birthDate", birthDate, cb, root));
    }

    public static Specification<PetEntity> hasWeight(Collection<Integer> weight){
        return in("weight", weight);
    }

    public static Specification<PetEntity> hasColor(SearchStringObject color){
        return (((root, query, criteriaBuilder) -> getStringCriteria("color", color, criteriaBuilder, root)));
    }


    public static Specification<PetEntity> hasLastVisitDate(SearchDateObject lastVisitDate) {
        return ((root, query, cb) -> getDateTimeCriteria("lastVisitDate", lastVisitDate, cb, root));
    }


    public static Specification<PetEntity> hasRegistrationDate(SearchDateObject registrationDate) {
        return ((root, query, cb) -> getDateTimeCriteria("registrationDate", registrationDate, cb, root));
    }

    public static Specification<PetEntity> hasCheapId(Collection<UUID> cheapId){
        return in("cheapId", cheapId);
    }

    public static Specification<PetEntity> hasCollarId(Collection<UUID> collarId){
        return in("collarId", collarId);
    }

    public static Specification<PetEntity> hasOwnerId(Collection<UUID> ownerId){
        return in("ownerId", ownerId);
    }
}
