package com.devmountain.WalletWatchApp.controllers;

import com.devmountain.WalletWatchApp.dtos.ExpenseDto;
import com.devmountain.WalletWatchApp.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expense")
    public List<ExpenseDto> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @PostMapping("/expense")
    public void addExpense(@RequestBody ExpenseDto expenseDto){
        expenseService.addExpense(expenseDto);
    }

    @DeleteMapping("/expense/{expenseId}")
    public void deleteExpenseById(@PathVariable Long expenseId){
        expenseService.deleteExpenseById(expenseId);
    }

    @PutMapping("/expense")
    public void updateExpenseById(@RequestBody ExpenseDto expenseDto){
        expenseService.updateExpenseById(expenseDto);
    }


}