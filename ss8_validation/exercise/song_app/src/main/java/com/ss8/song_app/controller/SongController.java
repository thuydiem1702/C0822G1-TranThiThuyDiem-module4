package com.ss8.song_app.controller;

import com.ss8.song_app.dto.SongDto;
import com.ss8.song_app.model.Song;
import com.ss8.song_app.service.ISongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping("/list")
    public String showListSong(Model model) {

        Iterable<Song> songList = songService.findAll();

        List<SongDto> songDtoList = new ArrayList<>();

        for (Song x : songList) {
            songDtoList.add(new SongDto(x.getId(), x.getName(), x.getPerformer(), x.getGenre()));
        }

        model.addAttribute("songList", songDtoList);

        return "song/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        model.addAttribute("songNew", new SongDto());

        return "/song/create";
    }

    @PostMapping("/create")
    public String createSong(@Validated @ModelAttribute(value = "songNew") SongDto songNew,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {


        new SongDto().validate(songNew, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/song/create";
        }

        Song song = new Song();
        BeanUtils.copyProperties(songNew, song);

        songService.save(song);

        redirectAttributes.addFlashAttribute("mess", "New song created successfully!");

        return "redirect:/song/list";
    }

    @GetMapping("/edit-song/{idEdit}")
    public String showEditForm(Model model, @PathVariable Integer idEdit) {
        Optional<Song> songEdit = songService.findById(idEdit);

        if (songEdit.isPresent()) {
            SongDto songDtoEdit = new SongDto();
            BeanUtils.copyProperties(songEdit.get(), songDtoEdit);
            model.addAttribute("songEdit", songDtoEdit);
            return "/song/formEdit";
        } else {
            return "/song/error";
        }
    }

    @PostMapping("/edit")
    public String editBlog(@Validated @ModelAttribute(value = "songEdit") SongDto songEdit,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        new SongDto().validate(songEdit, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/song/formEdit";
        }

        Song song = new Song();
        BeanUtils.copyProperties(songEdit, song);

        songService.save(song);

        redirectAttributes.addFlashAttribute("mess", "Song Edited successfully!");

        return "redirect:/song/list";
    }

}
