package com.myblog_mithu.repository;

import com.myblog_mithu.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
