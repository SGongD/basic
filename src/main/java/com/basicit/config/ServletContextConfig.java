package com.basicit.config;

import com.basicit.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Calendar;

/**
 * Application-level parameter configuration
 *
 * @author Crackers
 * @date 2022/2/16 14:03
 */
@Component
public class ServletContextConfig implements ServletContextAware {

    private static final Logger log = LoggerFactory.getLogger(ServletContextConfig.class);

    @Override
    public void setServletContext(ServletContext context) {
        String datetime = DateUtil.dateToString(Calendar.getInstance().getTime(), DateUtil.fm_yyyyMMddHHmmssSSS);
        String contextPath = context.getContextPath();
        log.info("# version={} , contextPath={}", datetime, contextPath);
        context.setAttribute("version_css", datetime);
        context.setAttribute("version_js", datetime);
        context.setAttribute("ctx", contextPath);
    }

}
