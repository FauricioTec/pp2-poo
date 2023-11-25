package com.poo.pp2.utilidad;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * Cliente para hacer consultas a la API de Emailable
 */
public class ClienteEmailable {

  /**
   * API Key de Emailable
   */
  private String apiKey;

  /**
   * Constructor que inicializa la clase con la API Key de Emailable
   *
   * @param pApiKey API Key de Emailable
   */
  public ClienteEmailable(String pApiKey) {
    apiKey = pApiKey;
  }

  /**
   * Metodo que verifica el estado de un email con la API de Emailable
   *
   * @param pEmail Email a verificar
   * @return String con el estado del email
   * @throws Exception Si ocurre un error al verificar el email
   */
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