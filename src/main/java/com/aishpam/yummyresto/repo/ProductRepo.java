package com.aishpam.yummyresto.repo;
import com.aishpam.yummyresto.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    //this is the execution of the query
    @Query("SELECT p from Product p where p.price between 15 and 30 order by p.price asc")
    List<Product> findAllProducts(Pageable pageable);

    //limit clause is not supported so using pageable instead

}
