package com.linku.backend.domain.icon.repository;

import com.linku.backend.domain.icon.Icon;
import com.linku.backend.domain.common.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IconRepository extends JpaRepository<Icon, Long> {

    List<Icon> findAllByOwner_UserIdAndStatus(Long userId, Status status);

    Optional<Icon> findByIconIdAndStatus(Long iconId, Status status);
}