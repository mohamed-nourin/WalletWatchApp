package com.devmountain.WalletWatchApp.dtos;

import com.devmountain.WalletWatchApp.entities.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto implements Serializable {
    private Long id;
    private String title;
    private String category;
    private Double paymentAmount;
    private Date date;

    private UserDto userDto;

    public ExpenseDto(Expense expense){
        if(expense.getId() != null){
            this.id = expense.getId();
        }
        if(expense.getTitle() != null){
            this.title = expense.getTitle();
        }
        if(expense.getCategory() != null){
            this.category = expense.getCategory();
        }
        if(expense.getPaymentAmount() != 0){
            this.paymentAmount = expense.getPaymentAmount();
        }
        if(expense.getDate() != null){
            this.date = expense.getDate();
        }
    }

}
