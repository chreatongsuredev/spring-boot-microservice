package com.example.product_service.category.repository;

import com.example.product_service.category.dto.CategoryResponseDto;
import com.example.product_service.category.repository.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,String> {
  @Query(value = """
          SELECT category_prefix FROM tbl_category WHERE category_id = :categoryId
          """,nativeQuery = true)
    String categoryPrefix(String categoryId);

  @Query(value = """
          SELECT category_id as categoryId ,
                 category_name as categoryName,
                 category_prefix as categoryPrefix 
          FROM tbl_category 
          """,nativeQuery = true,
          countQuery = "SELECT COUNT(*) FROM tbl_category")
  Page<CategoryResponseDto> categoryList(Pageable page);
}
