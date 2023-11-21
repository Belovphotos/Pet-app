package ru.belov.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ru.belov.base.entity.BaseEntity;

import java.util.UUID;

@Entity
@Table(catalog = "postgres", schema = "public", name = "collars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollarEntity extends BaseEntity {
    private UUID ownerId;
    private UUID petId;
}
