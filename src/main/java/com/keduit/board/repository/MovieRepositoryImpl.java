package com.keduit.board.repository;

import com.keduit.board.entity.Movie;
import com.keduit.board.entity.QMovie;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepositoryCustom{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Movie> findRecentMovies() {
        QMovie movie = QMovie.movie;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        return queryFactory.selectFrom(movie)
                .orderBy(movie.releaseDate.desc())
                .limit(20)
                .fetch();

    }
}
