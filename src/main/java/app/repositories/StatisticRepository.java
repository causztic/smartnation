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
				value = "SELECT * FROM statistic WHERE area_id = (SELECT a.id FROM area a WHERE a.name = :area)",
				nativeQuery = true)
		List<Statistic> findByArea(@Param("area") String area);
}
