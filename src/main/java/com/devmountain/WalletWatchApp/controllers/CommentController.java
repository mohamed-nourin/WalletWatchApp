//package com.devmountain.WalletWatchApp.controllers;
//
//import com.devmountain.WalletWatchApp.dtos.CommentDto;
//import com.devmountain.WalletWatchApp.services.CommentService;
//import com.devmountain.WalletWatchApp.services.ExpenseService;
//import com.devmountain.WalletWatchApp.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/comments")
//public class CommentController {
//
//    @Autowired
//    private ExpenseService expenseService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private CommentService commentService;
//
//    @GetMapping("/expense/{expenseId}")
//    public List<CommentDto> getAllComments(@PathVariable Long expenseId){
//        return commentService.getAllComments(expenseId);
//    }
//
//    @PostMapping("{userId}/expense/{expenseId}")
//    public void addComment(@RequestBody CommentDto commentDto, @PathVariable Long userId, @PathVariable Long expenseId){
//        commentService.addComment(commentDto,userId,expenseId);
//    }
//    @DeleteMapping("/{commentId}")
//    public void deleteComment(@PathVariable Long commentId){
//        commentService.deleteComment(commentId);
//    }
//}