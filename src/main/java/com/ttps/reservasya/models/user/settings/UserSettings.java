package com.ttps.reservasya.models.user.settings;

import com.ttps.reservasya.models.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "USER_SETTINGS")
public class UserSettings implements Serializable {

    private Long id;
    private User user;
    private LocalDateTime lastLogin = LocalDateTime.now();
    private int pointsToUse = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JoinColumn(name = "USER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getPointsToUse() {
        return pointsToUse;
    }

    public void setPointsToUse(int pointsToUse) {
        this.pointsToUse = pointsToUse;
    }
}
