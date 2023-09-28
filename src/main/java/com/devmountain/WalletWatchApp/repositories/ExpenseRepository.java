package com.devmountain.WalletWatchApp.repositories;

import com.devmountain.WalletWatchApp.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}