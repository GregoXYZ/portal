package presentation.util.images.threats;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import presentation.util.images.ProcesadorImagenes;

import common.util.FileUtils;

public class RunnableMiniaturaThread implements Runnable
{

	/**
	 * Logger for this class
	 */
	private final Log logger = LogFactory.getLog(RunnableMiniaturaThread.class);
	
	private String sourcePath;
	private String targetPath;
	private String image;

	public RunnableMiniaturaThread (String sourcePath, String targetPath, String image) 
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
			logger.info("Creando miniatura a partir de: " + sourcePath + image + ".");
			File fTarget = new File(targetPath + image);
			File fSource = new File(sourcePath + image);
			if ( !fTarget.exists() && fSource.exists() )
			{
				try {
					ProcesadorImagenes p = new ProcesadorImagenes();
					synchronized(p)
					{
						p.createThumbnail(sourcePath + image, 130, 130, 200, targetPath + image);
						logger.info("Miniatura " + targetPath + image + " creada.");
					}
				} catch (InterruptedException e) {
					logger.error(e);
				}
				/*
				 * Mas calidad pero muy lento
				 *
				f = new File(sourcePath + image);
				ProcesadorImagenes p = new ProcesadorImagenes();
				BufferedImage iMiniatura = p.escalarATamanyo(f, 130, 130, "jpg");
				if ( iMiniatura != null )
					p.salvarImagen(iMiniatura, targetPath + image, "jpg");
				*
				*/							
			}
		}
	}
}
