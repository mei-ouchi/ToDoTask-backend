package com.example.todo_backend.infrastructure.mapper;

import static com.example.todo_backend.infrastructure.mapper.TasksDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.example.todo_backend.domain.model.Tasks;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface TasksMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Tasks>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, title, description, status, dueDate);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TasksResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="DUE_DATE", property="dueDate", jdbcType=JdbcType.DATE)
    })
    List<Tasks> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TasksResult")
    Optional<Tasks> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, tasks, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, tasks, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Tasks row) {
        return MyBatis3Utils.insert(this::insert, row, tasks, c ->
            c.map(id).toProperty("id")
            .map(title).toProperty("title")
            .map(description).toProperty("description")
            .map(status).toProperty("status")
            .map(dueDate).toProperty("dueDate")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Tasks> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, tasks, c ->
            c.map(id).toProperty("id")
            .map(title).toProperty("title")
            .map(description).toProperty("description")
            .map(status).toProperty("status")
            .map(dueDate).toProperty("dueDate")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Tasks row) {
        return MyBatis3Utils.insert(this::insert, row, tasks, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(dueDate).toPropertyWhenPresent("dueDate", row::getDueDate)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Tasks> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, tasks, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Tasks> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, tasks, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Tasks> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, tasks, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Tasks> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, tasks, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Tasks row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(title).equalTo(row::getTitle)
                .set(description).equalTo(row::getDescription)
                .set(status).equalTo(row::getStatus)
                .set(dueDate).equalTo(row::getDueDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Tasks row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(description).equalToWhenPresent(row::getDescription)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(dueDate).equalToWhenPresent(row::getDueDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Tasks row) {
        return update(c ->
            c.set(title).equalTo(row::getTitle)
            .set(description).equalTo(row::getDescription)
            .set(status).equalTo(row::getStatus)
            .set(dueDate).equalTo(row::getDueDate)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Tasks row) {
        return update(c ->
            c.set(title).equalToWhenPresent(row::getTitle)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(dueDate).equalToWhenPresent(row::getDueDate)
            .where(id, isEqualTo(row::getId))
        );
    }
}