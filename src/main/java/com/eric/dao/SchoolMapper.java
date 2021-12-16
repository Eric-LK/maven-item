package com.eric.dao;

import com.eric.entity.School;


public interface SchoolMapper {

    School selectSchoolById(String id);

    School selectSchoolCollectionByName(String name);

    School selectSchoolFenBu(String id);
}
