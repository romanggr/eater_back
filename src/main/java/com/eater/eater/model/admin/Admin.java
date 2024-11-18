package com.eater.eater.model.admin;

import com.eater.eater.enums.Role;
import com.eater.eater.exception.AdminUnverifiedException;
import com.eater.eater.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private boolean isAccepted = false;


    public Admin() {
        super.setRole(Role.ADMIN);
    }

    @Override
    public boolean isEnabled() {
        super.isEnabled();
        if (!isAccepted) {
            throw new AdminUnverifiedException("Your account was created but  wasn't verified, please wait for another administrator to verify you. You get email");
        }
        return true;
    }
}
