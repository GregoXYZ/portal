package presentation.util;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.bo.impl.AccesosDirectosBOImpl;

import common.IConstantes;
import common.business.BusinessException;
import common.business.bo.AssetsBO;
import common.business.bo.FicherosBO;
import common.business.bo.UsuariosBO;
import common.dto.AssetsDTO;
import common.dto.FicherosDTO;
import common.dto.UsuariosDTO;
import common.util.FileUtils;
import common.util.spring.SpringUtil;

public class TestChecksum {

	private static Log logger = LogFactory.getLog(AccesosDirectosBOImpl.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = Parametros.get_instance().getValor(IConstantes.DEFAUL_USER_DIR);

		// Iniciamos factoria de Spring
		//SpringUtil.init();
		SpringUtil.getInstance();
		
    	AssetsBO assetBO = (AssetsBO) SpringUtil.getInstance().getBean("AssetsBO");
    	FicherosBO ficherosBO = (FicherosBO) SpringUtil.getInstance().getBean("FicherosBO");
    	UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");
    	try {
			AssetsDTO[] assets = assetBO.getAssets();
			
			for (AssetsDTO asset : assets)
			{
				if (asset.getTipAssFk() == 2)
				{
					UsuariosDTO usuario = usuariosBO.getByPrimaryKey(asset.getUsuFk());
					FicherosDTO fichero = ficherosBO.getByAsset(asset.getAssPk());
					
					if (fichero.getFicChecksum().trim().length() == 0)
					{
						try
						{
							fichero.setFicChecksum(FileUtils.getChecksum(path + usuario.getUsuUkUsuario() + "/" + fichero.getFicSysName()));
							ficherosBO.update(fichero);							
							logger.info("Fichero: " + fichero.getFicPk() + " Checksum: " + fichero.getFicChecksum());
						} catch (FileNotFoundException e) {
							logger.info("Borrando: " + path + usuario.getUsuUkUsuario() + "/" + fichero.getFicSysName());
							assetBO.delete(asset);
							logger.error(e.getMessage());
						}

					}					
				}
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
