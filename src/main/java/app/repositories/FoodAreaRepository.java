package app.repositories;

import org.springframework.transaction.annotation.Transactional;

import app.models.FoodArea;

@Transactional
public interface FoodAreaRepository extends BaseRepository<FoodArea> { }