package com.example.product_service.util.pageable;

import com.example.product_service.product.dto.DataTablesInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaginationUtil {

    public Pageable rePageable(DataTablesInput input) {
        Integer page = 0;
        if (input.getStart() == input.getLength()) {
            page = input.getStart() / input.getLength();
        } else if (input.getStart() > 0 && input.getStart() > input.getLength()) {
            page = ((input.getStart() + input.getLength()) / input.getLength()) - 1;
        }
        Pageable pageable ;
        if (input.getLength() < 0) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE);
        } else {
            pageable = PageRequest.of(page, input.getLength());
        }
        return pageable;
    }
}
