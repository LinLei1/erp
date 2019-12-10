package com.example.bus.cache;

import com.example.bus.domain.Customer;
import com.example.erp.cache.CacheAspect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务层缓存切面配置
 *
 * */
@Aspect
@Component
@EnableAspectJAutoProxy
public class BusinessCacheAspect {
    /**
     * 日志出处
     *
     * */

    private Log log = LogFactory.getLog(CacheAspect.class);

    /**
     *  声明一个缓存容器
     *
     * */
    private Map<String,Object> CACHE_CONTAINER = new HashMap<>();


    /**
     * 声明切面表达式
     *
     * */
    private static final String POINTCUT_CUSTOMER_UPDATE = "execution(* com.example.bus.service.impl.CustomerServiceImpl.updateById(..))";
    private static final String POINTCUT_CUSTOMER_ADD = "execution(* com.example.bus.service.impl.CustomerServiceImpl.save(..))";
    private static final String POINTCUT_CUSTOMER_GET = "execution(* com.example.bus.service.impl.CustomerServiceImpl.getById(..))";
    private static final String POINTCUT_CUSTOMER_DELETE = "execution(* com.example.bus.service.impl.CustomerServiceImpl.removeById(..))";
    private static final String POINTCUT_CUSTOMER_BATCHDELETE = "execution(* com.example.bus.service.impl.CustomerServiceImpl.removeByIds(..))";

    private static final String CACHE_CUSTOMER_PROFIX = "customer:";
    /**
     * 部门查询切入
     *
     * */
    @Around(value = POINTCUT_CUSTOMER_GET)
    public Object cacheCustomerGet(ProceedingJoinPoint joinPoint)throws Throwable{
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX+object);
        if (res1!=null){
            log.info("从缓存里面找到部门对象"+CACHE_CUSTOMER_PROFIX+object);
            return res1;
        }else {
            Customer res2 = (Customer) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX+res2.getId(),res2);
            log.info("未从缓存里面找到部门对象,去数据库中查询并放入缓存"+CACHE_CUSTOMER_PROFIX+res2.getId());
            return res2;
        }
    }

    /**
     * 部门添加切入
     *
     * */
    @Around(value = POINTCUT_CUSTOMER_ADD)
    public Object cacheCustomerAdd(ProceedingJoinPoint joinPoint)throws Throwable{
        //取出第一个参数
        Customer object = (Customer) joinPoint.getArgs()[0];
        //执行代理方法
        Boolean res = (Boolean)joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX+object.getId(),object);
        }
        return res;
    }

    /**
     * 部门更新切入
     *
     * */
    @Around(value = POINTCUT_CUSTOMER_UPDATE)
    public Object cacheCustomerUpdate(ProceedingJoinPoint joinPoint)throws Throwable{
        //取出第一个参数
        Customer customerVo = (Customer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Customer customer = (Customer) CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX+customerVo.getId());
            if (null==customer){
                customer = new Customer();
                BeanUtils.copyProperties(customerVo,customer);
                log.info("部门对象缓存已更新"+CACHE_CUSTOMER_PROFIX+customerVo.getId());
                CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX+customer.getId(),customer);
            }
        }
        return isSuccess;
    }


    /**
     * 部门删除切入
     *
     * */
    @Around(value = POINTCUT_CUSTOMER_DELETE)
    public Object cacheCustomerDelete(ProceedingJoinPoint joinPoint)throws Throwable{
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_CUSTOMER_PROFIX+id);
            log.info("部门对象缓存已删除"+CACHE_CUSTOMER_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 部门删除切入
     *
     * */
    @Around(value = POINTCUT_CUSTOMER_BATCHDELETE)
    public Object cacheCustomerBatchDelete(ProceedingJoinPoint joinPoint)throws Throwable{
        //取出第一个参数
        @SuppressWarnings("unchecked")
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            for (Serializable id: idList){
                //删除缓存
                CACHE_CONTAINER.remove(CACHE_CUSTOMER_PROFIX+id);
                log.info("客户对象缓存已删除"+CACHE_CUSTOMER_PROFIX+id);
            }
        }
        return isSuccess;
    }
}
