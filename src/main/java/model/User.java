package model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gonzalo de Achaval
 */

@Entity
@Table(name = "book")
public class User {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
