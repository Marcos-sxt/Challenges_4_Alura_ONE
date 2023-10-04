import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    
    // Consulta personalizada para encontrar tópicos por título
    List<Topico> findByTitulo(String titulo);
    
    // Consulta personalizada usando JPQL
    @Query("SELECT t FROM Topico t WHERE t.titulo LIKE %:titulo%")
    List<Topico> findTopicosByTituloContaining(String titulo);
    
    // Você pode adicionar mais consultas personalizadas aqui
    
}
