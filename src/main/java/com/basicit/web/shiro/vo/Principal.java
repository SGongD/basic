package com.basicit.web.shiro.vo;

import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;

import java.io.Serializable;
import java.util.List;

/**
 * Principal물체
 *
 * @author Crackers
 * @date 2022/3/15 11:05
 */
public class Principal implements Serializable {
    private static final long serialVersionUID = -6477583820961243636L;

    private User user;
    private List<Role> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return user.getTrueName();
    }

}
