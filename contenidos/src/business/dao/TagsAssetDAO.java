package business.dao;

import java.util.List;

import business.beans.TagsAsset;

import common.business.DaoException;

public interface TagsAssetDAO {
	public Long add(TagsAsset obj) throws DaoException;
	public void update(TagsAsset obj) throws DaoException;
	public void delete(TagsAsset obj) throws DaoException;
	public TagsAsset getByPrimaryKey(Long id) throws DaoException;
	public TagsAsset getByUniqueKey(Long tag, Long asset, Long user) throws DaoException;
	public List<TagsAsset> getByAsset(Long asset) throws DaoException;
	public List<TagsAsset> getByUser(Long user) throws DaoException;
	public List<TagsAsset> getTagsAsset() throws DaoException;
}
