package io.upschool.service;

import io.upschool.dto.AirplaneSaveRequest;
import io.upschool.dto.AirplaneSaveResponse;
import io.upschool.dto.CustomerSaveRequest;
import io.upschool.dto.CustomerSaveResponse;
import io.upschool.entity.*;
import io.upschool.exception.AirplaneReferenceException;
import io.upschool.exception.CustomerCheckException;
import io.upschool.exception.TicketfindAllByTicketCodeException;
import io.upschool.repository.CustomerRepository;
import io.upschool.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerSaveResponse save(CustomerSaveRequest customerSaveRequest) {
        List<Customer> checkCustomer=customerRepository.findAllByIdentityNo(customerSaveRequest.getIdentityNo());
        if (checkCustomer.size()>0)
            throw  new CustomerCheckException("Girilen TCKN'ye ait bir müşteri mevcut.Lütfen bilgilerinizi kontrol edin.");
        var newCustomer = Customer.builder()
                .identityNo(customerSaveRequest.getIdentityNo())
                .name(customerSaveRequest.getName())
                .surname(customerSaveRequest.getSurname())
                .phone(customerSaveRequest.getPhone())
                .build();
        Customer savedCustomer = customerRepository.save(newCustomer);
        return CustomerSaveResponse.builder()
                .id(savedCustomer.getId())
                .identityNo(savedCustomer.getIdentityNo())
                .name(customerSaveRequest.getName())
                .surname(customerSaveRequest.getSurname())
                .phone(customerSaveRequest.getPhone())
                .balance(savedCustomer.getBalance())
                .build();
    }
    public CustomerSaveResponse update(CustomerSaveRequest request) {
        Customer updateCustomer;
        var customer = customerRepository.findAllByIdentityNo(request.getIdentityNo());
        if (customer.size()>0) {
             updateCustomer = customer.get(0);
            updateCustomer.setIdentityNo(request.getIdentityNo());
            updateCustomer.setName(request.getName());
            updateCustomer.setSurname(request.getSurname());
            updateCustomer.setPhone(request.getPhone());
            updateCustomer= customerRepository.save(updateCustomer);
            return CustomerSaveResponse.builder()
                    .id(updateCustomer.getId())
                    .identityNo(updateCustomer.getIdentityNo())
                    .name(updateCustomer.getName())
                    .surname(updateCustomer.getSurname())
                    .phone(updateCustomer.getPhone())
                    .balance(updateCustomer.getBalance())
                    .build();
        }
        else
            throw new CustomerCheckException("Girilen TCKN'ye ait kişi bulunamadı.");
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional()
    public List<Customer> getCustomersById(Long id) {
        List<Customer> customerByReference = customerRepository.getCustomersById(id);
        if (customerByReference.size() == 0)
            throw new AirplaneReferenceException("Belirtilen Id'ye ait müşteri bilgisi bulunamadı.");
        return customerByReference;

    }

    public String maskCardNumber(String cardNumber) {
        StringBuilder maskedNumber = new StringBuilder();

        if (cardNumber.contains("-"))
            cardNumber = cardNumber.replace("-", "");
        else if (cardNumber.contains(" ")) {
            cardNumber = cardNumber.replaceAll("\\s+", "");

        }
        if (cardNumber.length() == 16) {
            String mask = "######******####";
            int index = 0;

            for (int i = 0; i < mask.length(); i++) {
                char c = mask.charAt(i);
                if (c == '#') {
                    maskedNumber.append(cardNumber.charAt(index));
                    index++;
                } else if (c == '*') {
                    maskedNumber.append(c);
                    index++;
                } else {
                    maskedNumber.append(c);
                }
            }
            return maskedNumber.toString();
        }
        return maskedNumber.toString();
    }

    public List<Customer> findAllByIdentityNo(Long identityNo) {
        List<Customer> customer = customerRepository.findAllByIdentityNo(identityNo);
        if (customer.size() == 0)
            throw new CustomerCheckException("Girilen TCKN'ye ait kişi bulunamadı.");
        return customer;

    }

    public void updateBalanceofCustomer(Customer customer, BigDecimal price) {
        customer.setBalance(price);
        customerRepository.save(customer);
//        return CustomerSaveResponse.builder()
//                .name(customer.getName() + " " + customer.getSurname())
//                .balance(customer.getBalance())
//                .build();

    }


}

