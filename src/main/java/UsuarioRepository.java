import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Consulta personalizada para encontrar usuários por email
    Usuario findByEmail(String email);
    
    // Consulta personalizada usando JPQL
    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %:nome%")
    List<Usuario> findUsuariosByNomeContaining(String nome);
    
    // Você pode adicionar mais consultas personalizadas aqui
    
}
