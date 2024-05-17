package com.codehows.mothproject.controller;

import com.codehows.mothproject.entity.Board;
import com.codehows.mothproject.repository.BoardRepository;
import com.codehows.mothproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final BoardService service;
    private final BoardRepository repository;
//    @GetMapping("/main")
//    public String mainpage(){
//        return "mainpage";
//    }

    @GetMapping("/main")
    public String mainpage(){
        return "boardlist";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @GetMapping("/admin/deleted")
    public String deletedBoard(){
        return "admin/deletedBoards";
    }

    @GetMapping("/admin/boards")
    public String manageBoards(){
        return "admin/manageBoards";
    }

    @GetMapping("/admin/comments")
    public String manageComments(){return "admin/manageComments";}

    @GetMapping("/admin/users")
    public String manageUsers(){
        return "admin/manageUsers";
    }

    @GetMapping("/admin/files")
    public String manageFiles(){
        return "admin/manageFiles";
    }

    @GetMapping("/mypage")
    public String mypage(){
        return "user/mypage";
    }

    @GetMapping("/newboard")
    public String newboard(){
        return "newboard";
    }

    //게시물 수정
    @GetMapping("/modifyboard")
    public String modifyboard(@RequestParam(required = false) Long bno, Model model){
        //지금은 board 데이터 전부 다 넘겨주고 있는데
        //나중에 제목, 내용, 해시태그, 첨부파일 이것만 보내주면 댐
        Board board = service.findByBno(bno);
        model.addAttribute("board", board);
        return "modifyboard";
    }

    //게시물 상세 페이지
    @GetMapping("/viewboard/{bno}")
    public String viewBoard(@PathVariable Long bno, Model model) {

        Board board = service.findByBno(bno);
        //없는 게시물에 들어갔을 때
        if(board == null){
            return "error2";
        }

        //삭제된 게시물에 접근할 때
        if(board.getBoard_condition().equals("Y")) {
            return "error2";
        }
        /*System.out.println(board.getTitle());
        System.out.println(board.getUser_id());*/

        //정상적인 페이지로 들어갔을 경우 조회수 + 1
        repository.incrementHitCount(bno);

        String hashtags = board.getHashtag();
        List<String> hashtagList = Arrays.asList(hashtags.split("#"));
        // 빈 문자열을 제외합니다.
        hashtagList = hashtagList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        model.addAttribute("hashtags", hashtagList);

        model.addAttribute("board", board);
        return "ViewBoard";
    }
}
