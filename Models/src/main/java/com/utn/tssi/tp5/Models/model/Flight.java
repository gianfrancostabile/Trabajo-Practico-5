package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Flights")
@NoArgsConstructor
public class Flight implements ValidationInterface<Flight>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id_Route", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Route route;

    @Column(name = "date_Flight")
    private String date;

    public Flight(long id, Route route, String date) {
        this.id = id;
        this.route = route;
        this.date = date;
    }

    public Flight(Route route, String date) {
        this.route = route;
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "route=" + route +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Flight)) return false;

        Flight flight = (Flight) o;
        return this.id == flight.getId() && this.route.equals(flight.getRoute()) && this.date.equals(flight.getDate());
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.route == null) ? 0 : this.route.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());

        return hash;
    }

    public boolean validateNullEmpty() {
        boolean bool = true;

        if(id >= 0 && route != null && !(route.validateNullEmpty()) && date != null && !(date.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }

    public boolean validateNullEmptyIdentifier() {
        boolean bool = true;

        if(route != null && !(route.validateNullEmptyIdentifier()) && date != null && !(date.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }

}
