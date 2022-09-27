package com.basicit.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Inherit the Free Marker Configurer class and override the after Properties Set() method;
 * Integrate shiro Tags
 *
 * @author Crackers
 * @date 2022/3/15 15:37
 */
@Component
public class FreemarkerConfig implements InitializingBean {

    @Autowired
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws Exception {
        // After adding this sentence, you can use the shiro tag on the page
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
