package app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import app.models.Area;

@NoRepositoryBean
public interface BaseRepository<T extends Area> 
extends CrudRepository<T, Long> {
	  T findOne(Long id);
	  Iterable<T> findAll();
	  Iterable<T> findAll(Sort sort);
	  Page<T> findAll(Pageable pageable);
}