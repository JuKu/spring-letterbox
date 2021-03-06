package com.jukusoft.letterbox.dao;

import com.jukusoft.letterbox.entity.user.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends PagingAndSortingRepository<RoleEntity, Long> {

    public boolean existsByName(String name);

    public Optional<RoleEntity> findByName(String name);

}
