package com.example.todo_backend.infrastructure.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class TasksDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Tasks tasks = new Tasks();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = tasks.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> title = tasks.title;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = tasks.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> status = tasks.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> dueDate = tasks.dueDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Tasks extends AliasableSqlTable<Tasks> {
        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("TITLE", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("DESCRIPTION", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("STATUS", JDBCType.VARCHAR);

        public final SqlColumn<Date> dueDate = column("DUE_DATE", JDBCType.DATE);

        public Tasks() {
            super("TASKS", Tasks::new);
        }
    }
}