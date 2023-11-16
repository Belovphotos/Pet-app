package ru.common;

import java.io.Serializable;
import java.util.UUID;

public interface Identifiable extends Serializable {
    UUID getId();

    void setId();
}
