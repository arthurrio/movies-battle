package com.letscode.moviesbattle.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.letscode.moviesbattle.MoviesBattleApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = MoviesBattleApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:sql/erase-data.sql"})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MoviesBattleSpringBootTest {
}
