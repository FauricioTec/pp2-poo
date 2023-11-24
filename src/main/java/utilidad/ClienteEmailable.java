package utilidad;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * Cliente para el servicio de emailable.com
 */
public class ClienteEmailable {

  private String apiKey;

  public ClienteEmailable(String pApiKey) {
    apiKey = pApiKey;
  }

  public String verificarEmail(String pEmail) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("https://api.emailable.com/v1/verify?email=" + pEmail + "&api_key=" + apiKey))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      throw new RuntimeException("Error al verificar el email");
    }

    JSONObject jsonObject = new JSONObject(response.body());

    return jsonObject.getString("state");
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

}