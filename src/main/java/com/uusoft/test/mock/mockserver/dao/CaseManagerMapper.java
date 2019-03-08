package com.uusoft.test.mock.mockserver.dao;

import com.uusoft.test.mock.mockserver.model.CaseManager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author minglu
 */
@Mapper
@Repository
public interface CaseManagerMapper {

  int deleteByPrimaryKey(String caseName);

  int insert(CaseManager record);

  int insertSelective(CaseManager record);

  CaseManager selectByPrimaryKey(String caseName);

  int updateByPrimaryKeySelective(CaseManager record);

  int updateByPrimaryKey(CaseManager record);
}