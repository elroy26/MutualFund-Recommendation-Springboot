package mybank.recommendation.mutualfund.dao.remotes;

import mybank.recommendation.mutualfund.dao.entity.FundsAvailable;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface FundRepository {
    List<FundsAvailable> callFundsAvailable() throws SQLException;
}
