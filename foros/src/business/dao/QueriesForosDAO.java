package business.dao;

import java.util.List;

import business.beans.Contenidos;
import business.beans.Entradas;

import common.business.DaoException;

public interface QueriesForosDAO {
	public List<?> getResults(Long user) throws DaoException;
	public List<?> getThreats(Long user, Long entrada) throws DaoException;
	public List<Entradas> getEntradas(Long user) throws DaoException;
	public List<Contenidos> getContenidos(Long entrada, Long user) throws DaoException;
	public Contenidos getUltimoContenido(Long entrada) throws DaoException;
	public Object[] getContenido(Long user, Long contenido) throws DaoException;
}
