package com.example.product_service.util.sequence;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceService {
    private final JdbcTemplate jdbcTemplate;

    public Long getNextValue(String sequenceName) {
        String sql = "SELECT nextval('" + sequenceName + "')";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
