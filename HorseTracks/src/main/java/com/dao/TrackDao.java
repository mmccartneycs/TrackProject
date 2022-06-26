/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dao;

import com.entity.Track;
import java.util.List;

/**
 *
 * @author mikeh
 */
public interface TrackDao {
    
    public Track getTrackById(int id);
    
    List<Track> getAllTracks();
    
    Track addTrack(Track track);
    
    void deleteTrackById(int id);
    
    void updateTrack(Track track);

}
