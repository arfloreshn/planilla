/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecciones;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "holamundo")
@RequestScoped
public class holamundo {

    /**
     * Creates a new instance of holamundo
     */
    public holamundo() {
    }
    
      private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sayHi() {
        this.message = this.message+ " received at " + LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final holamundo other = (holamundo) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }
    
}
