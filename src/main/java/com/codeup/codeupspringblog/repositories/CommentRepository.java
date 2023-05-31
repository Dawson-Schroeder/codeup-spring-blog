package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
