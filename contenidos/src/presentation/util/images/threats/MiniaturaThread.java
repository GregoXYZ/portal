package presentation.util.images.threats;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import presentation.util.images.ProcesadorImagenes;

import common.util.FileUtils;

public class MiniaturaThread extends Thread
{
	/**
	 * Logger for this class
	 */
	private final Log logger = LogFactory.getLog(MiniaturaThread.class);
	
	private String sourcePath;
	private String targetPath;
	private String image;

	public MiniaturaThread (String sourcePath, String targetPath, String image) 
	{
		super();
		this.sourcePath = sourcePath;
		this.targetPath = targetPath;
		this.image = image;
	}

	@Override
	public void run() {
		try {
			threatCreaMiniatura(sourcePath, targetPath, image);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	private void threatCreaMiniatura(String sourcePath, String targetPath, String image) throws IOException
	{
		if (FileUtils.getInstance().makeSureDirectoryExists(new File(targetPath)))
		{
			try {
				ProcesadorImagenes p = new ProcesadorImagenes();
				p.createThumbnail(sourcePath + image, 150, 150, 300, targetPath + image);
			} catch (InterruptedException e) {
				logger.error(e);
			}
			/*
			 * Mas calidad pero muy lento
			File f = new File(path + image);
			BufferedImage iMiniatura = p.escalarATamanyo(f, 150, 150, "jpg");
			if ( iMiniatura != null )
				p.salvarImagen(iMiniatura, targetPath + image, "jpg");
			*
			*/			
		}			
	}		
}
