package com.basicit.web.controller;

import com.basicit.model.auth.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 인증 관련 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 11:07
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("login")
    String login(Model model) {
        model.addAttribute("user", new User());
        LOG.info("#로그인하러가기");
        return "view/login/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userForm") User user, RedirectAttributes redirectAttributes) {
        LOG.info("# 로그인 ");
        if (null == user || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            LOG.error("# 잘못된 사용자 이름 또는 비밀번호");
            return "login";
        }

        String username = user.getUsername();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 현재 S 가져오기ubject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 통화 중login방법 후,SecurityManagerA를 받을 것이다uthenticationToken,구성된 R로 보냅니다.ealm필수 인증 확인 수행
            // 각 RealmㅏuthenticationTokens반응하다
            // 그래서 이 단계는login(token)방법,그것은 갈 것이다MyRealm.doGetAuthenticationInfo()방법,구체적인 확인 방법은 이 방법을 참고하세요.
            LOG.info("사용자에게[{}]로그인 인증을 수행합니다..验인증서 시작", username);
            currentUser.login(token);
            LOG.info("사용자에게[{}]로그인 인증을 수행합니다..验합격", username);
        } catch (UnknownAccountException uae) {
            LOG.error("사용자에게[{}]로그인 인증을 수행합니다..验인증서 실패,알 수 없는 계정", username);
            setRedirectAttributes(redirectAttributes, "알 수 없는 계정");
        } catch (IncorrectCredentialsException ice) {
            LOG.error("사용자에게[{}]로그인 인증을 수행합니다..验인증서 실패,잘못된 자격 증명", username);
            setRedirectAttributes(redirectAttributes, "비밀번호가 잘못되었습니다");
        } catch (LockedAccountException lae) {
            LOG.error("사용자에게[{}]로그인 인증을 수행합니다..验인증서 실패,계정이 잠겨 있습니다", username);
            setRedirectAttributes(redirectAttributes, "계정이 잠겨 있습니다");
        } catch (ExcessiveAttemptsException eae) {
            LOG.error("사용자에게[{}]로그인 인증을 수행합니다..验인증서 실패,너무 많은 오류", username);
            setRedirectAttributes(redirectAttributes, "잘못된 사용자 이름 또는 비밀번호가 너무 많습니다.");
        } catch (AuthenticationException ae) {
            // S를 처리하여hiro런타임 AuthenticationException사용자가 로그인에 실패하거나 비밀번호가 올바르지 않을 때 발생하는 일을 제어할 수 있습니다.
            LOG.error("사용자에게[{}]로그인 인증을 수행합니다..验인증서 실패,스택 추적은 다음과 같습니다", username);
            setRedirectAttributes(redirectAttributes, "잘못된 사용자 이름 또는 비밀번호");
        }
        // 로그인이 성공했는지 확인
        if (currentUser.isAuthenticated()) {
            LOG.info("사용자[{}]로그인 인증 통과(这里可以进行一些认합격后의一些系统参数初始化操作)", username);
            return "redirect:/index";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    private void setRedirectAttributes(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("message", message);
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "view/login/login";
    }

    @GetMapping("/403")
    public String unauthorizedRole() {
        LOG.info("------권한이 거부되었습니다.-------");
        return "403";
    }

}
