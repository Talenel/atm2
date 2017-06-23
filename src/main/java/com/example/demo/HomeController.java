package com.example.demo;

import com.example.demo.models.Account;
import com.example.demo.models.AccountRepository;
import com.example.demo.models.AtmRepository;
import com.example.demo.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private AtmRepository atmRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/deposit")
    public String depositForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "deposit";
    }

    @PostMapping("/deposit")
    public String depositSubmit(@Valid Transaction transaction, BindingResult bindingResult, Principal principal) {


        if (bindingResult.hasErrors()) {
            return "deposit";
        }
        Account account =accountRepository.findOneByUserName(principal.getName());
        transaction.setAcctNum(account.getAcctNum());
        transaction.setAction("Deposit");
        account.depositFunds(transaction.getAmt());
        accountRepository.save(account);
        atmRepository.save(transaction);
        return "result";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawSubmit(@Valid Transaction transaction, BindingResult bindingResult, Principal principal) {


        //@Valid Account account, BindingResult bindingResult

        if (bindingResult.hasErrors()) {
            return "withdraw";
        }
        Account account =accountRepository.findOneByUserName(principal.getName());
        transaction.setAcctNum(account.getAcctNum());
        transaction.setAction("Withdrawal");
        account.depositFunds(transaction.getAmt());
        accountRepository.save(account);
        atmRepository.save(transaction);

        return "result";


    }



    @RequestMapping("/transactions")
    public String transactionHistory(Model model, Principal principal)
    {

        Account account =accountRepository.findOneByUserName(principal.getName());
        model.addAttribute("transList",atmRepository.findAllByAcctNum(account.getAcctNum()));
        model.addAttribute("account", account);

        return "history";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
