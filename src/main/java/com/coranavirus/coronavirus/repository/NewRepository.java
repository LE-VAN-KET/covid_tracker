package com.coranavirus.coronavirus.repository;

import com.coranavirus.coronavirus.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<News, String> {
}
