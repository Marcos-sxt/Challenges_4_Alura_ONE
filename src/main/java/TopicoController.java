import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    @Autowired
    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @GetMapping
    public List<Topico> listarTodosOsTopicos() {
        return topicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Topico criarNovoTopico(@RequestBody Topico topico) {
        return topicoRepository.save(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody Topico topicoAtualizado) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isPresent()) {
            Topico t = topicoExistente.get();
            t.setTitulo(topicoAtualizado.getTitulo());
            t.setConteudo(topicoAtualizado.getConteudo());
            return ResponseEntity.ok().body(topicoRepository.save(t));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTopico(@PathVariable Long id) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

