package ru.belov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.base.repository.JpaBaseRepository;
import ru.belov.entity.PetEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaBaseRepository<PetEntity, UUID> {
    List<PetEntity> findAllByOwnerId(UUID ownerId);
}
