package com.up.student.dao;

import java.sql.SQLException;

import com.up.student.base.BaseDAO;

/**
 * 模块说明： 管理员增删改查
 *
 */
public class AdminDAO extends BaseDAO {/*懒汉式单例模式：
    所谓 “ 懒汉式” 就是说单例实例在第一次被使用时构建，
    而不是在JVM在加载这个类时就马上创建此唯一的单例实例。
*/
    private static AdminDAO ad = null;

    public static synchronized AdminDAO getInstance() { //判断当前单例是否已经存在，若存在则返回，不存在则再建立单例
        if (ad == null) {
            ad = new AdminDAO();
        }
        return ad;
    }

    public boolean queryForLogin(String username, String password) {//实现登录功能
        boolean result = false;
        if (username.length() == 0 || password.length() == 0) {
            return result;
        }
        String sql = "select * from admin where username=? and password=?";//SQL语句的框架，一个？表示一个占位符，将来会接受值
        String[] param = { username, password };
        rs = db.executeQuery(sql, param);
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            destroy();
        }
        return result;
    }

}