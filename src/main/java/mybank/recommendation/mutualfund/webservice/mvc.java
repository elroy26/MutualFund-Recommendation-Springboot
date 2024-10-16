package mybank.recommendation.mutualfund.webservice;

import mybank.recommendation.mutualfund.dao.entity.FundsAvailable;
import mybank.recommendation.mutualfund.dao.remotes.FundRepository;
import mybank.recommendation.mutualfund.dao.services.FundDbRepo;
import mybank.recommendation.mutualfund.dao.services.KMPAlgorithm;
import mybank.recommendation.mutualfund.dao.services.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui")
public class mvc {
    @Autowired
    private FundDbRepo fundRepository;

    @Autowired
    private RecommendationService recommendationService;
    Logger LOGGER = LoggerFactory.getLogger(mvc.class);


    @GetMapping("/dash")
    public String getAllFundAvailable( Model model) {
        try {
            List<FundsAvailable> fundAvailable = fundRepository.callFundsAvailable();

            model.addAttribute("fundAvailable", fundAvailable);
        } catch (SQLException e) {
            model.addAttribute("error", "Error fetching funds: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "fundsAvailable";
    }
    @GetMapping("/recommend")
    @ResponseBody // Indicate that you're returning JSON
    public List<FundsAvailable> getFundAvailable(
            @RequestParam(value = "selectedSubcategories", required = false) List<String> selectedSubcategories,
            @RequestParam(value = "fundSize", required = false) String fundSize, // Single value expected for fund size
            @RequestParam(value = "selectedAMCs", required = false) List<String> selectedAMCs) {
        try {
            LOGGER.info("AMCs: {}, Subcategories: {}, FundSize: {}", selectedAMCs, selectedSubcategories, fundSize);
            List<FundsAvailable> fundAvailable = fundRepository.callFundsAvailable();

            List<FundsAvailable> recommendedFunds;

            if ((selectedSubcategories == null || selectedSubcategories.isEmpty()) &&
                    (selectedAMCs == null || selectedAMCs.isEmpty()) && fundSize == null) {
                // Call the recommendation service with null to trigger default fund selection
                recommendedFunds = recommendationService.recommendFunds(null);
            } else {
                // Define the fund size ranges based on the `fundSize` parameter
                List<FundsAvailable> selectedFundsList = fundAvailable.stream()
                        .filter(fund -> {
                            // Filter by subcategory, AMC, and fund size
                            boolean matchesSubcategory = selectedSubcategories == null || selectedSubcategories.isEmpty() || selectedSubcategories.contains(fund.getSubCategory());
                            boolean matchesAMC = selectedAMCs == null || selectedAMCs.isEmpty() || selectedAMCs.contains(fund.getAmcName());
                            boolean matchesFundSize = true;

                            if (fundSize != null) {
                                switch (fundSize) {
                                    case "1": // 1-99 crores
                                        matchesFundSize = fund.getFundSizeCr() >= 1 && fund.getFundSizeCr() < 100;
                                        break;
                                    case "100": // 100-499 crores
                                        matchesFundSize = fund.getFundSizeCr() >= 100 && fund.getFundSizeCr() < 500;
                                        break;
                                    case "500": // 500-999 crores
                                        matchesFundSize = fund.getFundSizeCr() >= 500 && fund.getFundSizeCr() < 1000;
                                        break;
                                    case "1000": // 1000+ crores
                                        matchesFundSize = fund.getFundSizeCr() >= 1000;
                                        break;
                                    default:
                                        matchesFundSize = true; // If no fundSize filter is applied
                                }
                            }

                            // Return true if the fund matches all the filters
                            return matchesSubcategory && matchesAMC && matchesFundSize;
                        })
                        .collect(Collectors.toList());

                recommendedFunds = recommendationService.recommendFunds(selectedFundsList);
            }

            // Return the recommended funds as JSON
            return recommendedFunds;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching funds: " + e.getMessage(), e); // Handle exceptions properly
        }

    }

    @GetMapping("/search-funds")
    @ResponseBody
    public List<FundsAvailable> searchFunds(
            @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        try {
            LOGGER.info("Search term: {}", searchTerm);
            List<FundsAvailable> fundAvailable = fundRepository.callFundsAvailable();
            KMPAlgorithm kmp = new KMPAlgorithm();

            // Filter funds using KMP algorithm
            List<FundsAvailable> matchingFunds = fundAvailable.stream()
                    .filter(fund -> searchTerm != null &&
                            kmp.KMPSearch(fund.getSchemeName().toLowerCase(), searchTerm.toLowerCase()))
                    .collect(Collectors.toList());
            LOGGER.info(matchingFunds.toString());

            return matchingFunds; // Return the list of matching funds
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching funds: " + e.getMessage(), e);
        }
    }

}
