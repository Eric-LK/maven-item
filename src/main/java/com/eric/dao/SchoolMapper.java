package com.eric.dao;

import com.eric.mybatis.model.School;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolMapper {

    School selectSchoolById(String id);

    School selectSchoolCollectionByName(String name);

    School selectSchoolFenBu(String id);
}
