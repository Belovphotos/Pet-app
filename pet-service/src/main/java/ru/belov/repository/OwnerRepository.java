package ru.belov.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.belov.base.repository.JpaBaseRepository;
import ru.belov.dto.owner.FullOwnerDto;
import ru.belov.entity.OwnerEntity;

import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaBaseRepository<OwnerEntity, UUID> {
    OwnerEntity findOwnerEntityById(UUID ownerId);
}
