package com.example.todo_backend.infrastructure.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {
    @Select("SELECT 1")
    Integer selectOne();
}