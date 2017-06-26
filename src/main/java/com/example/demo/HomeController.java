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
    public String depositForm(Model model, Principal principal) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("acctList",accountRepository.findAllByUserName(principal.getName()));
        return "deposit";
    }

    @PostMapping("/deposit")
    public String depositSubmit(@Valid Transaction transaction, BindingResult bindingResult, Principal principal) {


        if (bindingResult.hasErrors()) {
            return "deposit";
        }
        //Account account =accountRepository.findOneByUserName(principal.getName());
        //transaction.setAcctNum(account.getAcctNum());
        Account account=accountRepository.findOneByAcctNum(transaction.getAcctNum());
        transaction.setAction("Deposit");
        account.depositFunds(transaction.getAmt());
        accountRepository.save(account);
        atmRepository.save(transaction);
        return "result";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model, Principal principal) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("acctList",accountRepository.findAllByUserName(principal.getName()));
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawSubmit(@Valid Transaction transaction, BindingResult bindingResult, Principal principal) {


        //@Valid Account account, BindingResult bindingResult

        if (bindingResult.hasErrors()) {
            return "withdraw";
        }
        //Account account =accountRepository.findOneByUserName(principal.getName());
        //transaction.setAcctNum(account.getAcctNum());
        Account account=accountRepository.findOneByAcctNum(transaction.getAcctNum());
        transaction.setAction("Withdrawal");
        account.depositFunds(transaction.getAmt());
        accountRepository.save(account);
        atmRepository.save(transaction);

        return "result";


    }



    @GetMapping("/transactions")
    public String transactionHistory(Model model, Principal principal)
    {

        Account account =accountRepository.findAllByUserName(principal.getName()).get(0);
        model.addAttribute("transList",atmRepository.findAllByAcctNum(account.getAcctNum()));
        model.addAttribute("account", account);
        model.addAttribute("acctList",accountRepository.findAllByUserName(principal.getName()));

        return "history";
    }

    @PostMapping("/transactions")
    public String transactionSelected(@ModelAttribute Account account, Model model, Principal principal){
        account=accountRepository.findOneByAcctNum(account.getAcctNum());
        model.addAttribute("transList",atmRepository.findAllByAcctNum(account.getAcctNum()));
        model.addAttribute("account", account);
        model.addAttribute("acctList",accountRepository.findAllByUserName(principal.getName()));


        return "history";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
