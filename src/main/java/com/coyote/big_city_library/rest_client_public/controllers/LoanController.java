package com.coyote.big_city_library.rest_client_public.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.coyote.big_city_library.rest_client_public.dto.LoanDto;
import com.coyote.big_city_library.rest_client_public.services.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mes-prets")
public class LoanController {

    @Autowired
    LoanService loanService;
    
    @GetMapping("")
    public String getUserLoans(HttpSession session, Model model) {

        List<LoanDto> loans = loanService.findLoansByUserPseudo(session, session.getAttribute("pseudo").toString());

        model.addAttribute("loans", loans);

        return "UserLoans";
    }

    @GetMapping("/etendre/{id}")
    public String extendLoan(HttpSession session, @PathVariable Integer id) {

        loanService.extendLoan(session, id);

        return "redirect:/mes-prets";
    }

}
