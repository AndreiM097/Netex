package com.company.Netex.solrjConection;

import com.company.Netex.model.Movie;
import com.company.Netex.model.QMovie;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@Component()
public class SolrJConnection {
    private static final String SOLR_CORE_URL = "http://localhost:8983/solr/MovieCoreSolr";
    private static final SolrClient solrClient = getSolrClient();

    public SolrJConnection() {
        try{
            List<Movie> movieList = jpaQueryFactory().selectFrom(QMovie.movie).fetch();
            System.out.printf("Indexing %d movies...\n", movieList.size());
            solrClient.addBeans(movieList);

            solrClient.commit();

            System.out.printf("%d movies indexed.\n", movieList.size());
        }catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing movies: %s", e.getMessage());
        }
    }

    private static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(SOLR_CORE_URL).withConnectionTimeout(5000).withSocketTimeout(3000).build();
    }

    public JPAQueryFactory jpaQueryFactory(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("movies");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JPAQueryFactory(entityManager);
    }
}
