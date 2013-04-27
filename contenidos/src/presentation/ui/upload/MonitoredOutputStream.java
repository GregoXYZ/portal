/* Licence:
*   Use this however/wherever you like, just don't blame me if it breaks anything.
*
* Credit:
*   If you're nice, you'll leave this bit:
*
*   Class by Pierre-Alexandre Losson -- http://www.telio.be/blog
*   email : plosson@users.sourceforge.net
*/
/*
*  Changed for Part 2, by Ken Cochrane
*  http://KenCochrane.net , http://CampRate.com , http://PopcornMonsters.com
*/
package presentation.ui.upload;

import org.apache.log4j.Logger;

import java.io.OutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Original : plosson on 05-janv.-2006 10:44:18 - Last modified  by $Author: plosson $ on $Date: 2006/01/05 10:44:49 $
 * @version 1.0 - Rev. $Revision: 1.2 $
 */
public class MonitoredOutputStream extends OutputStream
{
    Logger log = Logger.getLogger(this.getClass());
    private OutputStream target;
    private OutputStreamListener listener;

    public MonitoredOutputStream(OutputStream target, OutputStreamListener listener)
    {
    	if ( log.isDebugEnabled() )
    		log.debug("inside MonitoredOutputStream constructor ");
        this.target = target;
        this.listener = listener;
        this.listener.start();
    	if ( log.isDebugEnabled() )
    		log.debug("leaving MonitoredOutputStream contructor ");
    }

    public void write(byte b[], int off, int len) throws IOException
    {
        target.write(b,off,len);
        listener.bytesRead(len - off);
    }

    public void write(byte b[]) throws IOException
    {
        target.write(b);
        listener.bytesRead(b.length);
    }

    public void write(int b) throws IOException
    {
        target.write(b);
        listener.bytesRead(1);
    }

    public void close() throws IOException
    {
        target.close();
        listener.done();
    }

    public void flush() throws IOException
    {
        target.flush();
    }
}
