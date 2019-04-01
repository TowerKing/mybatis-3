package io.github.towerking.main;

import io.github.towerking.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Entrances {

    public static void main(String[] args) throws IOException {

        /**
         *  两个 MyBatis 学习的参考网址
         *  MyBatis 架构图 https://www.cnblogs.com/wangdaijun/p/5296830.html
         *  MyBatis 层次结构图 https://www.cnblogs.com/smile361/p/9199717.html
         * */

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 01. 从这一行代码开始进入查看 MyBatis 是如何加载配置文件(包括全局配置文件和 Mapper 配置文件)
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sessionFactory.openSession();
        String userName = session.selectOne("selectUserNameByUserId", 2);
        System.out.println(userName);

        User param = new User();
        param.setUserId(2);
        User user = session.selectOne("selectUserByUserId", param);
        System.out.println(user);
        session.close();
    }
}
