package com.greedy.data.menu.service;

import com.greedy.data.menu.dto.CategoryDTO;
import com.greedy.data.menu.dto.MenuDTO;
import com.greedy.data.menu.entity.Category;
import com.greedy.data.menu.entity.Menu;
import com.greedy.data.menu.repository.CategoryRepository;
import com.greedy.data.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public MenuDTO findMenuByCode(int menuCode) {

        Menu menu = menuRepository.findById(menuCode).get();

        return modelMapper.map(menu, MenuDTO.class);
    }

    public Page<MenuDTO> findMenuList(Pageable pageable) {

        /*
        * offset, limit, sort순으로 값을 전달함
        * pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1
        * pageable.getPageSize()  = 10  => limit = 10이랑 같은 의미
        * */
        pageable = PageRequest.of(pageable.getPageNumber() <= 0? 0: pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending());

        /* Sort.by 안에 컬럼명을 넣으면 해당 컬럼으로 sorting 후 조회 */
//        List<Menu> menuList = (List<Menu>) menuRepository.findAll(pageable);
////        List<Menu> menuList = menuRepository.findByMenuNameContaining("한우");
//        return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());

        return menuRepository.findAll(pageable).map(menu -> modelMapper.map(menu, MenuDTO.class));
    }


    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void registNewMenu(MenuDTO newMenu) {

        menuRepository.save(modelMapper.map(newMenu, Menu.class));
    }

    @Transactional
    public void modifyMenu(MenuDTO menu) {

        Menu foundMenu = menuRepository.findById(menu.getMenuCode()).get();
        foundMenu.setMenuName(menu.getMenuName());
    }
}
