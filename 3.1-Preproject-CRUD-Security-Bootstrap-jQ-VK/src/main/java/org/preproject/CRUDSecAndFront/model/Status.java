package org.preproject.CRUDSecAndFront.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="status")
//@Data
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "users_status")
    private boolean statusUpdate;
    @JsonIgnore
    @OneToMany(mappedBy = "status", cascade = {CascadeType.PERSIST, CascadeType.MERGE})//cascade = CascadeType.ALL REMOVE
    private Set<User> users;

    public Status(boolean statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    public boolean isStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(boolean statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "\nStatus{" +
                "id=" + id +
                ", statusUpdate=" + statusUpdate +
                '}';
    }
}
