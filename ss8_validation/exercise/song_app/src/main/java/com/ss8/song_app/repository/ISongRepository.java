package com.ss8.song_app.repository;

import com.ss8.song_app.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Song, Integer> {
}
