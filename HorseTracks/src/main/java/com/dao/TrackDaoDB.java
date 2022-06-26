/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.Track;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mikeh
 */
@Repository
public class TrackDaoDB implements TrackDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Track addTrack(Track track) {
        final String INSERT_TRACK = "INSERT INTO tracks(name,city,state,zip,owner) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_TRACK,
                track.getName(),
                track.getCity(),
                track.getState(),
                track.getZip(),
                track.getOwner());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        track.setId(newId);
        return track;
    }

    @Override
    public Track getTrackById(int id) {
        try {
            final String SELECT_TRACK_BY_ID = "SELECT * FROM tracks WHERE id = ?";
            return jdbc.queryForObject(SELECT_TRACK_BY_ID, new TrackMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Track> getAllTracks() {
        final String SELECT_ALL_TRACKS = "SELECT * FROM tracks";
        return jdbc.query(SELECT_ALL_TRACKS, new TrackMapper());
    }

    @Override
    public void deleteTrackById(int id) {
        final String DELETE_TRACK = "DELETE FROM tracks WHERE id = ?";
        jdbc.update(DELETE_TRACK, id);
    }

    @Override
    public void updateTrack(Track track) {
        final String UPDATE_TRACK = "UPDATE tracks SET name = ?, city = ?, state = ?, zip = ?, owner = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_TRACK,
                track.getName(),
                track.getCity(),
                track.getState(),
                track.getZip(),
                track.getOwner(),
                track.getId());

    }

    public static final class TrackMapper implements RowMapper<Track> {

        @Override
        public Track mapRow(ResultSet rs, int index) throws SQLException {
            Track track = new Track();
            track.setId(rs.getInt("id"));
            track.setName(rs.getString("name"));
            track.setCity(rs.getString("city"));
            track.setState(rs.getString("state"));
            track.setZip(rs.getInt("zip"));
            track.setOwner(rs.getString("owner"));

            return track;
        }
    }

}
