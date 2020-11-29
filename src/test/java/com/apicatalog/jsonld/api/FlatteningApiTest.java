/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apicatalog.jsonld.api;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.junit.Assert;
import org.junit.Test;

import com.apicatalog.jsonld.JsonLd;
import com.apicatalog.jsonld.document.JsonDocument;
import com.apicatalog.jsonld.http.media.MediaType;

import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;

public class FlatteningApiTest {

    public static final MockLoader MOCK_LOADER = new MockLoader(JsonValue.EMPTY_JSON_ARRAY);
    
    @Test    
    public void test1() throws JsonLdError {
        JsonStructure result = JsonLd.flatten(JsonDocument.of(JsonValue.EMPTY_JSON_OBJECT)).get();
        
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_ARRAY, result);
    }
    
    @Test    
    public void test2() throws JsonLdError {
        JsonStructure result = JsonLd.flatten(JsonDocument.of(MediaType.JSON, new ByteArrayInputStream(JsonValue.EMPTY_JSON_OBJECT.toString().getBytes()))).get();
        
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_ARRAY, result);
    }
    
    @Test    
    public void test3() throws JsonLdError {
        JsonStructure result = JsonLd.flatten("https://example.com").loader(MOCK_LOADER).get();
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_ARRAY, result);
    }

    @Test    
    public void test4() throws JsonLdError {
        JsonStructure result = JsonLd.flatten(URI.create("https://example.com")).loader(MOCK_LOADER).get();
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_ARRAY, result);
    }

    @Test    
    public void test5() throws JsonLdError {
        JsonStructure result = JsonLd.flatten("\thttps://example.com  ").loader(MOCK_LOADER).get();
        
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_ARRAY, result);
    }
    
    @Test    
    public void test6() throws JsonLdError {
        JsonStructure result = JsonLd.flatten("https://example.com").context(JsonDocument.of(JsonValue.EMPTY_JSON_OBJECT)).loader(MOCK_LOADER).ordered().get();
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_OBJECT, result);
    }    

    @Test    
    public void test7() throws JsonLdError {
        JsonStructure result = JsonLd.flatten("https://example.com").context(JsonDocument.of(MediaType.JSON, new InputStreamReader(new ByteArrayInputStream(JsonValue.EMPTY_JSON_OBJECT.toString().getBytes())))).loader(MOCK_LOADER).ordered().get();
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_OBJECT, result);
    }    

    @Test    
    public void test8() throws JsonLdError {
        JsonStructure result = JsonLd.flatten("https://example.com").context(JsonValue.EMPTY_JSON_OBJECT).loader(MOCK_LOADER).ordered().get();
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonValue.EMPTY_JSON_OBJECT, result);
    }    

}
