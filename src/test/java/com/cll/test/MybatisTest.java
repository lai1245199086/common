package com.cll.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.cll.dao.DaoSupport;
import com.cll.entity.Seckill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest{
	Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	//1.硬编码方式  - 编程式事物管理
	@Resource
	private TransactionTemplate transactionTemplate;
	
	@Resource(name = "daoSupport")  
	private DaoSupport dao;  
	 

	@Test
	public void getTest() {
		Seckill seckill= (Seckill)sqlSessionTemplate.selectOne("seckill.queryById", 1000l);//秒杀商品Id
		log.debug("SeckillId ==> {}",seckill.getSeckillId());
		log.debug("SeckiName ==> {}",seckill.getName());
	}
	
	@Test
	public void getTransaction() {
		//编程式事物
		Map<String, Object> paramMap = new HashMap<String, Object>();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				//逻辑代码
				paramMap.put("seckillId", 1002l);//秒杀商品Id
				paramMap.put("killTime", new Date());//秒杀商品时间
				int updateCount = sqlSessionTemplate.update("seckill.reduceNumber",paramMap);
				log.debug("update.commit {}",updateCount);
				
				paramMap.put("userPhone","13412511111");
				int insertCount = sqlSessionTemplate.insert("successkilled.insertSuccessKilled",paramMap);
				log.debug("insert.commit {}",insertCount);
				if (insertCount<1) {//插入秒杀记录失败
					throw new RuntimeException("插入秒杀记录失败");
				}
				//int i = 1/0;
				log.debug("transactionManager.commit");
			}
		});
	}
	
   	//通过DAO获取数据 
    @Test
    public void getUserAndRoleById() throws Exception {  
        Seckill seckill= (Seckill) dao.findForObject("seckill.queryById", 1000l);  
		log.debug("SeckillId ==> {}",seckill.getSeckillId());
		log.debug("SeckiName ==> {}",seckill.getName());
    }  
    
    //注解方式:事物
    /**
     * 秒杀
     * Transactional注解中的的属性
     *  propagation :事务的传播行为
     *  isolation :事务的隔离级别
     *  readOnly :只读
     * rollbackFor :发生哪些异常回滚 noRollbackFor :发生哪些异常不回滚
     * rollbackForClassName 根据异常类名回滚
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void submit() {
		// 逻辑代码
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seckillId", 1002l);// 秒杀商品Id
		paramMap.put("killTime", new Date());// 秒杀商品时间
		int updateCount = sqlSessionTemplate.update("seckill.reduceNumber", paramMap);
		log.debug("更新条数{}", updateCount);

		paramMap.put("userPhone", "13412511112");
		int insertCount = sqlSessionTemplate.insert("successkilled.insertSuccessKilled", paramMap);
		log.debug("插入条数 {}", insertCount);
		if (insertCount < 1) {// 插入秒杀记录失败
			throw new RuntimeException("插入秒杀记录失败");
		}
		// int i = 1/0;
		log.debug("transactionManager.commit");
    }

    /**
     * xml配置式事物
     * @throws Exception
     */
    @Test
    public void useAOPTransaction() throws Exception {  
    	List<String> list = new ArrayList<String>();
    	list.add("13411111111");
    	list.add("13412511111");
        dao.batchDelete("successkilled.batchDeleteByPhoneNo", list);  
    } 
}
