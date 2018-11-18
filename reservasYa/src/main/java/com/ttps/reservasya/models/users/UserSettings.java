package com.ttps.reservasya.models.users;

import com.ttps.reservasya.models.UserHistory;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_SETTINGS")
public class UserSettings implements Serializable {

    private Long id;
    private User user;
    private List<UserHistory> historyList = new ArrayList<>();
    private LocalDateTime lastLogin = LocalDateTime.now();
    private BigDecimal pointsToUse = BigDecimal.valueOf(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JoinColumn(name = "USER_ID")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ElementCollection(targetClass = UserHistory.class)
    public List<UserHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<UserHistory> historyList) {
        this.historyList = historyList;
    }


    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public BigDecimal getPointsToUse() {
        return pointsToUse;
    }

    public void setPointsToUse(BigDecimal pointsToUse) {
        this.pointsToUse = pointsToUse;
    }
}
