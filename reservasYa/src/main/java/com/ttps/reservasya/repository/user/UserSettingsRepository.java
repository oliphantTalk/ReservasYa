package com.ttps.reservasya.repository.user;

import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.models.user.settings.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
    Optional<UserSettings> findByUser(User user);
}
