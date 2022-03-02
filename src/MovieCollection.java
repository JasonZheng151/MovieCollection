import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }
    private void sortStrings(ArrayList<String> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            String string = listToSort.get(j);

            int possibleIndex = j;
            while (possibleIndex > 0 && string.compareTo(listToSort.get(possibleIndex - 1)) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, string);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }
   private void searchCast()
        {
                System.out.print("Enter a person to search for (first or last name): ");
                String searchName = scanner.nextLine();

                // prevent case sensitivity
                searchName = searchName.toLowerCase();

                ArrayList<String> cast = new ArrayList<>();
                ArrayList<String> lowerCaseCast = new ArrayList<>();
                for (int i = 0; i < movies.size(); i++)
                {
                    String[] castMember = movies.get(i).getCast().split("\\|");
                    String[] lowerCastMember = movies.get(i).getCast().toLowerCase().split("\\|"); // list of lower cases
                    for (int j = 0; j <castMember.length; j++)
                    {
                        cast.add(castMember[j]);
                    }
                    for (int j = 0; j < lowerCastMember.length; j++)
                    {
                        lowerCaseCast.add(lowerCastMember[j]);
                    }
                }
                for (int j = 0; j < lowerCaseCast.size(); j++)
                {
                    if (lowerCaseCast.get(j).indexOf(searchName) == -1)
                    {
                        lowerCaseCast.remove(j);
                        cast.remove(j);
                        j--;
                    }
                }
                // removes duplicates
                for (int k = 0; k < cast.size(); k++)
                {
                    for (int l = k + 1; l < cast.size() ; l++)
                    {
                        if (cast.get(k).equals(cast.get(l)))
                        {
                            cast.remove(l);
                            l--;
                        }
                    }
                }
                sortStrings(cast);
                for (int i = 0; i < cast.size(); i++)
                {
                    int choiceNum = i + 1;
                    System.out.println("" + choiceNum + ". " + cast.get(i));
                }
                System.out.println("Which would you like to see all movies for?");
                System.out.print("Enter number: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                ArrayList<Movie> moviesList = new ArrayList<>();
                for (int i = 0; i < movies.size(); i++)
                {
                    if (movies.get(i).getCast().indexOf(cast.get(choice - 1)) != -1)
                    {
                        moviesList.add(movies.get(i));
                    }
                }
                sortResults(moviesList);
                for (int i = 0; i < moviesList.size(); i++)
                {
                    int choiceNum = i + 1;

                    System.out.println("" + choiceNum + ". " + moviesList.get(i).getTitle());
                }
                System.out.println("Which movie would you like to learn more about?");
                System.out.print("Enter number: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                Movie selectedMovie = moviesList.get(choice - 1);
                displayMovieInfo(selectedMovie);
                System.out.println("\n  Press Enter to Return to Main Menu ");
                scanner.nextLine();
            }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword to search: ");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toLowerCase();
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++)
        {
            String movieKeyword = movies.get(i).getKeywords();
            movieKeyword= movieKeyword.toLowerCase();

            if (movieKeyword.contains(searchTerm))
            {
                results.add(movies.get(i));
            }
        }
        sortResults(results);
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = results.get(choice - 1);
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }
     private void listGenres() {
         /* TASK 5: IMPLEMENT ME! */

         // arraylist to hold search results
         ArrayList<String> allGenres = new ArrayList<>();
         ArrayList<Movie> results = new ArrayList<Movie>();
         ArrayList<Movie> Genre= new ArrayList<>();

         // search through ALL movies in collection
         for (int i = 0; i < movies.size(); i++) {
             String genres = movies.get(i).getGenres();
             genres = genres.toLowerCase();
             String[] genreList = genres.split("\\|");
             for (String genre : genreList) {
                 if (!allGenres.contains(genre)) {
                     allGenres.add(genre);
                 }
             }

         }
         String[] sortedGenres = new String[allGenres.size()];
         for (int i = 0; i < allGenres.size(); i++) {
             sortedGenres[i] = allGenres.get(i);

         }
         Arrays.sort(sortedGenres);

         // now, display them all to the user
         for (int i = 0; i < sortedGenres.length; i++) {
             int choiceNum = i + 1;

             System.out.println("" + choiceNum + ". " + sortedGenres[i]);
         }
         System.out.println("Which would you like to see all the movies for?");
         System.out.print("Enter the Name:");
         String choice= scanner.nextLine();
         choice= scanner.nextLine();
         for(int i=0; i< movies.size(); i++)
         {
             if(movies.get(i).getGenres().indexOf(choice)!=-1)
             {
                 Genre.add(movies.get(i));
                 System.out.println(Genre);
             }
         }


     }

    private void listHighestRated()
    {
        Movie[] top50 = new Movie[50];
        for (Movie movie : movies)
        {
            if(top50[49] == null || movie.getUserRating() > top50[49].getUserRating())
            {
                for (int i = 0; i < 50; i++)
                {
                    if (top50[i] == null || movie.getUserRating() > top50[i].getUserRating())
                    {
                        for (int j = 49; j > i; j--)
                        {
                            top50[j] = top50[j-1];
                        }
                        top50[i] = movie;
                        break;
                    }

                }
            }
        }
        for (int i = 0; i < top50.length; i++)
        {
            String title = top50[i].getTitle();
            double rating = top50[i].getUserRating();
            int choiceNum = i + 1;
            System.out.println("" + choiceNum + ". " + title + " " + rating);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = top50[choice - 1];
        displayMovieInfo(selectedMovie);
        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue ()
    {
        Movie[] top50 = new Movie[50];
        for (Movie movie : movies)
        {
            if(top50[49] == null || movie.getRevenue() > top50[49].getRevenue())
            {
                for (int i = 0; i < 50; i++) {
                    if (top50[i] == null || movie.getRevenue() > top50[i].getRevenue())
                    {
                        for (int j = 49; j > i; j--) {
                            top50[j] = top50[j-1];
                        }
                        top50[i] = movie;
                        break;
                    }

                }
            }
        }
        for (int i = 0; i < top50.length; i++)
        {
            String title = top50[i].getTitle();
            int revenue = top50[i].getRevenue();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + " " + revenue+"$");
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = top50[choice - 1];

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }



         private void importMovieList (String fileName)
         {
             try {
                 FileReader fileReader = new FileReader(fileName);
                 BufferedReader bufferedReader = new BufferedReader(fileReader);
                 String line = bufferedReader.readLine();

                 movies = new ArrayList<Movie>();

                 while ((line = bufferedReader.readLine()) != null) {

                     String[] movieFromCSV = line.split(",");


                     String title = movieFromCSV[0];
                     String cast = movieFromCSV[1];
                     String director = movieFromCSV[2];
                     String tagline = movieFromCSV[3];
                     String keyword = movieFromCSV[4];
                     String overview = movieFromCSV[5];
                     int runtime = Integer.parseInt(movieFromCSV[6]);
                     String genres = movieFromCSV[7];
                     double userRating = Double.parseDouble(movieFromCSV[8]);
                     int year = Integer.parseInt(movieFromCSV[9]);
                     int revenue = Integer.parseInt(movieFromCSV[10]);


                     Movie nextMovie = new Movie(title, cast, director, tagline, keyword, overview, runtime, genres, userRating, year, revenue);


                     movies.add(nextMovie);
                 }
                 bufferedReader.close();
             } catch (IOException exception) {
                 // Print out the exception that occurred
                 System.out.println("Unable to access " + exception.getMessage());
             }
         }
     }


    // ADD ANY ADDITIONAL PRIVATE HELPER METHODS you deem necessary

