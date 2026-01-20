package org.fhc.loanservice.repository;

import org.fhc.loanservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByLoanNumber(String loanNumber);
    Optional<Loan> findByMobileNumber(String mobileNumber);
}
