package org.fhc.loanservice.service.impl;

import lombok.AllArgsConstructor;
import org.fhc.loanservice.constants.LoanConstants;
import org.fhc.loanservice.dto.LoanDto;
import org.fhc.loanservice.entity.Loan;
import org.fhc.loanservice.exception.LoanAlreadyExistsException;
import org.fhc.loanservice.exception.ResourceNotFoundException;
import org.fhc.loanservice.mapper.LoanMapper;
import org.fhc.loanservice.repository.ILoanRepository;
import org.fhc.loanservice.service.ILoanService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private ILoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans = loanRepository.findByLoanNumber(mobileNumber);

        if (optionalLoans.isPresent())
            throw new LoanAlreadyExistsException("Loan already exist with given mobile number " + mobileNumber);

        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextLong(90000000000L);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {

        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
                );
        return LoanMapper.mapToLoanDto(loan);
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.loanNumber())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Loan", "loanNumber", loanDto.loanNumber())
                );

        LoanMapper.mapToLoan(loanDto, loan);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
                );

        loanRepository.deleteById(loan.getLoanId());

        return true;
    }
}
