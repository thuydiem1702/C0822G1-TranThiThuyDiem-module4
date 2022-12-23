package com.ss8.song_app.service.impl;

import com.ss8.song_app.model.Song;
import com.ss8.song_app.repository.ISongRepository;
import com.ss8.song_app.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService implements ISongService {

    @Autowired
    ISongRepository songRepository;


    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Integer id) {
        return songRepository.findById(id);
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public void deleteBlog(Integer id) {

    }
}
