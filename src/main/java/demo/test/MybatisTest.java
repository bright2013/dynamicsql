package demo.test;

import demo.po.Customer;
import demo.po.CustomerQuery;
import demo.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    /**
     * 根据客户姓名和职业组合条件查询客户信息列表
     */
    @Test
    public void findCustomerByNameAndJobsTest() {
        // 通过工具类生成SqlSession对象
        SqlSession session = MybatisUtils.getSession();
        // 创建Customer对象，封装需要组合查询的条件
        Customer customer = new Customer();
        customer.setUsername("jack");
//		customer.setJobs("teacher");
        // 执行SqlSession的查询方法，返回结果集
        List<Customer> customers = session.selectList("demo.mapper"
                + ".CustomerMapper.findCustomerByNameAndJobs", customer);
        // 输出查询结果信息
        for (Customer customer2 : customers) {
            // 打印输出结果
            System.out.println(customer2);
        }
        // 关闭SqlSession
        session.close();
    }

    /**
     * 根据客户姓名或职业查询客户信息列表
     */
    @Test
    public void findCustomerByNameOrJobsTest() {
        // 通过工具类生成SqlSession对象
        SqlSession session = MybatisUtils.getSession();
        // 创建Customer对象，封装需要组合查询的条件
        Customer customer = new Customer();
//	    customer.setUsername("jack");
//	    customer.setJobs("teacher");
        // 执行SqlSession的查询方法，返回结果集
        List<Customer> customers = session.selectList("demo.mapper"
                + ".CustomerMapper.findCustomerByNameOrJobs", customer);
        // 输出查询结果信息
        for (Customer customer2 : customers) {
            // 打印输出结果
            System.out.println(customer2);
        }
        // 关闭SqlSession
        session.close();
    }

    /**
     * 更新客户
     */
    @Test
    public void updateCustomerTest() {
        // 获取SqlSession
        SqlSession sqlSession = MybatisUtils.getSession();
        // 创建Customer对象，并向对象中添加数据
        Customer customer = new Customer();
        customer.setId(3);
        customer.setPhone("13311111234");
        // 执行SqlSession的更新方法，返回的是SQL语句影响的行数
        int rows = sqlSession.update("demo.mapper"
                + ".CustomerMapper.updateCustomer", customer);
        // 通过返回结果判断更新操作是否执行成功
        if (rows > 0) {
            System.out.println("您成功修改了" + rows + "条数据！");
        } else {
            System.out.println("执行修改操作失败！！！");
        }
        // 提交事务
        sqlSession.commit();
        // 关闭SqlSession
        sqlSession.close();
    }

    /**
     * 根据客户编号批量查询客户信息
     */
    @Test
    public void findCustomerByIdsTest() {
        // 获取SqlSession
        SqlSession session = MybatisUtils.getSession();
        // 创建List集合，封装查询id
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        // 执行SqlSession的查询方法，返回结果集
        List<Customer> customers = session.selectList("demo.mapper"
                + ".CustomerMapper.findCustomerByIds", ids);
        // 输出查询结果信息
        for (Customer customer : customers) {
            // 打印输出结果
            System.out.println(customer);
        }
        // 关闭SqlSession
        session.close();
    }

    /**
     * bind元素的使用：根据客户名模糊查询客户信息
     */
    @Test
    public void findCustomerByNameTest() {
        // 通过工具类生成SqlSession对象
        SqlSession session = MybatisUtils.getSession();
        // 创建Customer对象，封装查询的条件
        Customer customer = new Customer();
        customer.setUsername("j");
        // 执行sqlSession的查询方法，返回结果集
        List<Customer> customers = session.selectList("demo.mapper"
                + ".CustomerMapper.findCustomerByName", customer);
        // 输出查询结果信息
        for (Customer customer2 : customers) {
            // 打印输出结果
            System.out.println(customer2);
        }
        // 关闭SqlSession
        session.close();
    }


    /**
     * FOREACH传入MAP
     */
    @Test
    public void dynamicForeachMapTest() {
        SqlSession session = MybatisUtils.getSession();

        final List ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(6);
        ids.add(7);
        ids.add(9);
        CustomerQuery customerQuery = new CustomerQuery();
        customerQuery.setIdList(ids);
        List<Customer> customers = session.selectList("demo.mapper"
                + ".CustomerMapper.dynamicForeachPojo", customerQuery);
        for (Customer customer2 : customers) {
            // 打印输出结果
            System.out.println(customer2);
        }
        session.close();
    }

    /**
     * FOREACH传入POJO
     */
    @Test
    public void dynamicForeachPojoTest() {
        SqlSession session = MybatisUtils.getSession();

        final List ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(6);
        ids.add(7);
        ids.add(9);
        Map params = new HashMap();
        params.put("ids", ids);
        params.put("title", "中国");
        List<Customer> customers = session.selectList("demo.mapper"
                + ".CustomerMapper.dynamicForeachMap", params);
        for (Customer customer2 : customers) {
            // 打印输出结果
            System.out.println(customer2);
        }
        session.close();
    }

}
