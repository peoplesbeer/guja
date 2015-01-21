package com.wadpam.guja.oauth2.dao;

/*
 * #%L
 * guja-core
 * %%
 * Copyright (C) 2014 Wadpam
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.google.inject.Inject;
import com.wadpam.guja.oauth2.domain.DUser;
import net.sf.mardao.core.CursorPage;
import net.sf.mardao.core.filter.Filter;
import net.sf.mardao.dao.Supplier;

import javax.cache.annotation.CacheDefaults;

/**
 * Implementation of Business Methods related to entity DUser.
 * This (empty) class is generated by mardao, but edited by developers.
 * It is not overwritten by the generator once it exists.
 * <p/>
 * Generated on 2014-12-01T10:31:58.895+0100.
 *
 * @author mardao DAO generator (net.sf.mardao.plugin.ProcessDomainMojo)
 */
@CacheDefaults(cacheName = "DUser")
public class DUserDaoBean extends GeneratedDUserDaoImpl {

  @Inject
  public DUserDaoBean(Supplier supplier) {
    super(supplier);
  }

  // TODO Consider caching on username

  public CursorPage<DUser> queryFriends(Long id, int pageSize, String cursorKey) {
    return queryPage(false, pageSize, null,
        DUserMapper.Field.ID.getFieldName(), false,
        null, false,
        null, cursorKey,
        Filter.inFilter(DUserMapper.Field.FRIENDS.getFieldName(), id));
  }

  public CursorPage<DUser> queryByMatchingEmail(String email, int pageSize, String cursorKey) {
    // GAE workaround for LIKE queries
    // Search for emails that are equal or bigger than [email] AND smaller than [email+\uFFFD
    return queryPage(false, pageSize, null,
        DUserMapper.Field.EMAIL.getFieldName(), false,
        null, false,
        null, cursorKey,
        Filter.greaterThanOrEquals(DUserMapper.Field.EMAIL.getFieldName(), email),
        Filter.lessThan(DUserMapper.Field.EMAIL.getFieldName(), email + "\uFFFD"));

  }

  public CursorPage<DUser> queryByMatchingUsername(String username, int pageSize, String cursorKey) {
    return queryPage(false, pageSize, null,
        DUserMapper.Field.USERNAME.getFieldName(), false,
        null, false,
        null, cursorKey,
        Filter.greaterThanOrEquals(DUserMapper.Field.USERNAME.getFieldName(), username),
        Filter.lessThan(DUserMapper.Field.USERNAME.getFieldName(), username + "\uFFFD"));
  }

}
