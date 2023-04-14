package org.example.repository;

import org.example.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query(value = "SELECT * " +
            "FROM Car c " +
            "ORDER BY c.id " +
            "LIMIT :count"
            , nativeQuery = true)
    List<Car> findCars(@Param("count") Long count);
}
