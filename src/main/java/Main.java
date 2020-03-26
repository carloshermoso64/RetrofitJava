import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args){
        System.out.println("Inserta usuario GitHub");



        //Enter data using BufferReader
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        try {
            String username = reader.readLine();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
  //                  .baseUrl("https://api.github.com/")
                    .baseUrl("http://localhost:8080/dsaApp/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

 //           GitHubService service = retrofit.create(GitHubService.class);
            RestExampleService service = retrofit.create(RestExampleService.class);

  //          Call<List<Repo>> repos = service.listRepos(username);
            Call<List<Stats>> stats = service.listStats();

            try{
            //    List<Repo> result = repos.execute().body();
                List<Stats> result = stats.execute().body();
  //              for (Repo r: result){
  //                  System.out.println(r);
  //              }
                for (Stats s: result){
                    System.out.println(s);
                }
            }
            catch (Exception e){
                System.out.println("EXCEPCION:");
                System.out.println(e.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
