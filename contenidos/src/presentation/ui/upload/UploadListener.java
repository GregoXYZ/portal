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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Original : plosson on 06-janv.-2006 15:05:44 - Last modified  by $Author: vde $ on $Date: 2004/11/26 22:43:57 $
 * @version 1.0 - Rev. $Revision: 1.2 $
 */
public class UploadListener implements OutputStreamListener
{   
	Logger logger = Logger.getLogger(this.getClass());
    private HttpServletRequest request;
    private long delay = 0;
    private long startTime = 0;
    private int totalToRead = 0;
    private int totalBytesRead = 0;
    private int totalFiles = -1;

    public UploadListener(HttpServletRequest request, long debugDelay)
    {
        this.request = request;
        this.delay = debugDelay;
        this.totalToRead = request.getContentLength();
        this.startTime = System.currentTimeMillis();
    }

    public void start()
    {
        totalFiles ++;
        updateUploadInfo("start");
    }

    public void bytesRead(int bytesRead)
    {
        totalBytesRead = totalBytesRead + bytesRead;
        updateUploadInfo("progress");

        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void error(String message)
    {
        updateUploadInfo("error: " + message);
    }

    public void done()
    {
    	request.getSession().removeAttribute("uploadInfo");
        updateUploadInfo("done");
    }

    private long getDelta()
    {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    private void updateUploadInfo(String status)
    {
    	if (logger.isDebugEnabled())
    		logger.debug("inside updateUploadInfo ");
        long delta = getDelta();
        
    	if (logger.isDebugEnabled())
    		logger.debug("updateUploadInfo delta =  " + delta);
        request.getSession().setAttribute("uploadInfo", new UploadInfo(totalFiles, totalToRead, totalBytesRead,delta,status));
        
    	if (logger.isDebugEnabled())
    		logger.debug("leaving updateUploadInfo ");
    }

}
