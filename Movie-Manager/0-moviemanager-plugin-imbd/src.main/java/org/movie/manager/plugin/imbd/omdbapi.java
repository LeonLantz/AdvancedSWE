package org.movie.manager.plugin.imbd;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


/*
    - The Open Movie Database API -
    The OMDb API is a RESTful web service to obtain movie information,
    all content and images on the site are contributed and maintained by our users.

    FREE: 1,000 daily limit
 */
public class omdbapi {

    /*
        The following commands was the first idea to use Opdbapi.
        JSONObject was used. Possibly you should use something else here,
        since this is perhaps an unnecessary plugin.
        You also have to refactor the code for Clean Architects.

     */

//    public static void main(String[] args) {
//        //Daten einlesen und in ArrayList packen
//        ArrayList<String> movies = readFileAsList(new File("MoviesID.txt"));
//
//        JSONArray newJsonWithALlMovies = new JSONArray();
//        String key = "";
//
//        for (String movie : movies) {
//            //Name
////            String nameMovie = "http://www.omdbapi.com/?apikey=" + key + "&t=" + movie;
//
//            //IMDB-ID
//            String nameMovie = "http://www.omdbapi.com/?apikey=" + key + "&i=" + movie;
//
//            URL urlNameMovie;
//            try {
//                urlNameMovie = new URL(nameMovie);
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(urlNameMovie);
//
//
//            //Für jeden Film request stellen und annehmen
//            JSONObject jsonObject;
//            try {
//                jsonObject = readJsonFromUrl(nameMovie);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            //Test: Json einlesen
////            JSONObject jsonObject = new JSONObject(readFileAsString(new File("jsonFull.json")));
//
//            //Auswahl treffen
//            JSONObject newMovieJson = choice(jsonObject);
//
//            //In json packen
//            newJsonWithALlMovies.put(newMovieJson);
//        }
//
//        //Das Json abspeichern
//        writeFile(newJsonWithALlMovies.toString(), new File("newJSONMovies5.json"));
//    }
//
//    public static JSONObject choice(JSONObject jsonObject){
//        JSONObject newMovieJson = null;
//        try {
//
//            String name = (String) jsonObject.get("Title");
//            String year = (String) jsonObject.get("Year");
//            String runtime = (String) jsonObject.get("Runtime");
//            String genre = (String) jsonObject.get("Genre");
//            String director = (String) jsonObject.get("Director");
//            String plot = (String) jsonObject.get("Plot");
//            String metascore = (String) jsonObject.get("Metascore");
//            String imdbRating = (String) jsonObject.get("imdbRating");
//            String imdbVotes = (String) jsonObject.get("imdbVotes");
//            newMovieJson = new JSONObject()
//                    .put("Titel", name)
//                    .put("Jahr", year)
//                    .put("Laufzeit", runtime)
//                    .put("Genre", genre)
//                    .put("Direktor", director)
//                    .put("Handlung", plot)
//                    .put("Metascore", metascore)
//                    .put("IMDB_Rating", imdbRating)
//                    .put("IMDB_Votes", imdbVotes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return newMovieJson;
//    }
//
//    public static void writeFile(String line, File f) {
//        try( PrintWriter pw = new PrintWriter(new FileWriter(f, false))){
//            pw.println(line);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String readFileAsString(File file){
//        String jsonText="";
//
//        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//            jsonText = readAll(br);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonText;
//    }
//
//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is;
//        try {
//            is = new URL(url).openStream();
//            try {
//                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//                String jsonText = readAll(rd);
//                JSONObject json = new JSONObject(jsonText);
//                return json;
//            } finally {
//                is.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static String readAll(Reader rd) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        int cp;
//        while ((cp = rd.read()) != -1) {
//            sb.append((char) cp);
//        }
//        return sb.toString();
//    }
//
//    public static ArrayList<String> readFileAsList(File file){
//        ArrayList<String> movies = new ArrayList<>();
//
//        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//            while(br.ready()) {
//                movies.add(br.readLine());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return movies;
//    }
//}


    public String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public String requestFromUrl(String url) throws IOException {
        InputStream is;
        try {
            is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                return readAll(rd);
            } finally {
                is.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
