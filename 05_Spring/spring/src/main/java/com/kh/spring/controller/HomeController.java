package com.kh.spring.controller;

import com.kh.spring.model.dto.Toilet;
import com.kh.spring.model.dto.ToiletListResponse;
import com.kh.spring.model.vo.PageInfo;
import com.kh.spring.service.ToiletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ToiletService toiletservice;
//    @Autowired
//    public HomeController(ToiletService toiletService) {
//        this.toiletService = toiletService;
//    }
    @RequestMapping("/")
    public String home(@RequestParam(value = "cpage", defaultValue = "1") int currentPage, Model model){
        //공즁화장실 목록을 받아서 페이지에 전달
        //http://openapi.seoul.go.kr:8088/인증키/응답형식/mgisToiletPoi/시작인덱스/끝인덱스/
        //http://openapi.seoul.go.kr:8088/644f59686b6a756a3635686f5a4e76/json/mgisToiletPoi/6/10/

        //PageInfo를 사용해서 페이징 처리
        int itemLimit = 10; //한페이지에 보여줄 화장실 갯수
        int pageLimit = 5; //페이징 버튼 갯수

        int startIndex = (currentPage - 1) * itemLimit + 1;
        int endIndex = startIndex + itemLimit - 1;

        ToiletListResponse result = toiletservice.getToiletList(startIndex, endIndex);

        PageInfo pi = new PageInfo(currentPage, result.getListTotalCount(), pageLimit, itemLimit);

        model.addAttribute("pi", pi);
        model.addAttribute("toiletList", result.getRow());

        return "index";
    }
}
