package HubSpot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HubSpot {
    public static final String GET_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=ddce5c4d7674328a4520865db850";
    public static final String POST_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=ddce5c4d7674328a4520865db850";

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create Http Client
        HttpClient client = HttpClient.newHttpClient();

        // Create Gson
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        Gson gson = builder.create();

        // Get partner list
        List<Partner> partnerList = getPartnerList(client, gson);

        // Create a hash map to map our partners to their corresponding country.
        Map<String, List<Partner>> countryToPartners = new HashMap<>();
        for (Partner partner : partnerList) {
            String country = partner.country;
            countryToPartners.putIfAbsent(country, new ArrayList<>());
            countryToPartners.get(country).add(partner);
        }

        List<CountryResult> res = new ArrayList<>();
        for (Map.Entry<String, List<Partner>> entry : countryToPartners.entrySet()) {
            String countryName = entry.getKey();
            List<Partner> partnersInThisCountry = entry.getValue();
            // Map partners to their available dates. Make sure the map is sorted by ascending date.
            SortedMap<Date, Set<String>> dateToAvailablePartners = mapDateToAvailablePartners(partnersInThisCountry);

            List<Date> dateList = new ArrayList<>(dateToAvailablePartners.keySet());
            Date startingDate = null;
            Set<String> maxAttendees = new HashSet<>();
            for (int i = 0; i < dateList.size() - 1; ++i) {
                Date first = dateList.get(i);
                Date second = dateList.get(i + 1);
                // Skip if we are not looking at two consecutive dates
                if (!first.isNextDay(second)) {
                    continue;
                }
                Set<String> firstDayAttendees = dateToAvailablePartners.get(first);
                Set<String> secondDayAttendees = dateToAvailablePartners.get(second);
                // Get the intersection of two days and see how many people can attend on both days
                Set<String> intersection = new HashSet<>(firstDayAttendees);
                intersection.retainAll(secondDayAttendees);
                // Update the result if necessary
                if (intersection.size() > maxAttendees.size()) {
                    maxAttendees = intersection;
                    startingDate = first;
                }
            }
            String resultStartingDate = startingDate == null ? null : startingDate.toString();
            CountryResult result = new CountryResult(maxAttendees.size(), new ArrayList<>(maxAttendees), countryName, resultStartingDate);
            res.add(result);
        }
        FinalResult finalResult = new FinalResult(res);

        String postResponse = postResult(client, gson, finalResult);

        System.out.println(postResponse);
    }

    public static List<Partner> getPartnerList(HttpClient client, Gson gson) throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(GET_URL))
                .build();

        HttpResponse<String> response = client.send(getRequest,
                HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        String trimmedJsonString = jsonString.substring(12, jsonString.length() - 1);
        return gson.fromJson(trimmedJsonString, new TypeToken<ArrayList<Partner>>() {}.getType());
    }

    public static String postResult(HttpClient client, Gson gson, FinalResult finalResult) throws IOException, InterruptedException {
        String jsonResult = gson.toJson(finalResult);
        System.out.println(jsonResult);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(POST_URL))
                .POST(HttpRequest.BodyPublishers.ofString(jsonResult))
                .build();
        HttpResponse<String> postResponse = client.send(postRequest,
                HttpResponse.BodyHandlers.ofString());
        return postResponse.body();
    }

    public static SortedMap<Date, Set<String>> mapDateToAvailablePartners(List<Partner> partnersInThisCountry) {
        SortedMap<Date, Set<String>> dateToAvailablePartners = new TreeMap<>((d1, d2) -> {
            if (d1.year != d2.year) {
                return d1.year - d2.year;
            }
            if (d1.month != d2.month) {
                return d1.month - d2.month;
            }
            return d1.day - d2.day;
        });
        for (Partner partner : partnersInThisCountry) {
            List<String> availableDatesList = partner.availableDates;
            for (String d : availableDatesList) {
                Date date = new Date(d);
                dateToAvailablePartners.putIfAbsent(date, new HashSet<>());
                dateToAvailablePartners.get(date).add(partner.email);
            }
        }
        return dateToAvailablePartners;
    }

    static class FinalResult {
        List<CountryResult> countries;

        public FinalResult(List<CountryResult> countries) {
            this.countries = countries;
        }
    }
}
