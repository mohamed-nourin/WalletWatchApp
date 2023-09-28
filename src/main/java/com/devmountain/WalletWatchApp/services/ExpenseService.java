package com.devmountain.WalletWatchApp.services;

import com.devmountain.WalletWatchApp.dtos.ExpenseDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ExpenseService {


    List<ExpenseDto> getAllExpenses();


    @Transactional
    void addExpense(ExpenseDto expenseDto);

    @Transactional
    void deleteExpenseById(Long expenseId);

    @Transactional
    void updateExpenseById(ExpenseDto expenseDto);
}