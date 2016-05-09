package com.ithaca.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BookSearch is used for searching for a particular book from given a title. The class is made such that the implementation
 * of the actual search is modular and can be replaced by future/better methods.
 */
@Service
public class BookSearch {

    @Autowired
    BookRepository bookRepository;

    /**
     * @param title The name of the title the user would like to find books closest matched too.
     * @return A list of 10 books that have titles that closely match the searched for title.
     */
    public List<Book> search(String title) {
        List<Book> all = (List<Book>) bookRepository.findAll();

        return search(title, new ArrayList<>(), all);
    }

    /**
     * A recursive function to find a list of 10 books closest to the searched for title.
     *
     * @param title       The name of the title the user would like to find books closest matched too.
     * @param currentList The ongoing list of books closely matched.
     * @param all         The list of all books in the database used to find the top 10 closest matches.
     * @return The list of books that closely match the title.
     */
    private List<Book> search(String title, List<Book> currentList, List<Book> all) {

        // Base case
        if (currentList.size() == 10) {
            return currentList;
        }

        Integer bestValue = Integer.MAX_VALUE;
        Book best = null;

        // loop over all of the books, looking for close matches
        for (Book book : all) {

            // we are only concerned with books not already in the list, as we want 10 unique books
            if (!currentList.contains(book)) {

                // Calc the lev score comparing the current books title and the searched for title.
                // The lower the score, the closer the match.
                Integer score = levenshteinDistance(book.getTitle(), title);
                if (score < bestValue) {
                    bestValue = score;
                    best = book;
                }
            }
        }

        // Add to list and recursively call the function until we have 10 books that closely match the searched for.
        currentList.add(best);
        return search(title, currentList, all);

    }

    /**
     * A simple way to calculate the score between two strings. In our case we compare the searched for title to the
     * title of a book in the database. The lower the score that is returned, the closer the match is.
     *
     * @param lhs One of the strings being compared.
     * @param rhs The other string being compared.
     * @return The score between the two string. Lower = closer.
     */
    private Integer levenshteinDistance(String lhs, String rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for (int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert = cost[i] + 1;
                int cost_delete = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

}
