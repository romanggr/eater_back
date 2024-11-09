package com.eater.eater.model.admin;

import com.eater.eater.enums.Role;
import com.eater.eater.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private boolean isAccepted = false;

    public Admin(String email, String phone, String name, String password) {
        super(null, email, phone, name, password, Role.ADMIN);
    }

    public Admin() {
        super.setRole(Role.ADMIN);
    }
}
