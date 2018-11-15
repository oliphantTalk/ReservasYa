package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.users.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
}
