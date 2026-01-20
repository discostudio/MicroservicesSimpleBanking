package com.fhc.AccountsService.service.impl;

import com.fhc.AccountsService.constants.AccountsConstants;
import com.fhc.AccountsService.dto.AccountsDto;
import com.fhc.AccountsService.dto.CustomerDto;
import com.fhc.AccountsService.entity.Accounts;
import com.fhc.AccountsService.entity.Customer;
import com.fhc.AccountsService.exception.CustomerAlreadyExistsException;
import com.fhc.AccountsService.exception.ResourceNotFoundException;
import com.fhc.AccountsService.mapper.AccountsMapper;
import com.fhc.AccountsService.mapper.CustomerMapper;
import com.fhc.AccountsService.repository.IAccountsRepository;
import com.fhc.AccountsService.repository.ICustomerRepository;
import com.fhc.AccountsService.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private IAccountsRepository accountsRepository;
    private ICustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent())
            throw new CustomerAlreadyExistsException("Customer already exists with given mobile number" + customerDto.mobileNumber());

        //customer.setCreatedAt(LocalDateTime.now());
        //customer.setCreatedBy("TESTE");

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        //newAccount.setCreatedAt(LocalDateTime.now());
        //newAccount.setCreatedBy("TESTE");

        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        return CustomerMapper.mapToCustomerDto(customer, account);
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.accountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.accountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account", "Account Number", accountsDto.accountNumber().toString())
                    );

            AccountsMapper.mapToAccounts(accountsDto, accounts);

            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
                    );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
