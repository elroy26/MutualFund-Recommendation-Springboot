package mybank.recommendation.mutualfund.dao.services;

import mybank.recommendation.mutualfund.dao.entity.FundsAvailable;

import java.util.*;

public class FundGraph {
    private final Map<FundsAvailable, List<WeightedEdge>> adjacencyList;

    public FundGraph() {
        this.adjacencyList = new LinkedHashMap<>();
    }

    public void addFund(FundsAvailable fund) {
        adjacencyList.putIfAbsent(fund, new ArrayList<>());
    }

    public void addEdge(FundsAvailable from, FundsAvailable to, Double weight) {
        adjacencyList.get(from).add(new WeightedEdge(to, weight));
        adjacencyList.get(to).add(new WeightedEdge(from, weight)); // Assuming undirected graph
    }

    public List<FundsAvailable> dijkstra(FundsAvailable startFund) {
        // If startFund is null, select a default fund (e.g., the first fund in the graph)
        if (startFund == null) {
            Optional<FundsAvailable> defaultFund = adjacencyList.keySet().stream().findFirst();
            if (defaultFund.isEmpty()) {
                throw new IllegalStateException("No funds available in the graph.");
            }
            startFund = defaultFund.get(); // Set default fund as the start node
        }

        Map<FundsAvailable, Double> distances = new HashMap<>();
        PriorityQueue<WeightedFund> pq = new PriorityQueue<>(
                Comparator.comparingDouble((WeightedFund fund) -> fund.distance)
                        .thenComparing(fund -> fund.fund.getSchemeName())  // Secondary sorting by scheme name
        );
        // Initialize distances
        for (FundsAvailable fund : adjacencyList.keySet()) {
            distances.put(fund, Double.MAX_VALUE);
        }
        distances.put(startFund, 0.0);
        pq.offer(new WeightedFund(startFund, 0.0));

        while (!pq.isEmpty()) {
            WeightedFund current = pq.poll();

            for (WeightedEdge edge : adjacencyList.get(current.fund)) {
                double newDist = distances.get(current.fund) + edge.weight;
                if (newDist < distances.get(edge.toFund)) {
                    distances.put(edge.toFund, newDist);
                    pq.offer(new WeightedFund(edge.toFund, newDist));
                }
            }
        }

        // Get the recommended funds based on shortest distances
        List<FundsAvailable> recommendedFunds = new ArrayList<>();
        for (Map.Entry<FundsAvailable, Double> entry : distances.entrySet()) {
            if (entry.getValue() < Double.MAX_VALUE) {
                recommendedFunds.add(entry.getKey());
            }
        }
        return recommendedFunds;
    }


    // Class to represent edges with weights
    private static class WeightedEdge {
        FundsAvailable toFund;
        double weight;

        public WeightedEdge(FundsAvailable toFund, double weight) {
            this.toFund = toFund;
            this.weight = weight;
        }
    }

    // Class to hold fund with its current distance for the priority queue
    private static class WeightedFund {
        FundsAvailable fund;
        double distance;

        public WeightedFund(FundsAvailable fund, double distance) {
            this.fund = fund;
            this.distance = distance;
        }
    }
}
