package com.jukusoft.letterbox.dao;

import com.jukusoft.letterbox.entity.user.PermissionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDAO extends PagingAndSortingRepository<PermissionEntity, String> {
}
