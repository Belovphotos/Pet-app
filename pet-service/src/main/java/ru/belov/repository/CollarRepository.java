package ru.belov.repository;

import org.springframework.stereotype.Repository;
import ru.belov.base.repository.JpaBaseRepository;
import ru.belov.entity.CollarEntity;

import java.util.UUID;
@Repository
public interface CollarRepository extends JpaBaseRepository<CollarEntity, UUID> {
}
