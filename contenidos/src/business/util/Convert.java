package business.util;

import business.beans.AccesosDirectos;
import business.beans.Assets;
import business.beans.BusquedasPersonales;
import business.beans.Carpetas;
import business.beans.Compartidos;
import business.beans.Cuotas;
import business.beans.ExtensionFiles;
import business.beans.Ficheros;
import business.beans.Links;
import business.beans.MimeFiles;
import business.beans.MimeTypes;
import business.beans.Parametros;
import business.beans.Tags;
import business.beans.TiposAssets;

import common.dto.AccesosDirectosDTO;
import common.dto.AssetsDTO;
import common.dto.BusquedasPersonalesDTO;
import common.dto.CarpetasDTO;
import common.dto.CompartidosDTO;
import common.dto.CuotasDTO;
import common.dto.ExtensionFilesDTO;
import common.dto.FicherosDTO;
import common.dto.LinksDTO;
import common.dto.MimeFilesDTO;
import common.dto.MimeTypesDTO;
import common.dto.ParametrosDTO;
import common.dto.TagsDTO;
import common.dto.TiposAssetsDTO;

public abstract class Convert {
	public static Assets dto2dao(AssetsDTO dto) {
		Assets dao = new Assets();
		dao.setAssPk(dto.getAssPk());
		dao.setAssDescripcion(dto.getAssDescripcion());
		dao.setAssNombre(dto.getAssNombre());
		dao.setTipAssFk(dto.getTipAssFk());
		dao.setUsuFk(dto.getUsuFk());
		dao.setAssFechaAlta(dto.getAssFechaAlta());
		
		return dao;
	}
	
	public static Ficheros dto2dao(FicherosDTO dto) {
 
		Ficheros dao = new Ficheros();
		dao.setFicPk(dto.getFicPk());
		dao.setFicSize(dto.getFicSize());
		dao.setMimFilFk(dto.getMimFilFk());
		dao.setFicSysName(dto.getFicSysName());
		dao.setFicChecksum(dto.getFicChecksum());

		return dao;
	}
	
	public static Carpetas dto2dao(CarpetasDTO dto) {
		 
		Carpetas dao = new Carpetas();
		dao.setCarPk(dto.getCarPk());
		dao.setCarPk(dto.getCarFk());
		
		return dao;
	}
	
	public static AccesosDirectos dto2dao(AccesosDirectosDTO dto) {
		 
		AccesosDirectos dao = new AccesosDirectos();
		dao.setAccDirPk(dto.getAccDirPk());
		dao.setAssFk(dto.getAssFk());
		dao.setCarFk(dto.getCarFk());
		
		return dao;
	}
	
	public static TiposAssets dto2dao(TiposAssetsDTO dto) {
		 
		TiposAssets dao = new TiposAssets();
		dao.setTipAssPk(dto.getTipAssPk());
		dao.setTipAssUkCodigo(dto.getTipAssUkCodigo());
		dao.setTipAssDescripcion(dto.getTipAssDescripcion());
		dao.setTipAssNombre(dto.getTipAssNombre());
		
		return dao;
	}
	
	public static Cuotas dto2dao(CuotasDTO dto) {
		 
		Cuotas dao = new Cuotas(dto.getUsuFkPk(), dto.getCuoCuotaDisk(), dto.getCuoCuotaFile());
		
		return dao;
	}

	public static MimeFiles dto2dao(MimeFilesDTO dto)
	{
		MimeFiles dao = new MimeFiles(dto.getMimFilExtension(), dto.getMimFilMime());
		dao.setMimFilPk(dto.getMimFilPk());
		dao.setMimFilIcon(dto.getMimFilIcon());
		
		return dao;
	}

	public static ExtensionFiles dto2dao(ExtensionFilesDTO dto)
	{
		ExtensionFiles dao = new ExtensionFiles(dto.getExtFilCode(), dto.getMimTypFk());
		dao.setExtFilPk(dto.getExtFilPk());
		dao.setExtFilIcon(dto.getExtFilIcon());
		
		return dao;
	}

	public static MimeTypes dto2dao(MimeTypesDTO dto)
	{
		MimeTypes dao = new MimeTypes(dto.getMimTypCode());
		dao.setMimTypPk(dto.getMimTypPk());
		
		return dao;
	}

	public static Compartidos dto2dao(CompartidosDTO dto) {
		Compartidos dao = new Compartidos(dto.getAssFk(), dto.getUsuFk());
		dao.setComPk(dto.getComPk());
		dao.setComFechaAlta(dto.getAssFechaAlta());
		
		return dao;
	}

	public static Links dto2dao(LinksDTO dto) {
		Links dao = new Links(dto.getAssPk(), dto.getAssFkRef());
		dao.setLinPk(dto.getLinPk());
		
		return dao;
	}

	public static Tags dto2dao(TagsDTO dto) {
		Tags dao = new Tags(dto.getTagUkCodigo());
		dao.setTagPk(dto.getTagPk());
		dao.setTagCount(dto.getTagCount()==null?0:dto.getTagCount());
		
		return dao;
	}

	public static Parametros dto2dao(ParametrosDTO dto) {
		Parametros dao = new Parametros(dto.getParPk(), dto.getParValor(), dto.getParDescripcion());
		dao.setParPk(dto.getParPk());
		
		return dao;
	}

	public static BusquedasPersonales dto2dao(BusquedasPersonalesDTO dto) {
		BusquedasPersonales dao = new BusquedasPersonales(dto.getUsuFk(), dto.getTagFk(), dto.getBusPerFechaAlta());
		dao.setBusPerPk(dto.getBusPerPk());
		
		return dao;
	}

	public static MimeFilesDTO dao2dto(MimeFiles dao)
	{
		MimeFilesDTO dto = new MimeFilesDTO(dao.getMimFilExtension(), dao.getMimFilMime());
		dto.setMimFilPk(dao.getMimFilPk());
		dto.setMimFilIcon(dao.getMimFilIcon());
		
		return dto;
	}	

	public static ExtensionFilesDTO dao2dto(ExtensionFiles dao)
	{
		ExtensionFilesDTO dto = new ExtensionFilesDTO(dao.getExtFilCode(), dao.getMimTypFk());
		dto.setExtFilPk(dao.getExtFilPk());
		dto.setExtFilIcon(dao.getExtFilIcon());
		
		return dto;
	}	

	public static MimeTypesDTO dao2dto(MimeTypes dao)
	{
		MimeTypesDTO dto = new MimeTypesDTO(dao.getMimTypCode());
		dto.setMimTypPk(dao.getMimTypPk());
		
		return dto;
	}	
		
	public static AssetsDTO dao2dto(Assets dao)
	{
		AssetsDTO dto = new AssetsDTO();
		dto.setAssPk(dao.getAssPk());
		dto.setAssDescripcion(dao.getAssDescripcion());
		dto.setAssNombre(dao.getAssNombre());
		dto.setTipAssFk(dao.getTipAssFk());
		dto.setUsuFk(dao.getUsuFk());
		dto.setAssFechaAlta(dao.getAssFechaAlta());
		
		return dto;
	}
	
	public static FicherosDTO dao2dto(Ficheros dao)
	{
		FicherosDTO dto = new FicherosDTO();
		dto.setFicPk(dao.getFicPk());
		dto.setFicSysName(dao.getFicSysName());
		
    	//Long asset = null;
    	//if (dao.getAssets()!=null && dao.getAssets().getAssPk()!=null ) asset = dao.getAssets().getAssPk();		
		dto.setAssPk(dao.getAssets().getAssPk());
		dto.setAssNombre(dao.getAssets().getAssNombre());
		dto.setAssDescripcion(dao.getAssets().getAssDescripcion());
		dto.setAssFechaAlta(dao.getAssets().getAssFechaAlta());
		dto.setTipAssFk(dao.getAssets().getTipAssFk());
		dto.setUsuFk(dao.getAssets().getUsuFk());
		dto.setFicChecksum(dao.getFicChecksum());

    	Long carpeta = null;
    	if (dao.getCarpetas()!=null && dao.getCarpetas().getCarPk()!=null ) carpeta = dao.getCarpetas().getCarPk();		
		dto.setCarFk(carpeta);
		dto.setFicSize(dao.getFicSize());
		dto.setMimFilFk(dao.getMimFilFk());
		
		return dto;
	}
	
	public static CarpetasDTO dao2dto(Carpetas dao)
	{
		CarpetasDTO dto = new CarpetasDTO();
		dto.setCarPk(dao.getCarPk());
		if ( dao.getCarpetas() == null )
			dto.setCarFk(null);
		else
			dto.setCarFk(dao.getCarpetas().getCarPk());
		
		if ( dao.getAssets() == null )
			dto.setAssPk(null);
		else
		{
			dto.setAssPk(dao.getAssets().getAssPk());
			dto.setAssNombre(dao.getAssets().getAssNombre());
			dto.setAssDescripcion(dao.getAssets().getAssDescripcion());
			dto.setAssFechaAlta(dao.getAssets().getAssFechaAlta());
			dto.setTipAssFk(dao.getAssets().getTipAssFk());
			dto.setUsuFk(dao.getAssets().getUsuFk());
		}
		
		return dto;
	}
	
	public static AccesosDirectosDTO dao2dto(AccesosDirectos dao)
	{
		AccesosDirectosDTO dto = new AccesosDirectosDTO();
		dto.setAccDirPk(dao.getAccDirPk());
		dto.setAssFk(dao.getAssFk());
		dto.setCarFk(dao.getCarFk());
		
		return dto;
	}
	
	public static TiposAssetsDTO dao2dto(TiposAssets dao)
	{
		TiposAssetsDTO dto = new TiposAssetsDTO();
		dto.setTipAssPk(dao.getTipAssPk());
		dto.setTipAssUkCodigo(dao.getTipAssUkCodigo());
		dto.setTipAssNombre(dao.getTipAssNombre());
		dto.setTipAssDescripcion(dao.getTipAssDescripcion());
		
		return dto;
	}
	
	public static CuotasDTO dao2dto(Cuotas dao)
	{
		CuotasDTO dto = new CuotasDTO();
		dto.setUsuFkPk(dao.getUsuFkPk());
		dto.setCuoCuotaDisk(dao.getCuoCuotaDisk());
		dto.setCuoCuotaFile(dao.getCuoCuotaFile());
		
		return dto;
	}
	
	public static CompartidosDTO dao2dto(Compartidos dao)
	{
		CompartidosDTO dto = new CompartidosDTO();
		dto.setComPk(dao.getComPk());
		dto.setAssFk(dao.getAssFk());
		dto.setUsuFk(dao.getUsuFk());
		dto.setAssFechaAlta(dao.getComFechaAlta());
		
		return dto;
	}
	
	public static LinksDTO dao2dto(Links dao)
	{
		LinksDTO dto = new LinksDTO();
		dto.setLinPk(dao.getLinPk());
		dto.setAssPk(dao.getAssFk()); 
		dto.setAssFkRef(dao.getAssFkRef());
		return dto;
	}
	
	public static TagsDTO dao2dto(Tags dao)
	{
		TagsDTO dto = new TagsDTO();
		dto.setTagPk(dao.getTagPk());
		dto.setTagUkCodigo(dao.getTagUkCodigo());
		dto.setTagCount(dao.getTagCount());
		return dto;
	}
	
	public static ParametrosDTO dao2dto(Parametros dao)
	{
		ParametrosDTO dto = new ParametrosDTO();
		dto.setParPk(dao.getParPk());
		dto.setParValor(dao.getParValor());
		dto.setParDescripcion(dao.getParDescripcion());
		return dto;
	}
	
	public static BusquedasPersonalesDTO dao2dto(BusquedasPersonales dao)
	{
		BusquedasPersonalesDTO dto = new BusquedasPersonalesDTO(dao.getUsuFk(), dao.getTagFk(), dao.getBusPerFechaAlta());
		dto.setBusPerPk(dao.getBusPerPk());
		return dto;
	}
}
