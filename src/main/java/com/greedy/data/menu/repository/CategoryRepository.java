package com.greedy.data.menu.repository;

import com.greedy.data.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT A.CATEGORY_CODE, A.CATEGORY_NAME, A.REF_CATEGORY_CODE FROM TBL_CATEGORY A ORDER BY A.CATEGORY_CODE ASC", nativeQuery = true)
    List<Category> findAllCategory();

}
