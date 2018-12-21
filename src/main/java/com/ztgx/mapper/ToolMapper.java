package com.ztgx.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.*;
@Mapper
@Component(value = "toolMapper")
public interface ToolMapper {

    List<Map<String, Object>> findAllTables(Map<String, Object> map);
    List<Map<String, Object>> findTableByTablename(Map<String, Object> map);
    int UpdateComment(Map<String, Object> map);
    int UpdateTableComment(Map<String, Object> map);
}
