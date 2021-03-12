package com.jukusoft.letterbox.dao;

import com.jukusoft.letterbox.entity.general.GlobalSettingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalSettingDAO extends PagingAndSortingRepository<GlobalSettingEntity, String> {

    //

}
