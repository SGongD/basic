package com.basicit.config;

import com.basicit.web.shiro.AuthorizingRealmImpl;
import com.basicit.web.shiro.MShiroFilterFactoryBean;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Crackers
 * @Description Shiro Configure
 * @date Apr 12, 2017 3:51:55 PM
 */
@SuppressWarnings("all")
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:conf/ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "myShiroRealm")
    public AuthorizingRealmImpl myShiroRealm(EhCacheManager cacheManager) {
        AuthorizingRealmImpl realm = new AuthorizingRealmImpl();
        realm.setCacheManager(cacheManager);
        return realm;
    }

    /**
     * Register Delegating Filter Proxy (Shiro)
     * There are 2 ways to integrate Shiro:
     * 1. Assemble a Filter Registration Bean yourself according to this method (this method is more flexible, you can define your own Url Pattern,
     * In the project use, you may end up using it because of some very painful problems. If you want to use it, you may need to read the official website or already know the processing principle of Shiro very well)
     * 2. Use the Shiro Filter Factory Bean directly (this method is relatively simple, and the Shiro Filter is assembled internally, and the Url Pattern cannot be defined by itself,
     * Block by default /*???
     */
    // @Bean
    // public FilterRegistrationBean filterRegistrationBean() {
    // FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    // filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
    // // ???????????? false,S??? ????????? ???????????????.pringApplicationContext????????????,??? ??????true?????? ?????? S??? ???????????????.ervletContainer????????????
    // filterRegistration.addInitParameter("targetFilterLifecycle", "true");
    // filterRegistration.setEnabled(true);
    // filterRegistration.addUrlPatterns("/*");// ????????? ?????? ?????? ???????????? ????????? ??? ????????????.???S??? ????????? ?????? ?????? ?????? ???????????????.hiro????????? ????????? ?????????
    // return filterRegistration;
    // }
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthorizingRealmImpl myShiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
        // <!-- ????????? ?????? ??????/?????? ??????Cache, E??? ????????????hCache ????????? -->
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * Load shiro Filter permission control rules (read from database and then configure)
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        // authc???The page under this filter can only be accessed after authentication. It is an interceptor built in Shiro org.apache.shiro.web.filter.authc.Form Authentication Filter
        // anon???Its corresponding filter is empty and does nothing

        /////////////////////// The following rule configurations are best configured in the configuration file ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        logger.info("##################Read permission rules from database and load into shiroFilter##################");

        // filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// ???????????? ?????? ???????????????????????????????????????????????? ?????? ?????? ???????????? ?????? ?????? ????????????.

        filterChainDefinitionMap.put("/static/**", "anon");// anon ???????????? ?????? ????????? ????????? ??? ??????
        filterChainDefinitionMap.put("/favicon.ico", "anon");

        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/**", "authc");

        filterChainDefinitionMap.put("/logout", "logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter<br/>
     * Note that StudentService and IScoreDao in this parameter are just an example, because we can get the objects related to accessing the database in this way,
     * Then read the database-related configuration and configure it into the access rules of shiroFilterFactoryBean. In actual projects, please use your own Service to handle business logic.
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
        // must be set SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // If it is not set, it will automatically find the "/login.jsp" page in the root directory of the Web project.
        shiroFilterFactoryBean.setLoginUrl("/login");
        // The connection to jump to after successful login
        //shiroFilterFactoryBean.setSuccessUrl("/user");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

}
