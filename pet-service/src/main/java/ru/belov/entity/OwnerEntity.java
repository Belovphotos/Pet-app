package ru.belov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.belov.base.entity.BaseEntity;
@Entity
@Table(catalog = "postgres", schema = "public", name = "owners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OwnerEntity extends BaseEntity {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
