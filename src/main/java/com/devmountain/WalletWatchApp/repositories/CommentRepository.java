package com.devmountain.WalletWatchApp.repositories;

import com.devmountain.WalletWatchApp.entities.Comment;
import com.devmountain.WalletWatchApp.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByExpenseEquals(Expense expense);
}