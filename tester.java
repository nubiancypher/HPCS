import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class tester {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/billing"))
				.build();
		
		HttpResponse<String> response =
				client.send(request, BodyHandlers.ofString());
		
		System.out.println(response.body());
	}
}
