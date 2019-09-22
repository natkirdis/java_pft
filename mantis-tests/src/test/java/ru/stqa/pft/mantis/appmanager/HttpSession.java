package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HttpSession {
  private CloseableHttpClient httpClient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app;
    // создается новый клиент (сессия работы по протоколу http). Объект, который будет отправлять запросы на сервер.
    // setRedirectStrategy - создается стратегия перенаправлений. Без нее получим ответ 302. Должны будем его обрабатывать
    // LaxRedirectStrategy - сам обрабатывает перенаправления


    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }


  // Умеет выполнять логин
  public boolean login(String username, String password) throws Exception {

    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");

    // Формируется набор параметров
    List<BasicNameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));

    // Параметры упаковываются и складываются в заранее созданный запрос setEntity
    post.setEntity(new UrlEncodedFormEntity(params));

    // Отправка запроса. Результат - ответ
    CloseableHttpResponse response = httpClient.execute(post);



    // Отправка запроса. Обрабатываем ответ
    String body = geTextFrom(response);

    // Проверяем, что пользователь вошел. Код страницы содерижит строчку, к содержит имя пользователя.
    return body.contains(String.format("<a href=\"/mantisbt-2.22.0/account_page.php\">%s</a>", username));
  }

  private String geTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  // Определяет какой пользователь залогинен
  public boolean isLoggedInAs(String username) throws IOException {
    // сформировали запрос
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
    // Отправили запрос -> получили ответ
    CloseableHttpResponse response = httpClient.execute(get);
    String body = geTextFrom(response);

    // Проверяем, что в тексте страницы содержится нужный текст
    return body.contains(String.format("<a href=\"/mantisbt-2.22.0/account_page.php\">%s</a>", username));
  }


}
