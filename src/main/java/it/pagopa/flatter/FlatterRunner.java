package it.pagopa.flatter;

import it.pagopa.flatter.http.FlatterHttpServer;

import java.io.IOException;

public class FlatterRunner
{
    public static void main(String[] args) throws IOException
    {
        FlatterHttpServer http = new FlatterHttpServer();
        http.start(10001);
    }

}
