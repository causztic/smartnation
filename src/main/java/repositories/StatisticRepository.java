package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import models.Statistic;

@RepositoryRestResource
public interface StatisticRepository extends PagingAndSortingRepository<Statistic, Long>{
			
		@Query("SELECT s.* FROM statistic s WHERE (SELECT a.id FROM area a WHERE a.name = :area")
		List<Statistic> findByArea(@Param("area") String area);
}
