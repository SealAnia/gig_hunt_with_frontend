package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.user_id, u.nickname, u.role_id, u.town_id, u.category_id," +
            "u.is_busy, u.maximum, u.active_orders, u.address, u.e_mail," +
            "u.postal_code, u.phone_number, u.watsapp_number," +
            "u.first_name, u.second_name, u.account_not_expired, " +
            "u.account_not_locked, u.credentials_not_expired, u.enabled, u.password FROM user u "
            + "INNER JOIN category c ON u.category_id = c.category_id WHERE "
            + "u.category_id = :category_id AND c.available_online = true AND u.is_busy = false", nativeQuery = true)
    //List<Master> findMastersInCategory(@Param("category_id") Long categoryId);
    Set<Master> findMastersInCategory(@Param("category_id") Long categoryId);

    @Query(value = "SELECT u.user_id, u.nickname, u.role_id, u.town_id, u.category_id," +
            "u.is_busy, u.maximum, u.active_orders, u.address, u.e_mail,"  +
            "u.postal_code, u.phone_number, u.watsapp_number," +
            "u.first_name, u.second_name, u.account_not_expired, u.account_not_locked, " +
            "u.credentials_not_expired, u.enabled, u.password FROM user u "
            + "INNER JOIN category c ON u.category_id = c.category_id "
            + "INNER JOIN town t ON u.town_id = t.town_id WHERE u.category_id = :category_id AND "
            + "u.town_id = :town_id AND c.available_online = false AND u.is_busy = false", nativeQuery = true)
    //List<Master> findMastersInCategoryAndFromTown(@Param("category_id") Long categoryId, @Param("town_id") Long townId);
    Set<Master> findMastersInCategoryAndFromTown(@Param("category_id") Long categoryId, @Param("town_id") Long townId);

    @Query(value = "SELECT SUM(price * quantity) " +
            "FROM user u INNER JOIN goods g ON u.user_id = g.master_id " +
            "INNER JOIN order_details o ON g.goods_id = o.goods_id " +
            "WHERE o.order_status = 'COMPLETED' AND g.master_id = :master_id " +
            "GROUP BY u.user_id;", nativeQuery = true)
    Double countEarnedAmount(Long master_id);

    @Query(value = "SELECT SUM(price * quantity) FROM user u " +
            "INNER JOIN goods g ON u.user_id = g.master_id INNER JOIN order_details o ON " +
            "g.goods_id = o.goods_id WHERE o.order_status = 'COMPLETED' AND g.master_id = " +
            ":master_id AND DATE_FORMAT(o.date, '%Y-%m') = :month GROUP BY MONTH(date)", nativeQuery = true)
    Double countEarnedAmountForMonth(Long master_id, @Param("month") String month);

    User findByNickname(String nickname);

    //NEW
    //@Query(value = "SELECT u.user_id, u.nickname, u.role_id, u.town_id, u.category_id, u.type, " +
            //"u.is_busy, u.maximum, u.active_orders, u.address, u.e_mail, " +
            //"u.postal_code, u.phone_number, u.watsapp_number, " +
            //"u.first_name, u.second_name, u.account_not_expired, u.account_not_locked, " +
            //"u.credentials_not_expired, u.enabled, u.password FROM user u WHERE " +
            //"u.nickname = :username AND u.password = :password", nativeQuery = true)
    //@Query(value = "SELECT * FROM user u WHERE " +
            //"u.nickname = :nickname AND u.password = :password", nativeQuery = true)
    User findByNicknameAndPassword(String nickname, String password);

}
