package com.fhc.AccountsService.mapper;

import com.fhc.AccountsService.dto.AccountsDto;
import com.fhc.AccountsService.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return new AccountsDto(accounts.getAccountNumber(), accounts.getAccountType(), accounts.getBranchAddress());
    }

    //public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
    //    accounts.setAccountNumber(accountsDto.accountNumber());
    //    accounts.setAccountType(accountsDto.accountType());
    //    accounts.setBranchAddress(accountsDto.branchAddress());
    //    return accounts;
    //}

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.accountNumber());
        accounts.setAccountType(accountsDto.accountType());
        accounts.setBranchAddress(accountsDto.branchAddress());
        return accounts;
    }
}
