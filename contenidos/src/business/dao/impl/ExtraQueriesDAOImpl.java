package business.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import business.beans.Assets;
import business.beans.Cuotas;
import business.beans.Ficheros;
import business.beans.Tags;
import business.beans.TagsAsset;
import business.dao.ExtraQueriesDAO;

import common.IConstantes;
import common.business.DaoException;
import common.business.beans.Usuarios;
import common.business.hibernate.BusinessTransactionBo;

public class ExtraQueriesDAOImpl implements ExtraQueriesDAO {

	private static final Log logger = LogFactory.getLog(ExtraQueriesDAOImpl.class);

	private final BusinessTransactionBo bTx;

	public ExtraQueriesDAOImpl(BusinessTransactionBo bTx) {
		super();
		this.bTx = bTx;
	}

	@Override
	public List<?> getCarpetas(Long user, Long folder) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		if (folder == null || folder == 0)
		{
			q = se.getNamedQuery("Contenidos.carpetas.nullfolder");
			q.setLong("user", user);			
		}
		else
		{
			q = se.getNamedQuery("Contenidos.carpetas");
			q.setLong("user", user);			
			q.setLong("folder", folder);
		}
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return q.list();
	}

	@Override
	public List<?> getFicheros(Long user, Long folder) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		if ( folder == null || folder == 0 )
		{
			q = se.getNamedQuery("Contenidos.ficheros.nullfolder");
			q.setLong("user", user);			
		}
		else
		{
			q = se.getNamedQuery("Contenidos.ficheros");
			q.setLong("user", user);
			q.setLong("folder", folder);			
		}
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@Override
	public List<?> getFicherosCompartidos(Long user, Long propietario) throws DaoException {
		Session se = this.bTx.getSession();

		Query q = se.getNamedQuery("Contenidos.file_compartidos");
		q.setLong("user", user);
		q.setLong("propietario", propietario);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@Override
	public List<?> getFicherosCompartidos(Long user, Long propietario, Long folder)
			throws DaoException {
		Query q;
		Session se = this.bTx.getSession();
		if ( folder == null || folder == 0 )
		{
			q = se.getNamedQuery("Contenidos.file_compartidos_null_folder");
			q.setLong("user", user);
			q.setLong("propietario", propietario);
		}
		else
		{
			
			q = se.getNamedQuery("Contenidos.file_compartidos_folder");
			q.setLong("user", user);
			q.setLong("propietario", propietario);
			q.setLong("folder", folder);
		}
		
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		return q.list();			
	}

	@Override
	public Cuotas getCuotaRestante(Long id) throws DaoException {
		Session se = this.bTx.getSession();

		Query q = se.getNamedQuery("Contenidos.cuotarestante");
		q.setLong("user", id);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return (Cuotas) q.uniqueResult();
	}

	@Override
	public List<?> getMyFicherosCompartidos(Long user) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.my_file_compartidos");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@Override
	public List<?> getMyFicherosCompartidos(Long user, Long folder)
			throws DaoException {
		Query q;
		Session se = this.bTx.getSession();
		if (folder == null || folder == 0)
		{
			q = se.getNamedQuery("Contenidos.my_file_compartidos_null_folder");
			q.setLong("user", user);
			if ( logger.isDebugEnabled() )
				logger.debug(q.getQueryString());
		}
		else
		{
			q = se.getNamedQuery("Contenidos.my_file_compartidos_folder");
			q.setLong("user", user);
			q.setLong("folder", folder);
		}

		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());			
		return q.list();			
	}

	@Override
	public List<?> getCarpetasConCompartidos(Long user, Long propietario) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.carpetas_con_compartidos");
		q.setLong("user", user);
		q.setLong("propietario", propietario);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@Override
	public List<?> getMyCarpetasCompartidas(Long user) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.my_carpetas_compartidas");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> getUsersContenidos() throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.users_contenidos");
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> getUsersContenidos(Long user) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.users_contenidos_relacionados");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Assets> getAssetsCompartidos(Long user, Long propietario)
			throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.assets_compartidos");
		q.setLong("user", user);
		q.setLong("propietario", propietario);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuarios> getUsersComparten(Long user) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.users_comparten");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Assets> getLinks(Long user, Long asset) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.links_asset");
		q.setLong("asset", asset);
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tags> getTags(Long user, Long asset) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.tags_asset");
		q.setLong("asset", asset);
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@Override
	public void addTags(Long user, Long asset, String tags) throws DaoException
	{
		if (tags.trim().length() > 0)
		{
			String[] lTags;
			tags = tags.replace(";", ",").replace(".", ",").replace(":", ",").replace(" ", ",");
			
			lTags = tags.split(",");
			
			TagsDAOImpl tagsDAO = new TagsDAOImpl(bTx);
			TagsAssetDAOImpl tagsAssetDAO = new TagsAssetDAOImpl(bTx);
			for (String tag: lTags)
			{
				tag = tag.trim();
				if (tag.length() > 0)
				{
					Tags objTag = tagsDAO.getByCode(tag);
					if (objTag == null)
						tagsAssetDAO.add(new TagsAsset(tagsDAO.add(new Tags(tag)), asset, user));
					else
					{
						TagsAsset objTagAsset = tagsAssetDAO.getByUniqueKey(objTag.getTagPk(), asset, user);
						if (objTagAsset == null)
							tagsAssetDAO.add(new TagsAsset(objTag.getTagPk(), asset, user));
					}
				}
			}
		}
	}

	@Override
	public List<?> getTagsNube(Long user, Integer tipo) throws DaoException {
		Query q = null;
		Session se = this.bTx.getSession();

		if (tipo == null || tipo.compareTo(IConstantes.NUBE_CONTADOR) == 0)
			q = se.getNamedQuery("Contenidos.tags_nube");
		else if (tipo.compareTo(IConstantes.NUBE_TOTAL_BUSQUEDAS) == 0)
			q = se.getNamedQuery("Contenidos.tags_total_count");
		else if (tipo.compareTo(IConstantes.NUBE_BUSQUEDA_PERSONAL) == 0)
			q = se.getNamedQuery("Contenidos.personal_tags_count");
			
		q = se.createSQLQuery(q.getQueryString() + " ORDER BY t.tag_uk_codigo");
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Assets> getAssetsTag(Long user, Long tag) throws DaoException {
		Query q;
		Session se = this.bTx.getSession();

		q = se.getNamedQuery("Contenidos.assets_tag");
		q.setLong("user", user);
		q.setLong("tag", tag);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ficheros> getFicherosTag(Long user, Long tag) throws DaoException {
		Session se = this.bTx.getSession();

		Query q = se.getNamedQuery("Contenidos.ficheros_tag");
		q.setLong("user", user);
		q.setLong("tag", tag);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ficheros> findFiles(String criteria, String tags, Long user) throws DaoException {
		String sWhere = "";
		Session se = this.bTx.getSession();
		
		Query q = se.getNamedQuery("Contenidos.busca_ficheros");
		if (criteria != null && (criteria = criteria.trim().toUpperCase()).length() > 0)
		{
			sWhere = " AND (Upper(a.ass_nombre) like '%" + criteria + "%' OR Upper(a.ass_descripcion) like '%" + criteria + "%')";
		}

		if (tags != null && (tags=tags.trim().replace(" ", ",")).length() > 0)
		{
			sWhere += " AND EXISTS (SELECT '1' FROM contenidos.tags_asset ta " +
			"WHERE ta.ass_fk = a.ass_pk " +
			"AND ta.tag_fk in (" + tags + ") GROUP BY ta.ass_fk HAVING COUNT(*) > " + (tags.split(",").length - 1) + ")";				
		}
		
		if (sWhere.length() == 0)
		{
			return new ArrayList();
		}
		
		q = se.createSQLQuery(q.getQueryString() + sWhere);
		q.setLong("user", user);
		if ( logger.isDebugEnabled() )
			logger.debug(q.getQueryString());

		return q.list();
	}
}
