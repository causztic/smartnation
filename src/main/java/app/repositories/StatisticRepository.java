package app.repositories;

import java.sql.Timestamp;
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
				value = "SELECT * FROM statistic WHERE area_id = :area AND data_date BETWEEN :from AND :to ",
				nativeQuery = true)
		List<Statistic> findBetweenDates(@Param("area") int area, @Param("from") Timestamp from, @Param("to") Timestamp to);
		
		@RestResource
		@Query(
				value = "SELECT AVG(count) as avg_count, MIN(data_date) as date, date_part(:part, data_date) as grp"
						+ " FROM statistic WHERE area_id = :area "
						+ " AND data_date BETWEEN :from AND :to "
						+ " GROUP BY grp ORDER BY date",
				nativeQuery = true)
		List<Object[]> averagePerHour(@Param("area") int area, 
				@Param("from") Timestamp from,
				@Param("to") Timestamp to, 
				@Param("part") String part);
}