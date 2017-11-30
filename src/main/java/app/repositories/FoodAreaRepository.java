package app.repositories;

import org.springframework.transaction.annotation.Transactional;

import app.models.Area;

@Transactional
public interface FoodAreaRepository extends BaseRepository<Area> { }