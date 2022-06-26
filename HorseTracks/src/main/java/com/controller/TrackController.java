/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.dao.TrackDao;
import com.entity.Track;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mikeh
 */
@Controller
public class TrackController {

    @Autowired
    TrackDao trackDao;

    @PostMapping("addTrack")
    public String addTrack(HttpServletRequest request) {
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String owner = request.getParameter("owner");

        Track track = new Track();
        track.setName(name);
        track.setCity(city);
        track.setState(state);
        track.setZip(zip);
        track.setOwner(owner);
        trackDao.addTrack(track);

        return "redirect:/tracks";
    }

    @GetMapping("tracks")
    public String displayTrack(Model model) {
        List<Track> track = trackDao.getAllTracks();
        model.addAttribute("tracks", track);
        return "tracks";
    }

    @GetMapping("details")
    public String displayDetails(Model model) {
        List<Track> track = trackDao.getAllTracks();
        model.addAttribute("tracks", track);
        return "details";
    }

    @GetMapping("deleteTrack")
    public String deleteTrack(Integer id) {
        trackDao.deleteTrackById(id);
        return "redirect:/tracks";
    }

    @GetMapping("editTrack")
    public String editTrack(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Track track = trackDao.getTrackById(id);

        model.addAttribute("track", track);
        return "editTrack";
    }

    @PostMapping("editTrack")
    public String performEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Track track = trackDao.getTrackById(id);

        track.setName(request.getParameter("name"));
        track.setCity(request.getParameter("city"));
        track.setState(request.getParameter("state"));
        track.setZip(Integer.parseInt(request.getParameter("zip")));
        track.setOwner(request.getParameter("owner"));

        trackDao.updateTrack(track);

        return "redirect:/tracks";
    }

}
