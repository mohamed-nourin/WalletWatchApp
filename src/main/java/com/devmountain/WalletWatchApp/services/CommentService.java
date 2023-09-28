package com.devmountain.WalletWatchApp.services;

import com.devmountain.WalletWatchApp.dtos.CommentDto;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentService {
    @Transactional
    List<CommentDto> getAllComments(Long expenseId);

    @Transactional
    void addComment(CommentDto commentDto, Long userId, Long expenseId);

    @Transactional
    void deleteComment(Long commentId);
}