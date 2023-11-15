package hh.sof03.karaokeRooms.Domain;


import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JsonIgnoreProperties("users")
    @JoinColumn(name = "userId")
    private User user;
    
    @ManyToOne
    @JsonIgnoreProperties("reservations")
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    private Room room;

    public Reservation(Date date, LocalTime startTime, LocalTime endTime, Room room, User user) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.user = user;
    }

    public Reservation() {
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.room = null;
        this.user = null;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation [date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", user=" + user
                + ", room=" + room + "]";
    }
    
}
