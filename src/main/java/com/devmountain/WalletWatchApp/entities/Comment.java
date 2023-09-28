package com.devmountain.WalletWatchApp.entities;

import com.devmountain.WalletWatchApp.dtos.CommentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    @JsonBackReference
    private Expense expense;


    public Comment(CommentDto commentDto){
        if(commentDto.getComment() != null){
            this.comment = commentDto.getComment();
        }
    }




}