package business.dao;

import java.util.List;

import business.beans.Assets;
import business.beans.Cuotas;
import business.beans.Ficheros;
import business.beans.Tags;

import common.business.DaoException;
import common.business.beans.Usuarios;

public interface ExtraQueriesDAO {
	public List<?> getCarpetas(Long user, Long folder) throws DaoException;
	public List<?> getFicheros(Long user, Long folder) throws DaoException;
	public Cuotas getCuotaRestante(Long id) throws DaoException;
	public List<Usuarios> getUsersContenidos() throws DaoException;
	public List<Usuarios> getUsersContenidos(Long user) throws DaoException;
	public List<?> getFicherosCompartidos(Long user, Long propietario, Long folder) throws DaoException;
	public List<?> getFicherosCompartidos(Long user, Long propietario) throws DaoException;
	public List<?> getMyFicherosCompartidos(Long user, Long folder) throws DaoException;
	public List<?> getMyFicherosCompartidos(Long user) throws DaoException;
	public List<?> getCarpetasConCompartidos(Long user, Long propietario) throws DaoException;
	public List<?> getMyCarpetasCompartidas(Long user) throws DaoException;
	public List<Usuarios> getUsersComparten(Long user) throws DaoException;
	public List<Assets> getAssetsCompartidos(Long user, Long propietario) throws DaoException;
	public List<Assets> getLinks(Long user, Long asset) throws DaoException;
	public List<Tags> getTags(Long user, Long asset) throws DaoException;
	public void addTags(Long user, Long asset, String tags) throws DaoException;
	public List<?> getTagsNube(Long user, Integer tipo) throws DaoException;
	public List<Assets> getAssetsTag(Long user, Long tag) throws DaoException;
	public List<Ficheros> getFicherosTag(Long user, Long tag) throws DaoException;
	public List<Ficheros> findFiles(String criteria, String tags, Long user) throws DaoException;
}
