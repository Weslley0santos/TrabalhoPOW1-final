package br.ufsm.csi.service;

import br.ufsm.csi.dao.UsuarioRepository;
import br.ufsm.csi.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean inserirOuAtualizarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null ||
                usuario.getNome().isEmpty() || usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty()) {
            System.out.println("Erro: Os campos obrigatórios (nome, email, senha) não podem ser vazios.");
            return false;
        }

        try {
            usuarioRepository.save(usuario);
            System.out.println("Usuário salvo com sucesso.");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() == null) {
            System.out.println("Erro: ID do usuário é obrigatório para alteração.");
            return false;
        }
        return inserirOuAtualizarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado");
        } else {
            System.out.println("Lista encontrada com sucesso:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
        return usuarios;
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean excluirUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            System.out.println("Usuário excluído com sucesso.");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
            return false;
        }
    }

    public Usuario autenticar(String email, String senha) {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            System.out.println("Email ou senha não podem ser vazios para autenticação.");
            return null;
        }
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        if (usuario != null && usuario.isAtivo()) {
            System.out.println("Usuário autenticado com sucesso: " + usuario.getEmail());
            return usuario;
        } else {
            System.out.println("Falha na autenticação para o email: " + email);
            return null;
        }
    }
}