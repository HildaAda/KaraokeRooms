package hh.sof03.karaokeRooms.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;

@Entity
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    private String name;
    private int size;
    private double price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    @JsonIgnoreProperties("room")
    private List<Reservation> reservations;

    public Room(String name, int size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public Room() {
        this.name = null;
        this.size = 0;
        this.price = 0;
    }
    
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    } 

    @Override
    public String toString() {
        return "Room [roomId=" + roomId + ", name=" + name + ", size=" + size + ", price=" + price + "]";
    } 

}
