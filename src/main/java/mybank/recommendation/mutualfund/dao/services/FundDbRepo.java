package mybank.recommendation.mutualfund.dao.services;

import mybank.recommendation.mutualfund.dao.entity.FundsAvailable;
import mybank.recommendation.mutualfund.dao.exceptions.FundException;
import mybank.recommendation.mutualfund.dao.remotes.FundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FundDbRepo implements FundRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger LOGGER = LoggerFactory.getLogger(FundDbRepo.class);

    @Override
    public List<FundsAvailable> callFundsAvailable() throws SQLException {
        List<FundsAvailable> fundsAvailableList = new ArrayList<>();
        String sql = "select * from mutual_funds_available";
        try {
            fundsAvailableList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(FundsAvailable.class));
            LOGGER.info("Total fund available: {}", fundsAvailableList.size());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new SQLException(e.getMessage());
        }
        if (fundsAvailableList.isEmpty()) {
            throw new FundException("There is no fund available");
        }

        return fundsAvailableList;
    }
}
