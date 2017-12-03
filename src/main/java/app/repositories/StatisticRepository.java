package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import app.models.Statistic;

@RepositoryRestResource
public interface StatisticRepository extends PagingAndSortingRepository<Statistic, Long>{
		
		@RestResource
		@Query(
				value = "SELECT * FROM statistic WHERE area_id = :area",
				nativeQuery = true)
		List<Statistic> findByArea(@Param("area") int area);
		
		@RestResource
		@Query(
				value = "SELECT * FROM statistic WHERE data_date BETWEEN :to AND :from ",
				nativeQuery = true)
		List<Statistic> findBetweenDates(@Param("to") String to, @Param("from") String from);
}
