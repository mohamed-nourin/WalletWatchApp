//package com.devmountain.WalletWatchApp.services;
//
//import com.devmountain.WalletWatchApp.dtos.CommentDto;
//import com.devmountain.WalletWatchApp.entities.Comment;
//import com.devmountain.WalletWatchApp.entities.Expense;
//import com.devmountain.WalletWatchApp.entities.User;
//import com.devmountain.WalletWatchApp.repositories.CommentRepository;
//import com.devmountain.WalletWatchApp.repositories.ExpenseRepository;
//import com.devmountain.WalletWatchApp.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class CommentServiceImpl implements CommentService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ExpenseRepository expenseRepository;
//    @Autowired
//    private CommentRepository commentRepository;
//    @Override
//    public List<CommentDto> getAllComments(Long expenseId) {
//        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
//        if(expenseOptional.isPresent()){
//            List<Comment> commentList = commentRepository.findAllByExpenseEquals(expenseOptional.get());
//            return commentList.stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
//
//    @Override
//    @Transactional
//    public void addComment(CommentDto commentDto, Long userId, Long expenseId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
//        Comment comment = new Comment(commentDto);
//
//        if(expenseOptional.isPresent()){
//            if(userOptional.isPresent()){
//                comment.setUser(userOptional.get());
//                comment.setExpense(expenseOptional.get());
//            }
//        }
//        commentRepository.saveAndFlush(comment);
//
//    }
//
//    @Override
//    @Transactional
//    public void deleteComment(Long commentId) {
//        Optional<Comment> commentOptional = commentRepository.findById(commentId);
//        if(commentOptional.isPresent()){
//            commentRepository.delete(commentOptional.get());
//        }
//    }
//}