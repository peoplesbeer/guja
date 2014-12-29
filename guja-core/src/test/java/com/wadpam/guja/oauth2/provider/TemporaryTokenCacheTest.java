package com.wadpam.guja.oauth2.provider;

import com.wadpam.guja.cache.GuavaCacheBuilderProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

public class TemporaryTokenCacheTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(TemporaryTokenCacheTest.class);


  private static final String TOKEN = "some_token";
  private static final String KEY = "some_key";

  private TokenGenerator mockGenerator;
  private TemporaryTokenCache tokenCache;

  @Before
  public void setUp() throws Exception {
    mockGenerator = createMock(TokenGenerator.class);
    tokenCache = new TemporaryTokenCache(mockGenerator, new GuavaCacheBuilderProvider());
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testGenerateTemporaryToken() throws Exception {

    expect(mockGenerator.generate()).andReturn(TOKEN).once();
    replay(mockGenerator);

    String token = tokenCache.generateTemporaryToken(KEY, 10);
    assertTrue(null != token);
    assertTrue(TOKEN.equals(token));

  }

  @Test
  public void testValidateToken() throws Exception {

    expect(mockGenerator.generate()).andReturn(TOKEN).once();
    replay(mockGenerator);

    String token = tokenCache.generateTemporaryToken(KEY, 10);

    assertFalse(tokenCache.validateToken("wrong_key", TOKEN));
    assertFalse(tokenCache.validateToken(KEY, "wrong_token"));

    assertTrue(tokenCache.validateToken(KEY, TOKEN));
    assertFalse(tokenCache.validateToken(KEY, TOKEN));

  }

  @Test
  public void testRemoveToken() throws Exception {

    expect(mockGenerator.generate()).andReturn(TOKEN).once();
    replay(mockGenerator);

    String token = tokenCache.generateTemporaryToken(KEY, 10);

    tokenCache.removeToken(KEY);
    assertFalse(tokenCache.validateToken(KEY, TOKEN));

  }

}