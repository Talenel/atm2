package com.example.demo;

import com.example.demo.models.Account;
import com.example.demo.models.AtmRepository;
import com.example.demo.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private AtmRepository atmRepository;

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
    public String depositSubmit(@Valid Transaction transaction, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "deposit";
        }
        transaction.setAction("Deposit");
        atmRepository.save(transaction);
        return "result";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawSubmit(@Valid Transaction transaction, BindingResult bindingResult) {


        //@Valid Account account, BindingResult bindingResult

        if (bindingResult.hasErrors()) {
            return "withdraw";
        }
        transaction.setAction("Withdrawal");
        atmRepository.save(transaction);
        return "result";


    }


    @RequestMapping("/everything")
    public @ResponseBody Iterable<Transaction> getEveryThing()
    {
        return atmRepository.findAll();
    }

    @GetMapping("/transactions")
    public String transactionForm(Model model) {

        model.addAttribute("account",new Account());
        return "transactions";
    }


    @PostMapping("/transactions")
    public String transactionHistory(@ModelAttribute Account account, Model model)
    {


        model.addAttribute("transList",atmRepository.findAllByAcctNum(account.getAcctNum()));


        return "history";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
