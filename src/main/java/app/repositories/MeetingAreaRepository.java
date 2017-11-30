package app.repositories;

import org.springframework.transaction.annotation.Transactional;

import app.models.Area;

@Transactional
public interface MeetingAreaRepository extends BaseRepository<Area> { }