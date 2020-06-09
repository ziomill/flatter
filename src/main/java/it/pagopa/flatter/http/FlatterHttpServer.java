package it.pagopa.flatter.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import it.pagopa.flatter.impl.FlatterImpl;
import it.pagopa.flatter.specs.Flattable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class FlatterHttpServer
{

    private HttpServer server;

    public void start(int port) throws IOException
    {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(FlatterHttpServer::handleRequest);
        server.start();
        System.out.println("Server sarted on port: " + port);
    }

    public void stop()
    {
        server.stop(0);
    }

    private static void handleRequest(HttpExchange exchange) throws IOException
    {
        // Flatter Service
        Flattable flattable = new FlatterImpl();

        // From Json to Input bject
        Object input = requestToObject(exchange.getRequestBody());

        // The Flatter Service
        String path = exchange.getRequestURI().getPath();
        switch (path)
        {
            case "/flat":
            {
                Object[] result = flattable.flat(input);
                String response = objectToResponse(result);

                exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
                exchange.getResponseHeaders().add("Content-Type","application/json");
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                System.out.println("Response OK --> " + response);
                break;
            }
            default:
            {
                exchange.sendResponseHeaders(400,0);
                System.out.println("Response KO --> Path: " + path + " not handled!");
                break;
            }
        }
    }

    /**
     * Parse Json Request Body to Flatter Service's Input.
     * @param body Json Request Body
     * @return Parsed request
     */
    private static Object requestToObject(InputStream body)
    {
        JSONTokener tokener = new JSONTokener(new InputStreamReader(body));
        JSONArray input = new JSONArray(tokener);
        System.out.println("Request: " + input.toString(5));
        Object result = buildObjectsArray(input);
        return result;
    }

    /**
     * Parse Flatter Service's Output to Json Response Body.
     * @param output Flatter Service's Output
     * @return Parsed response
     */
    private static String objectToResponse(Object[] output)
    {
        String result = JSONObject.valueToString(output);
        return result;
    }

    /**
     * Helper for Parsing Request Body.
     * @param input Element to parse.
     * @return parsed element.
     */
    private static Object buildObjectsArray(Object input)
    {
        if(input instanceof JSONArray)
        {
            JSONArray elements = ((JSONArray) input);
            List<Object> nested = new ArrayList();
            elements.forEach(item ->
            {
                nested.add(buildObjectsArray(item));
            });
            return nested.toArray(Object[]::new);
        }
        else
        {
            return input;
        }
    }
}
