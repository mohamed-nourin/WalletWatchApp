package com.devmountain.WalletWatchApp.entities;

import com.devmountain.WalletWatchApp.dtos.ExpenseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//import java.util.Date;
import java.sql.Date;

@Entity
@Table(name = "Expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    private Double paymentAmount;

    private Date date;

    @ManyToOne
    @JsonBackReference
    private User user;


    public Expense(ExpenseDto expenseDto){
        if (expenseDto.getTitle() != null){
            this.title = expenseDto.getTitle();
        }
        if (expenseDto.getCategory() != null){
            this.category = expenseDto.getCategory();
        }
        if (expenseDto.getPaymentAmount() != 0){
            this.paymentAmount = expenseDto.getPaymentAmount();
        }
        if (expenseDto.getDate() != null){
            this.date = expenseDto.getDate();
        }
    }
}
