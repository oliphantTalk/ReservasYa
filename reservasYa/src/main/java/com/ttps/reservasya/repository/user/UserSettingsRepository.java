package com.ttps.reservasya.repository.user;

import com.ttps.reservasya.models.user.settings.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
}
