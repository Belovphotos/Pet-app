package ru.belov.repository;

import org.springframework.stereotype.Repository;
import ru.common.repository.JpaBaseRepository;
import ru.belov.entity.OwnerEntity;

import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaBaseRepository<OwnerEntity, UUID> {
    OwnerEntity findOwnerEntityById(UUID ownerId);
}
