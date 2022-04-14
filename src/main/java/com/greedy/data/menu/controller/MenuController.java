package com.greedy.data.menu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.data.common.paging.Pagenation;
import com.greedy.data.common.paging.PagingButtonInfo;
import com.greedy.data.menu.dto.CategoryDTO;
import com.greedy.data.menu.dto.MenuDTO;
import com.greedy.data.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Bean
    public MappingJackson2JsonView jsonView() {

        return new MappingJackson2JsonView();
    }

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuCode}")
    public ModelAndView findMenuByCode(ModelAndView mv, @PathVariable int menuCode) {

        MenuDTO menu = menuService.findMenuByCode(menuCode);

        mv.addObject("menu", menu);
        mv.setViewName("/menu/one");

        return mv;
    }

    @GetMapping("/list")
    public ModelAndView findMenuList(ModelAndView mv, @PageableDefault Pageable pageable) {

        System.out.println("pageable = " + pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        System.out.println("조회한 내용 목록 : " + menuList.getContent());
        System.out.println("총 페이지 수 : " + menuList.getTotalPages());
        System.out.println("총 메뉴 수 : " + menuList.getTotalElements());
        System.out.println("해당 페이지에 표시될 요소 수 : " + menuList.getSize());
        System.out.println("해당 페이지의 실제 요소 수 : " + menuList.getNumberOfElements());
        System.out.println("첫 페이지 여부 : " + menuList.isFirst());
        System.out.println("마지막 페이지 여부 : " + menuList.isLast());
        System.out.println("정렬 방식 : " + menuList.getSort());
        System.out.println("여러 페이지 중 현재 인덱스 : " + menuList.getNumber());

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(menuList);

        mv.addObject("menuList", menuList);
        mv.addObject("paging", paging);
        mv.setViewName("menu/list");

        return mv;
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping(value = "/category")
    @ResponseBody
    public ModelAndView findCategoryList(ModelAndView mv, HttpServletResponse response) {
        List<CategoryDTO> categoryList = menuService.findAllCategory();
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json; charset=UTF-8");

        mv.addObject("categoryList", categoryList);
        mv.setViewName("jsonView");


        return mv;
    }

    @PostMapping("/regist")
    public ModelAndView registMenu(ModelAndView mv, MenuDTO newMenu, RedirectAttributes rttr) {

        menuService.registNewMenu(newMenu);

        rttr.addFlashAttribute("registSuccessMessage", "메뉴 등록에 성공하셨습니다!");
        mv.setViewName("redirect:/menu/list");

        return mv;
    }

    @GetMapping("/modify")
    public void modifyPage() {}

    @PostMapping("/modify")
    public String modifyMenu(RedirectAttributes rttr, MenuDTO menu) {

        menuService.modifyMenu(menu);

        rttr.addFlashAttribute("modifySuccessMessage", "메뉴 수정에 성공하셨습니다!");

        return "redirect:/menu/" + menu.getMenuCode();
    }
}
