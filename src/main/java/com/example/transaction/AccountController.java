package com.example.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }
    @PostMapping("/transfer")
    public ResponseEntity<TransactionDetails> transferMoney(@RequestBody TransferRequest request) throws Exception {
        TransactionDetails  transactionDetails =  new TransactionDetails();
        transactionDetails.setDetails("Good!");

        transferService.transferMoney(request.getSenderAccountId(), request.getReceiverAccountId(), request.getAmount());
        //запускаем метод транзакции который переводит деньги и получаем ответ статуса
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(transactionDetails);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts()  {
        return transferService.getAllAccounts();
    }
}
