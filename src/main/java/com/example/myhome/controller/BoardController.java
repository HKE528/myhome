package com.example.myhome.controller;

import com.example.myhome.domain.Board;
import com.example.myhome.repository.BoardRepository;
import com.example.myhome.vaildator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardValidator boardValidator;

    @GetMapping("list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult result) {
        boardValidator.validate(board, result);

        if(result.hasErrors()) {
            return "board/form";
        }

        boardRepository.save(board);

        return "redirect:/board/list";
    }

}
