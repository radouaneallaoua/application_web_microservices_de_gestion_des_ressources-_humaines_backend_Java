package com.example.employeservice.repository;

import com.example.employeservice.entities.Notification;
import com.example.employeservice.enums.EtatNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByEmployeIdAndEtatNotification(String employeId, EtatNotification etatNotification);
    List<Notification> findAllByEmployeIdOrderByDateDesc(String employeId);
    List<Notification> findAllByEmployeIdAndDateAfter(String employeId, LocalDateTime date);
    List<Notification> findAllByEmployeIdAndDateBefore(String employeId, LocalDateTime date);
}
