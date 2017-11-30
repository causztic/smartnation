package app.repositories;

import org.springframework.transaction.annotation.Transactional;

import app.models.MeetingArea;

@Transactional
public interface MeetingAreaRepository extends BaseRepository<MeetingArea> { }