package com.devmountain.WalletWatchApp.services;

import com.devmountain.WalletWatchApp.dtos.ExpenseDto;
import com.devmountain.WalletWatchApp.entities.Expense;
import com.devmountain.WalletWatchApp.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;



    public List<ExpenseDto> getAllExpenses(){
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().map(expense -> new ExpenseDto(expense)).collect(Collectors.toList());
    }



    @Transactional
    public void addExpense(ExpenseDto expenseDto){

        Expense expense = new Expense(expenseDto);
        expenseRepository.saveAndFlush(expense);


    }


    @Override
    @Transactional
    public void deleteExpenseById(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        expenseOptional.ifPresent(expense -> expenseRepository.delete(expense));
    }

    @Override
    public void updateExpenseById(ExpenseDto expenseDto) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseDto.getId());
        expenseOptional.ifPresent(expense -> {
            expense.setTitle(expenseDto.getTitle());
            expense.setCategory(expenseDto.getCategory());
            expense.setPaymentAmount(expenseDto.getPaymentAmount());
            expense.setDate(expenseDto.getDate());
            expenseRepository.saveAndFlush(expense);
        });
    }


}