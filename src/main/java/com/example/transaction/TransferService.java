package com.example.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
//@Transaction - в этом случае все методы класса будут в Транзакции
public class TransferService{

    private final AccountRepository accountRepository;
    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional//отмечаем аннотация это значит что метод под транзакцей
    //Транзакция - если метод выполняется полностью без ошибок и исключений результат сохраняется
    //если выходит ошибка то отеняются все действия
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) throws NotEnoughMoneyException {

        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);
        //Получаем информацию
        //о счетах, чтобы извлечь
        //оттуда текущий баланс
        //для каждого счета

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
        //Вычисляем новый баланс
        //для счета получателя
        //Вычисляем новый баланс
        //для счета отправителя

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
        //Присваиваем новое значение баланса
        //для счета отправителя и получателя

        if(sender.getAmount().compareTo(amount) < 0){
            throw new NotEnoughMoneyException();
        }
    }
    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
