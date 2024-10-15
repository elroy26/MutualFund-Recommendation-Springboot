package mybank.recommendation.mutualfund.dao.services;

import mybank.recommendation.mutualfund.dao.entity.FundsAvailable;
import mybank.recommendation.mutualfund.dao.exceptions.FundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private FundDbRepo fundDbRepo;

    public List<FundsAvailable> recommendFunds(List<FundsAvailable> selectedFunds) throws SQLException {
        FundGraph graph = new FundGraph();
        List<FundsAvailable> allFunds = fundDbRepo.callFundsAvailable();

        // Add all funds to the graph
        for (FundsAvailable fund : allFunds) {
            graph.addFund(fund);
        }

        // Add edges based on composite scores (assuming compositeScore is the edge weight)
        for (FundsAvailable fundA : allFunds) {
            for (FundsAvailable fundB : allFunds) {
                if (!fundA.equals(fundB)) {
                    // Assuming compositeScore can be used as a weight for edges
                    graph.addEdge(fundA, fundB, fundA.getCompositeScore());
                }
            }
        }

        // Handle the case where no selected funds are provided by the user
        FundsAvailable startFund = null;
        if (selectedFunds == null || selectedFunds.isEmpty()) {
            // Default to the fund with the highest composite score if no funds are selected
            startFund = allFunds.stream()
                    .max(Comparator.comparing(FundsAvailable::getCompositeScore))
                    .orElseThrow(() -> new FundException("No funds available"));
        } else {
            // Use the first selected fund as the start node (you can modify this logic)
            startFund = selectedFunds.get(0);
        }

        // Run Dijkstra's algorithm starting from the determined startFund
        List<FundsAvailable> recommendedFunds = graph.dijkstra(startFund);

//        // Sort the recommended funds by composite score and then by scheme name for consistency
//        recommendedFunds.sort(Comparator.comparing(FundsAvailable::getCompositeScore)
//                .thenComparing(FundsAvailable::getSchemeName));
        return recommendedFunds;
    }
}
