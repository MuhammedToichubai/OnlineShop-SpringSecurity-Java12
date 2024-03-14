package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.model.Product;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record PaginationResponse(
        int page,
        int size,
        List<Product> productList
) {
}
