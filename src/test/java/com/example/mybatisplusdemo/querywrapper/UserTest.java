package com.example.mybatisplusdemo.querywrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatisplusdemo.user.repository.OrderRepository;
import com.example.mybatisplusdemo.user.repository.UserRepository;
import com.example.mybatisplusdemo.user.repository.entity.OrderEntity;
import com.example.mybatisplusdemo.user.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    // basic
    @Test
    public void basic() {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        List<UserEntity> userList = userRepository.selectList(qw);
        System.out.println("result: " + userList.size());
        for (UserEntity user: userList) {
            System.out.println(user.toString());
        }
    }

    // in
    @Test
    public void in() {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.in("username", "Alice", "Charlie");
        List<UserEntity> userList = userRepository.selectList(qw);
        System.out.println("result: " + userList.size());
        for (UserEntity user: userList) {
            System.out.println(user.toString());
        }
    }

    // groupBy
    @Test
    public void groupBy() {
        QueryWrapper<OrderEntity> qw = new QueryWrapper<>();
        qw.select("user_id", "sum(total_amount) total_amount").groupBy("user_id");
//        List<OrderEntity> list = orderRepository.selectList(qw);
        List<Map<String, Object>> list = orderRepository.selectMaps(qw);
        list.forEach(System.out::println);
    }

    // apply
    @Test
    public void apply() {
        QueryWrapper<OrderEntity> qw = new QueryWrapper<>();
        //  使用占位符避免sql注入的风险
        qw.apply("date_format(create_time,'%Y-%m-%d %H:%i:%s')>{0}", "2023-10-01 10:00:00")
                .apply("date_format(create_time,'%Y-%m-%d %H:%i:%s')<{0}", "2023-10-11 20:00:00")
                .inSql("user_id","select user_id from user_info where username like '%i%'");
        List<OrderEntity> list = orderRepository.selectList(qw);
        list.forEach(System.out::println);
    }

    // 自定義 SQL + XML 設定
    @Test
    public void selectMy() {
        LambdaQueryWrapper<UserEntity> lambda = Wrappers.lambdaQuery();
        lambda.like(UserEntity::getUsername, "i")
                .and(lqw -> lqw.lt(UserEntity::getAge, 30)
                        .or().isNotNull(UserEntity::getGender));

        List<UserEntity> userList = userRepository.selectAll(lambda);
        userList.forEach(System.out::println);
    }
}
