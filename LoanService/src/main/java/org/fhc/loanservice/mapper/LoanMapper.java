package org.fhc.loanservice.mapper;

import org.fhc.loanservice.dto.LoanDto;
import org.fhc.loanservice.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan) {
        //LoanDto loanDto = LoanMapper.mapToLoanDto(loan);
        return new LoanDto(loan.getMobileNumber(), loan.getLoanNumber(), loan.getLoanType(), loan.getTotalLoan(), loan.getAmountPaid(), loan.getOutstandingAmount());
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setMobileNumber(loanDto.mobileNumber());
        loan.setLoanNumber(loanDto.loanNumber());
        loan.setLoanType(loanDto.loanType());
        loan.setTotalLoan(loanDto.totalLoan());
        loan.setAmountPaid(loanDto.amountPaid());
        loan.setOutstandingAmount(loanDto.outstandingAmount());
        return loan;
    }
}
