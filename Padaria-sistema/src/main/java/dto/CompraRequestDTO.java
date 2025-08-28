package dto;

import lombok.Data;
import java.util.List;

@Data
public class CompraRequestDTO {
    private Long usuarioId;   // id do usuário
    private List<ProdutoDTO> produtos;

    @Data
    public static class ProdutoDTO {
        private Long id;   // id do produto (Padaria)
    }
}
