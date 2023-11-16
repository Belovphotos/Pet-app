package ru.belov.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;
@NoRepositoryBean
public interface JpaBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    default Page<T> findAll(Pageable pageable){
        return findAll(pageable);
    }

    @Override
    default Optional<T> findById(ID id) {
        return findById(id);
    }
}
