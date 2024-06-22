package com.example.demo.Repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.demo.Entity.UserRecord;

public interface UserRecordRepository extends  JpaRepositoryImplementation<UserRecord, Integer> {

}
