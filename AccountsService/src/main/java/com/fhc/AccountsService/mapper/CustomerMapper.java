package com.fhc.AccountsService.mapper;

import com.fhc.AccountsService.dto.AccountsDto;
import com.fhc.AccountsService.dto.CustomerDto;
import com.fhc.AccountsService.entity.Accounts;
import com.fhc.AccountsService.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, Accounts account) {
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(account);
        return new CustomerDto(customer.getName(), customer.getEmail(), customer.getMobileNumber(), accountsDto);
    }

    //public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
    //    accounts.setAccountNumber(accountsDto.accountNumber());
    //    accounts.setAccountType(accountsDto.accountType());
    //    accounts.setBranchAddress(accountsDto.branchAddress());
    //    return accounts;
    //}

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        return customer;
    }
}
