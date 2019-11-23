/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos.dao.Interfaces;

import java.util.List;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author root
 */
public interface usuarioDao {
   public List<Usuario> Listar();
   public List<Rol> ListarRoles();
   public Usuario validarlogin(Usuario usuario);
   public Usuario buscarUsuario(Usuario usuario);
   public boolean crearUsuario(Usuario usuario);
   public boolean editarUsuario(Usuario usuario);
   public boolean borrarUsuario(Integer id);
 
   
}
