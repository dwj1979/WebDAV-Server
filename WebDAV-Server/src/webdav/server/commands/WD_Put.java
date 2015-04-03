package webdav.server.commands;

import http.server.HTTPCommand;
import http.server.HTTPEnvironment;
import http.server.HTTPMessage;
import webdav.server.Helper;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class WD_Put extends HTTPCommand
{
    public WD_Put()
    {
        super("put");
    }
    
    @Override
    public HTTPMessage Compute(HTTPMessage input, HTTPEnvironment environment) 
    {
        HTTPMessage msg = new HTTPMessage(200, "OK");
        
        File f = new File(environment.getRoot() + input.getPath().replace("/", "\\").trim());
        try
        {
            f.createNewFile();
        }
        catch (IOException ex)
        { }
        if(!input.getHeader("content-length").equals("0"))
        {
            try
            {
                Files.write(f.toPath(), input.getContent());
            }
            catch (IOException ex)
            { }
        }
        
        msg.setHeader("Content-Type", "text/xml; charset=\"utf-8\"");
        return msg;
    }
    
}