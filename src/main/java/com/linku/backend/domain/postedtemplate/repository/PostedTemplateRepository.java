package com.linku.backend.domain.postedtemplate.repository;

import com.linku.backend.domain.common.enums.Status;
import com.linku.backend.domain.postedtemplate.PostedTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostedTemplateRepository extends JpaRepository<PostedTemplate, Long> {

    Optional<PostedTemplate> findByPostedTemplateIdAndStatus(Long postedTemplateId, Status status);

    Optional<PostedTemplate> findByPostedTemplateIdAndOwner_UserIdAndStatus(Long postedTemplateId, Long userId, Status status);

    @Query("SELECT pt FROM PostedTemplate pt WHERE pt.owner.userId = :userId AND pt.status = :status ORDER BY " +
           "CASE WHEN :sort = 'most-liked' THEN pt.likesCount END DESC, " +
           "CASE WHEN :sort = 'most-used' THEN pt.usageCount END DESC, " +
           "CASE WHEN :sort = 'newest' THEN pt.createdAt END DESC, " +
           "CASE WHEN :sort = 'oldest' THEN pt.createdAt END ASC, " +
           "pt.createdAt DESC")
    List<PostedTemplate> findByOwner_UserIdAndStatusOrderBySort(
            @Param("userId") Long userId, 
            @Param("status") Status status,
            @Param("sort") String sort);

    @Query("SELECT pt FROM PostedTemplate pt WHERE pt.owner.userId = :userId AND pt.status = :status AND (pt.name LIKE %:query% OR pt.owner.name LIKE %:query%) ORDER BY " +
           "CASE WHEN :sort = 'most-liked' THEN pt.likesCount END DESC, " +
           "CASE WHEN :sort = 'most-used' THEN pt.usageCount END DESC, " +
           "CASE WHEN :sort = 'newest' THEN pt.createdAt END DESC, " +
           "CASE WHEN :sort = 'oldest' THEN pt.createdAt END ASC, " +
           "pt.createdAt DESC")
    List<PostedTemplate> findByOwner_UserIdAndStatusAndNameContainingOrOwnerNameContainingOrderBySort(
            @Param("userId") Long userId, 
            @Param("status") Status status, 
            @Param("query") String query, 
            @Param("query") String query2,
            @Param("sort") String sort);

    @Query("SELECT pt FROM PostedTemplate pt WHERE pt.status = :status ORDER BY " +
           "CASE WHEN :sort = 'most-liked' THEN pt.likesCount END DESC, " +
           "CASE WHEN :sort = 'most-used' THEN pt.usageCount END DESC, " +
           "CASE WHEN :sort = 'newest' THEN pt.createdAt END DESC, " +
           "CASE WHEN :sort = 'oldest' THEN pt.createdAt END ASC, " +
           "pt.createdAt DESC")
    List<PostedTemplate> findByStatusOrderBySort(
            @Param("status") Status status,
            @Param("sort") String sort);

    @Query("SELECT pt FROM PostedTemplate pt WHERE pt.status = :status AND (pt.name LIKE %:query% OR pt.owner.name LIKE %:query%) ORDER BY " +
           "CASE WHEN :sort = 'most-liked' THEN pt.likesCount END DESC, " +
           "CASE WHEN :sort = 'most-used' THEN pt.usageCount END DESC, " +
           "CASE WHEN :sort = 'newest' THEN pt.createdAt END DESC, " +
           "CASE WHEN :sort = 'oldest' THEN pt.createdAt END ASC, " +
           "pt.createdAt DESC")
    List<PostedTemplate> findByStatusAndNameContainingOrOwnerNameContainingOrderBySort(
            @Param("status") Status status, 
            @Param("query") String query, 
            @Param("query") String query2,
            @Param("sort") String sort);
}