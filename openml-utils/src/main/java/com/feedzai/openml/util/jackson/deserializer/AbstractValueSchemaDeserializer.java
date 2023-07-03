/*
 * Copyright 2018 Feedzai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.feedzai.openml.util.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.feedzai.openml.data.schema.AbstractValueSchema;
import com.feedzai.openml.data.schema.CategoricalValueSchema;
import com.feedzai.openml.data.schema.NumericValueSchema;
import com.feedzai.openml.data.schema.StringValueSchema;

import java.io.IOException;
import java.util.SortedSet;

/**
 * Custom {@link JsonDeserializer} for known {@link AbstractValueSchema} instances.
 *
 * @author Paulo Pereira (paulo.pereira@feedzai.com)
 * @since 0.1.0
 */
public class AbstractValueSchemaDeserializer extends StdDeserializer<AbstractValueSchema> {

    /**
     * Name of the json field that indicates whether missing values should be allowed.
     */
    public static final String ALLOW_MISSING = "allowMissing";

    /**
     * Name of the json field that indicates the type of the field.
     */
    public static final String VALUE_TYPE = "@type";

    /**
     * Name of the json value for {@link #VALUE_TYPE} that identifies a numeric field.
     */
    public static final String NUMERIC_TYPE = "numeric";

    /**
     * Name of the json value for {@link #VALUE_TYPE} that identifies a string field.
     */
    public static final Strin