//package com.devmountain.WalletWatchApp.dtos;
//
//import com.devmountain.WalletWatchApp.entities.Comment;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CommentDto {
//    private Long id;
//    private String comment;
//
//    private UserDto userDto;
//    private ExpenseDto expenseDto;
//
//    public CommentDto(Comment comment){
//        if (comment.getId() != null) {
//            this.id = comment.getId();
//        }
//        if (comment.getComment() != null) {
//            this.comment = comment.getComment();
//        }
//        if (comment.getUser() != null) {
//            this.userDto = new UserDto(comment.getUser());
//        }
//        if (comment.getExpense() != null) {
//            this.expenseDto = new ExpenseDto((comment.getExpense()));
//        }
//
//    }
//
//}